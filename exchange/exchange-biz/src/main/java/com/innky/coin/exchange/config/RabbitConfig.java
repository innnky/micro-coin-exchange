package com.innky.coin.exchange.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author innnky
 * @date 2022/9/22 18:49
 */
@Configuration
public class RabbitConfig {

	@Bean
	Queue orderQueue(){
		return new Queue("orders");
	}

}
