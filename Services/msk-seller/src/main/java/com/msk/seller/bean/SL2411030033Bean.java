package com.msk.seller.bean;

import com.msk.core.entity.SlEpBrand;
import com.msk.core.entity.SlEpBrandHonor;

import java.util.List;

/**
 * Created by fjm on 2016/1/29.
 */
public class SL2411030033Bean extends SlEpBrand {
    //证书编码
    private Integer honorId;
    private String honorNo;
    /** 发证日期 */
    private java.util.Date certDate;
    /** 发证单位 */
    private String certIssuer;
    /**荣誉描述*/
    private String honorDes;
    /**卖家编码*/
    private String slCode;
    /**品牌分类*/
    private String brandClass;
    //企业产品品牌荣誉
    private List<SlEpBrandHonor> slEpBrandHonorList;

    /**
     * Getter method for property <tt>brandClass</tt>.
     *
     * @return property value of brandClass
     */
    @Override
    public String getBrandClass() {
        return brandClass;
    }

    /**
     * Setter method for property <tt>brandClass</tt>.
     *
     * @param brandClass value to be assigned to property brandClass
     */
    @Override
    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>honorDes</tt>.
     *
     * @return property value of honorDes
     */
    public String getHonorDes() {
        return honorDes;
    }

    /**
     * Setter method for property <tt>honorDes</tt>.
     *
     * @param honorDes value to be assigned to property honorDes
     */
    public void setHonorDes(String honorDes) {
        this.honorDes = honorDes;
    }

    public Integer getHonorId() {
        return honorId;
    }

    public void setHonorId(Integer honorId) {
        this.honorId = honorId;
    }

    public java.util.Date getCertDate() {
        return certDate;
    }

    public void setCertDate(java.util.Date certDate) {
        this.certDate = certDate;
    }

    public String getCertIssuer() {
        return certIssuer;
    }

    public void setCertIssuer(String certIssuer) {
        this.certIssuer = certIssuer;
    }

    public String getHonorNo() {
        return honorNo;
    }

    public void setHonorNo(String honorNo) {
        this.honorNo = honorNo;
    }

    /**
     * Getter method for property <tt>slEpBrandHonorList</tt>.
     *
     * @return property value of slEpBrandHonorList
     */
    public List<SlEpBrandHonor> getSlEpBrandHonorList() {
        return slEpBrandHonorList;
    }

    /**
     * Setter method for property <tt>slEpBrandHonorList</tt>.
     *
     * @param slEpBrandHonorList value to be assigned to property slEpBrandHonorList
     */
    public void setSlEpBrandHonorList(List<SlEpBrandHonor> slEpBrandHonorList) {
        this.slEpBrandHonorList = slEpBrandHonorList;
    }
}
