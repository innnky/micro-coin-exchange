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
import com.innky.coin.user.entity.UserEmail;
import com.innky.coin.user.service.UserEmailService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * 用户邮件表
 *
 * @author pig code generator
 * @date 2022-09-16 15:31:40
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/useremail")
@Tag(name = "用户邮件表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class UserEmailController {

	private final UserEmailService userEmailService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param userEmail 用户邮件表
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('user_useremail_get')")
	public R getUserEmailPage(Page page, UserEmail userEmail) {
		return R.ok(userEmailService.page(page, Wrappers.query(userEmail)));
	}

	/**
	 * 通过id查询用户邮件表
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('user_useremail_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(userEmailService.getById(id));
	}

	/**
	 * 新增用户邮件表
	 * @param userEmail 用户邮件表
	 * @return R
	 */
	@Operation(summary = "新增用户邮件表", description = "新增用户邮件表")
	@SysLog("新增用户邮件表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('user_useremail_add')")
	public R save(@RequestBody UserEmail userEmail) {
		return R.ok(userEmailService.save(userEmail));
	}

	/**
	 * 修改用户邮件表
	 * @param userEmail 用户邮件表
	 * @return R
	 */
	@Operation(summary = "修改用户邮件表", description = "修改用户邮件表")
	@SysLog("修改用户邮件表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('user_useremail_edit')")
	public R updateById(@RequestBody UserEmail userEmail) {
		return R.ok(userEmailService.updateById(userEmail));
	}

	/**
	 * 通过id删除用户邮件表
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id删除用户邮件表", description = "通过id删除用户邮件表")
	@SysLog("通过id删除用户邮件表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('user_useremail_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(userEmailService.removeById(id));
	}

}
