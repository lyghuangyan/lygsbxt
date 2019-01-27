package com.tom.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tom.dao.generator.OfficeuserMapper;
import com.tom.model.generator.Officeuser;
import com.tom.model.generator.OfficeuserExample;
import com.tom.model.generator.OfficeuserExample.Criteria;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

/**
 * 派单员service
 * @author tom
 * @since 2018-12-2 12:50:10
 */
@Service("officeuserService")
public class OfficeuserService {
	@Autowired
	private OfficeuserMapper officeuserMapper;
	
	public void delOfficeuse(String officerid) {
		Officeuser officeuser = officeuserMapper.selectByPrimaryKey(officerid);
		if(officeuser != null) {
			officeuser.setIsdelete(1);
			officeuserMapper.updateByPrimaryKey(officeuser);
		}
	}
	
	public void resetPassword(String officerid) {
		Officeuser officeuser = officeuserMapper.selectByPrimaryKey(officerid);
		if(officeuser != null) {
			officeuser.setPassword(DigestUtils.md5Hex("123456"));
			officeuserMapper.updateByPrimaryKey(officeuser);
		}
	}
	
	public TomPage pageOfficeuser(
			Integer pageNumber, Integer pageSize, String keyword) {
		OfficeuserExample officeuserExample = new OfficeuserExample();
		Criteria criteria = officeuserExample.createCriteria();
		if(StringUtils.isNotBlank(keyword)) {
			criteria.andRealnameOrUsernameLike("%"+keyword+"%");
		}
		criteria.andIsdeleteEqualTo(0);
		Page<Officeuser> page = PageHelper.startPage(pageNumber, pageSize);
		officeuserMapper.selectByExample(officeuserExample);
		TomPage tomPage = new TomPage(page);
		return tomPage;
	}
	
	@SuppressWarnings("rawtypes")
	public synchronized ApiResponse saveOfficeuser(
			String officerid, String username, 
			String realname, String photourl, String remark) {
		ApiResponse apiResponsere = new ApiResponse();
		//校验数据完整性
		if(StringUtils.isBlank(username) 
				|| StringUtils.isBlank(realname) 
				|| StringUtils.isBlank(photourl)) {
			apiResponsere.setCode("500");
			apiResponsere.setMsg("请将信息填写完整");
			return apiResponsere;
		}
		boolean isInsert = false;
		Officeuser officeuser = null;
		//获取当前维修人员原信息
		if(StringUtils.isNotBlank(officerid)) {
			officeuser = officeuserMapper.selectByPrimaryKey(officerid);
		}
		//根据手机号查找维修人员信息
		OfficeuserExample officeuserExample = new OfficeuserExample();
		officeuserExample.createCriteria().andUsernameEqualTo(username);
		List<Officeuser> officeuserList = 
				officeuserMapper.selectByExample(officeuserExample);
		//校验手机号是否重复
		if(officeuserList.size() > 0) {
			apiResponsere.setCode("500");
			apiResponsere.setMsg("手机号重复");
			if(officeuser == null) {
				return apiResponsere;
			}
			if(officeuserList.size() > 1) {
				return apiResponsere;
			}
			if(!officeuser.getOfficerid()
					.equals(officeuserList.get(0).getOfficerid())) {
				return apiResponsere;
			}
			apiResponsere = new ApiResponse();
		}
		if(officeuser == null) {
			isInsert = true;
			officeuser = new Officeuser();
			officeuser.setOfficerid(UUID.randomUUID().toString());
			officeuser.setPassword(DigestUtils.md5Hex("123456"));
			officeuser.setIsdelete(0);
		}
		officeuser.setUsername(username);
		officeuser.setRealname(realname);
		officeuser.setPhotourl(photourl);
		officeuser.setRemark(remark);
		if(isInsert == true) {
			officeuserMapper.insertSelective(officeuser);
			apiResponsere.setMsg("新增成功");
		} else {
			officeuserMapper.updateByPrimaryKey(officeuser);
			apiResponsere.setMsg("修改成功");
		}
		return apiResponsere;
	}
	
	public Officeuser login(String username, String password) {
		Officeuser officeuser = null;
		password = DigestUtils.md5Hex(password);
		OfficeuserExample officeuserExample = new OfficeuserExample();
		officeuserExample.createCriteria()
			.andUsernameEqualTo(username).andPasswordEqualTo(password)
			.andIsdeleteEqualTo(0);
		List<Officeuser> officeuserList = 
				officeuserMapper.selectByExample(officeuserExample);
		if(officeuserList != null && officeuserList.size() > 0) {
			officeuser = officeuserList.get(0);
		}
		return officeuser;
	}
	
	public ApiResponse changePassword(
			String officerid, String oldPassword,
			String newPassword, String confirmPassword) {
		ApiResponse apiResponsere = new ApiResponse();
		apiResponsere.setCode("500");
		if(newPassword == null || newPassword.length()<6 || newPassword.length()>16) {
			apiResponsere.setMsg("新密码长度应在6-16位之间！");
			return apiResponsere;
		}
		if(!newPassword.equals(confirmPassword)) {
			apiResponsere.setMsg("新密码与确认密码不一致！");
			return apiResponsere;
		}
		oldPassword = DigestUtils.md5Hex(oldPassword);
		newPassword = DigestUtils.md5Hex(newPassword);
		Officeuser officeuser = officeuserMapper.selectByPrimaryKey(officerid);
		if(officeuser == null) {
			apiResponsere.setMsg("当前用户未找到，请重新登录！");
			return apiResponsere;
		}
		if(!officeuser.getPassword().equals(oldPassword)) {
			apiResponsere.setMsg("原密码输入错误！");
			return apiResponsere;
		}
		officeuser.setPassword(newPassword);
		officeuserMapper.updateByPrimaryKey(officeuser);
		apiResponsere.setCode("200");
		apiResponsere.setMsg("修改成功！");
		return apiResponsere;
	}
}
