package com.tom.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tom.dao.generator.MaintainuserMapper;
import com.tom.model.MaintainuserWithCommentsQualifications;
import com.tom.model.generator.Maintainuser;
import com.tom.model.generator.MaintainuserExample;
import com.tom.model.generator.MaintainuserExample.Criteria;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@Service("maintainuserService")
public class MaintainuserService {
	@Autowired
	private MaintainuserMapper maintainuserMapper;
	@Autowired
	private CommentsService commentsService;

	public void delMaintainuser(String maintainid) {
		Maintainuser maintainuser = maintainuserMapper.selectByPrimaryKey(maintainid);
		if(maintainuser != null) {
			maintainuser.setIsdelete(1);
			maintainuserMapper.updateByPrimaryKey(maintainuser);
		}
	}
	
	public void resetPassword(String maintainid) {
		Maintainuser maintainuser = maintainuserMapper.selectByPrimaryKey(maintainid);
		if(maintainuser != null) {
			maintainuser.setPassword(DigestUtils.md5Hex("123456"));
			maintainuserMapper.updateByPrimaryKey(maintainuser);
		}
	}
	
	public TomPage pageMaintainuser(
			Integer pageNumber, Integer pageSize, String keyword) {
		MaintainuserExample maintainuserExample = new MaintainuserExample();
		Criteria criteria = maintainuserExample.createCriteria();
		if(StringUtils.isNotBlank(keyword)) {
			criteria.andRealnameOrUsernameLike("%"+keyword+"%");
		}
		criteria.andIsdeleteEqualTo(0);
		maintainuserExample.setOrderByClause(" maintainid");
		Page<Maintainuser> page = PageHelper.startPage(pageNumber, pageSize);
		maintainuserMapper.selectByExample(maintainuserExample);
		TomPage tomPage = new TomPage(page);
		return tomPage;
	}
	
	@SuppressWarnings("rawtypes")
	public synchronized ApiResponse saveMaintainuser(
			String maintainid, String username, 
			String realname, String photourl,
			String remark) {
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
		Maintainuser maintainuser = null;
		//获取当前维修人员原信息
		if(StringUtils.isNotBlank(maintainid)) {
			maintainuser = maintainuserMapper.selectByPrimaryKey(maintainid);
		}
		//根据手机号查找维修人员信息
		MaintainuserExample maintainuserExample = new MaintainuserExample();
		maintainuserExample.createCriteria()
			.andUsernameEqualTo(username).andIsdeleteEqualTo(0);
		List<Maintainuser> maintainuserList = 
				maintainuserMapper.selectByExample(maintainuserExample);
		//校验手机号是否重复
		if(maintainuserList.size() > 0) {
			apiResponsere.setCode("500");
			apiResponsere.setMsg("手机号重复");
			if(maintainuser == null) {
				return apiResponsere;
			}
			if(maintainuserList.size() > 1) {
				return apiResponsere;
			}
			if(!maintainuser.getMaintainid()
					.equals(maintainuserList.get(0).getMaintainid())) {
				return apiResponsere;
			}
			apiResponsere = new ApiResponse();
		}
		if(maintainuser == null) {
			isInsert = true;
			maintainuser = new Maintainuser();
			maintainuser.setMaintainid(UUID.randomUUID().toString());
			maintainuser.setPassword(DigestUtils.md5Hex("123456"));
			maintainuser.setIsdelete(0);
		}
		maintainuser.setUsername(username);
		maintainuser.setRealname(realname);
		maintainuser.setPhotourl(photourl);
		maintainuser.setRemark(remark);
		if(isInsert == true) {
			//maintainuserMapper.insert(maintainuser);
			maintainuserMapper.insertSelective(maintainuser);
			apiResponsere.setMsg("新增成功");
		} else {
			maintainuserMapper.updateByPrimaryKey(maintainuser);
			apiResponsere.setMsg("修改成功");
		}
		return apiResponsere;
	}
	
	public Maintainuser login(String username, String password) {
		password = DigestUtils.md5Hex(password);
		MaintainuserExample maintainuserExample = new MaintainuserExample();
		maintainuserExample.createCriteria()
			.andUsernameEqualTo(username)
			.andPasswordEqualTo(password)
			.andIsdeleteEqualTo(0);
		List<Maintainuser> maintainuserList = 
				maintainuserMapper.selectByExample(maintainuserExample);
		Maintainuser maintainuser = null;
		if(maintainuserList != null && maintainuserList.size() > 0) {
			maintainuser = maintainuserList.get(0);
		}
		return maintainuser;
	}
	
	public ApiResponse changePassword(
			String maintainid, String oldPassword,
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
		Maintainuser maintainuser = maintainuserMapper.selectByPrimaryKey(maintainid);
		if(maintainuser == null) {
			apiResponsere.setMsg("当前用户未找到，请重新登录！");
			return apiResponsere;
		}
		if(!maintainuser.getPassword().equals(oldPassword)) {
			apiResponsere.setMsg("原密码输入错误！");
			return apiResponsere;
		}
		maintainuser.setPassword(newPassword);
		maintainuserMapper.updateByPrimaryKey(maintainuser);
		apiResponsere.setCode("200");
		apiResponsere.setMsg("修改成功！");
		return apiResponsere;
	}
	
	public List<Maintainuser> listMaintainuser() {
		MaintainuserExample maintainuserExample = new MaintainuserExample();
		maintainuserExample.createCriteria().andIsdeleteEqualTo(0);
		List<Maintainuser> maintainusers = 
				maintainuserMapper.selectByExample(maintainuserExample);
		return maintainusers;
	}
	
	public MaintainuserWithCommentsQualifications getMaintainuserDetail(
			String openid, String maintainid) {
		Maintainuser maintainuser = maintainuserMapper.selectByPrimaryKey(maintainid);
		if(maintainuser == null) {
			return null;
		}
		boolean hasCommentsQualifications = 
				commentsService.hasCommentsQualifications(maintainid, openid);
		MaintainuserWithCommentsQualifications maintainuserWithCommentsQualifications =
				new MaintainuserWithCommentsQualifications(maintainuser);
		maintainuserWithCommentsQualifications
			.setHasCommentsQualifications(hasCommentsQualifications);
		return maintainuserWithCommentsQualifications;
	}
}
