package com.innky.coin.match.service.impl;

import com.innky.coin.common.core.constant.enums.OrderTypeEnum;
import com.innky.coin.match.entity.Order;
import com.innky.coin.match.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author innnky
 * @date 2022/9/23 14:03
 */
@Service
@Slf4j
public class LimitMatchServiceImpl implements MatchService {
	@Override
	public void match(Order order) {
		log.info("限价撮合:{}",order);
	}

	@Override
	public boolean support(Order order) {
		return OrderTypeEnum.LIMIT.getCode().equals(order.getType());
	}
}
