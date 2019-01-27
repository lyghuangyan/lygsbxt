package com.tom.model;

import com.tom.model.generator.Maintainuser;

public class MaintainuserWithCommentsQualifications extends Maintainuser {
	
	private Boolean hasCommentsQualifications;
	
	public MaintainuserWithCommentsQualifications(Maintainuser maintainuser) {
		this.setAllstars(maintainuser.getAllstars());
		this.setAveragestars(maintainuser.getAveragestars());
		this.setCommentcount(maintainuser.getCommentcount());
		this.setIsdelete(maintainuser.getIsdelete());
		this.setMaintainid(maintainuser.getMaintainid());
		this.setPassword(maintainuser.getPassword());
		this.setPhotourl(maintainuser.getPhotourl());
		this.setRealname(maintainuser.getRealname());
		this.setUsername(maintainuser.getUsername());
		this.setRemark(maintainuser.getRemark());
		this.setHasCommentsQualifications(false);
	}

	public Boolean getHasCommentsQualifications() {
		return hasCommentsQualifications;
	}

	public void setHasCommentsQualifications(Boolean hasCommentsQualifications) {
		this.hasCommentsQualifications = hasCommentsQualifications;
	}

}
