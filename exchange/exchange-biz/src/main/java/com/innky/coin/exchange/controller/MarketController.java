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
import com.innky.coin.common.core.constant.CacheConstants;
import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.common.log.annotation.SysLog;
import com.innky.coin.common.security.annotation.Inner;
import com.innky.coin.exchange.dto.MarketDto;
import com.innky.coin.exchange.dto.OrderDto;
import com.innky.coin.exchange.entity.Market;
import com.innky.coin.exchange.service.MarketService;
import com.innky.coin.information.dto.KlineItemDto;
import com.innky.coin.match.dto.TradePlateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
//TODO ???????????? ??????????????????
/**
 * ????????????
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
@Tag(name = "??????????????????")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class MarketController {

	private final MarketService marketService;
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * ????????????
	 * @param page ????????????
	 * @param market ????????????
	 * @return
	 */
	@Operation(summary = "????????????", description = "????????????")
	@GetMapping("/page")
	// @PreAuthorize("@pms.hasPermission('exchange_market_get')")
	public R getMarketPage(Page page, Market market) {
		return R.ok(marketService.page(page, Wrappers.query(market)));
	}

	/**
	 * ??????id??????????????????
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "??????id??????", description = "??????id??????")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('exchange_market_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(marketService.getById(id));
	}

	/**
	 * ??????????????????
	 * @param market ????????????
	 * @return R
	 */
	@Operation(summary = "??????????????????", description = "??????????????????")
	@SysLog("??????????????????")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('exchange_market_add')")
	public R save(@RequestBody Market market) {

		return R.ok(marketService.saveMarket(market));
	}

	/**
	 * ??????????????????
	 * @param market ????????????
	 * @return R
	 */
	@Operation(summary = "??????????????????", description = "??????????????????")
	@SysLog("??????????????????")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('exchange_market_edit')")
	public R updateById(@RequestBody Market market) {
		return R.ok(marketService.updateById(market));
	}

	/**
	 * ??????id??????????????????
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "??????id??????????????????", description = "??????id??????????????????")
	@SysLog("??????id??????????????????")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('exchange_market_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(marketService.removeById(id));
	}

	/**
	 * ??????????????????
	 *
	 * @return {@link R}<{@link List}<{@link MarketDto}>>
	 */
	@GetMapping("/all")
	@Inner
	R<List<MarketDto>> getAllMarkets(){
		return R.ok(marketService.list().stream().map(market -> {
			MarketDto marketDto = new MarketDto();
			BeanUtils.copyProperties(market,marketDto);
			return marketDto;
		}).collect(Collectors.toList()));
	}

	/**
	 * ??????????????????
	 *
	 * @return {@link R}
	 */
	@GetMapping("/{symbol}/marketprice")
	public R<BigDecimal> getMarketPrice(@PathVariable String symbol){
		BigDecimal price = (BigDecimal) redisTemplate.opsForValue().get(CacheConstants.MARKET_PRICE_KEY + symbol);
		return R.ok(price);
	}
	/**
	 * ??????????????????
	 *
	 * @return {@link R}
	 */
	@GetMapping("/{symbol}/depth")
	public R<TradePlateDto> getDepth(@PathVariable String symbol){
		TradePlateDto tradePlateDto = (TradePlateDto) redisTemplate.opsForValue().get(CacheConstants.DEPTH_KEY + symbol);
		return R.ok(tradePlateDto);
	}

	/**
	 * ??????k?????????
	 *
	 * @param symbol ??????
	 * @param level  ??????
	 * @return {@link R}<{@link List}<{@link Object}>>
	 */
	@GetMapping("/{symbol}/kline")
	public R<List<Object>> getKlineData(@PathVariable String symbol, @RequestParam(required = false, defaultValue = "1m") String level){

		List<Object> range = redisTemplate.opsForList().range(
				CacheConstants.KLINE_KEY + symbol + ":" + level,
				0,
				-1
		);
		return R.ok(range);
	}






}
