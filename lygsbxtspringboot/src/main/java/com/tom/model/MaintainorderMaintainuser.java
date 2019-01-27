package com.tom.model;

import java.util.ArrayList;
import java.util.List;

import com.tom.model.generator.Comments;
import com.tom.model.generator.Maintainorder;
import com.tom.model.generator.Maintainuser;
import com.tom.model.generator.Wxuser;
import com.tom.util.DateUtil;

public class MaintainorderMaintainuser extends Maintainorder {
	private List<String> pictureurls;
	private Wxuser wxuser;
	private Comments comments;
	private Maintainuser maintainuser;
	private String createtimeStr;
	
	public MaintainorderMaintainuser(Maintainorder maintainorder) {
		if(maintainorder == null) {
			return;
		}
		this.setAddress(maintainorder.getAddress());
		this.setCommenttime(maintainorder.getCommenttime());
		this.setContent(maintainorder.getContent());
		this.setCreatetime(maintainorder.getCreatetime());
		this.setIsdelete(maintainorder.getIsdelete());
		this.setMaintainid(maintainorder.getMaintainid());
		this.setMaintaintime(maintainorder.getMaintaintime());
		this.setOfficerid(maintainorder.getOfficerid());
		this.setOfficertime(maintainorder.getOfficertime());
		this.setOrderid(maintainorder.getOrderid());
		this.setStatus(maintainorder.getStatus());
		this.setTitle(maintainorder.getTitle());
		this.setWxuserid(maintainorder.getWxuserid());
		this.setPictureurls(new ArrayList<String>());
		this.setWxuser(new Wxuser());
		this.setComments(new Comments());
		this.setMaintainuser(new Maintainuser());
		if(maintainorder.getCreatetime() != null) {
			this.setCreatetimeStr(DateUtil.getALLTime(maintainorder.getCreatetime()));
		}
	}
	
	public List<String> getPictureurls() {
		return pictureurls;
	}
	public void setPictureurls(List<String> pictureurls) {
		this.pictureurls = pictureurls;
	}
	public Wxuser getWxuser() {
		return wxuser;
	}
	public void setWxuser(Wxuser wxuser) {
		this.wxuser = wxuser;
	}
	public Comments getComments() {
		return comments;
	}
	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public Maintainuser getMaintainuser() {
		return maintainuser;
	}

	public void setMaintainuser(Maintainuser maintainuser) {
		this.maintainuser = maintainuser;
	}

	public String getCreatetimeStr() {
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
	
}
