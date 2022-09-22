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

package com.innky.coin.exchange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.innky.coin.exchange.entity.UserAssets;

import java.math.BigDecimal;

/**
 * 用户资产
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
public interface UserAssetsService extends IService<UserAssets> {

	/**
	 * 通过Symbol获取可用资产
	 *
	 * @param userId 用户id
	 * @param symbol 交易对
	 * @return {@link BigDecimal}
	 */
	BigDecimal getAvailableAssetsBySymbol(Long userId, String symbol);

    boolean userCharge(Long userId, Long coinId, BigDecimal quantity);
}
