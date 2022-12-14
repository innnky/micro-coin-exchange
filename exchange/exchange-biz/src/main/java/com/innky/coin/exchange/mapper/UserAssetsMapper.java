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

package com.innky.coin.exchange.mapper;
import java.math.BigDecimal;
import java.util.List;

import com.innky.coin.exchange.vo.UserAssetsVO;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.innky.coin.exchange.entity.UserAssets;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户资产
 *
 * @author pig code generator
 * @date 2022-09-16 15:30:21
 */
@Mapper
public interface UserAssetsMapper extends BaseMapper<UserAssets> {

	List<UserAssetsVO> getAllByUserId(@Param("userId") Long userId);

	int updateAmountByCoinIdAndUserId(@Param("amount") BigDecimal amount, @Param("coinId") Long coinId, @Param("userId") Long userId);

	List<UserAssets> getAmountByCoinIdAndUserIdForUpdate(@Param("coinId") Long coinId, @Param("userId") Long userId);
}
