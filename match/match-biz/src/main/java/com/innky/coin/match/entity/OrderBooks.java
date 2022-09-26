package com.innky.coin.match.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author innnky
 * @date 2022/9/23 11:41
 */
public class OrderBooks {
	Map<String, OrderBook> orderBookMap = new HashMap<>();
	public void createOrderBook(String symbol, BigDecimal marketPrice,Integer plateMaxDepth){
		OrderBook orderbook = new OrderBook(symbol, plateMaxDepth);
		orderbook.setMarketPrice(marketPrice);
		orderBookMap.put(symbol, orderbook);
	}
	public OrderBook getOrderBook(String symbol){
		return orderBookMap.get(symbol);
	}

	public Map<String, OrderBook> getOrderBookMap() {
		return orderBookMap;
	}
}
