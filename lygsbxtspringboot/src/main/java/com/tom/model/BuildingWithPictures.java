package com.tom.model;

import java.util.ArrayList;
import java.util.List;

import com.tom.model.generator.Building;

public class BuildingWithPictures extends Building{
	private List<String> pictureurls;
	private String typename;
	private Boolean hasCommentsQualifications;

	public BuildingWithPictures(Building building) {
		this.setAllstars(building.getAllstars());
		this.setAveragestars(building.getAveragestars());
		this.setBuildid(building.getBuildid());
		this.setCommentcount(building.getCommentcount());
		this.setFirstpicture(building.getFirstpicture());
		this.setName(building.getName());
		this.setType(building.getType());
		this.setPictureurls(new ArrayList<String>());
	}

	public List<String> getPictureurls() {
		return pictureurls;
	}

	public void setPictureurls(List<String> pictureurls) {
		this.pictureurls = pictureurls;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Boolean getHasCommentsQualifications() {
		return hasCommentsQualifications;
	}

	public void setHasCommentsQualifications(Boolean hasCommentsQualifications) {
		this.hasCommentsQualifications = hasCommentsQualifications;
	}
	
}
