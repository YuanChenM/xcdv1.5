package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
public class ISO151427DeliverParam extends BaseParam{
    //配送单id
    private String deliverId;
    //配送单编码
    private String deliverCode;

    public String getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(String deliverId) {
        this.deliverId = deliverId;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }
}
