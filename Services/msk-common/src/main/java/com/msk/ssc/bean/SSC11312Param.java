package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by xia_xiaojie on 2016/7/5.
 */
public class SSC11312Param extends BasePageParam {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 差异单ID */
    private Long differId;
    /** 入库差异单编号 */
    private String differCode;
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 发货订单号 */
    private String deliveryCode;

    private List<SSC11312Bean> sscDifferDetails;
    private List<Long> differIds;
    private List<Long> deliveryIds;


    public Long getDifferId() {
        return differId;
    }

    public void setDifferId(Long differId) {
        this.differId = differId;
    }

    public String getDifferCode() {
        return differCode;
    }

    public void setDifferCode(String differCode) {
        this.differCode = differCode;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public List<SSC11312Bean> getSscDifferDetails() {
        return sscDifferDetails;
    }

    public void setSscDifferDetails(List<SSC11312Bean> sscDifferDetails) {
        this.sscDifferDetails = sscDifferDetails;
    }

    public List<Long> getDifferIds() {
        return differIds;
    }

    public void setDifferIds(List<Long> differIds) {
        this.differIds = differIds;
    }

    public List<Long> getDeliveryIds() {
        return deliveryIds;
    }

    public void setDeliveryIds(List<Long> deliveryIds) {
        this.deliveryIds = deliveryIds;
    }

}
