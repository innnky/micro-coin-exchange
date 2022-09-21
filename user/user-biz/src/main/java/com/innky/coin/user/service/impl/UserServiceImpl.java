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
package com.innky.coin.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.innky.coin.user.dto.PortalUserInfo;
import com.innky.coin.user.entity.User;
import com.innky.coin.user.mapper.UserMapper;
import com.innky.coin.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表
 *
 * @author pig code generator
 * @date 2022-09-16 15:31:40
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public PortalUserInfo getPortalUserInfo(User user) {
		return new PortalUserInfo(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
	}

}
