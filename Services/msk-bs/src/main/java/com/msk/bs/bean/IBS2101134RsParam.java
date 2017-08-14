package com.msk.bs.bean;

import java.util.List;

import com.hoperun.core.bean.BaseParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/13.
 */
@ApiModel(value = "IBS2101134RsParam", description = "param")
public class IBS2101134RsParam extends BaseParam {

    @ApiModelProperty(value = "订单员")
    private String orderTaker;

    @ApiModelProperty(value = "订单")
    private List<IBS2101135RsParam> orders;

    public List<IBS2101135RsParam> getOrders() {
        return orders;
    }

    public void setOrders(List<IBS2101135RsParam> orders) {
        this.orders = orders;
    }

    public String getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }
}
