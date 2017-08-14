package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ren_qiang on 2016/8/31.
 */
@ApiModel(value = "IBS21011121Bean",description = "管家分类集合")
public class IBS21011121Bean{

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
