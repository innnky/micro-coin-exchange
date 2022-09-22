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
import com.innky.coin.common.security.util.SecurityUtils;
import com.innky.coin.exchange.entity.UserAssets;
import com.innky.coin.exchange.service.UserAssetsService;
import com.innky.coin.exchange.vo.ChargeVO;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 用户资产
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userassets")
@Tag(name = "用户资产管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class UserAssetsController {

	private final UserAssetsService userAssetsService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param userAssets 用户资产
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('exchange_userassets_get')")
	public R getUserAssetsPage(Page page, UserAssets userAssets) {
		return R.ok(userAssetsService.page(page, Wrappers.query(userAssets)));
	}

	/**
	 * 通过id查询用户资产
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('exchange_userassets_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(userAssetsService.getById(id));
	}

	/**
	 * 新增用户资产
	 * @param userAssets 用户资产
	 * @return R
	 */
	@Operation(summary = "新增用户资产", description = "新增用户资产")
	@SysLog("新增用户资产")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('exchange_userassets_add')")
	public R save(@RequestBody UserAssets userAssets) {
		return R.ok(userAssetsService.save(userAssets));
	}

	/**
	 * 修改用户资产
	 * @param userAssets 用户资产
	 * @return R
	 */
	@Operation(summary = "修改用户资产", description = "修改用户资产")
	@SysLog("修改用户资产")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('exchange_userassets_edit')")
	public R updateById(@RequestBody UserAssets userAssets) {
		return R.ok(userAssetsService.updateById(userAssets));
	}

	/**
	 * 通过id删除用户资产
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id删除用户资产", description = "通过id删除用户资产")
	@SysLog("通过id删除用户资产")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('exchange_userassets_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(userAssetsService.removeById(id));
	}


	/**
	 * 通过Symbol获取可用资产
	 *
	 * @param symbol 交易对符号
	 * @return {@link R}
	 */
	@GetMapping("/available")
	public R getAvailableAssetsBySymbol(String symbol){
		Long userId = SecurityUtils.getUser().getId();
		return R.ok(userAssetsService.getAvailableAssetsBySymbol(userId, symbol));
	}

	/**
	 * 用户充值
	 *
	 * @return {@link R}
	 */
	@PostMapping("/charge")
	public R userCharge(@RequestBody ChargeVO chargeVO){
		Long userId = SecurityUtils.getUser().getId();
		boolean data = userAssetsService.userCharge(userId, chargeVO.getCoinId(), chargeVO.getQuantity());
		return R.ok(data);
	}
}
