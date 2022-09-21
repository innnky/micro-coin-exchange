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
 * 用户资产
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Data
@TableName("user_assets")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户资产")
public class UserAssets extends BaseEntity {

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
	 * 币种id
	 */
	@Schema(description = "币种id")
	private Long coinId;

	/**
	 * 数量
	 */
	@Schema(description = "数量")
	private BigDecimal amount;

}
