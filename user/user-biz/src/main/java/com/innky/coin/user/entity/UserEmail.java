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
package com.innky.coin.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.innky.coin.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户邮件表
 *
 * @author pig code generator
 * @date 2022-09-16 15:31:40
 */
@Data
@TableName("user_email")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户邮件表")
public class UserEmail extends BaseEntity {

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "id")
	private Long id;

	/**
	 * 用户id
	 */
	@Schema(description = "用户id")
	private Long userId;

	/**
	 * 邮件目的地
	 */
	@Schema(description = "邮件目的地")
	private String sendTo;

	/**
	 * 邮件发送方
	 */
	@Schema(description = "邮件发送方")
	private String sendFrom;

	/**
	 * 邮件标题
	 */
	@Schema(description = "邮件标题")
	private String title;

	/**
	 * 邮件内容
	 */
	@Schema(description = "邮件内容")
	private String content;

}
