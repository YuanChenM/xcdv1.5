package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.Date;

/**
 * Created by liu_tao2 on 2016/8/17.
 */
public class ISO151414BuyerValidDateRsParam extends BaseParam {

    /** 买家Id */
    private String buyerId;

    /** 1：申请中 2：专属会员 */
    private Integer applyStatus;

    /** 订单创建时间 */
    private Date orderCrtTime;

    /** 更新天数 */
    private Integer days;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getOrderCrtTime() {
        return orderCrtTime;
    }

    public void setOrderCrtTime(Date orderCrtTime) {
        this.orderCrtTime = orderCrtTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
