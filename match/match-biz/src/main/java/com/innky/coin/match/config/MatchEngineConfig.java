package com.innky.coin.match.config;

import com.innky.coin.match.entity.OrderBook;
import com.innky.coin.match.entity.OrderBooks;
import org.aspectj.weaver.ast.Or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author innnky
 * @date 2022/9/23 10:35
 */
@Configuration
public class MatchEngineConfig {

	@Bean
	OrderBooks matchOrderBook(){
		return new OrderBooks();
	}


}
