package com.innky.coin.match.listener;

import com.innky.coin.common.core.constant.RabbitMQConstants;
import com.innky.coin.match.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author innnky
 * @date 2022/9/23 09:34
 */
@Component
@Slf4j
public class OrdersListener {

	@RabbitListener(queues = RabbitMQConstants.ORDERS_QUEUE)
	public void receiveOrder(Order order) {
		log.info("收到消息:{}", order);
	}

}
