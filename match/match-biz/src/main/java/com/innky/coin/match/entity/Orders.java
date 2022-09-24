package com.innky.coin.match.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author innnky
 * @date 2022/9/23 11:21
 */
@Data
public class Orders implements Iterable<Order>{
	private BigDecimal price;
	private LinkedList<Order> orders = new LinkedList<>();

	public Orders(BigDecimal price) {
		this.price = price;
	}

	public void addOrder(Order order) {
		if (order.getPrice().equals(price)) {
			orders.add(order);
		}
		else {
			throw new RuntimeException("订单价格不匹配");
		}
	}


	@NotNull
	@Override
	public Iterator<Order> iterator() {
		return orders.iterator();
	}
}
