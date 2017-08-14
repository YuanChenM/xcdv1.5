package com.msk.bs.bean;

import java.util.List;

import com.msk.core.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/12.
 */
@ApiModel(value = "IBS2101134RsResult", description = "result")
public class IBS2101134RsResult extends BaseEntity {


    @ApiModelProperty(value = "委托订单详情")
    private List<IBS2101134RsBean> orders;

    public List<IBS2101134RsBean> getOrders() {
        return orders;
    }

    public void setOrders(List<IBS2101134RsBean> orders) {
        this.orders = orders;
    }
}
