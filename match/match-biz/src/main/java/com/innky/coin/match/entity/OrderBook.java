package com.innky.coin.match.entity;

import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import com.innky.coin.exchange.dto.OrderDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Comparator;
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
		sellOrders = new TreeMap<>(Comparator.naturalOrder());

		buyOrders = new TreeMap<>(Comparator.reverseOrder());

		this.symbol = symbol;
		this.marketPrice = BigDecimal.ZERO;
	}


	public BigDecimal getOppositeFirstPrice(OrderSideEnum orderSide) {
		return getFirstPrice(orderSide.getOpposite());
	}
	public BigDecimal getFirstPrice(OrderSideEnum orderSide) {
		if (orderSide == OrderSideEnum.SELL) {
			if (sellOrders.isEmpty()){
				return null;
			}
			return sellOrders.firstEntry().getValue().getPrice();
		}
		else {
			if (buyOrders.isEmpty()){
				return null;
			}
			return buyOrders.firstEntry().getValue().getPrice();
		}
	}

	public TreeMap<BigDecimal, Orders> getOrdersMap(OrderSideEnum orderSide){
		if (orderSide == OrderSideEnum.BUY){
			return buyOrders;
		}
		else {
			return sellOrders;
		}
	}


	public TreeMap<BigDecimal, Orders> getOppositeOrdersMap(OrderSideEnum orderSide) {
		return getOrdersMap(orderSide.getOpposite());
	}
}
