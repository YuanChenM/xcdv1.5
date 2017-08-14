package com.msk.ssc.bean;

import com.msk.core.entity.SscPaymentInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wu_honglei on 2016/8/4.
 */
@ApiModel(value = "SSC1130801RsBean",description = "支付管控单入参")
public class SSC1130801RsBean extends SscPaymentInfo{

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "转账时间")
    private String remitTimeStr;

    @ApiModelProperty(value = "发货id")
    private Long deliveryId;

    @ApiModelProperty(value = "付款类型")
    private String paymentType;

    @ApiModelProperty(value = "正负标记")
    private Boolean moneyFlag;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemitTimeStr() {
        return remitTimeStr;
    }

    public void setRemitTimeStr(String remitTimeStr) {
        this.remitTimeStr = remitTimeStr;
    }

    public Boolean getMoneyFlag() {
        return moneyFlag;
    }

    public void setMoneyFlag(Boolean moneyFlag) {
        this.moneyFlag = moneyFlag;
    }
}
