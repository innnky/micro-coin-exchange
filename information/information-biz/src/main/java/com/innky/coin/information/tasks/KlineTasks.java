package com.innky.coin.information.tasks;

import com.innky.coin.common.core.constant.CacheConstants;
import com.innky.coin.information.entity.Kline;
import com.innky.coin.information.dto.KlineItemDto;
import com.innky.coin.information.entity.MarketInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author innnky
 * @date 2022/9/26 09:12
 */
@Component
public class KlineTasks {

	private final RedisTemplate<String, Object> redisTemplate;
	private final MarketInformation marketInformation;

	@Value("${information.kline-max-len}")
	private  Integer klineMaxLen;

	public KlineTasks(RedisTemplate<String, Object> redisTemplate, MarketInformation marketInformation) {
		this.redisTemplate = redisTemplate;
		this.marketInformation = marketInformation;
	}

	@Scheduled(fixedRate = 1000)
	public void oneSecondKline(){
		String level = "1s";
		updateKline(level);
	}

	@Scheduled(fixedRate = 1000 * 30)
	public void oneMinuteKline(){
		String level = "30s";
		updateKline(level);
	}

	@Scheduled(fixedRate = 1000 * 60 * 15)
	public void fifteenMinuteKline(){
		String level = "15m";
		updateKline(level);
	}

	private void updateKline(String level) {
		List<String> symbols = marketInformation.getSymbols();
		if (symbols == null){
			return;
		}
		for (String symbol : symbols) {
			String klineKey = CacheConstants.KLINE_KEY + symbol + ":" + level;
			BigDecimal marketPrice = marketInformation.getMarketPrice(symbol);
			if (marketPrice == null){
				continue;
			}
			// 获取当前k线
			Kline kline = marketInformation.getKline(symbol, level);
			ConcurrentLinkedDeque<KlineItemDto> items = kline.getItems();
			// 为前一根k线设置收盘价
			try {
				KlineItemDto lastKline = items.getLast();
				lastKline.setClose(marketPrice);
				redisTemplate.opsForList().set(klineKey, -1, lastKline);
			} catch (NoSuchElementException ignored){}

			// 创建一根新的k线
			KlineItemDto item = new KlineItemDto();
			item.setOpen(marketPrice);
			item.setHigh(marketPrice);
			item.setLow(marketPrice);
			item.setAmount(BigDecimal.ZERO);
			item.setClose(null);
			items.add(item);
			redisTemplate.opsForList().rightPush(klineKey,item);
			if ( items.size() > klineMaxLen ) {
				items.removeFirst();
				redisTemplate.opsForList().leftPop(klineKey);
			}

		}
	}


}
