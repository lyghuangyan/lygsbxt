package com.tom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tom.dao.generator.CommentsMapper;
import com.tom.dao.generator.MaintainorderMapper;
import com.tom.dao.generator.MaintainuserMapper;
import com.tom.dao.generator.OfficeuserMapper;
import com.tom.dao.generator.PicturesMapper;
import com.tom.dao.generator.WxuserMapper;
import com.tom.model.CommentsWithWxuser;
import com.tom.model.MaintainorderMaintainuser;
import com.tom.model.MaintainorderWithPicturesAndMaintainuser;
import com.tom.model.generator.Building;
import com.tom.model.generator.Comments;
import com.tom.model.generator.CommentsExample;
import com.tom.model.generator.Maintainorder;
import com.tom.model.generator.MaintainorderExample;
import com.tom.model.generator.MaintainorderExample.Criteria;
import com.tom.model.generator.Maintainuser;
import com.tom.model.generator.MaintainuserExample;
import com.tom.model.generator.Officeuser;
import com.tom.model.generator.Pictures;
import com.tom.model.generator.PicturesExample;
import com.tom.model.generator.Wxuser;
import com.tom.model.generator.WxuserExample;
import com.tom.util.ApiResponse;
import com.tom.util.DateUtil;
import com.tom.util.TomDateUtil;
import com.tom.util.TomPage;

@Service("maintainorderService")
public class MaintainorderService {
	@Autowired
	private MaintainorderMapper maintainorderMapper;
	@Autowired
	private WxuserService wxuserService;
	@Autowired
	private WxuserMapper wxuserMapper;
	@Autowired
	private PicturesMapper picturesMapper;
	@Autowired
	private MaintainuserMapper maintainuserMapper;
	@Autowired
	private OfficeuserMapper officeuserMapper;
	@Autowired
	private CommentsMapper commentsMapper;
	
	/**
	 * 提交工单--悲观锁
	 * @param orderid
	 * @param openid
	 * @param title
	 * @param address
	 * @param content
	 * @param pictureurls
	 * @return
	 */
	public synchronized ApiResponse submitMaintainorder(
			String orderid,
			String openid, String title, String address, String content,
			String[] pictureurls) {
		ApiResponse apiResponse = new ApiResponse();
		if(StringUtils.isBlank(openid)
				|| StringUtils.isBlank(title)
				|| StringUtils.isBlank(address)
				|| StringUtils.isBlank(content)) {
			apiResponse.setCode("400");
			apiResponse.setMsg("信息提交不完整，请完善后再提交！");
			return apiResponse;
		}
		if(pictureurls != null && pictureurls.length > 6) {
			apiResponse.setCode("400");
			apiResponse.setMsg("图片最多上传6涨，请筛选后再提交！");
			return apiResponse;
		}
		Wxuser wxuser = wxuserService.getWxuserInfo(openid);
		if(wxuser == null) {
			apiResponse.setCode("500");
			apiResponse.setMsg("openid不正确！");
			return apiResponse;
		}
		//限定一分钟内只能提交一次工单
		Date beforeDate = TomDateUtil.beforeDateByMinute(-1);
		MaintainorderExample maintainorderExample = new MaintainorderExample();
		maintainorderExample.createCriteria().andWxuseridEqualTo(openid)
			.andIsdeleteEqualTo(0).andCreatetimeGreaterThanOrEqualTo(beforeDate);
		List<Maintainorder> maintainorders = 
				maintainorderMapper.selectByExample(maintainorderExample);
		if(maintainorders != null && maintainorders.size() > 0) {
			apiResponse.setCode("500");
			apiResponse.setMsg("一分钟内只能提交一次报修工单！");
			return apiResponse;
		}
		Maintainorder maintainorder = null;
		if(StringUtils.isNotBlank(orderid)) {
			maintainorder = maintainorderMapper.selectByPrimaryKey(orderid);
		}
		boolean isInsert = false;
		if(maintainorder == null) {
			isInsert = true;
			maintainorder = new Maintainorder();
			maintainorder.setOrderid(DateUtil.getSmoothMilliTime());
			maintainorder.setIsdelete(0);
			maintainorder.setCreatetime(new Date());
			maintainorder.setStatus("未派单");
		}
		if(!"未派单".equals(maintainorder.getStatus())) {
			apiResponse.setCode("500");
			apiResponse.setMsg("工单已派发，不可修改！");
			return apiResponse;
		}
		maintainorder.setAddress(address);
		maintainorder.setContent(content);
		maintainorder.setTitle(title);
		maintainorder.setWxuserid(openid);
		this.submitMaintainorder(maintainorder, pictureurls, isInsert);
		return apiResponse;
	}
	
	/**
	 * 提交工单--事务
	 * @param maintainorder
	 * @param pictureurls
	 * @param isInsert
	 */
	@Transactional
	public void submitMaintainorder(
			Maintainorder maintainorder,
			String[] pictureurls,
			boolean isInsert) {
		if(isInsert == true) {
			maintainorderMapper.insertSelective(maintainorder);
		} else {
			maintainorderMapper.updateByPrimaryKeySelective(maintainorder);
		}
		
		PicturesExample picturesExample = new PicturesExample();
		picturesExample.createCriteria().andIsdeleteEqualTo(0)
			.andRelationidEqualTo(maintainorder.getOrderid());
		List<Pictures> picturesList = picturesMapper.selectByExample(picturesExample);
		//删除旧图片
		if(picturesList != null && picturesList.size() > 0) {
			for(Pictures oldPictures : picturesList) {
				oldPictures.setIsdelete(1);
				picturesMapper.updateByPrimaryKeySelective(oldPictures);
			}
		}
		//新增新图片
		if(pictureurls != null) {
			for(String pictureurl : pictureurls) {
				Pictures pictures = new Pictures();
				pictures.setIsdelete(0);
				pictures.setPictureid(UUID.randomUUID().toString());
				pictures.setPictureurl(pictureurl);
				pictures.setRelationid(maintainorder.getOrderid());
				picturesMapper.insertSelective(pictures);
			}
		}
	}
	
	/**
	 * 获取工单信息，含图片信息和维修员信息
	 * @param orderid
	 * @return
	 */
	public MaintainorderWithPicturesAndMaintainuser getMaintainorderDetail(
			String orderid) {
		Maintainorder maintainorder = maintainorderMapper.selectByPrimaryKey(orderid);
		MaintainorderWithPicturesAndMaintainuser 
			maintainorderWithPicturesAndMaintainuser = 
				this.getMaintainorderDetail(maintainorder);
		return maintainorderWithPicturesAndMaintainuser;
	}
	
	/**
	 * 删除工单
	 * @param orderid
	 * @return
	 */
	public ApiResponse delMaintainorder(String orderid) {
		ApiResponse apiResponse = new ApiResponse();
		Maintainorder maintainorder = maintainorderMapper.selectByPrimaryKey(orderid);
		if(maintainorder == null) {
			apiResponse.setCode("500");
			apiResponse.setMsg("工单未找到！");
			return apiResponse;
		}
		if(!"未派单".equals(maintainorder.getStatus())) {
			apiResponse.setCode("500");
			apiResponse.setMsg("工单已派发，不可删除！");
			return apiResponse;
		}
		maintainorder.setIsdelete(1);
		maintainorderMapper.updateByPrimaryKeySelective(maintainorder);
		apiResponse.setCode("200");
		apiResponse.setMsg("删除成功！");
		return apiResponse;
	}
	
	private MaintainorderWithPicturesAndMaintainuser getMaintainorderDetail(
			Maintainorder maintainorder) {
		if(maintainorder == null) {
			return null;
		}
		MaintainorderWithPicturesAndMaintainuser 
			maintainorderWithPicturesAndMaintainuser = 
				new MaintainorderWithPicturesAndMaintainuser(maintainorder);
		if(maintainorderWithPicturesAndMaintainuser.getOrderid() != null) {
			List<String> pictureurls = new ArrayList<String>();
			PicturesExample picturesExample = new PicturesExample();
			picturesExample.createCriteria().andIsdeleteEqualTo(0)
				.andRelationidEqualTo(
						maintainorderWithPicturesAndMaintainuser.getOrderid());
			List<Pictures> picturesList = picturesMapper.selectByExample(picturesExample);
			if(picturesList != null && picturesList.size() > 0) {
				for(Pictures pictures : picturesList) {
					String pictureurl = pictures.getPictureurl();
					pictureurls.add(pictureurl);
				}
			}
			maintainorderWithPicturesAndMaintainuser.setPictureurls(pictureurls);
		}
		if(StringUtils.isNotBlank(maintainorder.getMaintainid())) {
			Maintainuser maintainuser = 
					maintainuserMapper.selectByPrimaryKey(
							maintainorder.getMaintainid());
			if(maintainuser != null) {
				maintainorderWithPicturesAndMaintainuser.setMaintainuser(maintainuser);
			}
		}
		if("已完成".contentEquals(maintainorder.getStatus())) {
			CommentsExample commentsExample = new CommentsExample();
			commentsExample.createCriteria().andOrderidEqualTo(maintainorder.getOrderid())
			.andIsdeleteEqualTo(0);
			List<Comments> commentsList = commentsMapper.selectByExample(commentsExample);
			if(commentsList != null && commentsList.size() > 0) {
				Comments comments = commentsList.get(0);
				Wxuser wxuser = wxuserService.getWxuserInfo(comments.getWxuserid());
				if(wxuser != null) {
					CommentsWithWxuser commentsWithWxuser = 
							new CommentsWithWxuser(comments);
					commentsWithWxuser.setWxuser(wxuser);
					maintainorderWithPicturesAndMaintainuser
						.setCommentsWithWxuser(commentsWithWxuser);
				}
			}
		}
		return maintainorderWithPicturesAndMaintainuser;
	}
	
	public TomPage pageWxuserMaintainorder(
			String openid, String status, Integer days,
			Integer pageNumber, Integer pageSize) {
		MaintainorderExample maintainorderExample = new MaintainorderExample();
		Criteria criteria = maintainorderExample.createCriteria();
		criteria.andWxuseridEqualTo(openid).andIsdeleteEqualTo(0);
		if(StringUtils.isNotBlank(status)) {
			criteria.andStatusEqualTo(status);
		}
		if(days >= 0) {
			days = -1 * days;
			Date date = TomDateUtil.beforeDateByDay(days);
			criteria.andCreatetimeGreaterThanOrEqualTo(date);
		}
		maintainorderExample.setOrderByClause(" createtime desc");
		Page<Maintainorder> page = PageHelper.startPage(pageNumber, pageSize);
		maintainorderMapper.selectByExample(maintainorderExample);
		List<Maintainorder> maintainorders = page.getResult();
		List<MaintainorderWithPicturesAndMaintainuser> 
			maintainorderWithPicturesAndMaintainuserList = 
				new ArrayList<MaintainorderWithPicturesAndMaintainuser>();
		for(Maintainorder maintainorder : maintainorders) {
			MaintainorderWithPicturesAndMaintainuser 
				maintainorderWithPicturesAndMaintainuser =
					this.getMaintainorderDetail(maintainorder);
			maintainorderWithPicturesAndMaintainuserList.add(
					maintainorderWithPicturesAndMaintainuser);
		}
		TomPage tomPage = 
				new TomPage(page, maintainorderWithPicturesAndMaintainuserList);
		return tomPage;
	}
	
	public TomPage pageOfficeuserMaintainorder(
			String status, Integer pageNumber, Integer pageSize) {
		MaintainorderExample maintainorderExample = new MaintainorderExample();
		Criteria criteria = maintainorderExample.createCriteria();
		criteria.andIsdeleteEqualTo(0);
		if("未派单".equals(status)) {
			criteria.andStatusEqualTo("未派单");
		} else {
			criteria.andStatusNotEqualTo("未派单");
		}
		maintainorderExample.setOrderByClause(" createtime desc");
		Page<Maintainorder> page = PageHelper.startPage(pageNumber, pageSize);
		maintainorderMapper.selectByExample(maintainorderExample);
		List<Maintainorder> maintainorders = page.getResult();
		List<MaintainorderWithPicturesAndMaintainuser> 
			maintainorderWithPicturesAndMaintainuserList = 
				new ArrayList<MaintainorderWithPicturesAndMaintainuser>();
		for(Maintainorder maintainorder : maintainorders) {
			MaintainorderWithPicturesAndMaintainuser 
				maintainorderWithPicturesAndMaintainuser =
					this.getMaintainorderDetail(maintainorder);
			maintainorderWithPicturesAndMaintainuserList.add(
					maintainorderWithPicturesAndMaintainuser);
		}
		TomPage tomPage = 
				new TomPage(page, maintainorderWithPicturesAndMaintainuserList);
		return tomPage;
	}
	
	public synchronized ApiResponse assignMaintainorder(
			String orderid, String officerid, String maintainid) {
		ApiResponse apiResponse = new ApiResponse();
		if(StringUtils.isBlank(orderid)
				|| StringUtils.isBlank(officerid)
				|| StringUtils.isBlank(maintainid)) {
			apiResponse.setCode("400");
			apiResponse.setMsg("参数不完整，请核对后再提交！");
			return apiResponse;
		}
		Maintainuser maintainuser = maintainuserMapper.selectByPrimaryKey(maintainid);
		Maintainorder maintainorder = maintainorderMapper.selectByPrimaryKey(orderid);
		Officeuser officeuser = officeuserMapper.selectByPrimaryKey(officerid);
		if(maintainuser == null || maintainorder == null
				|| officeuser ==null || maintainuser.getIsdelete() != 0
				|| maintainorder.getIsdelete() != 0 || officeuser.getIsdelete() != 0) {
			apiResponse.setCode("500");
			apiResponse.setMsg("报修工单、维修员或派单员信息未找到！");
			return apiResponse;
		}
		if(!"未派单".equals(maintainorder.getStatus())) {
			apiResponse.setCode("500");
			apiResponse.setMsg("报修工单状态异常！");
			return apiResponse;
		}
		maintainorder.setStatus("维修中");
		maintainorder.setOfficertime(new Date());
		maintainorder.setMaintainid(maintainid);
		maintainorder.setOfficerid(officerid);
		maintainorderMapper.updateByPrimaryKeySelective(maintainorder);
		apiResponse.setCode("200");
		apiResponse.setMsg("派单成功！");
		return apiResponse;
	}
	
	public TomPage pageMaintainuserMaintainorder(
			String maintainid, String status, Integer pageNumber, Integer pageSize) {
		MaintainorderExample maintainorderExample = new MaintainorderExample();
		Criteria criteria = maintainorderExample.createCriteria();
		criteria.andIsdeleteEqualTo(0)
			.andStatusNotEqualTo("未派单")
			.andMaintainidEqualTo(maintainid);
		if("未完成".equals(status)) {
			criteria.andStatusEqualTo("维修中");
		} else {
			criteria.andStatusNotEqualTo("维修中");
		}
		maintainorderExample.setOrderByClause(" createtime desc");
		Page<Maintainorder> page = PageHelper.startPage(pageNumber, pageSize);
		maintainorderMapper.selectByExample(maintainorderExample);
		List<Maintainorder> maintainorders = page.getResult();
		List<MaintainorderMaintainuser> 
			maintainorderMaintainuserList = 
				new ArrayList<MaintainorderMaintainuser>();
		for(Maintainorder maintainorder : maintainorders) {
			MaintainorderMaintainuser 
				maintainorderMaintainuser =
					this.getMaintainorderMaintainuser(maintainorder);
			maintainorderMaintainuserList.add(
					maintainorderMaintainuser);
		}
		TomPage tomPage = 
				new TomPage(page, maintainorderMaintainuserList);
		return tomPage;
	}

	private MaintainorderMaintainuser getMaintainorderMaintainuser(
			Maintainorder maintainorder) {
		if(maintainorder == null) {
			return null;
		}
		MaintainorderMaintainuser 
			maintainorderMaintainuser = 
				new MaintainorderMaintainuser(maintainorder);
		if(maintainorderMaintainuser.getOrderid() != null) {
			List<String> pictureurls = new ArrayList<String>();
			PicturesExample picturesExample = new PicturesExample();
			picturesExample.createCriteria().andIsdeleteEqualTo(0)
				.andRelationidEqualTo(
						maintainorderMaintainuser.getOrderid());
			List<Pictures> picturesList = picturesMapper.selectByExample(picturesExample);
			if(picturesList != null && picturesList.size() > 0) {
				for(Pictures pictures : picturesList) {
					String pictureurl = pictures.getPictureurl();
					pictureurls.add(pictureurl);
				}
			}
			maintainorderMaintainuser.setPictureurls(pictureurls);
		}
		if(StringUtils.isNotBlank(maintainorder.getWxuserid())) {
			Wxuser wxuser = wxuserService.getWxuserInfo(maintainorder.getWxuserid());
			if(wxuser != null) {
				maintainorderMaintainuser.setWxuser(wxuser);
			}
		}
		if("已完成".equals(maintainorder.getStatus())) {
			CommentsExample commentsExample = new CommentsExample();
			commentsExample.createCriteria().andIsdeleteEqualTo(0)
				.andOrderidEqualTo(maintainorder.getOrderid());
			List<Comments> commentsList = 
					commentsMapper.selectByExample(commentsExample);
			if(commentsList != null && commentsList.size() > 0) {
				maintainorderMaintainuser.setComments(commentsList.get(0));
			}
		}
		return maintainorderMaintainuser;
	}
	
	public synchronized ApiResponse completeMaintainorder(
	        String maintainid, String orderid, Float charge) {
	    
		ApiResponse apiResponse = new ApiResponse();
		if(StringUtils.isBlank(orderid)
				|| StringUtils.isBlank(maintainid)
				|| charge == null || charge < 0) {
			apiResponse.setCode("400");
			apiResponse.setMsg("参数不完整，请核对后再提交！");
			return apiResponse;
		}
		Maintainorder maintainorder = maintainorderMapper.selectByPrimaryKey(orderid);
		if(maintainorder == null 
				|| !"维修中".equals(maintainorder.getStatus())
				|| !maintainid.equals(maintainorder.getMaintainid())) {
			apiResponse.setCode("500");
			apiResponse.setMsg("报修工单信息异常！");
			return apiResponse;
		}
		maintainorder.setMaintaintime(new Date());
		maintainorder.setStatus("待评价");
		maintainorder.setCharge(charge);
		maintainorderMapper.updateByPrimaryKeySelective(maintainorder);
		apiResponse.setCode("200");
		apiResponse.setMsg("操作成功！");
		return apiResponse;
	}
	
	public TomPage pageWebMaintainorder(
			String maintainid, String status, Integer pageNumber, Integer pageSize) {
		MaintainorderExample maintainorderExample = new MaintainorderExample();
		Criteria criteria = maintainorderExample.createCriteria();
		criteria.andIsdeleteEqualTo(0);
		if(StringUtils.isNotBlank(maintainid)&&!"all".equals(maintainid)) {
			criteria.andMaintainidEqualTo(maintainid);
		}
		if(StringUtils.isNotBlank(status)&&!"all".equals(status)) {
			criteria.andStatusEqualTo(status);
		}
		maintainorderExample.setOrderByClause(" createtime desc");
		Page<Maintainorder> page = PageHelper.startPage(pageNumber, pageSize);
		maintainorderMapper.selectByExample(maintainorderExample);
		List<Maintainorder> maintainorders = page.getResult();
		List<MaintainorderMaintainuser> 
			maintainorderMaintainuserList = 
				new ArrayList<MaintainorderMaintainuser>();
		for(Maintainorder maintainorder : maintainorders) {
			MaintainorderMaintainuser 
				maintainorderMaintainuser =
					this.getWebMaintainuser(maintainorder);
			maintainorderMaintainuserList.add(
					maintainorderMaintainuser);
		}
		TomPage tomPage = 
				new TomPage(page, maintainorderMaintainuserList);
		return tomPage;
	}
	
	public TomPage maintainorderListReportIndexPage(
            String status, Integer pageNumber, Integer pageSize,
            String keyword, String repairMan, String maintain) {
        MaintainorderExample maintainorderExample = new MaintainorderExample();
        Criteria criteria = maintainorderExample.createCriteria();
        criteria.andIsdeleteEqualTo(0);
        if(StringUtils.isNotBlank(status)&&!"all".equals(status)) {
            criteria.andStatusEqualTo(status);
        }
        if(StringUtils.isNotBlank(keyword)) {
            criteria.andTitleOrContentLike("%"+keyword+"%");
        }
        //报修人
        if(StringUtils.isNotBlank(repairMan)) {
            WxuserExample wxuserExample = new WxuserExample();
            wxuserExample.createCriteria().andNicknameLike("%"+repairMan+"%");
            List<Wxuser> wxuserList = wxuserMapper.selectByExample(wxuserExample);
            List<String> wxuserids = new ArrayList<String>();
            wxuserList.forEach(wxuser -> wxuserids.add(wxuser.getOpenid()));
            criteria.andWxuseridIn(wxuserids);
        }
        //维修人
        if(StringUtils.isNotBlank(maintain)) {
            MaintainuserExample maintainuserExample = new MaintainuserExample();
            maintainuserExample.createCriteria()
                .andIsdeleteEqualTo(0).andRealnameLike("%"+maintain+"%");
            List<Maintainuser> maintainuserList = 
                maintainuserMapper.selectByExample(maintainuserExample);
            List<String> maintainuserids = new ArrayList<String>();
            maintainuserList.forEach(
                    maintainuser -> maintainuserids.add(maintainuser.getMaintainid()));
            criteria.andMaintainidIn(maintainuserids);
        }
        maintainorderExample.setOrderByClause(" createtime desc");
        Page<Maintainorder> page = PageHelper.startPage(pageNumber, pageSize);
        maintainorderMapper.selectByExample(maintainorderExample);
        List<Maintainorder> maintainorders = page.getResult();
        List<MaintainorderMaintainuser> 
            maintainorderMaintainuserList = 
                new ArrayList<MaintainorderMaintainuser>();
        for(Maintainorder maintainorder : maintainorders) {
            MaintainorderMaintainuser 
                maintainorderMaintainuser =
                    this.getWebMaintainuser(maintainorder);
            maintainorderMaintainuserList.add(
                    maintainorderMaintainuser);
        }
        TomPage tomPage = 
                new TomPage(page, maintainorderMaintainuserList);
        return tomPage;
    }

	private MaintainorderMaintainuser getWebMaintainuser(
			Maintainorder maintainorder) {
		if(maintainorder == null) {
			return null;
		}
		MaintainorderMaintainuser 
			maintainorderMaintainuser = 
				new MaintainorderMaintainuser(maintainorder);
		if(maintainorderMaintainuser.getOrderid() != null) {
			List<String> pictureurls = new ArrayList<String>();
			PicturesExample picturesExample = new PicturesExample();
			picturesExample.createCriteria().andIsdeleteEqualTo(0)
				.andRelationidEqualTo(
						maintainorderMaintainuser.getOrderid());
			List<Pictures> picturesList = picturesMapper.selectByExample(picturesExample);
			if(picturesList != null && picturesList.size() > 0) {
				for(Pictures pictures : picturesList) {
					String pictureurl = pictures.getPictureurl();
					pictureurls.add(pictureurl);
				}
			}
			maintainorderMaintainuser.setPictureurls(pictureurls);
		}
		if(StringUtils.isNotBlank(maintainorder.getWxuserid())) {
			Wxuser wxuser = wxuserService.getWxuserInfo(maintainorder.getWxuserid());
			if(wxuser != null) {
				maintainorderMaintainuser.setWxuser(wxuser);
			}
		}
		if("已完成".equals(maintainorder.getStatus())) {
			CommentsExample commentsExample = new CommentsExample();
			commentsExample.createCriteria().andIsdeleteEqualTo(0)
				.andOrderidEqualTo(maintainorder.getOrderid());
			List<Comments> commentsList = 
					commentsMapper.selectByExample(commentsExample);
			if(commentsList != null && commentsList.size() > 0) {
				maintainorderMaintainuser.setComments(commentsList.get(0));
			}
		}
		if(StringUtils.isNotBlank(maintainorder.getMaintainid())) {
			Maintainuser maintainuser = 
					maintainuserMapper.selectByPrimaryKey(
							maintainorder.getMaintainid());
			if(maintainuser != null) {
				maintainorderMaintainuser.setMaintainuser(maintainuser);
			}
		}
		return maintainorderMaintainuser;
	}
	
}
