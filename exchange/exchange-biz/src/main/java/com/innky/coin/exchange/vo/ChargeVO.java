package com.innky.coin.exchange.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author innnky
 * @date 2022/9/22 10:43
 */
@Data
public class ChargeVO {
	private Long coinId;
	private BigDecimal quantity;
}
