package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.service.WechatService;
import com.tom.util.ApiResponse;

@RestController
@RequestMapping("/wechat")
public class WechatController extends BaseController {
	@Autowired
	private WechatService wechatService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/loginWithCode")
	public ApiResponse loginWithCode(@RequestParam("code") String code) {
		ApiResponse apiResponse = wechatService.loginWithCode(code);
		return apiResponse;
	}
}
