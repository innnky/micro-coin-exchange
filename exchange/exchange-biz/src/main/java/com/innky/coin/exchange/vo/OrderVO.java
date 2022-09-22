package com.innky.coin.exchange.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author innnky
 * @date 2022/9/22 15:54
 */
@Data
public class OrderVO {

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 交易对符号
	 */
	private String symbol;

	/**
	 * 交易方向
	 */
	private String side;

	/**
	 * 交易类型
	 */
	private String type;

	/**
	 * 交易价格
	 */
	private BigDecimal price;

	/**
	 * 交易数量
	 */
	private BigDecimal quantity;

}
