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

package com.innky.coin.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.innky.coin.common.core.util.R;
import com.innky.coin.common.log.annotation.SysLog;
import com.innky.coin.user.entity.LoginLog;
import com.innky.coin.user.service.LoginLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录日志
 *
 * @author pig code generator
 * @date 2022-09-16 15:31:40
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginlog")
@Tag(name = "用户登录日志管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class LoginLogController {

	private final LoginLogService loginLogService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param loginLog 用户登录日志
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('user_loginlog_get')")
	public R getLoginLogPage(Page page, LoginLog loginLog) {
		return R.ok(loginLogService.page(page, Wrappers.query(loginLog)));
	}

	/**
	 * 通过id查询用户登录日志
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('user_loginlog_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(loginLogService.getById(id));
	}

	/**
	 * 新增用户登录日志
	 * @param loginLog 用户登录日志
	 * @return R
	 */
	@Operation(summary = "新增用户登录日志", description = "新增用户登录日志")
	@SysLog("新增用户登录日志")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('user_loginlog_add')")
	public R save(@RequestBody LoginLog loginLog) {
		return R.ok(loginLogService.save(loginLog));
	}

	/**
	 * 修改用户登录日志
	 * @param loginLog 用户登录日志
	 * @return R
	 */
	@Operation(summary = "修改用户登录日志", description = "修改用户登录日志")
	@SysLog("修改用户登录日志")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('user_loginlog_edit')")
	public R updateById(@RequestBody LoginLog loginLog) {
		return R.ok(loginLogService.updateById(loginLog));
	}

	/**
	 * 通过id删除用户登录日志
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id删除用户登录日志", description = "通过id删除用户登录日志")
	@SysLog("通过id删除用户登录日志")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('user_loginlog_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(loginLogService.removeById(id));
	}

}
