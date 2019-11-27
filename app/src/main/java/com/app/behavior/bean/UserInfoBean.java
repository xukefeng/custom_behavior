package com.app.behavior.bean;

public class UserInfoBean {
    private String account;
    private String name;
    private String userId;
    private int viewType;

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public int getViewType() {
        return viewType;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
