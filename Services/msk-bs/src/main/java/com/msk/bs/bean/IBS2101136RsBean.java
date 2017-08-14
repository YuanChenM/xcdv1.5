package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by whc on 2016/10/12.
 */
@ApiModel(value = "IBS2101136RsBean",
    description = "委托订单详情（配送信息）")
public class IBS2101136RsBean {

    @ApiModelProperty(value = "动检证要求")
    private Integer vicFlg;
    @ApiModelProperty(value = "装卸要求")
    private String unloadReq;
    @ApiModelProperty(value = "包装要求")
    private String packingReq;
    @ApiModelProperty(value = "其它配送服务要求")
    private String otherDeliveryReq;
    @ApiModelProperty(value = "本次配送服务要求")
    private String thisDeliveryReq;

    public Integer getVicFlg() {
        return vicFlg;
    }

    public void setVicFlg(Integer vicFlg) {
        this.vicFlg = vicFlg;
    }

    public String getUnloadReq() {
        return unloadReq;
    }

    public void setUnloadReq(String unloadReq) {
        this.unloadReq = unloadReq;
    }

    public String getPackingReq() {
        return packingReq;
    }

    public void setPackingReq(String packingReq) {
        this.packingReq = packingReq;
    }

    public String getOtherDeliveryReq() {
        return otherDeliveryReq;
    }

    public void setOtherDeliveryReq(String otherDeliveryReq) {
        this.otherDeliveryReq = otherDeliveryReq;
    }

    public String getThisDeliveryReq() {
        return thisDeliveryReq;
    }

    public void setThisDeliveryReq(String thisDeliveryReq) {
        this.thisDeliveryReq = thisDeliveryReq;
    }
}
