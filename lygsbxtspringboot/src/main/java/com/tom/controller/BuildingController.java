package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.model.BuildingWithPictures;
import com.tom.service.BuildingService;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

/**
 * 建筑控制层
 * @author tom
 * @since 2018-11-30 10:00:07
 */
@RestController
@RequestMapping("/building")
public class BuildingController extends BaseController {
	@Autowired
	private BuildingService buildingService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/saveBuilding")
	public ApiResponse saveBuilding(
			@RequestParam(value = "buildid", required = false) String buildid,
			@RequestParam("type") String type,
			@RequestParam("name") String name,
			@RequestParam(value = "pictureurlList[]", required = false)
				String[] pictureurlList) {
		ApiResponse apiResponse = 
				buildingService.saveBuilding(buildid, type, name, pictureurlList);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageBuilding")
	public ApiResponse pageBuilding(
			@RequestParam("page") Integer pageNumber,
			@RequestParam("size") Integer pageSize,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "keyword", required = false) String keyword) {
		TomPage tomPage = 
				buildingService.pageBuilding(pageNumber, pageSize, type, keyword);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delBuilding")
	public ApiResponse delBuilding(@RequestParam("buildid") String buildid) {
		buildingService.delBuilding(buildid);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMsg("删除成功");
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getBuildingDetail")
	public ApiResponse getBuildingDetail(
			@RequestParam("buildid") String buildid,
			@RequestParam(value = "openid", required = false, defaultValue = "")
			String openid) {
		BuildingWithPictures buildingWithPictures =
				buildingService.getBuildingDetail(buildid, openid);
		ApiResponse apiResponse = new ApiResponse();
		if(buildingWithPictures == null) {
			apiResponse.setCode("500");
			apiResponse.setMsg("操作失败");
		} else {
			apiResponse.setData(buildingWithPictures);
		}
		return apiResponse;
	}
}
