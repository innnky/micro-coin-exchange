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
 * 委托订单表
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Data
@TableName("order")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "委托订单表")
public class Order extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Long id;

    /**
     * 用户id
     */
    @Schema(description ="用户id")
    private Long userId;

    /**
     * 交易对符号
     */
    @Schema(description ="交易对符号")
    private String symbol;

    /**
     * 交易方向
     */
    @Schema(description ="交易方向")
    private Integer side;

    /**
     * 交易类型
     */
    @Schema(description ="交易类型")
    private Integer type;

    /**
     * 交易价格
     */
    @Schema(description ="交易价格")
    private BigDecimal price;

    /**
     * 交易数量
     */
    @Schema(description ="交易数量")
    private BigDecimal quantity;

    /**
     * 委托单状态
     */
    @Schema(description ="委托单状态")
    private Integer orderStatus;


}
