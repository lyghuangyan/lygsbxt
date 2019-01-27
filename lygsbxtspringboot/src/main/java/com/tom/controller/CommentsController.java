package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tom.service.CommentsService;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@RestController
@RequestMapping("/comments")
public class CommentsController extends BaseController {
	@Autowired
	private CommentsService commentsService;
	
	/**
	 * 评论服务
	 * @param openid
	 * @param buildid
	 * @param content
	 * @param stars
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/commentBuilding")
	public ApiResponse commentBuilding(
			@RequestParam("openid") String openid,
			@RequestParam("buildid") String buildid,
			@RequestParam("content") String content,
			@RequestParam("stars") Integer stars) {
		ApiResponse apiResponse = 
				commentsService.commentBuilding(openid, buildid, content, stars);
		return apiResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pageComments")
	public ApiResponse pageComments(
			@RequestParam(value = "page", required = false, defaultValue = "1")
			Integer pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "10")
			Integer pageSize,
			@RequestParam(value = "relationid", required = false, defaultValue = "") 
			String relationid,
			@RequestParam(value = "orderid", required = false, defaultValue = "") 
			String orderid) {
		TomPage tomPage = commentsService.pageComments(
				pageNumber, pageSize, relationid, orderid);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(tomPage);
		return apiResponse;
	}
	
	/**
	 * 评论维修员
	 * @param openid
	 * @param maintainid
	 * @param content
	 * @param stars
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/commentMaintainuser")
	public ApiResponse commentMaintainuser(
			@RequestParam("openid") String openid,
			@RequestParam("maintainid") String maintainid,
			@RequestParam("content") String content,
			@RequestParam("stars") Integer stars) {
		ApiResponse apiResponse = 
				commentsService.commentMaintainuser(openid, maintainid, content, stars);
		return apiResponse;
	}
	
	/**
	 * 评论工单
	 * @param openid
	 * @param orderid
	 * @param content
	 * @param stars
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/commentOrder")
	public ApiResponse commentOrder(
			@RequestParam("openid") String openid,
			@RequestParam("orderid") String orderid,
			@RequestParam("content") String content,
			@RequestParam("stars") Integer stars) {
		ApiResponse apiResponse = 
				commentsService.commentOrder(openid, orderid, content, stars);
		return apiResponse;
	}
}
