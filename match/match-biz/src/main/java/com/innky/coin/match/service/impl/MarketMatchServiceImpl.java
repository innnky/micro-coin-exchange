package com.innky.coin.match.service.impl;

import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import com.innky.coin.common.core.constant.enums.OrderTypeEnum;
import com.innky.coin.match.entity.Order;
import com.innky.coin.match.entity.OrderBook;
import com.innky.coin.match.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author innnky
 * @date 2022/9/23 15:21
 */
@Component
@Slf4j
public class MarketMatchServiceImpl implements MatchService {
	@Override
	public void match(OrderBook orderBook, Order order) {

		log.info("市价委托:{}", order);
	}

	@Override
	public boolean support(Order order) {
		return OrderTypeEnum.MARKET.getCode().equals(order.getType());
	}
}
