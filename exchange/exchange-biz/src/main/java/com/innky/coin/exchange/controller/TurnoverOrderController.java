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
import com.innky.coin.exchange.entity.TurnoverOrder;
import com.innky.coin.exchange.service.TurnoverOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 成交订单
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/turnoverorder" )
@Tag(name = "成交订单管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class TurnoverOrderController {

    private final  TurnoverOrderService turnoverOrderService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param turnoverOrder 成交订单
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('exchange_turnoverorder_get')" )
    public R getTurnoverOrderPage(Page page, TurnoverOrder turnoverOrder) {
        return R.ok(turnoverOrderService.page(page, Wrappers.query(turnoverOrder)));
    }


    /**
     * 通过id查询成交订单
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('exchange_turnoverorder_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(turnoverOrderService.getById(id));
    }

    /**
     * 新增成交订单
     * @param turnoverOrder 成交订单
     * @return R
     */
    @Operation(summary = "新增成交订单", description = "新增成交订单")
    @SysLog("新增成交订单" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('exchange_turnoverorder_add')" )
    public R save(@RequestBody TurnoverOrder turnoverOrder) {
        return R.ok(turnoverOrderService.save(turnoverOrder));
    }

    /**
     * 修改成交订单
     * @param turnoverOrder 成交订单
     * @return R
     */
    @Operation(summary = "修改成交订单", description = "修改成交订单")
    @SysLog("修改成交订单" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('exchange_turnoverorder_edit')" )
    public R updateById(@RequestBody TurnoverOrder turnoverOrder) {
        return R.ok(turnoverOrderService.updateById(turnoverOrder));
    }

    /**
     * 通过id删除成交订单
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除成交订单", description = "通过id删除成交订单")
    @SysLog("通过id删除成交订单" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('exchange_turnoverorder_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(turnoverOrderService.removeById(id));
    }

}
