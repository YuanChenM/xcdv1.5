package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhu_kai1 on 2016/9/18.
 */
@ApiModel(value = "IBA2141204RsResult", description = "result")
public class IBA2141204RsResult extends BaseEntity {

    @ApiModelProperty(value = "管家code")
    private String houseCode;

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
}
