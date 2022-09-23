package com.innky.coin.match.listener;

import com.innky.coin.common.core.constant.RabbitMQConstants;
import com.innky.coin.exchange.dto.OrderDto;
import com.innky.coin.match.data.OrderDataLoader;
import com.innky.coin.match.entity.Order;
import com.innky.coin.match.handler.OrderHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author innnky
 * @date 2022/9/23 09:34
 */
@Component
@Slf4j
@AllArgsConstructor
public class OrdersListener {

	private final OrderHandler orderHandler;
	private final OrderDataLoader orderDataLoader;

	@RabbitListener(queues = RabbitMQConstants.ORDERS_QUEUE)
	public void receiveOrder(OrderDto order) throws InterruptedException {
		//	等待订单账本数据初始化完成
		synchronized (orderDataLoader) {
			while (!orderDataLoader.orderBooksInitialized) {
				orderDataLoader.wait();
			}
		}
		//	查询是否是重复数据
		if (orderDataLoader.initialOrderIds.contains(order.getId())){
			log.info("队列重复消息:{}", order);
			return;
		}
		//  处理委托订单
		log.info("队列收到消息:{}", order);
		orderHandler.processOrder(order);
	}

}
