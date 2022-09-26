package com.innky.coin.match.entity;

import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Comparator;
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
	private TradePlate plate;

	public OrderBook(String symbol, Integer plateMaxDepth) {
		sellOrders = new TreeMap<>(Comparator.naturalOrder());
		buyOrders = new TreeMap<>(Comparator.reverseOrder());
		plate = new TradePlate(plateMaxDepth, symbol);
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

	public void addPlateItem(BigDecimal price, BigDecimal quantity, OrderSideEnum orderSide) {
		plate.addItem(price, quantity, orderSide);
	}

	public void delPlateItem(BigDecimal price, BigDecimal quantity, OrderSideEnum orderSide) {
		plate.delItem(price, quantity, orderSide);
	}
}
