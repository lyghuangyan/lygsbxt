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
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tom.dao.generator.BuildingMapper;
import com.tom.dao.generator.PicturesMapper;
import com.tom.model.BuildingWithPictures;
import com.tom.model.generator.Building;
import com.tom.model.generator.BuildingExample;
import com.tom.model.generator.BuildingExample.Criteria;
import com.tom.model.generator.Buildingtype;
import com.tom.model.generator.Pictures;
import com.tom.model.generator.PicturesExample;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

/**
 * 建筑service（食堂、宿管等）
 * @author tom
 * @since 2018-11-30 09:44:03
 */
@Service("buildingService")
public class BuildingService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private PicturesMapper picturesMapper;
	@Autowired
	private BuildingtypeService buildingtypeService;
	@Autowired
	private CommentsService commentsService;
	
	public void delBuilding(String buildid) {
		Building building = buildingMapper.selectByPrimaryKey(buildid);
		if(building != null) {
			building.setIsdelete(1);
			buildingMapper.updateByPrimaryKey(building);
		}
	}
	
	public TomPage pageBuilding(
			Integer pageNumber, Integer pageSize, String type, String keyword) {
		BuildingExample buildingExample = new BuildingExample();
		Criteria criteria = buildingExample.createCriteria();
		if(StringUtils.isNotBlank(type)&&!"all".equals(type)) {
			criteria.andTypeEqualTo(type);
		}
		if(StringUtils.isNotBlank(keyword)) {
			criteria.andNameLike("%"+keyword+"%");
		}
		criteria.andIsdeleteEqualTo(0);
		List<Buildingtype> buildingtypeList = buildingtypeService.listBuildingtype();
		List<String> typeidList = new ArrayList<String>();
		buildingtypeList.forEach(buildingtype -> typeidList.add(buildingtype.getTypeid()));
		criteria.andTypeIn(typeidList);
		Page<Building> page = PageHelper.startPage(pageNumber, pageSize);
		buildingMapper.selectByExample(buildingExample);
		List<Building> buildingList = page.getResult();
		List<BuildingWithPictures> buildingWithPicturesList = 
				new ArrayList<BuildingWithPictures>();
		//查找建筑所有图片
		for(Building building : buildingList) {
			PicturesExample picturesExample = new PicturesExample();
			picturesExample.createCriteria()
				.andRelationidEqualTo(building.getBuildid())
				.andIsdeleteEqualTo(0);
			List<Pictures> picturesList = picturesMapper.selectByExample(picturesExample);
			List<String> pictureurls = new ArrayList<String>();
			for(Pictures pictures : picturesList) {
				pictureurls.add(pictures.getPictureurl());
			}
			BuildingWithPictures buildingWithPictures =
					new BuildingWithPictures(building);
			buildingWithPictures.setPictureurls(pictureurls);
			Buildingtype buildingtype = 
					buildingtypeService.getBuildingtypeByTypeid(building.getType());
			if(buildingtype != null) {
				buildingWithPictures.setTypename(buildingtype.getTypename());
			}
			buildingWithPicturesList.add(buildingWithPictures);
		}
		TomPage tomPage = 
			new TomPage(page, buildingWithPicturesList);
		return tomPage;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public ApiResponse saveBuilding(
			String buildid, String type, String name, String[] pictureurlList) {
		ApiResponse apiResponsere = new ApiResponse();
		//校验数据完整性
		if(StringUtils.isBlank(type) 
				|| StringUtils.isBlank(name) 
				|| pictureurlList == null 
				|| pictureurlList.length == 0) {
			apiResponsere.setCode("500");
			apiResponsere.setMsg("请将信息填写完整");
			return apiResponsere;
		}
		boolean isInsert = false;
		Building building = null;
		//获取建筑物信息、删除原图片
		if(StringUtils.isNotBlank(buildid)) {
			building = buildingMapper.selectByPrimaryKey(buildid);
			PicturesExample picturesExample = new PicturesExample();
			picturesExample.createCriteria()
				.andRelationidEqualTo(buildid).andIsdeleteEqualTo(0);
			List<Pictures> delPictures = picturesMapper.selectByExample(picturesExample);
			for(Pictures delPicture : delPictures) {
				delPicture.setIsdelete(1);
				picturesMapper.updateByPrimaryKey(delPicture);
			}
		}
		
		if(building == null) {
			isInsert = true;
			building = new Building();
			building.setBuildid(UUID.randomUUID().toString());
			building.setIsdelete(0);
		}
		building.setType(type);
		building.setName(name);
		building.setFirstpicture(pictureurlList[0]);
		//插入pictures
		int pictureCount = 0;
		for(String pictureurl : pictureurlList) {
			pictureCount++;
			//建筑图片数量限制6个
			if(pictureCount > 6) {
				break;
			}
			Pictures pictures = new Pictures();
			pictures.setPictureid(UUID.randomUUID().toString());
			pictures.setPictureurl(pictureurl);
			pictures.setRelationid(building.getBuildid());
			pictures.setIsdelete(0);
			picturesMapper.insert(pictures);
		}
		//保存building
		if(isInsert == true) {
			buildingMapper.insertSelective(building);
			apiResponsere.setMsg("新增成功");
		} else {
			buildingMapper.updateByPrimaryKey(building);
			apiResponsere.setMsg("修改成功");
		}
		return apiResponsere;
	}
	
	public List<Building> getBuildingsByTypeid(String typeid) {
		BuildingExample buildingExample = new BuildingExample();
		buildingExample.createCriteria().andIsdeleteEqualTo(0).andTypeEqualTo(typeid);
		List<Building> buildings = buildingMapper.selectByExample(buildingExample);
		return buildings;
	}
	
	public BuildingWithPictures getBuildingDetail(
			String buildid, String openid) {
		BuildingWithPictures buildingWithPictures = null;
		Building building = buildingMapper.selectByPrimaryKey(buildid);
		if(building != null) {
			PicturesExample picturesExample = new PicturesExample();
			picturesExample.createCriteria()
				.andRelationidEqualTo(building.getBuildid())
				.andIsdeleteEqualTo(0);
			List<Pictures> picturesList = picturesMapper.selectByExample(picturesExample);
			List<String> pictureurls = new ArrayList<String>();
			for(Pictures pictures : picturesList) {
				pictureurls.add(pictures.getPictureurl());
			}
			buildingWithPictures =
					new BuildingWithPictures(building);
			buildingWithPictures.setPictureurls(pictureurls);
			Buildingtype buildingtype = 
					buildingtypeService.getBuildingtypeByTypeid(building.getType());
			if(buildingtype != null) {
				buildingWithPictures.setTypename(buildingtype.getTypename());
			}
			boolean hasCommentsQualifications = 
					commentsService.hasCommentsQualifications(buildid, openid);
			buildingWithPictures
				.setHasCommentsQualifications(hasCommentsQualifications);
		}
		return buildingWithPictures;
	}
}
