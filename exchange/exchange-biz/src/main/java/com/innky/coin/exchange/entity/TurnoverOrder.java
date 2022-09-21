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
 * 成交订单
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Data
@TableName("turnover_order")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "成交订单")
public class TurnoverOrder extends BaseEntity {

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "id")
	private Long id;

	/**
	 * 交易对符号
	 */
	@Schema(description = "交易对符号")
	private String symbol;

	/**
	 * 成交价格
	 */
	@Schema(description = "成交价格")
	private BigDecimal price;

	/**
	 * 成交数量
	 */
	@Schema(description = "成交数量")
	private BigDecimal quantity;

	/**
	 * 买方订单id
	 */
	@Schema(description = "买方订单id")
	private Long buyOrderId;

	/**
	 * 卖方订单id
	 */
	@Schema(description = "卖方订单id")
	private Long sellOrderId;

}
