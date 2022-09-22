package com.innky.coin.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author innnky
 * @date 2022/9/22 16:24
 */
@RequiredArgsConstructor
@Getter
public enum OrderStatusEnum {

	/**
	 * 待成交
	 */
	OPEN(0, "OPEN", "待成交"),
	/**
	 * 已完成
	 */
	CLOSED(1, "CLOSED", "已完成"),
	/**
	 * 已撤销
	 */
	CANCELED(2, "CANCELED", "已撤销"),
	/**
	 * 异常
	 */
	ERROR(3, "ERROR", "异常");

	/**
	 * 编码
	 */
	private final Integer code;

	/**
	 * 值
	 */
	private final String value;

	/**
	 * 描述
	 */
	private final String description;

}
