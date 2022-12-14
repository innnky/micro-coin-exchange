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
package com.innky.coin.exchange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.innky.coin.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 币种表
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Data
@TableName("coin")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "币种表")
public class Coin extends BaseEntity {

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "id")
	private Long id;

	/**
	 * 币种名称
	 */
	@Schema(description = "币种名称")
	private String coinName;

	/**
	 * 币种系统余额
	 */
	@Schema(description = "币种系统余额")
	private BigDecimal balance;

	/**
	 * 币种全称
	 */
	@Schema(description = "币种全称")
	private String fullName;

	/**
	 * 币种描述
	 */
	@Schema(description = "币种描述")
	private String coinDescription;

}
