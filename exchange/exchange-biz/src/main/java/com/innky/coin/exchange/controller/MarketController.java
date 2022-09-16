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
import com.innky.coin.exchange.entity.Market;
import com.innky.coin.exchange.service.MarketService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 交易对表
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/market" )
@Tag(name = "交易对表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class MarketController {

    private final  MarketService marketService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param market 交易对表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('exchange_market_get')" )
    public R getMarketPage(Page page, Market market) {
        return R.ok(marketService.page(page, Wrappers.query(market)));
    }


    /**
     * 通过id查询交易对表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('exchange_market_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(marketService.getById(id));
    }

    /**
     * 新增交易对表
     * @param market 交易对表
     * @return R
     */
    @Operation(summary = "新增交易对表", description = "新增交易对表")
    @SysLog("新增交易对表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('exchange_market_add')" )
    public R save(@RequestBody Market market) {
        return R.ok(marketService.save(market));
    }

    /**
     * 修改交易对表
     * @param market 交易对表
     * @return R
     */
    @Operation(summary = "修改交易对表", description = "修改交易对表")
    @SysLog("修改交易对表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('exchange_market_edit')" )
    public R updateById(@RequestBody Market market) {
        return R.ok(marketService.updateById(market));
    }

    /**
     * 通过id删除交易对表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除交易对表", description = "通过id删除交易对表")
    @SysLog("通过id删除交易对表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('exchange_market_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(marketService.removeById(id));
    }

}
