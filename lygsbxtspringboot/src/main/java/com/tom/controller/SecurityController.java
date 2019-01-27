package com.tom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.util.ApiResponse;

@RestController
@RequestMapping("/security")
public class SecurityController extends BaseController {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/isLogin")
	public ApiResponse isLogin() {
		ApiResponse apiResponse = new ApiResponse();
		if(getCurrentUser() != null) {
			apiResponse.setMsg("已登录");
		} else {
			apiResponse.setCode("500");
			apiResponse.setMsg("未登录");
		}
		return apiResponse;
	}
}
