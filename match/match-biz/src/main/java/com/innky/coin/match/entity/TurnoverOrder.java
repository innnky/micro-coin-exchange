package com.innky.coin.match.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import com.innky.coin.exchange.dto.OrderDto;
import com.innky.coin.match.dto.TurnoverOrderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author innnky
 * @date 2022/9/23 19:08
 */
@Data
public class TurnoverOrder {
	public TurnoverOrder(String symbol, BigDecimal price, BigDecimal quantity,
						 Order currentOrder, Order oppositeOrder, OrderSideEnum side) {
		this.symbol = symbol;
		this.price = price;
		this.quantity = quantity;
		this.buyOrder = new OrderDto();
		this.sellOrder = new OrderDto();
		if (side == OrderSideEnum.BUY){
			BeanUtils.copyProperties(currentOrder, this.buyOrder);
			BeanUtils.copyProperties(oppositeOrder, this.sellOrder);
		}
		else {
			BeanUtils.copyProperties(currentOrder, this.sellOrder);
			BeanUtils.copyProperties(oppositeOrder, this.buyOrder);
		}
	}

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
	 * 买方订单
	 */
	private OrderDto buyOrder;

	/**
	 * 卖方订单id
	 */
	private OrderDto sellOrder;

	public TurnoverOrderDto toDto(){
		TurnoverOrderDto turnoverOrderDto = new TurnoverOrderDto();
		BeanUtils.copyProperties(this, turnoverOrderDto);
		return turnoverOrderDto;
	}

}
