package com.tom.model;

import java.util.ArrayList;
import java.util.List;

import com.tom.model.generator.Comments;
import com.tom.model.generator.Maintainorder;
import com.tom.model.generator.Maintainuser;

public class MaintainorderWithPicturesAndMaintainuser extends Maintainorder {
	
	private List<String> pictureurls;
	
	private Maintainuser maintainuser;
	
	private CommentsWithWxuser commentsWithWxuser;
	
	public MaintainorderWithPicturesAndMaintainuser(Maintainorder maintainorder) {
		this.setAddress(maintainorder.getAddress());
		this.setCommenttime(maintainorder.getCommenttime());
		this.setContent(maintainorder.getContent());
		this.setCreatetime(maintainorder.getCreatetime());
		this.setIsdelete(maintainorder.getIsdelete());
		this.setMaintaintime(maintainorder.getMaintaintime());
		this.setOfficertime(maintainorder.getOfficertime());
		this.setOrderid(maintainorder.getOrderid());
		this.setStatus(maintainorder.getStatus());
		this.setTitle(maintainorder.getTitle());
		this.setWxuserid(maintainorder.getWxuserid());
		this.setMaintainid(maintainorder.getMaintainid());
		this.setOfficerid(maintainorder.getOfficerid());
		this.setPictureurls(new ArrayList<String>());
		this.setMaintainuser(new Maintainuser());
		this.setCommentsWithWxuser(new CommentsWithWxuser(new Comments()));
	}

	public List<String> getPictureurls() {
		return pictureurls;
	}

	public void setPictureurls(List<String> pictureurls) {
		this.pictureurls = pictureurls;
	}

	public Maintainuser getMaintainuser() {
		return maintainuser;
	}

	public void setMaintainuser(Maintainuser maintainuser) {
		this.maintainuser = maintainuser;
	}

	public CommentsWithWxuser getCommentsWithWxuser() {
		return commentsWithWxuser;
	}

	public void setCommentsWithWxuser(CommentsWithWxuser commentsWithWxuser) {
		this.commentsWithWxuser = commentsWithWxuser;
	}
	
}
