package com.msk.bs.bean;

import java.util.List;

import com.msk.core.entity.SlHouseAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "IBS2101104SlHouseAccountParam",description = "冻品管家分类")
public class IBS2101104SlHouseAccountParam{

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
