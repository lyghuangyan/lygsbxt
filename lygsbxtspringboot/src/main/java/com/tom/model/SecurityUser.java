package com.tom.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tom.model.generator.Manageuser;

public class SecurityUser extends Manageuser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2868075223562767919L;
	private List<SimpleGrantedAuthority> authorities;
	
	public SecurityUser(Manageuser manageuser, List<SimpleGrantedAuthority> authorities) {
		this.setManageid(manageuser.getManageid());
		this.setPassword(manageuser.getPassword());
		this.setUsername(manageuser.getUsername());
		this.setRole(manageuser.getRole());
		this.setAuthorities(authorities);
	}
	
	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
