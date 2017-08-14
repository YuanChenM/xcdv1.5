package com.msk.order.bean;


import com.hoperun.core.bean.BaseParam;

import java.util.Date;

/**
 * 
 * ISO151407RsParam接受参数.
 *
 * @author sunjiaju
 */
public class ISO151407RsParam extends BaseParam {
    // 买家ID
    private String buyerId;
    // 下单开始时间
    private Date startDate;
    // 下单结束时间
    private Date endDate;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
