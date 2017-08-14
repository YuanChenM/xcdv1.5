/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlEpCertItem;

import java.util.List;

/**
 * <p>表sl_ep_cert对应的SlEpCert。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ISL231127CertInfoList extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** CERT_SEQ */
    private Long certSeq;
    /** EP_ID */
    private Long epId;
    /** CERT_ID */
    private Long certId;
    /** CERT_NAME */
    private String certName;
    /** 新增企业专业资质传入参数中的证照项目信息list*/
    private List<SlEpCertItem> certItemList;

    /**
     * 获得certItemList
     */
    public List<SlEpCertItem> getCertItemList() {
        return certItemList;
    }

    /**
     * 设置certItemList
     */
    public void setCertItemList(List<SlEpCertItem> certItemList) {
        this.certItemList = certItemList;
    }

    /**
     * <p>默认构造函数。</p>
     */
    public ISL231127CertInfoList() {

    }

    /**
     * <p>CERT_SEQ。</p>
     *
     * @return the CERT_SEQ
     */
    public Long getCertSeq() {
        return certSeq;
    }

    /**
     * <p>CERT_SEQ。</p>
     *
     * @param certSeq CERT_SEQ。
     */
    public void setCertSeq(Long certSeq) {
        this.certSeq = certSeq;
    }

    /**
     * <p>EP_ID。</p>
     *
     * @return the EP_ID
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * <p>EP_ID。</p>
     *
     * @param epId EP_ID。
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * <p>CERT_ID。</p>
     *
     * @return the CERT_ID
     */
    public Long getCertId() {
        return certId;
    }

    /**
     * <p>CERT_ID。</p>
     *
     * @param certId CERT_ID。
     */
    public void setCertId(Long certId) {
        this.certId = certId;
    }

    /**
     * <p>CERT_NAME。</p>
     *
     * @return the CERT_NAME
     */
    public String getCertName() {
        return certName;
    }

    /**
     * <p>CERT_NAME。</p>
     *
     * @param certName CERT_NAME。
     */
    public void setCertName(String certName) {
        this.certName = certName;
    }

}
