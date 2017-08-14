package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;


/**
 * Created by geng_xingdong on 2016/6/16.
 */
public class ISL231199RsBean extends BaseEntity {

    /**
     * 区域code
     */
    private String lgcsCode;

    /**
     * 区域name
     */
    private String lgcsName;

    /**
     * 卖家编码
     */
    private String suppCode;

    /**
     * 卖家名称
     */
    private String suppName;

    /**
     * 产品质量标准定级
     */
    private Integer slQltGradeCode;

    /**
     * 主分类
     */
    private Integer slmainClass;

    /**
     * 产品分类编码
     */
    private String pdClassesCode;

    /**
     * 产品加工程度编码
     */
    private String pdMachiningCode;

    /**
     * 产品品种编码
     */
    private String pdBreedCode;

    /**
     * 产品特征编码
     */
    private String pdFeatureCode;

    /**
     * 产品净重编码
     */
    private String pdWeightCode;

    /**
     * 产品编码
     */
    private String pdCode;

    //Add for 2719 at 2016/09/22 by likai Start
    /**
     * 产品技术标准定级(加工质量标准)
     */
    private Integer slTncGradeCode;
    //Add for 2719 at 2016/09/22 by likai End

    private List<ISL231199RsBean> isl231199RsBeanList;

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public Integer getSlQltGradeCode() {
        return slQltGradeCode;
    }

    public void setSlQltGradeCode(Integer slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
    }

    public Integer getSlmainClass() {
        return slmainClass;
    }

    public void setSlmainClass(Integer slmainClass) {
        this.slmainClass = slmainClass;
    }

    public String getPdClassesCode() {
        return pdClassesCode;
    }

    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }

    public String getPdMachiningCode() {
        return pdMachiningCode;
    }

    public void setPdMachiningCode(String pdMachiningCode) {
        this.pdMachiningCode = pdMachiningCode;
    }

    public String getPdBreedCode() {
        return pdBreedCode;
    }

    public void setPdBreedCode(String pdBreedCode) {
        this.pdBreedCode = pdBreedCode;
    }

    public String getPdFeatureCode() {
        return pdFeatureCode;
    }

    public void setPdFeatureCode(String pdFeatureCode) {
        this.pdFeatureCode = pdFeatureCode;
    }

    public String getPdWeightCode() {
        return pdWeightCode;
    }

    public void setPdWeightCode(String pdWeightCode) {
        this.pdWeightCode = pdWeightCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public List<ISL231199RsBean> getIsl231199RsBeanList() {
        return isl231199RsBeanList;
    }

    public void setIsl231199RsBeanList(List<ISL231199RsBean> isl231199RsBeanList) {
        this.isl231199RsBeanList = isl231199RsBeanList;
    }

    public Integer getSlTncGradeCode() {
        return slTncGradeCode;
    }

    public void setSlTncGradeCode(Integer slTncGradeCode) {
        this.slTncGradeCode = slTncGradeCode;
    }
}
