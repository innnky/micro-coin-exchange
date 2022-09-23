package com.innky.coin.match.data;

import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.constant.enums.OrderTypeEnum;
import com.innky.coin.common.core.util.R;
import com.innky.coin.common.core.util.RetOps;
import com.innky.coin.exchange.dto.MarketDto;
import com.innky.coin.exchange.dto.OrderDto;
import com.innky.coin.exchange.feign.RemoteMarketService;
import com.innky.coin.exchange.feign.RemoteOrderService;
import com.innky.coin.match.entity.OrderBook;
import com.innky.coin.match.entity.OrderBooks;
import com.innky.coin.match.handler.OrderHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author innnky
 * @date 2022/9/23 09:59
 */
@Component
@Slf4j
public class OrderDataLoader implements CommandLineRunner {

	private final RemoteOrderService remoteOrderService;
	private final RemoteMarketService remoteMarketService;
	private final OrderBooks orderBooks;
	private final OrderHandler orderHandler;

	public boolean orderBooksInitialized = false;
	public Set<Long> initialOrderIds = new HashSet<>();

	public OrderDataLoader(RemoteOrderService remoteOrderService, RemoteMarketService remoteMarketService, OrderBooks orderBooks, OrderHandler orderHandler) {
		this.remoteOrderService = remoteOrderService;
		this.remoteMarketService = remoteMarketService;
		this.orderBooks = orderBooks;
		this.orderHandler = orderHandler;
	}

	@Override
	public synchronized void run(String... args) throws Exception {
		R<List<OrderDto>> openOrders = remoteOrderService.getOpenOrders(SecurityConstants.FROM_IN);
		R<List<MarketDto>> allMarkets = remoteMarketService.getAllMarkets(SecurityConstants.FROM_IN);
		List<OrderDto> orderDtos = RetOps.of(openOrders).getData().orElseThrow(RuntimeException::new);
		List<MarketDto> marketDtos = RetOps.of(allMarkets).getData().orElseThrow(RuntimeException::new);

		for (MarketDto marketDto : marketDtos) {
			orderBooks.createOrderBook(marketDto.getSymbol(), marketDto.getMarketPrice());
		}

		for (OrderDto orderDto : orderDtos) {
			log.info("数据库获取订单:{}", orderDto);
			initialOrderIds.add(orderDto.getId());
			orderHandler.processOrder(orderDto);
		}
		this.orderBooksInitialized = true;
		this.notifyAll();


	}
}
