package com.innky.coin.information.listener;

import com.innky.coin.common.core.constant.RabbitMQConstants;
import com.innky.coin.information.dto.KlineItemDto;
import com.innky.coin.information.entity.Kline;
import com.innky.coin.information.entity.MarketInformation;
import com.innky.coin.match.dto.TurnoverOrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author innnky
 * @date 2022/9/23 09:34
 */
@Component
@Slf4j
@AllArgsConstructor
public class TurnOverListener {

	private final MarketInformation marketInformation;

	@RabbitListener(queues = RabbitMQConstants.TURNOVER_INFO_QUEUE)
	public void receiveOrder(@NotNull TurnoverOrderDto turnoverOrder) {
		log.info("队列收到消息:{}", turnoverOrder);
		String symbol = turnoverOrder.getSymbol();
		BigDecimal price = turnoverOrder.getPrice();
		BigDecimal quantity = turnoverOrder.getQuantity();
		//根据成交记录更新市价
		marketInformation.setMarketPrice(symbol, price);
		//根据成交记录更新该交易对各个时间级别的K线信息
		List<Kline> klines = marketInformation.getSymbolAllKline(symbol);
		if (klines == null){
			return;
		}
		for (Kline kline : klines) {
			KlineItemDto itemDto = kline.getItems().getLast();
			itemDto.setAmount(itemDto.getAmount().add(quantity));
			if (price.compareTo(itemDto.getHigh()) > 0){
				itemDto.setHigh(price);
			}
			if (price.compareTo(itemDto.getLow()) < 0) {
				itemDto.setLow(price);
			}
		}
	}

}
