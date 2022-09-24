package com.innky.coin.match.service.impl;

import com.innky.coin.common.core.constant.RabbitMQConstants;
import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import com.innky.coin.common.core.constant.enums.OrderTypeEnum;
import com.innky.coin.match.entity.Order;
import com.innky.coin.match.entity.OrderBook;
import com.innky.coin.match.entity.Orders;
import com.innky.coin.match.entity.TurnoverOrder;
import com.innky.coin.match.service.MatchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author innnky
 * @date 2022/9/23 14:03
 */
@Service
@Slf4j
@AllArgsConstructor
public class LimitMatchServiceImpl implements MatchService {

	RabbitTemplate rabbitTemplate;

	@Override
	public void match(OrderBook orderBook, Order order) {
		OrderSideEnum orderSide =OrderSideEnum.of(order.getSide());
		int sign = OrderSideEnum.BUY == orderSide ? 1 :-1;
		// sign 1 为买方 -1为卖方

		BigDecimal oppositeFirstPrice = orderBook.getOppositeFirstPrice(orderSide);

		if (oppositeFirstPrice != null && sign * order.getPrice().compareTo(oppositeFirstPrice) >= 0) {
			// 吃单
			takeOrder(order, orderSide, orderBook, sign);
		}
		else {
			// 挂单
			makeOrder(order, orderSide, orderBook);
		}

		log.info("限价撮合:{}",order);

	}

	private void takeOrder(Order order, OrderSideEnum orderSide, OrderBook orderBook, int sign) {
		TreeMap<BigDecimal, Orders> oppositeOrdersMap = orderBook.getOppositeOrdersMap(orderSide);
		Iterator<Map.Entry<BigDecimal, Orders>> oppositeOrdersIterator = oppositeOrdersMap.entrySet().iterator();
		while (oppositeOrdersIterator.hasNext()) {
			Map.Entry<BigDecimal, Orders> e = oppositeOrdersIterator.next();
			BigDecimal price = e.getKey();
			Orders oppositeOrders = e.getValue();

			if (sign * price.compareTo(order.getPrice())>0 ) {
				//未成交部分变成挂单
				makeOrder(order, orderSide, orderBook);
				return;
			}

			Iterator<Order> orderIterator = oppositeOrders.iterator();

			while (orderIterator.hasNext()) {
				Order oppositeOrder = orderIterator.next();
				if (oppositeOrder.getVolumeLeft().compareTo(order.getVolumeLeft()) > 0) {
					//当前委托完成
					oppositeOrder.completeVolume(order.getVolumeLeft());
					order.close();
					TurnoverOrder turnoverOrder = new TurnoverOrder(order.getSymbol(),price, order.getVolumeLeft(),
							order, oppositeOrder, orderSide);
					completeOrder(turnoverOrder,orderBook);
					return;
				}
				else if (oppositeOrder.getVolumeLeft().compareTo(order.getVolumeLeft())==0){
					//两个委托都完成
					order.close();
					oppositeOrder.close();
					TurnoverOrder turnoverOrder = new TurnoverOrder(order.getSymbol(),price, order.getVolumeLeft(),
							order, oppositeOrder, orderSide);
					completeOrder(turnoverOrder,orderBook);
					orderIterator.remove();
					// 当前价格委托全部完成 移除Map中此项
					if (!orderIterator.hasNext()){
						oppositeOrdersIterator.remove();
					}
					return;
				}
				else {
					//对手委托完成
					order.completeVolume(oppositeOrder.getVolumeLeft());
					oppositeOrder.close();
					TurnoverOrder turnoverOrder = new TurnoverOrder(order.getSymbol(),price, oppositeOrder.getVolumeLeft(),
							order, oppositeOrder, orderSide);
					completeOrder(turnoverOrder,orderBook);
					orderIterator.remove();
				}
			}
			// 当前价格委托全部完成 移除Map中此项
			oppositeOrdersIterator.remove();
		}
		//未成交部分变成挂单
		makeOrder(order, orderSide, orderBook);
	}

	public void makeOrder(Order order, OrderSideEnum orderSide, OrderBook orderBook) {
		TreeMap<BigDecimal, Orders> ordersMap = orderBook.getOrdersMap(orderSide);
		if (ordersMap.containsKey(order.getPrice())) {
			ordersMap.get(order.getPrice()).addOrder(order);
		}
		else {
			Orders orders = new Orders(order.getPrice());
			orders.addOrder(order);
			ordersMap.put(order.getPrice(), orders);
		}
	}

	public void completeOrder(TurnoverOrder turnoverOrder, OrderBook orderBook){
		rabbitTemplate.convertAndSend(RabbitMQConstants.TURNOVER_QUEUE, turnoverOrder.toDto());
		orderBook.setMarketPrice(turnoverOrder.getPrice());

	}

	@Override
	public boolean support(Order order) {
		return OrderTypeEnum.LIMIT.getCode().equals(order.getType());
	}
}
