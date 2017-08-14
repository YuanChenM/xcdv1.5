package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

import java.util.Date;

/**
 * ISO151407_买家平台下单数量统计
 * Created by sun_jiaju on 2016/8/24.
 */
public class ISO151407RestParam extends BaseParam {
    // 买家ID
    @Required(required = true, message = "买家Id不能为空")
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
