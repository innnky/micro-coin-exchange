package com.innky.coin.information.entity;

import com.innky.coin.information.dto.KlineItemDto;
import lombok.Data;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author innnky
 * @date 2022/9/26 10:06
 */
@Data
public class Kline {

	/**
	 * 交易对符号
	 */
	private String symbol;

	/**
	 * k线级别
	 */
	private String level;

	/**
	 * k线项
	 */
	private ConcurrentLinkedDeque<KlineItemDto> items ;

	public Kline(String symbol, String level) {
		this.symbol = symbol;
		this.level = level;

		items =new ConcurrentLinkedDeque<>();
	}


}
