package com.innky.coin.match.entity;

import com.innky.coin.exchange.dto.OrderDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author innnky
 * @date 2022/9/23 10:34
 */
@Data
public class OrderBook {
	private TreeMap<BigDecimal, Orders> buyOrders;
	private TreeMap<BigDecimal, Orders> sellOrders;

	private String symbol;
	private BigDecimal marketPrice;
	private TradePlate buyPlate;
	private TradePlate sellPlate;

	public OrderBook(String symbol) {
		buyOrders = new TreeMap<>();
		sellOrders = new TreeMap<>();
		this.symbol = symbol;
		this.marketPrice = BigDecimal.ZERO;
	}

	public void createOrder(OrderDto orderDto) {
//		orderDto.
	}
}
