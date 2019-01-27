package com.tom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.model.generator.Buildingtype;
import com.tom.service.BuildingtypeService;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@RestController
@RequestMapping("/buildingtype")
public class BuildingtypeController extends BaseController {
	@Autowired
	private BuildingtypeService buildingtypeService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/saveBuildingtype")
	public ApiResponse saveBuildingtype(
			@RequestParam(value = "typeid", required = false) String typeid,
			@RequestParam("typename") String typename,
			@RequestParam("sort") Integer sort) {
		ApiResponse apiResponse = 
				buildingtypeService.saveBuildingtype(typeid, typename, sort);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageBuildingtype")
	public ApiResponse pageBuildingtype(
			@RequestParam("page") Integer pageNumber,
			@RequestParam("size") Integer pageSize,
			@RequestParam(value = "keyword", required = false) String keyword) {
		TomPage tomPage = 
				buildingtypeService.pageBuildingtype(pageNumber, pageSize, keyword);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delBuildingtype")
	public ApiResponse delBuildingtype(@RequestParam("typeid") String typeid) {
		buildingtypeService.delBuildingtype(typeid);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMsg("删除成功");
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getBuildingtypeList")
	public ApiResponse getBuildingtypeList() {
		List<Buildingtype> buildingtypeList =
				buildingtypeService.listBuildingtype();
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(buildingtypeList);
		return apiResponse;
	}
}
