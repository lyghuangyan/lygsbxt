package com.tom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tom.dao.generator.ManageuserMapper;
import com.tom.model.BuildingWithPictures;
import com.tom.model.generator.Building;
import com.tom.model.generator.BuildingExample;
import com.tom.model.generator.Buildingtype;
import com.tom.model.generator.Manageuser;
import com.tom.model.generator.ManageuserExample;
import com.tom.model.generator.Pictures;
import com.tom.model.generator.PicturesExample;
import com.tom.model.generator.BuildingExample.Criteria;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

/**
 * 管理员业务层
 * @author tom
 * @since 2019-01-09 19:31:51
 */
@Service("manageuserService")
public class ManageuserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ManageuserMapper manageuserMapper;
	
	public Manageuser findByUsername(String username) {
		ManageuserExample manageuserExample = new ManageuserExample();
		manageuserExample.createCriteria()
			.andUsernameEqualTo(username).andIsdeleteEqualTo(0);
		List<Manageuser> manageuserList = 
				manageuserMapper.selectByExample(manageuserExample);
		if(manageuserList.size() > 0) {
			return manageuserList.get(0);
		}
		return null;
	}
	
	public ApiResponse changePassword(
			String manageid, String oldPassword, 
			String newPassword, String confirmPassword) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode("500");
		Manageuser manageuser = manageuserMapper.selectByPrimaryKey(manageid);
		if(manageuser == null) {
			apiResponse.setMsg("用户未找到！");
			return apiResponse;
		}
		oldPassword = DigestUtils.md5Hex(oldPassword);
		if(!manageuser.getPassword().equals(oldPassword)) {
			apiResponse.setMsg("原密码错误！");
			return apiResponse;
		}
		if(newPassword == null 
				|| newPassword.length() < 6 
				|| newPassword.length() > 16) {
			apiResponse.setMsg("新密码长度应在6-16位！");
			return apiResponse;
		}
		if(!newPassword.equals(confirmPassword)) {
			apiResponse.setMsg("新密码与确认密码不一致！");
			return apiResponse;
		}
		newPassword = DigestUtils.md5Hex(newPassword);
		manageuser.setPassword(newPassword);
		manageuserMapper.updateByPrimaryKeySelective(manageuser);
		apiResponse.setCode("200");
		apiResponse.setMsg("密码修改成功！");
		return apiResponse;
	}
	
	public TomPage pageManageuser(Integer pageNumber, Integer pageSize) {
		ManageuserExample manageuserExample = new ManageuserExample();
		manageuserExample.createCriteria().andIsdeleteEqualTo(0);
		Page<Manageuser> page = PageHelper.startPage(pageNumber, pageSize);
		manageuserMapper.selectByExample(manageuserExample);
		TomPage tomPage = new TomPage(page);
		return tomPage;
	}
	
	public void resetPassword(String manageid) {
		Manageuser manageuser = manageuserMapper.selectByPrimaryKey(manageid);
		if(manageuser != null) {
			manageuser.setPassword(DigestUtils.md5Hex("123456"));
			manageuserMapper.updateByPrimaryKeySelective(manageuser);
		}
	}
	
	public void delManageuser(String manageid) {
		Manageuser manageuser = manageuserMapper.selectByPrimaryKey(manageid);
		if(manageuser != null) {
			manageuser.setIsdelete(1);
			manageuserMapper.updateByPrimaryKeySelective(manageuser);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public synchronized ApiResponse saveManageuser(String manageid, String username, String role) {
		ApiResponse apiResponse = new ApiResponse();
		if(StringUtils.isBlank(username) || username.length() < 4 || username.length() > 16) {
			apiResponse.setCode("500");
			apiResponse.setMsg("登录名长度在4-16之间！");
			return apiResponse;
		}
		if(StringUtils.isBlank(role) || 
				(!"1".contentEquals(role) && !"2".contentEquals(role) && !"3".contentEquals(role))) {
			apiResponse.setCode("500");
			apiResponse.setMsg("请选择管理员类型！");
			return apiResponse;
		}
		
		if(StringUtils.isNotBlank(manageid)) {
			ManageuserExample manageuserExample = new ManageuserExample();
			manageuserExample.createCriteria().andManageidNotEqualTo(manageid).andUsernameEqualTo(username);
			List<Manageuser> manageusers = manageuserMapper.selectByExample(manageuserExample);
			if(manageusers != null && manageusers.size() > 0) {
				apiResponse.setCode("500");
				apiResponse.setMsg("登录名重复！");
				return apiResponse;
			}
		}
		Manageuser manageuser = null;
		if(StringUtils.isNotBlank(manageid)) {
			manageuser = manageuserMapper.selectByPrimaryKey(manageid);
		}
		boolean isInsert = false;
		if(manageuser == null) {
			isInsert = true;
			manageuser = new Manageuser();
			manageuser.setIsdelete(0);
			manageuser.setManageid(UUID.randomUUID().toString());
			manageuser.setPassword(DigestUtils.md5Hex("123456"));
		}
		manageuser.setRole(Integer.parseInt(role));
		manageuser.setUsername(username);
		if(isInsert == true) {
			manageuserMapper.insertSelective(manageuser);
		} else {
			manageuserMapper.updateByPrimaryKeySelective(manageuser);
		}
		return apiResponse;
	}
}
