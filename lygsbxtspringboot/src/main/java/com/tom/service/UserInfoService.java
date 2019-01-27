package com.tom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tom.model.SecurityUser;
import com.tom.model.generator.Manageuser;


/**
 * security认证+授权
 * @author tom
 * @since 2018-10-21 22:00:15
 */
@Service("userInfoService")
public class UserInfoService implements UserDetailsService {
	@Autowired
	private ManageuserService manageuserService;
	
	/**
	 * 根据username获取用户信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Manageuser manageuser = manageuserService.findByUsername(username);
		if(manageuser != null) {
			//获取到登陆信息，用户名相同默认获取第一个用户，因为注册时需要校验用户名唯一性
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_LOGIN"));
			return new SecurityUser(manageuser, authorities);
		} else {
			throw new UsernameNotFoundException("用户未注册");
		}
	}

}
