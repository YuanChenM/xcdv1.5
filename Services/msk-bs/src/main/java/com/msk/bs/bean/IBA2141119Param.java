package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhu_kai1 on 2016/7/18.
 */
@ApiModel(value = "IBA2141119Param", description = "param")
public class IBA2141119Param extends BaseParam {

    @ApiModelProperty(value = "管家code")
    private String houseCode;

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
}
