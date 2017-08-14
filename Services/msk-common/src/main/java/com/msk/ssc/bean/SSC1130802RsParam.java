package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.List;

/**
 * Created by wu_honglei on 2016/8/17.
 */
public class SSC1130802RsParam extends BasePageParam {


    /** 发货订单ID */
    private String deliveryId;

    /**发货关联合同状态*/
    private Integer contractRelationType;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getContractRelationType() {
        return contractRelationType;
    }

    public void setContractRelationType(Integer contractRelationType) {
        this.contractRelationType = contractRelationType;
    }
}
