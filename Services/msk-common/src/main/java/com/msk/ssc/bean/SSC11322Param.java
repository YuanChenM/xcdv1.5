package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/9.
 */
@ApiModel(value = "SSC11322Param", description = "核销单明细的查询参数")
public class SSC11322Param extends BasePageParam {
    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "核销单ID")
    private Long verificationId;

    @ApiModelProperty(value = "发货订单ID")
    private List<Long> deliveryIds;


    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public List<Long> getDeliveryIds() {
        return deliveryIds;
    }

    public void setDeliveryIds(List<Long> deliveryIds) {
        this.deliveryIds = deliveryIds;
    }

}
