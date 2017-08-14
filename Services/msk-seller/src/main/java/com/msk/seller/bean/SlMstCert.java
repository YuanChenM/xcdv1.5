/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlMstCertItem;

import java.util.List;

/**
 * <p>表sl_mst_cert对应的SlMstCert。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlMstCert extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** CERT_ID */
    private Long certId;
    /** CERT_NAME */
    private String certName;
    /** 0:不必须,1:必须,2:按产品类别 */
    private Integer reqFlg;
    /** SORT */
    private Integer sort;
    /** 证照项目List */
    private List<SlMstCertItem> certItemList;
    /**
     * <p>默认构造函数。</p>
     */
    public SlMstCert() {

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

    /**
     * <p>0:不必须,1:必须,2:按产品类别。</p>
     *
     * @return the 0:不必须,1:必须,2:按产品类别
     */
    public Integer getReqFlg() {
        return reqFlg;
    }

    /**
     * <p>0:不必须,1:必须,2:按产品类别。</p>
     *
     * @param reqFlg 0:不必须,1:必须,2:按产品类别。
     */
    public void setReqFlg(Integer reqFlg) {
        this.reqFlg = reqFlg;
    }

    /**
     * <p>SORT。</p>
     *
     * @return the SORT
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * <p>SORT。</p>
     *
     * @param sort SORT。
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SlMstCertItem> getCertItemList() {
        return certItemList;
    }

    public void setCertItemList(List<SlMstCertItem> certItemList) {
        this.certItemList = certItemList;
    }
}
