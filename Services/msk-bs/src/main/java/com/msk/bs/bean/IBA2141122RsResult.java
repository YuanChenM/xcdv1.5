package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ni_shaotang on 2016/10/11.
 */

@ApiModel(value = "IBA2141122RsResult", description = "result")
public class IBA2141122RsResult extends BaseEntity {

    @ApiModelProperty(value = "查询结果总数")
    private Integer totalCount;
    @ApiModelProperty(value = "查询结果总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "查询结果当前页数")
    private Integer pageNo;
    @ApiModelProperty(value = "销售订单列表")
    private List<IBA2141122Bean> orders;

    public List<IBA2141122Bean> getOrders() {
        return orders;
    }

    public void setOrders(List<IBA2141122Bean> orders) {
        this.orders = orders;
    }
}
