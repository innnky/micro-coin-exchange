package com.innky.coin.match.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.innky.coin.common.security.service.CoinUserDetailsService;
import com.innky.coin.exchange.dto.OrderDto;
import com.innky.coin.match.entity.Order;
import com.innky.coin.match.entity.OrderBook;
import com.innky.coin.match.entity.OrderBooks;
import com.innky.coin.match.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author innnky
 * @date 2022/9/23 12:07
 */
@Component
@AllArgsConstructor
public class OrderHandler {

	private final OrderBooks orderBooks;

	public void processOrder(OrderDto orderDto){
		Order order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		OrderBook orderBook = orderBooks.getOrderBook(order.getSymbol());

		// 从Spring容器中获取所有的撮合服务
		Map<String, MatchService> matchServiceMap = SpringUtil
				.getBeansOfType(MatchService.class);

		Optional<MatchService> optional = matchServiceMap.values().stream()
				.filter(service -> service.support(order)).findAny();
		optional.orElseThrow(()->{
			return new RuntimeException("不支持此类型订单");
		}).match(orderBook, order);

	}
}
