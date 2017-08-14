package com.msk.org.bean.param;


import com.msk.common.bean.param.BaseRestParam;

public class LoginUserParam extends BaseRestParam {
    private String userName;
    private String userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
