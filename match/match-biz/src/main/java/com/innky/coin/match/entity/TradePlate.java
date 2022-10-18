package com.innky.coin.match.entity;

import cn.hutool.extra.spring.SpringUtil;
import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import com.innky.coin.match.dto.TradePlateDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author innnky
 * @date 2022/9/23 11:08
 */
@Data
public class TradePlate {
	private Integer maxDepth;

	/**
	 * 买方深度数据  Price -> Quantity
	 */
	private TreeMap<BigDecimal,BigDecimal> buyItems ;
	/**
	 * 卖方深度数据  Price -> Quantity
	 */
	private TreeMap<BigDecimal,BigDecimal> sellItems ;
	private String symbol;

	public TradePlate(Integer maxDepth, String symbol) {
		this.maxDepth = maxDepth;
		this.symbol = symbol;
		buyItems = new TreeMap<>(Comparator.reverseOrder());
		sellItems = new TreeMap<>(Comparator.naturalOrder());
	}


	private TreeMap<BigDecimal,BigDecimal> getItems(OrderSideEnum side){
		if (side == OrderSideEnum.BUY) {
			return buyItems;
		}
		else {
			return sellItems;
		}
	}

	/**
	 * 向盘口添加一个订单项
	 *
	 * @param price     价格
	 * @param quantity  数量
	 * @param orderSide 方向
	 */
	public void addItem(BigDecimal price, BigDecimal quantity, OrderSideEnum orderSide) {
		TreeMap<BigDecimal, BigDecimal> items = getItems(orderSide);
		BigDecimal priceAmount = items.get(price);
		if (priceAmount ==null ){
			// 当前价格不存在,新增一项
			items.put(price, quantity);
			if ( items.size() > maxDepth){
				// 超出最大深度值, 移除最后一项
				items.remove(items.lastKey());
			}
		}else {
			items.replace(price, items.get(price).add(quantity));
		}
	}


	public void delItem(BigDecimal price, BigDecimal quantity, OrderSideEnum orderSide){
		TreeMap<BigDecimal, BigDecimal> items = getItems(orderSide);
		BigDecimal priceAmount = items.get(price);
		if (priceAmount == null){
			return;
		}
		BigDecimal left = priceAmount.subtract(quantity);
		if (left.equals(BigDecimal.ZERO)){
			items.remove(price);
		}
		else if (left.compareTo(BigDecimal.ZERO) >0 ){
			items.replace(price, left);
		}
		else {
			items.remove(price);
		}

	}

	public TradePlateDto toDto() {
		TradePlateDto tradePlateDto = new TradePlateDto();
		BeanUtils.copyProperties(this, tradePlateDto);
		return tradePlateDto;
	}
}
