package com.innky.coin.match.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * @author innnky
 * @date 2022/9/24 16:24
 */
@Data
public class TradePlateDto implements Serializable {
	/**
	 * 买方深度数据  Price -> Quantity
	 */
	private TreeMap<BigDecimal,BigDecimal> buyItems ;
	/**
	 * 卖方深度数据  Price -> Quantity
	 */
	private TreeMap<BigDecimal,BigDecimal> sellItems ;
	private String symbol;

}
