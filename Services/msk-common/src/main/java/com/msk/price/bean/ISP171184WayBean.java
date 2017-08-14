package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by peng_hao on 2016/6/8.
 */
@ApiModel(value = "ISP171184WayBean", description = "价格列表")
public class ISP171184WayBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单等级等级（10级）")
    private String orderLevel;

    @ApiModelProperty(value = "箱数下限")
    private String boxMin;

    @ApiModelProperty(value = "箱数上限")
    private String boxMax;

    public String getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(String orderLevel) {
        this.orderLevel = orderLevel;
    }

    public String getBoxMin() {
        return boxMin;
    }

    public void setBoxMin(String boxMin) {
        this.boxMin = boxMin;
    }

    public String getBoxMax() {
        return boxMax;
    }

    public void setBoxMax(String boxMax) {
        this.boxMax = boxMax;
    }
}
