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
import com.innky.coin.admin.api.entity.SysUser;
import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.exception.ErrorCodes;
import com.innky.coin.common.core.util.MsgUtils;
import com.innky.coin.common.core.util.R;
import com.innky.coin.common.log.annotation.SysLog;
import com.innky.coin.common.security.annotation.Inner;
import com.innky.coin.common.security.service.CoinUser;
import com.innky.coin.common.security.util.SecurityUtils;
import com.innky.coin.user.dto.PortalUserInfo;
import com.innky.coin.user.entity.User;
import com.innky.coin.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表
 *
 * @author pig code generator
 * @date 2022-09-16 15:31:40
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "用户表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class UserController {

	private final UserService userService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param user 用户表
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('user_user_get')")
	public R getUserPage(Page page, User user) {
		return R.ok(userService.page(page, Wrappers.query(user)));
	}

	/**
	 * 通过id查询用户表
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('user_user_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(userService.getById(id));
	}

	/**
	 * 新增用户表
	 * @param user 用户表
	 * @return R
	 */
	@Operation(summary = "新增用户表", description = "新增用户表")
	@SysLog("新增用户表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('user_user_add')")
	public R save(@RequestBody User user) {
		return R.ok(userService.save(user));
	}

	/**
	 * 修改用户表
	 * @param user 用户表
	 * @return R
	 */
	@Operation(summary = "修改用户表", description = "修改用户表")
	@SysLog("修改用户表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('user_user_edit')")
	public R updateById(@RequestBody User user) {
		return R.ok(userService.updateById(user));
	}

	/**
	 * 通过id删除用户表
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id删除用户表", description = "通过id删除用户表")
	@SysLog("通过id删除用户表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('user_user_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(userService.removeById(id));
	}

	/**
	 * 通过用户名查询用户、角色信息
	 * @param username 用户名
	 * @return R
	 */
	@Inner
	@GetMapping("/info/{username}")
	R<PortalUserInfo> info(@PathVariable("username") String username) {
		User user = userService.getOne(Wrappers.<User>query().lambda().eq(User::getUsername, username));
		if (user == null) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERINFO_EMPTY, username));
		}
		return R.ok(userService.getPortalUserInfo(user));
	}

	@GetMapping("/info")
	R<User> info(){
		User user = new User();

		CoinUser coinUser = SecurityUtils.getUser();
		user.setUsername(coinUser.getUsername());
		return R.ok(user);
	}

}
