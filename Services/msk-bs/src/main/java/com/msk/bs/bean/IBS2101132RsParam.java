package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/13.
 */
@ApiModel(value = "IBS2101132RsParam", description = "param")
public class IBS2101132RsParam extends BaseParam {

    @ApiModelProperty(value = "管家编号")
    private String houseCode;

    @ApiModelProperty(value = "买家编号list")
    private List<String> buyerIds;

    @ApiModelProperty(value = "原因ID")
    private String msgId;

    @ApiModelProperty(value = "解除方式 1.买家主动解除管家 2.管家主动放弃买家")
    private String unbandSide;

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public List<String> getBuyerIds() {
        return buyerIds;
    }

    public void setBuyerIds(List<String> buyerIds) {
        this.buyerIds = buyerIds;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getUnbandSide() {
        return unbandSide;
    }

    public void setUnbandSide(String unbandSide) {
        this.unbandSide = unbandSide;
    }
}
