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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innky.coin.common.core.constant.enums.OrderSideEnum;
import com.innky.coin.exchange.entity.Market;
import com.innky.coin.exchange.entity.UserAssets;
import com.innky.coin.exchange.entity.UserAssetsHistory;
import com.innky.coin.exchange.mapper.UserAssetsMapper;
import com.innky.coin.exchange.service.MarketService;
import com.innky.coin.exchange.service.UserAssetsHistoryService;
import com.innky.coin.exchange.service.UserAssetsService;
import com.innky.coin.exchange.vo.UserAssetsVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户资产
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Service
@AllArgsConstructor
public class UserAssetsServiceImpl extends ServiceImpl<UserAssetsMapper, UserAssets> implements UserAssetsService {

	private final MarketService marketService;

	private final UserAssetsHistoryService userAssetsHistoryService;

	@Override
	public UserAssets getAvailableAssetsBySymbol(Long userId, String symbol, OrderSideEnum side) {
		// TODO 添加缓存 优化性能
		Market market = marketService.getOne(new LambdaQueryWrapper<Market>().eq(Market::getSymbol, symbol));
		if (market == null) {
			throw new RuntimeException("没有这个交易对");
		}
		Long coinId;
		switch (side){
			case BUY:
				coinId = market.getBuyCoinId();
				break;
			case SELL:
				coinId = market.getSellCoinId();
				break;
			default:
				coinId = null;
		}

		LambdaQueryWrapper<UserAssets> query = new LambdaQueryWrapper<UserAssets>().eq(UserAssets::getUserId, userId)
				.eq(UserAssets::getCoinId, coinId);
		UserAssets userAssets = getOne(query);
		if (userAssets == null) {
			UserAssets assets = new UserAssets();
			assets.setUserId(userId);
			assets.setAmount(BigDecimal.ZERO);
			assets.setCoinId(coinId);
			return assets;
		}
		return userAssets;
	}

	@Override
	public boolean userCharge(Long userId, Long coinId, BigDecimal quantity) {
		UserAssets asset = this.getOne(new LambdaQueryWrapper<UserAssets>().eq(UserAssets::getUserId, userId)
				.eq(UserAssets::getCoinId, coinId));
		boolean result;
		if (asset == null) {
			asset = new UserAssets();
			asset.setUserId(userId);
			asset.setCoinId(coinId);
			asset.setAmount(quantity);
			result = this.save(asset);
		}
		else {
			asset.setAmount(asset.getAmount().add(quantity));
			result = this.updateById(asset);
		}
		// TODO 保存用户资产历史 修正历史表结构
//		UserAssetsHistory history = new UserAssetsHistory();
		// history.set
		// userAssetsHistoryService.save()
		return result;
	}

	@Override
	public List<UserAssetsVO> getUserCoins(Long id) {

		return getBaseMapper().getAllByUserId(id);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void deductAssets(Long userId, BigDecimal orderAmount, Long coinId) {
		List<UserAssets> userAssets = baseMapper.getAmountByCoinIdAndUserIdForUpdate(coinId, userId);
		if (userAssets.size()!= 1){
			throw new RuntimeException("用户币种资产记录数不存在或大于一条");
		}
		UserAssets assets = userAssets.get(0);
		BigDecimal amountLeft = assets.getAmount().subtract(orderAmount);
		if (amountLeft.compareTo(BigDecimal.ZERO) < 0){
			throw new RuntimeException("用户余额不足");
		}
		if (baseMapper.updateAmountByCoinIdAndUserId(amountLeft, coinId, userId) != 1){
			throw new RuntimeException("扣减余额失败");
		}
		//TODO 用户资产流水记录
//		userAssetsHistoryService.addHistory()
	}

}
