package com.msk.order.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msk.common.base.BaseBean;
import com.msk.core.entity.SoOrder;

/**
 *
 * 订单列表
 *
 * @author rwf
 */

public class SO151401Bean extends SoOrder {

    private  Long  orderQty;// 订单数量

    private  String saName;;// 冻品管家名称

    public Long getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Long orderQty) {
        this.orderQty = orderQty;
    }


    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }
}