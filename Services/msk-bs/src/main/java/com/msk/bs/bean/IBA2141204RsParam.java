package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhu_kai1 on 2016/9/18.
 */
@ApiModel(value = "IBA2141204RsParam", description = "param")
public class IBA2141204RsParam extends BaseParam {

    @ApiModelProperty(value = "买家id")
    private String buyerId;
    @ApiModelProperty(value = "applyStatus")
    private String applyStatus;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }
}
