package com.tom.model;

import com.tom.model.generator.Comments;
import com.tom.model.generator.Wxuser;
import com.tom.util.DateUtil;

public class CommentsWithWxuser extends Comments {
	
	private Wxuser wxuser;
	
	private String createtimeStr;
	
	public CommentsWithWxuser(Comments comments) {
		this.setCommentid(comments.getCommentid());
		this.setContent(comments.getContent());
		this.setCreatetime(comments.getCreatetime());
		this.setIsdelete(comments.getIsdelete());
		this.setOrderid(comments.getOrderid());
		this.setRelationid(comments.getRelationid());
		this.setStars(comments.getStars());
		this.setWxuserid(comments.getWxuserid());
		this.setWxuser(new Wxuser());
		if(comments.getCreatetime() != null) {
			this.setCreatetimeStr(DateUtil.getALLTime(comments.getCreatetime()));
		} else {
			this.setCreatetimeStr("");
		}
	}

	public String getCreatetimeStr() {
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public Wxuser getWxuser() {
		return wxuser;
	}

	public void setWxuser(Wxuser wxuser) {
		this.wxuser = wxuser;
	}
	
}
