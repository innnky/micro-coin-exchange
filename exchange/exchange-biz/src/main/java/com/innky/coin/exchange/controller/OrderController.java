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

package com.innky.coin.exchange.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.innky.coin.common.core.util.R;
import com.innky.coin.common.log.annotation.SysLog;
import com.innky.coin.common.security.annotation.Inner;
import com.innky.coin.common.security.util.SecurityUtils;
import com.innky.coin.exchange.dto.OrderDto;
import com.innky.coin.exchange.entity.Order;
import com.innky.coin.exchange.service.OrderService;
import com.innky.coin.exchange.vo.OrderVO;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 委托订单表
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
// TODO 修改前端页面订单添加字段
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Tag(name = "委托订单表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OrderController {

	private final OrderService orderService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param order 委托订单表
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('exchange_order_get')")
	public R getOrderPage(Page page, Order order) {
		return R.ok(orderService.page(page, Wrappers.query(order)));
	}

	/**
	 * 通过id查询委托订单表
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('exchange_order_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(orderService.getById(id));
	}

	/**
	 * 新增委托订单表
	 * @param order 委托订单表
	 * @return R
	 */
	@Operation(summary = "新增委托订单表", description = "新增委托订单表")
	@SysLog("新增委托订单表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('exchange_order_add')")
	public R save(@RequestBody Order order) {
		return R.ok(orderService.save(order));
	}

	/**
	 * 修改委托订单表
	 * @param order 委托订单表
	 * @return R
	 */
	@Operation(summary = "修改委托订单表", description = "修改委托订单表")
	@SysLog("修改委托订单表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('exchange_order_edit')")
	public R updateById(@RequestBody Order order) {
		return R.ok(orderService.updateById(order));
	}

	/**
	 * 通过id删除委托订单表
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id删除委托订单表", description = "通过id删除委托订单表")
	@SysLog("通过id删除委托订单表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('exchange_order_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(orderService.removeById(id));
	}

	/**
	 * 创建委托单
	 * @param order 订单
	 * @return {@link R}
	 */
	@PostMapping("/new")
	public R createOrder(@RequestBody OrderVO order) {
		//TODO 买卖余额计算逻辑错误
		return R.ok(orderService.createOrder(SecurityUtils.getUser().getId(), order));
	}

	@GetMapping("/open")
	@Inner
	R<List<OrderDto>> getOpenOrders(){
		return R.ok(orderService.getOpenOrders());
	}


}
