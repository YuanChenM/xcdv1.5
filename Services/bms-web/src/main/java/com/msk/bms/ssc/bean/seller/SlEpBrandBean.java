package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.SlEpBrand;

import java.util.List;

/**
 * 企业产品品牌bean
 */
public class SlEpBrandBean extends SlEpBrand {
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
    private List<SlEpBrandHonorBean> slEpBrandHonorList;
    // 企业名称
    private String epName;
    /**品牌证书图片路径*/
    private String brandPath;
    /**品牌包装图片路径*/
    private String brandPacPath;

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getBrandPath() {
        return brandPath;
    }

    public void setBrandPath(String brandPath) {
        this.brandPath = brandPath;
    }

    public String getBrandPacPath() {
        return brandPacPath;
    }

    public void setBrandPacPath(String brandPacPath) {
        this.brandPacPath = brandPacPath;
    }

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

    public List<SlEpBrandHonorBean> getSlEpBrandHonorList() {
        return slEpBrandHonorList;
    }

    public void setSlEpBrandHonorList(List<SlEpBrandHonorBean> slEpBrandHonorList) {
        this.slEpBrandHonorList = slEpBrandHonorList;
    }
}
