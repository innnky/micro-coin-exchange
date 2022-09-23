/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the coin4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.innky.coin.exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innky.coin.exchange.entity.Coin;
import com.innky.coin.exchange.entity.Market;
import com.innky.coin.exchange.exception.InsertException;
import com.innky.coin.exchange.mapper.MarketMapper;
import com.innky.coin.exchange.service.CoinService;
import com.innky.coin.exchange.service.MarketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 交易对表
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Service
@AllArgsConstructor
public class MarketServiceImpl extends ServiceImpl<MarketMapper, Market> implements MarketService {

	private final CoinService coinService;

	@Override
	public boolean saveMarket(Market market) {
		Coin sellCoin = coinService.getById(market.getSellCoinId());
		Coin buyCoin = coinService.getById(market.getBuyCoinId());
		if (sellCoin == null || buyCoin == null) {
			throw new InsertException("交易对币种不存在");
		}
		String symbol = sellCoin.getCoinName() + buyCoin.getCoinName();
		market.setSymbol(symbol);
		return save(market);
	}

}
