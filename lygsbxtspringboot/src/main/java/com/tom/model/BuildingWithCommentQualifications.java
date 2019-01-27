package com.tom.model;

import com.tom.model.generator.Building;

public class BuildingWithCommentQualifications extends Building {
	
	private Boolean hasCommentsQualifications;
	
	public BuildingWithCommentQualifications(Building building) {
		this.setAllstars(building.getAllstars());
		this.setAveragestars(building.getAveragestars());
		this.setBuildid(building.getBuildid());
		this.setCommentcount(building.getCommentcount());
		this.setFirstpicture(building.getFirstpicture());
		this.setIsdelete(building.getIsdelete());
		this.setName(building.getName());
		this.setType(building.getType());
		this.setHasCommentsQualifications(false);
	}

	public Boolean getHasCommentsQualifications() {
		return hasCommentsQualifications;
	}

	public void setHasCommentsQualifications(Boolean hasCommentsQualifications) {
		this.hasCommentsQualifications = hasCommentsQualifications;
	}
}
