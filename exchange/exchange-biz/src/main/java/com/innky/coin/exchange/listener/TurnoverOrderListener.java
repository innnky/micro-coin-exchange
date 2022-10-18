package com.innky.coin.exchange.listener;

import com.innky.coin.common.core.constant.RabbitMQConstants;
import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import com.innky.coin.exchange.entity.Order;
import com.innky.coin.exchange.entity.TurnoverOrder;
import com.innky.coin.exchange.service.MarketService;
import com.innky.coin.exchange.service.OrderService;
import com.innky.coin.exchange.service.TurnoverOrderService;
import com.innky.coin.exchange.service.UserAssetsService;
import com.innky.coin.match.dto.TurnoverOrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author innnky
 * @date 2022/9/30 16:25
 */
@Component
@AllArgsConstructor
@Slf4j
public class TurnoverOrderListener {

	TurnoverOrderService turnoverOrderService;
	OrderService orderService;
	UserAssetsService userAssetsService;
	MarketService marketService;

	@RabbitListener(queues = RabbitMQConstants.TURNOVER_QUEUE)
	@Transactional(rollbackFor = RuntimeException.class)
	void processTurnoverOrder(TurnoverOrderDto turnoverOrderDto){
		log.info("收到队列完成订单消息:{}", turnoverOrderDto);
		TurnoverOrder turnoverOrder = new TurnoverOrder();
		BeanUtils.copyProperties(turnoverOrderDto, turnoverOrder);
		turnoverOrder.setBuyOrderId(turnoverOrderDto.getBuyOrder().getId());
		turnoverOrder.setSellOrderId(turnoverOrderDto.getSellOrder().getId());

		turnoverOrderService.save(turnoverOrder);
		Order buyOrder = new Order();
		Order sellOrder = new Order();
		BeanUtils.copyProperties(turnoverOrderDto.getBuyOrder(), buyOrder);
		BeanUtils.copyProperties(turnoverOrderDto.getSellOrder(), sellOrder);

		orderService.updateById(buyOrder);
		orderService.updateById(sellOrder);

		Long buyCoinId = marketService.getCoinIdBySymbol(turnoverOrder.getSymbol(), OrderSideEnum.BUY);
		Long sellCoinId = marketService.getCoinIdBySymbol(turnoverOrder.getSymbol(), OrderSideEnum.SELL);
		userAssetsService.userCharge(buyOrder.getUserId(), sellCoinId, turnoverOrder.getQuantity());
		userAssetsService.userCharge(sellOrder.getUserId(), buyCoinId, turnoverOrder.getQuantity().multiply(turnoverOrder.getPrice()));
//		userAssetsService.getAvailableAssetsBySymbol()
//		buyOrder.
	}

}
