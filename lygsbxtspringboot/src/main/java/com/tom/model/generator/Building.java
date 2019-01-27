package com.tom.model.generator;

public class Building {
    private String buildid;

    private String type;

    private String name;

    private String firstpicture;

    private Integer allstars;

    private Integer commentcount;

    private Float averagestars;

    private Integer isdelete;

    public String getBuildid() {
        return buildid;
    }

    public void setBuildid(String buildid) {
        this.buildid = buildid == null ? null : buildid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFirstpicture() {
        return firstpicture;
    }

    public void setFirstpicture(String firstpicture) {
        this.firstpicture = firstpicture == null ? null : firstpicture.trim();
    }

    public Integer getAllstars() {
        return allstars;
    }

    public void setAllstars(Integer allstars) {
        this.allstars = allstars;
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public Float getAveragestars() {
        return averagestars;
    }

    public void setAveragestars(Float averagestars) {
        this.averagestars = averagestars;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}