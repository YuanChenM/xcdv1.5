package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdMachining;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 产品价格通道阶梯
 * Created by ni_shaotang on 2016/7/14.
 */
@ApiModel(value = "IBA2141106RsBean", description = "bean")
public class IBA2141106RsBean extends BaseEntity {

    @ApiModelProperty(value = "订单等级等级（10级）0-9")
    private String orderLevel;

    @ApiModelProperty(value = "商品价格（公斤价）")
    private String priceOfKg;

    @ApiModelProperty(value = "商品价格（箱价）")
    private String priceOfBox;

    @ApiModelProperty(value = "箱数下限")
    private String boxMin;

    @ApiModelProperty(value = "箱数上限")
    private String boxMax;

    @ApiModelProperty(value = "箱数显示")
    private String boxName;

    public String getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(String orderLevel) {
        this.orderLevel = orderLevel;
    }

    public String getPriceOfKg() {
        return priceOfKg;
    }

    public void setPriceOfKg(String priceOfKg) {
        this.priceOfKg = priceOfKg;
    }

    public String getPriceOfBox() {
        return priceOfBox;
    }

    public void setPriceOfBox(String priceOfBox) {
        this.priceOfBox = priceOfBox;
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

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }
}
