package com.innky.coin.user.feign;

import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.constant.ServiceNameConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.user.dto.PortalUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author innnky
 * @date 2022/9/20 10:27
 */
@FeignClient(contextId = "remotePortalUserService", value = ServiceNameConstants.USER_SERVICE)
public interface RemotePortalUserService {

	/**
	 * 通过用户名查询用户、角色信息
	 * @param username 用户名
	 * @param from 调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<PortalUserInfo> info(@PathVariable("username") String username,
			@RequestHeader(SecurityConstants.FROM) String from);

}
