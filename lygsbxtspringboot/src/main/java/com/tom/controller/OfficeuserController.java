package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.model.generator.Officeuser;
import com.tom.service.OfficeuserService;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@RestController
@RequestMapping("/officeuser")
public class OfficeuserController extends BaseController {
	@Autowired
	private OfficeuserService officeuserService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/saveOfficeuser")
	public ApiResponse saveOfficeuser(
			@RequestParam(value = "officerid", required = false) String officerid,
			@RequestParam("username") String username,
			@RequestParam("realname") String realname,
			@RequestParam("photourl") String photourl,
			@RequestParam("remark") String remark) {
		ApiResponse apiResponse =  
				officeuserService
					.saveOfficeuser(
							officerid, username, realname, photourl, remark);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageOfficeuser")
	public ApiResponse pageOfficeuser(
			@RequestParam("page") Integer pageNumber,
			@RequestParam("size") Integer pageSize,
			@RequestParam(value = "keyword", required = false) String keyword) {
		TomPage tomPage = 
				officeuserService.pageOfficeuser(pageNumber, pageSize, keyword);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/resetPassword")
	public ApiResponse resetPassword(@RequestParam("officerid") String officerid) {
		officeuserService.resetPassword(officerid);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMsg("密码重置成功");
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delOfficeuser")
	public ApiResponse delOfficeuser(@RequestParam("officerid") String officerid) {
		officeuserService.delOfficeuse(officerid);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMsg("删除成功");
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/login")
	public ApiResponse login(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Officeuser officeuser = officeuserService.login(username, password);
		ApiResponse apiResponse = new ApiResponse();
		if(officeuser != null) {
			apiResponse.setMsg("登录成功！");
			apiResponse.setData(officeuser);
		} else {
			apiResponse.setCode("500");
			apiResponse.setMsg("登录失败！");
		}
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/changePassword")
	public ApiResponse changePassword(
			@RequestParam("officerid") String officerid,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		ApiResponse apiResponse = officeuserService.changePassword(
				officerid, oldPassword, newPassword, confirmPassword);
		return apiResponse;
	}
}
