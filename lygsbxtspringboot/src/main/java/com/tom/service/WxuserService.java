package com.tom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.dao.generator.WxuserMapper;
import com.tom.model.BuildingWithCommentQualifications;
import com.tom.model.BuildingtypeWithBuilding;
import com.tom.model.MaintainuserWithCommentsQualifications;
import com.tom.model.generator.Building;
import com.tom.model.generator.Buildingtype;
import com.tom.model.generator.Maintainuser;
import com.tom.model.generator.Wxuser;

@Service("wxuserService")
public class WxuserService {
	@Autowired
	private WxuserMapper wxuserMapper;
	@Autowired
	private BuildingtypeService buildingtypeService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private MaintainuserService maintainuserService;
	@Autowired
	private CommentsService commentsService;
	
	public boolean saveWxuser(String openid, String nickName, String avatarUrl) {
		if(StringUtils.isBlank(openid)) {
			return false;
		}
		Wxuser wxuser = wxuserMapper.selectByPrimaryKey(openid);
		if(wxuser == null) {
			if(StringUtils.isBlank(nickName) || StringUtils.isBlank(avatarUrl)) {
				return false;
			}
			wxuser = new Wxuser();
			wxuser.setOpenid(openid);
			wxuser.setHeadurl(avatarUrl);
			wxuser.setNickname(nickName);
			wxuserMapper.insertSelective(wxuser);
		} else {
			if(StringUtils.isNotBlank(nickName)) {
				wxuser.setNickname(nickName);
			}
			if(StringUtils.isNotBlank(avatarUrl)) {
				wxuser.setHeadurl(avatarUrl);
			}
			wxuserMapper.updateByPrimaryKeySelective(wxuser);
		}
		return true;
	}
	
	/**
	 * 获取普通用户首页展示的数据
	 * @param openid	普通用户的openid
	 * @return
	 */
	public Map<String, Object> wxuserIndex(String openid) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<BuildingtypeWithBuilding> buildingtypeWithBuildings =
				new ArrayList<BuildingtypeWithBuilding>();
		List<Buildingtype> buildingtypes = buildingtypeService.listBuildingtype();
		for(Buildingtype buildingtype : buildingtypes) {
			List<Building> buildings = 
					buildingService.getBuildingsByTypeid(buildingtype.getTypeid());
			List<BuildingWithCommentQualifications>
				buildingWithCommentQualificationsList = 
					new ArrayList<BuildingWithCommentQualifications>();
			for(Building building: buildings) {
				BuildingWithCommentQualifications buildingWithCommentQualifications =
						new BuildingWithCommentQualifications(building);
				//判断当前用户是否有评论该服务的资格：每年三月一号和九月一号刷新评论资格
				boolean hasCommentsQualifications =
						commentsService.hasCommentsQualifications(
								building.getBuildid(), openid);
				buildingWithCommentQualifications
					.setHasCommentsQualifications(hasCommentsQualifications);
				buildingWithCommentQualificationsList
					.add(buildingWithCommentQualifications);
			}
			BuildingtypeWithBuilding buildingtypeWithBuilding =
					new BuildingtypeWithBuilding(buildingtype);
			//buildingtypeWithBuilding.setBuildings(buildings);
			buildingtypeWithBuilding
				.setBuildingWithCommentQualificationsList(
						buildingWithCommentQualificationsList);
			buildingtypeWithBuildings.add(buildingtypeWithBuilding);
		}
		result.put("buildingtypeWithBuildings", buildingtypeWithBuildings);
		List<Maintainuser> maintainusers = maintainuserService.listMaintainuser();
		List<MaintainuserWithCommentsQualifications> 
			maintainuserWithCommentsQualificationsList =
				new ArrayList<MaintainuserWithCommentsQualifications>();
		for(Maintainuser maintainuser : maintainusers) {
			MaintainuserWithCommentsQualifications 
				maintainuserWithCommentsQualifications =
					new MaintainuserWithCommentsQualifications(maintainuser);
			//判断当前用户是否有评论该维修人员的资格：每年三月一号和九月一号刷新评论资格
			boolean hasCommentsQualifications =
					commentsService.hasCommentsQualifications(
							maintainuser.getMaintainid(), openid);
			maintainuserWithCommentsQualifications
				.setHasCommentsQualifications(hasCommentsQualifications);
			maintainuserWithCommentsQualificationsList
				.add(maintainuserWithCommentsQualifications);
		}
		result.put("maintainuserWithCommentsQualificationsList",
				maintainuserWithCommentsQualificationsList);
		return result;
	}
	
	public Wxuser getWxuserInfo(String openid) {
		Wxuser wxuser = wxuserMapper.selectByPrimaryKey(openid);
		return wxuser;
	}
}
