package com.tom.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.model.generator.Wxuser;
import com.tom.service.WxuserService;
import com.tom.util.ApiResponse;

@RestController
@RequestMapping("/wxuser")
public class WxuserController extends BaseController {
	@Autowired
	private WxuserService wxuserService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/saveWxuser")
	public ApiResponse saveWxuser(
			@RequestParam(value = "openid", required = false) String openid,
			@RequestParam(value = "nickName", required = false) String nickName,
			@RequestParam(value = "avatarUrl", required = false) String avatarUrl) {
		boolean flag = wxuserService.saveWxuser(openid, nickName, avatarUrl);
		ApiResponse apiResponse = new ApiResponse();
		if(flag == true) {
		} else {
			apiResponse.setCode("500");
			apiResponse.setMsg("操作成功");
		}
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/wxuserIndex")
	public ApiResponse wxuserIndex(
			@RequestParam(value = "openid", required = false, defaultValue = "")
			String openid) {
		Map<String, Object> result = 
				wxuserService.wxuserIndex(openid);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(result);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getWxuserInfo")
	public ApiResponse getWxuserInfo(@RequestParam("openid") String openid) {
		Wxuser wxuser = wxuserService.getWxuserInfo(openid);
		ApiResponse apiResponse = new ApiResponse();
		if(wxuser != null) {
			apiResponse.setData(wxuser);
		} else {
			apiResponse.setCode("500");
			apiResponse.setMsg("操作成功");
		}
		return apiResponse;
	}
}
