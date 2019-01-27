package com.tom.model.generator;

public class Manageuser {
    private String manageid;

    private String username;

    private String password;

    private Integer isdelete;

    private Integer role;

    public String getManageid() {
        return manageid;
    }

    public void setManageid(String manageid) {
        this.manageid = manageid == null ? null : manageid.trim();
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

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}