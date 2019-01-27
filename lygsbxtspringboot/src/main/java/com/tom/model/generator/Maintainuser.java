package com.tom.model.generator;

public class Maintainuser {
    private String maintainid;

    private String username;

    private String password;

    private String realname;

    private String photourl;

    private Integer allstars;

    private Integer commentcount;

    private Float averagestars;

    private Integer isdelete;

    private String remark;

    public String getMaintainid() {
        return maintainid;
    }

    public void setMaintainid(String maintainid) {
        this.maintainid = maintainid == null ? null : maintainid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl == null ? null : photourl.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}