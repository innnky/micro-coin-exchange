package com.innky.coin.common.security.service;

import com.innky.coin.admin.api.dto.UserInfo;
import com.innky.coin.common.core.constant.CacheConstants;
import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.common.core.util.RetOps;
import com.innky.coin.user.dto.PortalUserInfo;
import com.innky.coin.user.feign.RemotePortalUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

/**
 * @author innnky
 * @date 2022/9/20 10:34
 */
@Slf4j
@RequiredArgsConstructor
public class CoinPortalUserDetailsServiceImpl implements CoinUserDetailsService {

	private final RemotePortalUserService remotePortalUserService;

	private final CacheManager cacheManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cache cache = cacheManager.getCache(CacheConstants.PORTAL_USER_DETAILS);
		if (cache != null && cache.get(username) != null) {
			return (CoinUser) cache.get(username).get();
		}
		R<PortalUserInfo> result = remotePortalUserService.info(username, SecurityConstants.FROM_IN);
		UserDetails userDetails = getPortalUserDetails(result);
		if (cache != null) {
			cache.put(username, userDetails);
		}
		return userDetails;
	}

	private UserDetails getPortalUserDetails(R<PortalUserInfo> result) {
		PortalUserInfo userInfo = RetOps.of(result).getData().orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
		return new CoinUser(userInfo.getId(), null, userInfo.getUsername(), userInfo.getPassword(), null, true, true,
				true, true, AuthorityUtils.NO_AUTHORITIES);
	}

	@Override
	public boolean support(String clientId, String grantType) {
		return SecurityConstants.PORTAL.equals(clientId);
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE + 1;
	}

}
