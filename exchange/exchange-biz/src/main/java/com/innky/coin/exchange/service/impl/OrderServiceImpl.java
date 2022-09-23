/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the coin4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.innky.coin.exchange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innky.coin.common.core.constant.RabbitMQConstants;
import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import com.innky.coin.common.core.constant.enums.OrderStatusEnum;
import com.innky.coin.common.core.constant.enums.OrderTypeEnum;
import com.innky.coin.exchange.dto.OrderDto;
import com.innky.coin.exchange.entity.Order;
import com.innky.coin.exchange.entity.UserAssets;
import com.innky.coin.exchange.mapper.OrderMapper;
import com.innky.coin.exchange.service.OrderService;
import com.innky.coin.exchange.service.UserAssetsService;
import com.innky.coin.exchange.vo.OrderVO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 委托订单表
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	private final UserAssetsService userAssetsService;

	private RabbitTemplate rabbitTemplate;

	@Override
	public boolean createOrder(Long userId, OrderVO order) {
		Order newOrder = new Order();
		if (OrderTypeEnum.MARKET.getValue().equals(order.getType())) {
			newOrder.setType(OrderTypeEnum.MARKET.getCode());

		}
		else if (OrderTypeEnum.LIMIT.getValue().equals(order.getType())) {
			UserAssets assets = userAssetsService.getAvailableAssetsBySymbol(userId, order.getSymbol(), OrderSideEnum.of(order.getSide()));
			if (assets.getAmount().compareTo(order.getQuantity().multiply(order.getPrice())) < 0) {
				throw new RuntimeException("余额不足");
			}

			newOrder.setType(OrderTypeEnum.LIMIT.getCode());
			newOrder.setPrice(order.getPrice());
			// TODO 扣减余额
			// userAssetsService.deductAssets(userId, )

		}

		newOrder.setSide(OrderSideEnum.of(order.getSide()).getCode());
		newOrder.setUserId(userId);
		newOrder.setOrderStatus(OrderStatusEnum.OPEN.getCode());
		newOrder.setSymbol(order.getSymbol());
		newOrder.setQuantity(order.getQuantity());
		newOrder.setVolume(BigDecimal.ZERO);
		boolean save = this.save(newOrder);
		// 将订单放入消息队列
		OrderDto rabbitOrder = new OrderDto();
		BeanUtils.copyProperties(newOrder,rabbitOrder);
		rabbitTemplate.convertAndSend(RabbitMQConstants.MICRO_TRADING_EXCHANGE, RabbitMQConstants.ORDERS_QUEUE,
				rabbitOrder);
		return save;
	}

	@Override
	public List<OrderDto> getOpenOrders() {

		List<Order> orders = this.getBaseMapper().selectList(new LambdaQueryWrapper<Order>().eq(Order::getOrderStatus, OrderStatusEnum.OPEN.getCode()));
		return orders.stream().map(order -> {
			OrderDto orderDTO = new OrderDto();
			BeanUtils.copyProperties(order, orderDTO);
			return orderDTO;
		}).collect(Collectors.toList());
	}

}
