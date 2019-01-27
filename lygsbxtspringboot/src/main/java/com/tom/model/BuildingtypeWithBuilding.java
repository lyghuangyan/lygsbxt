package com.tom.model;

import java.util.ArrayList;
import java.util.List;

import com.tom.model.generator.Building;
import com.tom.model.generator.Buildingtype;

public class BuildingtypeWithBuilding extends Buildingtype {
	
	private List<Building> buildings;
	
	private List<BuildingWithCommentQualifications> 
		buildingWithCommentQualificationsList;
	
	public BuildingtypeWithBuilding(Buildingtype buildingtype) {
		this.setTypeid(buildingtype.getTypeid());
		this.setTypename(buildingtype.getTypename());
		this.setSort(buildingtype.getSort());
		this.setIsdelete(buildingtype.getIsdelete());
		this.setBuildings(new ArrayList<Building>());
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public List<BuildingWithCommentQualifications> getBuildingWithCommentQualificationsList() {
		return buildingWithCommentQualificationsList;
	}

	public void setBuildingWithCommentQualificationsList(
			List<BuildingWithCommentQualifications> buildingWithCommentQualificationsList) {
		this.buildingWithCommentQualificationsList = buildingWithCommentQualificationsList;
	}
	
}
