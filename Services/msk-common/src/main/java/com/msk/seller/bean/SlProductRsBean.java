package com.msk.seller.bean;

import com.msk.core.entity.SlProduct;

import java.math.BigDecimal;

/**
 * Created by liu_yan2 on 2016/6/21.
 */
public class SlProductRsBean extends SlProduct implements Comparable<SlProductRsBean> {


    /** 卖家编码显示用 */
    private String slCodeDis;

    /** 卖家类型中文名 */
    private String slMainClassName;

    /** 卖家主分类 */
    private String slMainClass;

    /** 卖家生产商编码 */
    private String slCodeManufacture;

    /** 卖家地区 */
    private String slLicAddr;

    /** 卖家名称 */
    private String slName;

    /** 生产商地区 */
    private String sllfAddr;

    /** 生产商名称  */
    private String sllfName;

    /** 品牌分类名称 */
    private String brandClassName;

    /** 品牌分类 */
    private String brandClass;

    /** 品牌名称 */
    private String brandName;

    /** 卖家产品包装ID */
    private String pkgCode;

    /** 产品编码 */
    private String pdCode;

    /** 产品名称 */
    private String pdName;

    /** 产品名称描述 */
    private String pdDesc;

    /** 产品标准 */
    private Integer  standardId;

    /** 卖家产品包装ID */
    private String normsCode;

    /**外包装规格*/
    private String normsOut;

    /**净重值*/
    private BigDecimal weightVal;

    /**产品质量标准定级名称*/
    private String slTncGradeName;

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlMainClassName() {
        return slMainClassName;
    }

    public void setSlMainClassName(String slMainClassName) {
        this.slMainClassName = slMainClassName;
    }

    public String getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }

    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    public String getSlLicAddr() {
        return slLicAddr;
    }

    public void setSlLicAddr(String slLicAddr) {
        this.slLicAddr = slLicAddr;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getSllfAddr() {
        return sllfAddr;
    }

    public void setSllfAddr(String sllfAddr) {
        this.sllfAddr = sllfAddr;
    }

    public String getSllfName() {
        return sllfName;
    }

    public void setSllfName(String sllfName) {
        this.sllfName = sllfName;
    }

    public String getBrandClassName() {
        return brandClassName;
    }

    public void setBrandClassName(String brandClassName) {
        this.brandClassName = brandClassName;
    }

    public String getBrandClass() {
        return brandClass;
    }

    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPkgCode() {
        return pkgCode;
    }

    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public Integer getStandardId() {
        return standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public BigDecimal getWeightVal() {
        return weightVal;
    }

    public void setWeightVal(BigDecimal weightVal) {
        this.weightVal = weightVal;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsOut() {
        return normsOut;
    }

    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    public String getSlTncGradeName() {
        return slTncGradeName;
    }

    public void setSlTncGradeName(String slTncGradeName) {
        this.slTncGradeName = slTncGradeName;
    }

    public String getPdDesc() {
        return pdDesc;
    }

    public void setPdDesc(String pdDesc) {
        this.pdDesc = pdDesc;
    }

    @Override
    public int compareTo(SlProductRsBean o) {
        int result = this.pdCode.compareTo(o.getPdCode());
        return result;
    }
}
