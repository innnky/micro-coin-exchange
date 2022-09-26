package com.innky.coin.information.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author innnky
 * @date 2022/9/22 18:49
 */
@Configuration
public class RabbitConfig {

	@Bean
	MessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}


}
