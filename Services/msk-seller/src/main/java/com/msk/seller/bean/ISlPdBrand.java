/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.seller.bean;
import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * <p>表sl_pd_brand对应的SlPdBrand。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ISlPdBrand extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** BRAND_EP_ID */
    private java.lang.Long brandEpId;
    /** BRAND_ID */
    private java.lang.Long brandId;
    /** BRAND_NAME */
    private java.lang.String brandName;
    /** 1:自有品牌,2:代理品牌 */
    private java.lang.Integer brandType;
    /** CONTRACT_NO */
    private java.lang.String contractNo;
    /** SL_CODE */
    private java.lang.String slCode;
    /** TERM_BEGIN */
    private java.util.Date termBegin;
    /** TERM_END */
    private java.util.Date termEnd;
    /**品牌分类*/
    private String brandClass;

    /**
     * Getter method for property <tt>brandClass</tt>.
     *
     * @return property value of brandClass
     */
    public String getBrandClass() {
        return brandClass;
    }

    /**
     * Setter method for property <tt>brandClass</tt>.
     *
     * @param brandClass value to be assigned to property brandClass
     */
    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass;
    }

    /**
     * <p>默认构造函数。</p>
     */
    public ISlPdBrand() {

    }

    /**
     * <p>BRAND_EP_ID。</p>
     *
     * @return the BRAND_EP_ID
     */
    public java.lang.Long getBrandEpId() {
        return brandEpId;
    }

    /**
     * <p>BRAND_EP_ID。</p>
     *
     * @param brandEpId BRAND_EP_ID。
     */
    public void setBrandEpId(java.lang.Long brandEpId) {
        this.brandEpId = brandEpId;
    }

    /**
     * <p>BRAND_ID。</p>
     *
     * @return the BRAND_ID
     */
    public java.lang.Long getBrandId() {
        return brandId;
    }

    /**
     * <p>BRAND_ID。</p>
     *
     * @param brandId BRAND_ID。
     */
    public void setBrandId(java.lang.Long brandId) {
        this.brandId = brandId;
    }

    /**
     * <p>BRAND_NAME。</p>
     *
     * @return the BRAND_NAME
     */
    public java.lang.String getBrandName() {
        return brandName;
    }

    /**
     * <p>BRAND_NAME。</p>
     *
     * @param brandName BRAND_NAME。
     */
    public void setBrandName(java.lang.String brandName) {
        this.brandName = brandName;
    }

    /**
     * <p>1:自有品牌,2:代理品牌。</p>
     *
     * @return the 1:自有品牌,2:代理品牌
     */
    public java.lang.Integer getBrandType() {
        return brandType;
    }

    /**
     * <p>1:自有品牌,2:代理品牌。</p>
     *
     * @param brandType 1:自有品牌,2:代理品牌。
     */
    public void setBrandType(java.lang.Integer brandType) {
        this.brandType = brandType;
    }

    /**
     * <p>CONTRACT_NO。</p>
     *
     * @return the CONTRACT_NO
     */
    public java.lang.String getContractNo() {
        return contractNo;
    }

    /**
     * <p>CONTRACT_NO。</p>
     *
     * @param contractNo CONTRACT_NO。
     */
    public void setContractNo(java.lang.String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * <p>SL_CODE。</p>
     *
     * @return the SL_CODE
     */
    public java.lang.String getSlCode() {
        return slCode;
    }

    /**
     * <p>SL_CODE。</p>
     *
     * @param slCode SL_CODE。
     */
    public void setSlCode(java.lang.String slCode) {
        this.slCode = slCode;
    }

    public Date getTermBegin() {
        return termBegin;
    }

    public void setTermBegin(Date termBegin) {
        this.termBegin = termBegin;
    }

    public Date getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(Date termEnd) {
        this.termEnd = termEnd;
    }
}
