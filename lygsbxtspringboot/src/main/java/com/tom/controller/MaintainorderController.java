package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.model.MaintainorderWithPicturesAndMaintainuser;
import com.tom.service.MaintainorderService;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@RestController
@RequestMapping("/maintainorder")
public class MaintainorderController extends BaseController {
	@Autowired
	private MaintainorderService maintainorderService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/submitMaintainorder")
	public ApiResponse submitMaintainorder(
			@RequestParam(value = "orderid", required = false) String orderid,
			@RequestParam("openid") String openid,
			@RequestParam("title") String title,
			@RequestParam("address") String address,
			@RequestParam("content") String content,
			@RequestParam(value = "pictureurls[]", required = false)
			String[] pictureurls) {
		ApiResponse apiResponse =  maintainorderService.submitMaintainorder(
				orderid,openid, title, address, content, pictureurls);
		return apiResponse;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getMaintainorderDetail")
	public ApiResponse getMaintainorderDetail(
			@RequestParam("orderid") String orderid) {
		MaintainorderWithPicturesAndMaintainuser 
			maintainorderWithPicturesAndMaintainuser =
					maintainorderService.getMaintainorderDetail(orderid);
		ApiResponse apiResponse = new ApiResponse();
		if(maintainorderWithPicturesAndMaintainuser != null) {
			apiResponse.setData(maintainorderWithPicturesAndMaintainuser);
		} else {
			apiResponse.setCode("500");
			apiResponse.setMsg("操作失败");
		}
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delMaintainorder")
	public ApiResponse delMaintainorder(@RequestParam("orderid") String orderid) {
		ApiResponse apiResponse = maintainorderService.delMaintainorder(orderid);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageWxuserMaintainorder")
	public ApiResponse pageWxuserMaintainorder(
			@RequestParam(value = "page", required = false, defaultValue = "1")
			Integer pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "10")
			Integer pageSize,
			@RequestParam("openid") String openid,
			@RequestParam(value = "status", required = false, defaultValue = "")
			String status,
			@RequestParam(value = "days", required = false, defaultValue = "-1")
			Integer days) {
		TomPage tomPage = maintainorderService.pageWxuserMaintainorder(
				openid, status, days, pageNumber, pageSize);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageOfficeuserMaintainorder")
	public ApiResponse pageOfficeuserMaintainorder(
			@RequestParam(value = "page", required = false, defaultValue = "1")
			Integer pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "10")
			Integer pageSize,
			@RequestParam("status") String status) {
		TomPage tomPage = 
				maintainorderService.pageOfficeuserMaintainorder(
						status, pageNumber, pageSize);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/assignMaintainorder")
	public ApiResponse assignMaintainorder(
			@RequestParam("orderid") String orderid,
			@RequestParam("officerid") String officerid,
			@RequestParam("maintainid") String maintainid) {
		ApiResponse apiResponse = 
				maintainorderService.assignMaintainorder(orderid, officerid, maintainid);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageMaintainuserMaintainorder")
	public ApiResponse pageMaintainuserMaintainorder(
			@RequestParam(value = "page", required = false, defaultValue = "1")
			Integer pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "10")
			Integer pageSize,
			@RequestParam("status") String status,
			@RequestParam("maintainid") String maintainid) {
		TomPage tomPage = 
				maintainorderService.pageMaintainuserMaintainorder(
						maintainid, status, pageNumber, pageSize);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/completeMaintainorder")
	public ApiResponse completeMaintainorder(
			@RequestParam("maintainid") String maintainid,
			@RequestParam("orderid") String orderid,
			@RequestParam(value = "charge", required = false, defaultValue = "0")
			Float charge) {
		ApiResponse apiResponse =
				maintainorderService.completeMaintainorder(
				        maintainid, orderid,charge);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageWebMaintainorder")
	public ApiResponse pageWebMaintainorder(
			@RequestParam(value = "page", required = false, defaultValue = "1")
			Integer pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "10")
			Integer pageSize,
			@RequestParam("status") String status,
			@RequestParam("maintainid") String maintainid) {
		TomPage tomPage = 
				maintainorderService.pageWebMaintainorder(
						maintainid, status, pageNumber, pageSize);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	/**
	 * 获取报表首页十个工单信息
	 * @param state
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/maintainorderListReportIndex")
	public ApiResponse maintainorderListReportIndex(
			@RequestParam(value = "page", required = false, defaultValue = "1")
			Integer pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "10")
			Integer pageSize,
			@RequestParam(value = "state", required = false, defaultValue = "") 
			String state) {
		String status = "";
		switch(state) {
		case "待维修" : status = "未派单"; break;
		case "维修中" : status = "维修中"; break;
		case "待评价" : status = "待评价"; break;
		case "已完成" : status = "已完成"; break;
		default : status = "all";
		}
		TomPage tomPage = 
				maintainorderService.pageWebMaintainorder(
						"all", status, pageNumber, pageSize);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	/**
     * @param state
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/maintainorderListReportIndexPage")
    public ApiResponse maintainorderListReportIndexPage(
            @RequestParam(value = "page", required = false, defaultValue = "1")
            Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10")
            Integer pageSize,
            @RequestParam(value = "state", required = false, defaultValue = "") 
            String state,
            @RequestParam(value = "keyword", required = false, defaultValue = "") 
            String keyword,
            @RequestParam(value = "repairMan", required = false, defaultValue = "") 
            String repairMan,
            @RequestParam(value = "maintain", required = false, defaultValue = "") 
            String maintain) {
        String status = "";
        switch(state) {
        case "待维修" : status = "未派单"; break;
        case "维修中" : status = "维修中"; break;
        case "待评价" : status = "待评价"; break;
        case "已完成" : status = "已完成"; break;
        default : status = "all";
        }
        TomPage tomPage = 
                maintainorderService
                    .maintainorderListReportIndexPage(
                            status, pageNumber, pageSize, keyword, repairMan, maintain);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(tomPage);
        return apiResponse;
    }
}
