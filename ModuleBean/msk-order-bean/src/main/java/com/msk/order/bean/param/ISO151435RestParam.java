package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

/**
 * ISO151435_根据订单号(订单编码)获取订单相关信息
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151435RestParam extends BaseParam {

    @Required(required = true,message = "订单编码不能为空")
    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
