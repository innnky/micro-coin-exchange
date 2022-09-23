package com.innky.coin.exchange.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.innky.coin.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author innnky
 * @date 2022/9/23 11:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarketDto extends BaseEntity {
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
