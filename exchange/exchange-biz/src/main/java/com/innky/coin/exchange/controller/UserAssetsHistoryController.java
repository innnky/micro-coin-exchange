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
import com.innky.coin.exchange.entity.UserAssetsHistory;
import com.innky.coin.exchange.service.UserAssetsHistoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 用户资产流水
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userassetshistory" )
@Tag(name = "用户资产流水管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class UserAssetsHistoryController {

    private final  UserAssetsHistoryService userAssetsHistoryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param userAssetsHistory 用户资产流水
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('exchange_userassetshistory_get')" )
    public R getUserAssetsHistoryPage(Page page, UserAssetsHistory userAssetsHistory) {
        return R.ok(userAssetsHistoryService.page(page, Wrappers.query(userAssetsHistory)));
    }


    /**
     * 通过id查询用户资产流水
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('exchange_userassetshistory_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(userAssetsHistoryService.getById(id));
    }

    /**
     * 新增用户资产流水
     * @param userAssetsHistory 用户资产流水
     * @return R
     */
    @Operation(summary = "新增用户资产流水", description = "新增用户资产流水")
    @SysLog("新增用户资产流水" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('exchange_userassetshistory_add')" )
    public R save(@RequestBody UserAssetsHistory userAssetsHistory) {
        return R.ok(userAssetsHistoryService.save(userAssetsHistory));
    }

    /**
     * 修改用户资产流水
     * @param userAssetsHistory 用户资产流水
     * @return R
     */
    @Operation(summary = "修改用户资产流水", description = "修改用户资产流水")
    @SysLog("修改用户资产流水" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('exchange_userassetshistory_edit')" )
    public R updateById(@RequestBody UserAssetsHistory userAssetsHistory) {
        return R.ok(userAssetsHistoryService.updateById(userAssetsHistory));
    }

    /**
     * 通过id删除用户资产流水
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除用户资产流水", description = "通过id删除用户资产流水")
    @SysLog("通过id删除用户资产流水" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('exchange_userassetshistory_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(userAssetsHistoryService.removeById(id));
    }

}
