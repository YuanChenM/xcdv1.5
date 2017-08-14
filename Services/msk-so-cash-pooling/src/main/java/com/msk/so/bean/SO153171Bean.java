package com.msk.so.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**账套设置
 * wu_honglei
 */
public class SO153171Bean extends BaseEntity {

    /**账期id */
    private Long paymentDaysId;
    /** 用户ID (slCode)*/
    private String userId;
    /**卖家编码显示用(用户编号)*/
    private String userNo;
    /** 用户角色 1：平台（神农客） 2：买家  3：卖家  4：买手*/
    private Integer userRole;
    /** 用户角色名称*/
    private String userRoleName;
    /** 用户名称 */
    private String userName;
    /** 启用日期 */
    private String commDate;
    /** 上个结束日 */
    private String lastPeriodEnd;
    /**  账期周期*/
    private Integer period;


    public Long getPaymentDaysId() {
        return paymentDaysId;
    }

    public void setPaymentDaysId(Long paymentDaysId) {
        this.paymentDaysId = paymentDaysId;
    }

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

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommDate() {
        return commDate;
    }

    public void setCommDate(String commDate) {
        this.commDate = commDate;
    }

    public String getLastPeriodEnd() {
        return lastPeriodEnd;
    }

    public void setLastPeriodEnd(String lastPeriodEnd) {
        this.lastPeriodEnd = lastPeriodEnd;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
