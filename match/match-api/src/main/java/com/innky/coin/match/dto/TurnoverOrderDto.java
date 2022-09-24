package com.innky.coin.match.dto;

import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author innnky
 * @date 2022/9/23 19:08
 */
@Data
public class TurnoverOrderDto {
	/**
	 * id
	 */
	private Long id;

	/**
	 * 交易对符号
	 */
	private String symbol;

	/**
	 * 成交价格
	 */
	private BigDecimal price;

	/**
	 * 成交数量
	 */
	private BigDecimal quantity;

	/**
	 * 买方订单id
	 */
	private Long buyOrderId;

	/**
	 * 卖方订单id
	 */
	private Long sellOrderId;

}
