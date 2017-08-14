package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhu_kai1 on 2016/8/29.
 */
@ApiModel(value = "IBA2141110RsBean", description = "产品信息")
public class IBA2141110RsBean  extends BaseEntity{

    @ApiModelProperty(value = "产品编码")
    private String pdCode;

    @ApiModelProperty(value = "产品数量")
    private String pdNum;

    @ApiModelProperty(value = "购物车明细")
    private String carDetailId;

    @ApiModelProperty(value = "购物车ID")
    private String carId;
    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdNum() {
        return pdNum;
    }

    public void setPdNum(String pdNum) {
        this.pdNum = pdNum;
    }

    public String getCarDetailId() {
        return carDetailId;
    }

    public void setCarDetailId(String carDetailId) {
        this.carDetailId = carDetailId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
