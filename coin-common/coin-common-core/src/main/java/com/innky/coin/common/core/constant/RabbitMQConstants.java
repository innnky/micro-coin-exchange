package com.innky.coin.common.core.constant;

/**
 * @author innnky
 * @date 2022/9/22 19:22
 */
public interface RabbitMQConstants {

	/**
	 * 订单队列
	 */
	String ORDERS_QUEUE = "orders";

	String MICRO_TRADING_EXCHANGE = "microTradingExchange";

	String TURNOVER_QUEUE = "turnoverQueue";

	//TODO 修改key名称,在rabbitmq删除原有的binding

	String TURNOVER_KEY = "turnoverQueue";

	String TURNOVER_INFO_QUEUE = "turnoverInfoQueue";
}
