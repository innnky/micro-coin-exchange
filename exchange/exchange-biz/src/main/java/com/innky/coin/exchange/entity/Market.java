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
 * 交易对表
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Data
@TableName("market")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "交易对表")
public class Market extends BaseEntity {

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "id")
	private Long id;

	/**
	 * 符号
	 */
	@Schema(description = "符号")
	private String symbol;

	/**
	 * 卖方币种id
	 */
	@Schema(description = "卖方币种id")
	private Long sellCoinId;

	/**
	 * 买方币种id
	 */
	@Schema(description = "买方币种id")
	private Long buyCoinId;

	/**
	 * 开盘价
	 */
	@Schema(description = "开盘价")
	private BigDecimal openPrice;

	/**
	 * 市价
	 */
	@Schema(description = "市价")
	private BigDecimal marketPrice;

}
