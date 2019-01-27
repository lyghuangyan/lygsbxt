package com.tom.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tom.dao.generator.BuildingtypeMapper;
import com.tom.model.generator.Buildingtype;
import com.tom.model.generator.BuildingtypeExample;
import com.tom.model.generator.BuildingtypeExample.Criteria;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@Service("buildingtypeService")
public class BuildingtypeService {
	@Autowired
	private BuildingtypeMapper buildingtypeMapper;
	
	public void delBuildingtype(String typeid) {
		Buildingtype buildingtype = buildingtypeMapper.selectByPrimaryKey(typeid);
		if(buildingtype != null) {
			buildingtype.setIsdelete(1);
			buildingtypeMapper.updateByPrimaryKey(buildingtype);
		}
	}
	
	public TomPage pageBuildingtype(
			Integer pageNumber, Integer pageSize, String keyword) {
		BuildingtypeExample buildingtypeExample = new BuildingtypeExample();
		Criteria criteria = buildingtypeExample.createCriteria();
		if(StringUtils.isNotBlank(keyword)) {
			criteria.andTypenameLike("%"+keyword+"%");
		}
		criteria.andIsdeleteEqualTo(0);
		buildingtypeExample.setOrderByClause(" sort ");
		Page<Buildingtype> page = PageHelper.startPage(pageNumber, pageSize);
		buildingtypeMapper.selectByExample(buildingtypeExample);
		TomPage tomPage = new TomPage(page);
		return tomPage;
	}
	
	@SuppressWarnings("rawtypes")
	public synchronized ApiResponse saveBuildingtype(
			String typeid, String typename, Integer sort) {
		ApiResponse apiResponsere = new ApiResponse();
		//校验数据完整性
		if(StringUtils.isBlank(typename) 
				|| sort == null) {
			apiResponsere.setCode("500");
			apiResponsere.setMsg("请将信息填写完整");
			return apiResponsere;
		}
		boolean isInsert = false;
		Buildingtype buildingtype = null;
		//获取当前维修人员原信息
		if(StringUtils.isNotBlank(typeid)) {
			buildingtype = buildingtypeMapper.selectByPrimaryKey(typeid);
		}
		//服务分类名不能重复
		BuildingtypeExample buildingtypeExample = new BuildingtypeExample();
		buildingtypeExample.createCriteria()
			.andTypenameEqualTo(typename).andIsdeleteEqualTo(0);
		List<Buildingtype> buildingtypeList = 
				buildingtypeMapper.selectByExample(buildingtypeExample);
		//校验手机号是否重复
		if(buildingtypeList.size() > 0) {
			apiResponsere.setCode("500");
			apiResponsere.setMsg("手机号重复");
			if(buildingtype == null) {
				return apiResponsere;
			}
			if(buildingtypeList.size() > 1) {
				return apiResponsere;
			}
			if(!buildingtype.getTypeid()
					.equals(buildingtypeList.get(0).getTypeid())) {
				return apiResponsere;
			}
			apiResponsere = new ApiResponse();
		}
		if(buildingtype == null) {
			isInsert = true;
			buildingtype = new Buildingtype();
			buildingtype.setTypeid(UUID.randomUUID().toString());
			buildingtype.setIsdelete(0);
		}
		buildingtype.setTypename(typename);
		buildingtype.setSort(sort);
		if(isInsert == true) {
			buildingtypeMapper.insertSelective(buildingtype);
			apiResponsere.setMsg("新增成功");
		} else {
			buildingtypeMapper.updateByPrimaryKey(buildingtype);
			apiResponsere.setMsg("修改成功");
		}
		return apiResponsere;
	}
	
	public List<Buildingtype> listBuildingtype() {
		BuildingtypeExample buildingtypeExample = new BuildingtypeExample();
		buildingtypeExample.createCriteria().andIsdeleteEqualTo(0);
		buildingtypeExample.setOrderByClause(" sort ");
		List<Buildingtype> buildingtypeList = 
				buildingtypeMapper.selectByExample(buildingtypeExample);
		return buildingtypeList;
	}
	
	public Buildingtype getBuildingtypeByTypeid(String typeid) {
		Buildingtype buildingtype = buildingtypeMapper.selectByPrimaryKey(typeid);
		return buildingtype;
	}
}
