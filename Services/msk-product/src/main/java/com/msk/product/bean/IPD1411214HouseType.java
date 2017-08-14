package com.msk.product.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/17.
 */
@ApiModel(value = "IPD1411214HouseType",description = "冻品管家分类")
public class IPD1411214HouseType {

    @ApiModelProperty(value = "管家一级分类")
    private String hkTypeCode;
    @ApiModelProperty(value = "管家二级分类")
    private String hkSecdTypeCode;

    public String getHkTypeCode() {
        return hkTypeCode;
    }

    public void setHkTypeCode(String hkTypeCode) {
        this.hkTypeCode = hkTypeCode;
    }

    public String getHkSecdTypeCode() {
        return hkSecdTypeCode;
    }

    public void setHkSecdTypeCode(String hkSecdTypeCode) {
        this.hkSecdTypeCode = hkSecdTypeCode;
    }
}
