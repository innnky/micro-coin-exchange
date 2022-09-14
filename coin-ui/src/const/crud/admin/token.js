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

export const tableOption = {
  border: true,
  index: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  searchMenuSpan: 6,
  viewBtn: false,
  addBtn: false,
  editBtn: false,
  column: [{
    label: '用户名',
    prop: 'username',
    align: 'center',
    slot: true
  }, {
    label: "客户端",
    prop: "clientId",
    align: "center"
  }, {
    label: '令牌',
    prop: 'accessToken',
    align: 'center',
    overHidden: true
  }, {
    label: '创建时间',
    prop: 'issuedAt',
    align: 'center'
  }, {
    label: '过期时间',
    prop: 'expiresAt',
    align: 'center'
  }]
}
