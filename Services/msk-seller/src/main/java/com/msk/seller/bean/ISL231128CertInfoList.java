package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlEpCertItem;

import java.util.List;

/**
 * Created by Administrator on 2016/2/22.
 */
public class ISL231128CertInfoList extends BaseEntity {
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
    /**list<SlEpCertItem></> certItemList*/
    private List<SL24110302_1Bean> certItemList;

    /**
     * Getter method for property <tt>certSeq</tt>.
     *
     * @return property value of certSeq
     */
    public Long getCertSeq() {
        return certSeq;
    }

    /**
     * Setter method for property <tt>certSeq</tt>.
     *
     * @param certSeq value to be assigned to property certSeq
     */
    public void setCertSeq(Long certSeq) {
        this.certSeq = certSeq;
    }

    /**
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * Getter method for property <tt>certId</tt>.
     *
     * @return property value of certId
     */
    public Long getCertId() {
        return certId;
    }

    /**
     * Setter method for property <tt>certId</tt>.
     *
     * @param certId value to be assigned to property certId
     */
    public void setCertId(Long certId) {
        this.certId = certId;
    }

    /**
     * Getter method for property <tt>certItemList</tt>.
     *
     * @return property value of certItemList
     */
    public List<SL24110302_1Bean> getCertItemList() {
        return certItemList;
    }

    /**
     * Setter method for property <tt>certItemList</tt>.
     *
     * @param certItemList value to be assigned to property certItemList
     */
    public void setCertItemList(List<SL24110302_1Bean> certItemList) {
        this.certItemList = certItemList;
    }
}
