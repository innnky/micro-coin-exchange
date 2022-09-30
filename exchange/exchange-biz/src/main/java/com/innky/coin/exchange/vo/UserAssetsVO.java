package com.innky.coin.exchange.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author innnky
 * @date 2022/9/30 15:58
 */
@Data
public class UserAssetsVO {
	BigDecimal amount;
	String name;
}
