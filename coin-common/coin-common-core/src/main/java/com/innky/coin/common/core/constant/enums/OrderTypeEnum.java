package com.innky.coin.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author innnky
 * @date 2022/9/22 16:02
 */
@Getter
@RequiredArgsConstructor
public enum OrderTypeEnum {

	/**
	 * 限价单
	 */
	LIMIT(0, "limit"),

	/**
	 * 市价单
	 */
	MARKET(1, "market");

	/**
	 * 编码
	 */
	private final Integer code;

	/**
	 * 值
	 */
	private final String value;

}
