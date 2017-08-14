package com.msk.price.bean;


import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;

/**
 * OEM需求发布详细Param
 *
 */
public class SP171101Param extends BasePageParam {

    //区域名称
    private String lgcsName;
    //区域名称
    private String lgcsCode;

    /*产品名称*/
    private String pdName;

    //产品类别（一级分类名称）
    private String classesName;

    //二级分类名称
    private String machiningName;

    // 产品特征
    private String featureName;

    /*产品编码不到等级*/
    private String pdTypeCode;

    /*发布数量*/
    private String publishTotalNum;

    /*发布日期*/
    private String demandYearmonth;

    /**
     * 规格
     */
    private String breedName;



    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getPdTypeCode() {
        return pdTypeCode;
    }

    public void setPdTypeCode(String pdTypeCode) {
        this.pdTypeCode = pdTypeCode;
    }

    public String getPublishTotalNum() {
        return publishTotalNum;
    }

    public void setPublishTotalNum(String publishTotalNum) {
        this.publishTotalNum = publishTotalNum;
    }

    public String getDemandYearmonth() {
        return demandYearmonth;
    }

    public void setDemandYearmonth(String demandYearmonth) {
        this.demandYearmonth = demandYearmonth;
    }

    private static final long serialVersionUID = 1L;


}
