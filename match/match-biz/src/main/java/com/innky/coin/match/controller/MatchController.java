package com.innky.coin.match.controller;

import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.common.security.annotation.Inner;
import com.innky.coin.match.dto.TradePlateDto;
import com.innky.coin.match.entity.OrderBook;
import com.innky.coin.match.entity.OrderBooks;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author innnky
 * @date 2022/9/24 20:54
 */
@RestController
@AllArgsConstructor
public class MatchController {
	//	@Inner

	OrderBooks orderBooks;
	@GetMapping("/depth/{symbol}")
	@Inner(value = false)
	R<TradePlateDto> getDepth(@PathVariable("symbol") String symbol){
		return R.ok(orderBooks.getOrderBook(symbol).getPlate().toDto());
	}

	@GetMapping("/depth/")
	@Inner
	R<Map<String, TradePlateDto>> getAllDepth(){
		Map<String, TradePlateDto> resultMap = new HashMap<>();
		orderBooks.getOrderBookMap().forEach(((symbol, orderBook) -> {
			resultMap.put(symbol, orderBook.getPlate().toDto());
		}));
		return R.ok(resultMap);
	}


}
