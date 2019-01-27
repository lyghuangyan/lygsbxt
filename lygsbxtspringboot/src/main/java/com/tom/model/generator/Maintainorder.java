package com.tom.model.generator;

import java.util.Date;

public class Maintainorder {
    private String orderid;

    private String title;

    private String address;

    private String content;

    private String wxuserid;

    private Date createtime;

    private String status;

    private Date officertime;

    private Date maintaintime;

    private Date commenttime;

    private Integer isdelete;

    private String maintainid;

    private String officerid;

    private Float charge;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getWxuserid() {
        return wxuserid;
    }

    public void setWxuserid(String wxuserid) {
        this.wxuserid = wxuserid == null ? null : wxuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getOfficertime() {
        return officertime;
    }

    public void setOfficertime(Date officertime) {
        this.officertime = officertime;
    }

    public Date getMaintaintime() {
        return maintaintime;
    }

    public void setMaintaintime(Date maintaintime) {
        this.maintaintime = maintaintime;
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getMaintainid() {
        return maintainid;
    }

    public void setMaintainid(String maintainid) {
        this.maintainid = maintainid == null ? null : maintainid.trim();
    }

    public String getOfficerid() {
        return officerid;
    }

    public void setOfficerid(String officerid) {
        this.officerid = officerid == null ? null : officerid.trim();
    }

    public Float getCharge() {
        return charge;
    }

    public void setCharge(Float charge) {
        this.charge = charge;
    }
}