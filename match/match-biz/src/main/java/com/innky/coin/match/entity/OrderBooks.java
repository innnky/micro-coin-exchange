package com.innky.coin.match.entity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author innnky
 * @date 2022/9/23 11:41
 */
public class OrderBooks {
	Map<String, OrderBook> orderBookMap;
	public void createOrderBook(String symbol, BigDecimal marketPrice){
		OrderBook orderbook = new OrderBook(symbol);
		orderbook.setMarketPrice(marketPrice);
		orderBookMap.put(symbol, orderbook);
	}
	public OrderBook getOrderBook(String symbol){
		return orderBookMap.get(symbol);
	}
}
