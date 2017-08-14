package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ni_shaotang on 2016/10/11.
 */

@ApiModel(value = "IBA2141123RsResult", description = "result")
public class IBA2141123RsResult extends BaseEntity {

    @ApiModelProperty(value = "销售订单详情列表")
    private List<IBA2141123Bean> orders;

    public List<IBA2141123Bean> getOrders() {
        return orders;
    }

    public void setOrders(List<IBA2141123Bean> orders) {
        this.orders = orders;
    }
}
