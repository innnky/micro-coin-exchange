package com.innky.coin.match.data;

import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.common.core.util.RetOps;
import com.innky.coin.exchange.dto.MarketDto;
import com.innky.coin.exchange.dto.OrderDto;
import com.innky.coin.exchange.feign.RemoteMarketService;
import com.innky.coin.exchange.feign.RemoteOrderService;
import com.innky.coin.match.entity.OrderBook;
import com.innky.coin.match.entity.OrderBooks;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author innnky
 * @date 2022/9/23 09:59
 */
@Component
@AllArgsConstructor
public class OrderDataLoader implements CommandLineRunner {

	private final RemoteOrderService remoteOrderService;
	private final RemoteMarketService remoteMarketService;
	private final OrderBooks orderBooks;
	@Override
	public void run(String... args) throws Exception {
		R<List<OrderDto>> openOrders = remoteOrderService.getOpenOrders(SecurityConstants.FROM_IN);
		R<List<MarketDto>> allMarkets = remoteMarketService.getAllMarkets(SecurityConstants.FROM_IN);
		List<OrderDto> orderDtos = RetOps.of(openOrders).getData().orElseThrow(RuntimeException::new);
		List<MarketDto> marketDtos = RetOps.of(allMarkets).getData().orElseThrow(RuntimeException::new);

		for (MarketDto marketDto : marketDtos) {
			orderBooks.createOrderBook(marketDto.getSymbol(), marketDto.getMarketPrice());
		}
		for (OrderDto orderDto : orderDtos) {
			OrderBook orderBook = orderBooks.getOrderBook(orderDto.getSymbol());
			orderBook.createOrder(orderDto);
		}

	}
}
