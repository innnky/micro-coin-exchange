package com.innky.coin.common.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Collection;
import java.util.Map;

//TODO 实现返回空值的方法
/**
 * @author innnky
 * @date 2022/9/20 10:43
 */
public class PortalUser extends User implements OAuth2AuthenticatedPrincipal {

	private final Long id;

	private final String email;

	public PortalUser(Long id, String email, String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.email = email;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

}
