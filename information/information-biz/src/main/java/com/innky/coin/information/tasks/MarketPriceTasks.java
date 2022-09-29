package com.innky.coin.information.tasks;

import com.innky.coin.common.core.constant.CacheConstants;
import com.innky.coin.information.entity.MarketInformation;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author innnky
 * @date 2022/9/26 17:55
 */
@Component
@AllArgsConstructor
public class MarketPriceTasks {

	private final RedisTemplate<String, Object> redisTemplate;
	private final MarketInformation marketInformation;

	@Scheduled(fixedRate = 1000)
	public void updateMarketPrice(){
		marketInformation.getMarketPrices().forEach(((s, bigDecimal) -> {
			redisTemplate.opsForValue().set(CacheConstants.MARKET_PRICE_KEY + s, bigDecimal);
		}));
	}

}
