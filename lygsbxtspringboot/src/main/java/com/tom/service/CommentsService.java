package com.tom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.org.eclipse.jdt.internal.compiler.util.FloatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tom.dao.generator.BuildingMapper;
import com.tom.dao.generator.CommentsMapper;
import com.tom.dao.generator.MaintainorderMapper;
import com.tom.dao.generator.MaintainuserMapper;
import com.tom.dao.generator.WxuserMapper;
import com.tom.model.CommentsWithWxuser;
import com.tom.model.generator.Building;
import com.tom.model.generator.Comments;
import com.tom.model.generator.CommentsExample;
import com.tom.model.generator.Maintainorder;
import com.tom.model.generator.Maintainuser;
import com.tom.model.generator.Wxuser;
import com.tom.util.ApiResponse;
import com.tom.util.TomDateUtil;
import com.tom.util.TomFloatUtil;
import com.tom.util.TomPage;

/**
 * 评论业务层
 * @author tom
 * @since 2018-12-15 20:02:16
 */
@Service("commentsService")
public class CommentsService {
	@Autowired
	private CommentsMapper commentsMapper;
	@Autowired
	private WxuserMapper wxuserMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private MaintainuserMapper maintainuserMapper;
	@Autowired
	private MaintainorderMapper maintainorderMapper;
	
	public synchronized ApiResponse commentBuilding(
			String openid, String buildid, String content, Integer stars) {
		ApiResponse apiResponse = new ApiResponse();
		
		if(StringUtils.isBlank(openid) 
				|| StringUtils.isBlank(buildid)
				|| StringUtils.isBlank(content)
				|| stars == null) {
			apiResponse.setCode("400");
			apiResponse.setMsg("请检查参数不能为空！");
			return apiResponse;
		}
		Building building = buildingMapper.selectByPrimaryKey(buildid);
		Wxuser wxuser = wxuserMapper.selectByPrimaryKey(openid);
		if(building == null || wxuser == null) {
			apiResponse.setCode("500");
			apiResponse.setMsg("用户或服务信息未找到！");
			return apiResponse;
		}
		//判断是否有资格评论
		if(this.hasCommentsQualifications(buildid, openid) == false) {
			apiResponse.setCode("500");
			apiResponse.setMsg("您已评论，每年三月一号和九月一号刷新评论资格！");
			return apiResponse;
		}
		Comments comments = new Comments();
		comments.setCommentid(UUID.randomUUID().toString());
		comments.setContent(content);
		comments.setCreatetime(new Date());
		comments.setIsdelete(0);
		comments.setRelationid(buildid);
		comments.setStars(stars);
		comments.setWxuserid(openid);
		this.commentBuilding(building, comments);
		apiResponse.setCode("200");
		apiResponse.setMsg("评论成功！");
		return apiResponse;
	}
	
	@Transactional
	public boolean commentBuilding(Building building, Comments comments) {
		building.setCommentcount(building.getCommentcount() + 1);
		building.setAllstars(building.getAllstars() + comments.getStars());
		building.setAveragestars(
				TomFloatUtil.division(
						building.getAllstars(), building.getCommentcount()));
		buildingMapper.updateByPrimaryKeySelective(building);
		commentsMapper.insertSelective(comments);
		return true;
	}
	
	/**
	 * 查询是否有评论资格
	 * @param relationid
	 * @param wxuserid
	 * @return
	 */
	public boolean hasCommentsQualifications(String relationid, String wxuserid) {
		CommentsExample commentsExample = new CommentsExample();
		commentsExample.createCriteria()
			.andRelationidEqualTo(relationid)
			.andWxuseridEqualTo(wxuserid)
			.andOrderidIsNull()
			.andIsdeleteEqualTo(0);
		commentsExample.setOrderByClause(" createtime desc ");
		List<Comments> commentsList = commentsMapper.selectByExample(commentsExample);
		if(commentsList != null && commentsList.size() > 0) {
			Comments oldComments = commentsList.get(0);
			if(TomDateUtil.isAbleComment(oldComments.getCreatetime()) == false) {
				//没有资格评论
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
	
	
	public TomPage pageComments(
			Integer pageNumber, Integer pageSize, String relationid, String orderid) {
		CommentsExample commentsExample = new CommentsExample();
		if(StringUtils.isNotBlank(relationid)) {
			commentsExample.createCriteria()
			.andRelationidEqualTo(relationid)
			.andIsdeleteEqualTo(0);
		} else if(StringUtils.isNotBlank(orderid)) {
			commentsExample.createCriteria()
			.andOrderidEqualTo(orderid)
			.andIsdeleteEqualTo(0);
		} else {
			commentsExample.createCriteria()
			.andRelationidEqualTo(relationid)
			.andIsdeleteEqualTo(0);
		}
		
		commentsExample.setOrderByClause(" createtime desc ");
		Page<Comments> page = PageHelper.startPage(pageNumber, pageSize);
		List<Comments> commentsList = commentsMapper.selectByExample(commentsExample);
		List<CommentsWithWxuser> commentsWithWxuserList = 
				new ArrayList<CommentsWithWxuser>();
		for(Comments comments : commentsList) {
			Wxuser wxuser = wxuserMapper.selectByPrimaryKey(comments.getWxuserid());
			CommentsWithWxuser commentsWithWxuser = new CommentsWithWxuser(comments);
			commentsWithWxuser.setWxuser(wxuser);
			commentsWithWxuserList.add(commentsWithWxuser);
		}
		TomPage tomPage = new TomPage(page, commentsWithWxuserList);
		return tomPage;
	}
	
	public synchronized ApiResponse commentMaintainuser(
			String openid, String maintainid, String content, Integer stars) {
		ApiResponse apiResponse = new ApiResponse();
		
		if(StringUtils.isBlank(openid) 
				|| StringUtils.isBlank(maintainid)
				|| StringUtils.isBlank(content)
				|| stars == null) {
			apiResponse.setCode("400");
			apiResponse.setMsg("请检查参数不能为空！");
			return apiResponse;
		}
		Maintainuser maintainuser = maintainuserMapper.selectByPrimaryKey(maintainid);
		Wxuser wxuser = wxuserMapper.selectByPrimaryKey(openid);
		if(maintainuser == null || wxuser == null) {
			apiResponse.setCode("500");
			apiResponse.setMsg("用户或维修员信息未找到！");
			return apiResponse;
		}
		//判断是否有资格评论
		if(this.hasCommentsQualifications(maintainid, openid) == false) {
			apiResponse.setCode("500");
			apiResponse.setMsg("您已评论，每年三月一号和九月一号刷新评论资格！");
			return apiResponse;
		}
		Comments comments = new Comments();
		comments.setCommentid(UUID.randomUUID().toString());
		comments.setContent(content);
		comments.setCreatetime(new Date());
		comments.setIsdelete(0);
		comments.setRelationid(maintainid);
		comments.setStars(stars);
		comments.setWxuserid(openid);
		this.commentMaintainuser(maintainuser, comments);
		apiResponse.setCode("200");
		apiResponse.setMsg("评论成功！");
		return apiResponse;
	}
	
	@Transactional
	public boolean commentMaintainuser(Maintainuser maintainuser, Comments comments) {
		maintainuser.setCommentcount(maintainuser.getCommentcount() + 1);
		maintainuser.setAllstars(maintainuser.getAllstars() + comments.getStars());
		maintainuser.setAveragestars(
				TomFloatUtil.division(
						maintainuser.getAllstars(), maintainuser.getCommentcount()));
		maintainuserMapper.updateByPrimaryKeySelective(maintainuser);
		commentsMapper.insertSelective(comments);
		return true;
	}
	
	public synchronized ApiResponse commentOrder(
			String openid, String orderid, String content, Integer stars) {
		ApiResponse apiResponse = new ApiResponse();
		
		if(StringUtils.isBlank(openid) 
				|| StringUtils.isBlank(orderid)
				|| StringUtils.isBlank(content)
				|| stars == null) {
			apiResponse.setCode("400");
			apiResponse.setMsg("请检查参数不能为空！");
			return apiResponse;
		}
		Maintainorder maintainorder = 
				maintainorderMapper.selectByPrimaryKey(orderid);
		Wxuser wxuser = wxuserMapper.selectByPrimaryKey(openid);
		if(maintainorder == null || wxuser == null) {
			apiResponse.setCode("500");
			apiResponse.setMsg("用户或工单信息未找到！");
			return apiResponse;
		}
		//判断是否有资格评论
		if(maintainorder.getIsdelete() != 0 
				|| !"待评价".equals(maintainorder.getStatus())) {
			apiResponse.setCode("500");
			apiResponse.setMsg("工单状态异常，评论失败！");
			return apiResponse;
		}
		if(StringUtils.isBlank(maintainorder.getMaintainid())) {
			apiResponse.setCode("500");
			apiResponse.setMsg("维修员信息未找到，评论失败！");
			return apiResponse;
		}
		Maintainuser maintainuser = 
				maintainuserMapper.selectByPrimaryKey(maintainorder.getMaintainid());
		if(maintainuser == null) {
			apiResponse.setCode("500");
			apiResponse.setMsg("维修员信息未找到，评论失败！");
			return apiResponse;
		}
		Comments comments = new Comments();
		comments.setCommentid(UUID.randomUUID().toString());
		comments.setContent(content);
		comments.setCreatetime(new Date());
		comments.setIsdelete(0);
		comments.setRelationid(maintainorder.getMaintainid());
		comments.setOrderid(orderid);
		comments.setStars(stars);
		comments.setWxuserid(openid);
		this.commentOrder(maintainuser, maintainorder, comments);
		apiResponse.setCode("200");
		apiResponse.setMsg("评论成功！");
		return apiResponse;
	}
	
	@Transactional
	public boolean commentOrder(
			Maintainuser maintainuser, Maintainorder maintainorder, Comments comments) {
		maintainuser.setCommentcount(maintainuser.getCommentcount() + 1);
		maintainuser.setAllstars(maintainuser.getAllstars() + comments.getStars());
		maintainuser.setAveragestars(
				TomFloatUtil.division(
						maintainuser.getAllstars(), maintainuser.getCommentcount()));
		maintainuserMapper.updateByPrimaryKeySelective(maintainuser);
		commentsMapper.insertSelective(comments);
		maintainorder.setStatus("已完成");
		maintainorder.setCommenttime(new Date());
		maintainorderMapper.updateByPrimaryKeySelective(maintainorder);
		return true;
	}
}
