package com.msk.order.bean;

import com.msk.common.base.BaseBean;


/**
 * 
 * 订单卖家信息.
 *
 * @author sjj
 */
public class SellerListResult extends BaseBean {
    /** 供应商ID*/
    private String userId;
    /** 供应商编码*/
    private String userNo;
    /** 供应商名字*/
    private String userName;
    /** 订单状态*/
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
