package com.innky.coin.match.service;

import com.innky.coin.match.entity.Order;
import com.innky.coin.match.entity.OrderBook;

/**
 * @author innnky
 * @date 2022/9/23 12:29
 */
public interface MatchService {

	/**
	 * 撮合
	 *
	 * @param order 订单
	 */
	public void match(OrderBook orderBook,Order order);

	/**
	 * 是否支持处理当前类型订单
	 *
	 * @param order 订单
	 * @return boolean
	 */
	public boolean support(Order order);

}
