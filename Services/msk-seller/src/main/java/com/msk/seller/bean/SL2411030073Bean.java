package com.msk.seller.bean;



import com.msk.common.base.BaseBean;

import java.util.Date;

/**
 * Created by writer on 2016/2/5.
 */
public class SL2411030073Bean extends BaseBean {
    private String slCode;
    private Long producerEpId;
    private Long epId;
    private String epName;
    private Long brandId;
    private Integer brandType;
    /** BRAND_NAME */
    private String brandName;
    private String contractNo;
    private Long brandEpId;
    private Date  termBegin;
    private Date  termEnd;
    /**授权单位*/
    private String authEpName;
    /**授权期限开始*/
    private Date authTermBegin;
    /**授权期限结束*/
    private Date authTermEnd;
    /**授权期限长期标志*/
    private String authTermUnliimited;
    /**商标注册编码*/
    private String brandNo;
    /**品牌类型*/
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
     * Getter method for property <tt>brandNo</tt>.
     *
     * @return property value of brandNo
     */
    public String getBrandNo() {
        return brandNo;
    }

    /**
     * Setter method for property <tt>brandNo</tt>.
     *
     * @param brandNo value to be assigned to property brandNo
     */
    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    /**
     * Getter method for property <tt>authEpName</tt>.
     *
     * @return property value of authEpName
     */
    public String getAuthEpName() {
        return authEpName;
    }

    /**
     * Setter method for property <tt>authEpName</tt>.
     *
     * @param authEpName value to be assigned to property authEpName
     */
    public void setAuthEpName(String authEpName) {
        this.authEpName = authEpName;
    }

    /**
     * Getter method for property <tt>authTermBegin</tt>.
     *
     * @return property value of authTermBegin
     */
    public Date getAuthTermBegin() {
        return authTermBegin;
    }

    /**
     * Setter method for property <tt>authTermBegin</tt>.
     *
     * @param authTermBegin value to be assigned to property authTermBegin
     */
    public void setAuthTermBegin(Date authTermBegin) {
        this.authTermBegin = authTermBegin;
    }

    /**
     * Getter method for property <tt>authTermEnd</tt>.
     *
     * @return property value of authTermEnd
     */
    public Date getAuthTermEnd() {
        return authTermEnd;
    }

    /**
     * Setter method for property <tt>authTermEnd</tt>.
     *
     * @param authTermEnd value to be assigned to property authTermEnd
     */
    public void setAuthTermEnd(Date authTermEnd) {
        this.authTermEnd = authTermEnd;
    }

    /**
     * Getter method for property <tt>authTermUnliimited</tt>.
     *
     * @return property value of authTermUnliimited
     */
    public String getAuthTermUnliimited() {
        return authTermUnliimited;
    }

    /**
     * Setter method for property <tt>authTermUnliimited</tt>.
     *
     * @param authTermUnliimited value to be assigned to property authTermUnliimited
     */
    public void setAuthTermUnliimited(String authTermUnliimited) {
        this.authTermUnliimited = authTermUnliimited;
    }

    /**
     * Getter method for property <tt>termBegin</tt>.
     *
     * @return property value of termBegin
     */
    public Date getTermBegin() {
        return termBegin;
    }

    /**
     * Setter method for property <tt>termBegin</tt>.
     *
     * @param termBegin value to be assigned to property termBegin
     */
    public void setTermBegin(Date termBegin) {
        this.termBegin = termBegin;
    }

    /**
     * Getter method for property <tt>termEnd</tt>.
     *
     * @return property value of termEnd
     */
    public Date getTermEnd() {
        return termEnd;
    }

    /**
     * Setter method for property <tt>termEnd</tt>.
     *
     * @param termEnd value to be assigned to property termEnd
     */
    public void setTermEnd(Date termEnd) {
        this.termEnd = termEnd;
    }

    public Long getProducerEpId() {
        return producerEpId;
    }

    public void setProducerEpId(Long producerEpId) {
        this.producerEpId = producerEpId;
    }

    public Long getEpId() {
        return epId;
    }

    public void setEpId(Long epId) {
        this.epId = epId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getBrandType() {
        return brandType;
    }

    public void setBrandType(Integer brandType) {
        this.brandType = brandType;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Long getBrandEpId() {
        return brandEpId;
    }

    public void setBrandEpId(Long brandEpId) {
        this.brandEpId = brandEpId;
    }
}
