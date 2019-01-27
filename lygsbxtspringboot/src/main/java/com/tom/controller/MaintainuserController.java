package com.tom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.model.MaintainuserWithCommentsQualifications;
import com.tom.model.generator.Maintainuser;
import com.tom.service.MaintainuserService;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@RestController
@RequestMapping("/maintainuser")
public class MaintainuserController extends BaseController {
	@Autowired
	private MaintainuserService maintainuserService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/saveMaintainuser")
	public ApiResponse saveMaintainuser(
			@RequestParam(value = "maintainid", required = false) String maintainid,
			@RequestParam("username") String username,
			@RequestParam("realname") String realname,
			@RequestParam("photourl") String photourl,
			@RequestParam("remark") String remark) {
		ApiResponse apiResponse = maintainuserService.saveMaintainuser(
				maintainid, username, realname, photourl, remark);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageMaintainuser")
	public ApiResponse pageMaintainuser(
			@RequestParam("page") Integer pageNumber,
			@RequestParam("size") Integer pageSize,
			@RequestParam(value = "keyword", required = false) String keyword) {
		TomPage tomPage = 
				maintainuserService.pageMaintainuser(pageNumber, pageSize, keyword);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/resetPassword")
	public ApiResponse resetPassword(@RequestParam("maintainid") String maintainid) {
		maintainuserService.resetPassword(maintainid);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMsg("密码重置成功");
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delMaintainuser")
	public ApiResponse delMaintainuser(@RequestParam("maintainid") String maintainid) {
		maintainuserService.delMaintainuser(maintainid);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMsg("删除成功");
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/login")
	public ApiResponse login(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Maintainuser maintainuser = maintainuserService.login(username, password);
		ApiResponse apiResponse = new ApiResponse();
		if(maintainuser != null) {
			apiResponse.setMsg("登录成功！");
			apiResponse.setData(maintainuser);
		} else {
			apiResponse.setCode("500");
			apiResponse.setMsg("登录失败！");
		}
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/changePassword")
	public ApiResponse changePassword(
			@RequestParam("maintainid") String maintainid,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		ApiResponse apiResponse =
				maintainuserService.changePassword(
						maintainid, oldPassword, newPassword, confirmPassword);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listMaintainuser")
	public ApiResponse listMaintainuser() {
		List<Maintainuser> maintainusers = maintainuserService.listMaintainuser();
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(maintainusers);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getMaintainuserDetail")
	public ApiResponse getMaintainuserDetail(
			@RequestParam("openid") String openid,
			@RequestParam("maintainid") String maintainid) {
		MaintainuserWithCommentsQualifications maintainuserWithCommentsQualifications =
				maintainuserService.getMaintainuserDetail(openid, maintainid);
		ApiResponse apiResponse = new ApiResponse();
		if(maintainuserWithCommentsQualifications != null) {
			apiResponse.setData(maintainuserWithCommentsQualifications);
		} else {
			apiResponse.setCode("500");
			apiResponse.setMsg("操作失败");
		}
		return apiResponse;
	}
}
