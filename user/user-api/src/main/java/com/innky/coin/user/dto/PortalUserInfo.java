package com.innky.coin.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author innnky
 * @date 2022/9/20 10:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortalUserInfo {

	private Long id;

	private String username;

	private String password;

	private String email;

}
