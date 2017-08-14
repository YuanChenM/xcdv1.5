package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * Created by Administrator on 2016/2/29.
 */
public class ISL231180SLEpAuth extends BaseEntity {
    /**1：卖家代理及分销授权:2：卖家OEM委托授权标志*/
    private String flag;
    /** SL_CODE */
    private java.lang.String slCode;
    /** PRODUCER_EP_ID */
    private java.lang.Long producerEpId;
    /** CONTRACT_NO */
    private java.lang.String contractNo;
    /** AUTH_EP_NAME */
    private java.lang.String authEpName;
    /** AUTH_TERM_BEGIN */
    private java.util.Date authTermBegin;
    /** AUTH_TERM_END */
    private java.util.Date authTermEnd;
    /** 1:长期 */
    private java.lang.String authTermUnliimited;
    /**
     * 获得flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 获得slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * 设置slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * 获得producerEpId
     */
    public Long getProducerEpId() {
        return producerEpId;
    }

    /**
     * 设置producerEpId
     */
    public void setProducerEpId(Long producerEpId) {
        this.producerEpId = producerEpId;
    }

    /**
     * 获得contractNo
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置contractNo
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * 获得authEpName
     */
    public String getAuthEpName() {
        return authEpName;
    }

    /**
     * 设置authEpName
     */
    public void setAuthEpName(String authEpName) {
        this.authEpName = authEpName;
    }

    /**
     * 获得authTermBegin
     */
    public Date getAuthTermBegin() {
        return authTermBegin;
    }

    /**
     * 设置authTermBegin
     */
    public void setAuthTermBegin(Date authTermBegin) {
        this.authTermBegin = authTermBegin;
    }

    /**
     * 获得authTermEnd
     */
    public Date getAuthTermEnd() {
        return authTermEnd;
    }

    /**
     * 设置authTermEnd
     */
    public void setAuthTermEnd(Date authTermEnd) {
        this.authTermEnd = authTermEnd;
    }

    /**
     * 获得authTermUnliimited
     */
    public String getAuthTermUnliimited() {
        return authTermUnliimited;
    }

    /**
     * 设置authTermUnliimited
     */
    public void setAuthTermUnliimited(String authTermUnliimited) {
        this.authTermUnliimited = authTermUnliimited;
    }
}
