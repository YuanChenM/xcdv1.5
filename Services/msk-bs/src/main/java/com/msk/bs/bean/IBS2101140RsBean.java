package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by whc on 2016/10/24.
 */
@ApiModel(value = "IBS2101140RsBean", description = "买家信息")
public class IBS2101140RsBean extends BaseParam{


    @ApiModelProperty(value = "买家ID")
    private String buyerId;
    @ApiModelProperty(value = "绑定结果信息")
    private String result;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
