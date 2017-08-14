package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhu_kai1 on 2016/7/18.
 */
@ApiModel(value = "IBA2141119Result", description = "result")
public class IBA2141119Result extends BaseEntity {

    @ApiModelProperty(value = "买手code")
    private String slCode;

    @ApiModelProperty(value = "管家虚拟物流区编码")
    private String vlgcsAreaCode;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getVlgcsAreaCode() {
        return vlgcsAreaCode;
    }

    public void setVlgcsAreaCode(String vlgcsAreaCode) {
        this.vlgcsAreaCode = vlgcsAreaCode;
    }
}
