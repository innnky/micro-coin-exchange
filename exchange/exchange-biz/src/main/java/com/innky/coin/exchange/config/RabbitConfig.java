package com.innky.coin.exchange.config;

import com.innky.coin.common.core.constant.RabbitMQConstants;
import org.springframework.amqp.core.*;
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
	Queue orderQueue() {
		return new Queue(RabbitMQConstants.ORDERS_QUEUE, true);
	}

	@Bean
	Queue turnoverOrderQueue() {
		return new Queue(RabbitMQConstants.TURNOVER_QUEUE, true);
	}

	@Bean
	Queue turnoverInformationQueue(){
		return new Queue(RabbitMQConstants.TURNOVER_INFO_QUEUE, true);
	}

	@Bean
	Exchange microTradingExchange() {
		return new DirectExchange(RabbitMQConstants.MICRO_TRADING_EXCHANGE);
	}


	@Bean
	Binding orderBinding() {
		return BindingBuilder.bind(orderQueue()).to(microTradingExchange()).with(RabbitMQConstants.ORDERS_QUEUE)
				.noargs();
	}

	@Bean
	Binding turnoverOrderBinding() {
		return BindingBuilder.bind(turnoverOrderQueue()).to(microTradingExchange()).with(RabbitMQConstants.TURNOVER_KEY)
				.noargs();
	}

	@Bean
	Binding turnoverInformationBinding() {
		return BindingBuilder.bind(turnoverInformationQueue()).to(microTradingExchange()).with(RabbitMQConstants.TURNOVER_KEY)
				.noargs();
	}

	@Bean
	MessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
