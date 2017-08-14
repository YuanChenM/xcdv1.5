package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by whc on 2016/10/24.
 */
@ApiModel(value = "IBS2101140Bean", description = "买家信息")
public class IBS2101140Bean extends BaseParam{

    @ApiModelProperty(value = "买家管家关系")
    private String relationType;
    @ApiModelProperty(value = "买家ID")
    private String buyerId;


    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
