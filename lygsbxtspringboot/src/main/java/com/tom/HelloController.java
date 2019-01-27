package com.tom;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/hello")
public class HelloController {
	
	
	//不分配默认登录用户即可访问
	//@Secured(value = {"ROLE_ADMIN", "ROLE_USER"})//分配多个权限
	//@Secured(value = "LOGIN111")//分配单个权限
	//@Secured(value = "ANONYMOUS")//匿名用户
	//@Secured(value = "LOGIN")//分配单个权限
	@Secured("ROLE_LOGIN")
	@RequestMapping("")
	public String index() {
		Object o =SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		o.equals(null);
		return "hello";
	}
	
	@RequestMapping("/1")
	public String index1() {

		return "index1";
	}
}
