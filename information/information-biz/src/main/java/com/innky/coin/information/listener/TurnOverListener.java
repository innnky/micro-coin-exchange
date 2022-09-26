package com.innky.coin.information.listener;

import com.innky.coin.common.core.constant.RabbitMQConstants;
import com.innky.coin.match.dto.TurnoverOrderDto;
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
public class TurnOverListener {


	@RabbitListener(queues = RabbitMQConstants.TURNOVER_INFO_QUEUE)
	public void receiveOrder(TurnoverOrderDto turnoverOrder) {


	}

}
