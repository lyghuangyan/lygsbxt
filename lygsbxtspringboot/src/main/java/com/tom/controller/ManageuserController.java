package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.model.SecurityUser;
import com.tom.service.ManageuserService;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@RestController
@RequestMapping("/manageuser")
public class ManageuserController extends BaseController {
	@Autowired
	private ManageuserService manageuserService;
	
	@SuppressWarnings("rawtypes")
	@Secured(value = "ROLE_LOGIN")
	@RequestMapping("/changePassword")
	public ApiResponse changePassword(
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		ApiResponse apiResponse = manageuserService.changePassword(
				this.getCurrentUser().getManageid(), 
				oldPassword, newPassword, confirmPassword);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Secured(value = "ROLE_LOGIN")
	@RequestMapping("/getPersonalUserInfo")
	public ApiResponse getPersonalUserInfo() {
		SecurityUser user = getCurrentUser();
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(user);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageManageuser")
	public ApiResponse pageManageuser(
			@RequestParam("page") Integer pageNumber,
			@RequestParam("size") Integer pageSize) {
		TomPage tomPage = 
				manageuserService.pageManageuser(pageNumber, pageSize);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/resetPassword")
	public ApiResponse resetPassword(
			@RequestParam("manageid") String manageid) {
		manageuserService.resetPassword(manageid);
		ApiResponse apiResponse = new ApiResponse();
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delManageuser")
	public ApiResponse delManageuser(@RequestParam("manageid") String manageid) {
		manageuserService.delManageuser(manageid);
		ApiResponse apiResponse = new ApiResponse();
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/saveManageuser")
	public ApiResponse saveManageuser(
			@RequestParam("manageid") String manageid,
			@RequestParam("username") String username,
			@RequestParam("role") String role) {
		ApiResponse apiResponse = manageuserService.saveManageuser(manageid, username, role);
		return apiResponse;
	}
	
}
