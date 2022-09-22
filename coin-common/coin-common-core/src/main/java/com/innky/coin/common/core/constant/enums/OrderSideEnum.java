package com.innky.coin.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author innnky
 * @date 2022/9/22 16:10
 */
@Getter
@RequiredArgsConstructor
public enum OrderSideEnum {
	/**
	 * 买
	 */
	BUY(0, "BUY"),
	/**
	 * 卖
	 */
	SELL(1, "SELL");

	/**
	 * 编码
	 */
	private final Integer code;

	/**
	 * 值
	 */
	private final String value;

	public static OrderSideEnum get(String description){
		if(SELL.getValue().equals(description)){
			return SELL;
		}
		else if(BUY.getValue().equals(description)) {
			return BUY;
		}
		else {
			throw new RuntimeException("交易方向不合法");
		}
	}


}
