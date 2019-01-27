package com.tom.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.alibaba.fastjson.JSONObject;
import com.tom.model.SecurityUser;
//import com.jinge.model.SecurityUser;
import com.tom.util.ApiResponse;

public class BaseController {
	
	/**
	 * 获取当前登录用户信息
	 * @return
	 */
	protected SecurityUser getCurrentUser() {
		SecurityUser securityUser = null;
		Object principal = 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal != null){
			if(principal instanceof SecurityUser) {
				securityUser = (SecurityUser) principal;
			}
		}
		return securityUser;
	}
	
	
}
