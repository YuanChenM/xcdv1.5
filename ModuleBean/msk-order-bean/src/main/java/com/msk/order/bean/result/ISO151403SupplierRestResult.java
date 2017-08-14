package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * ISO151403_查询供应商列表接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151403SupplierRestResult extends BaseResult {
    private String userId;
    private String userNo;
    private String userName;
    private Integer userRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
}
