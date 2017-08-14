package com.msk.price.bean;


import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;

/**
 * Created by xu_wei
 */
public class SP171103Param extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    //序号
    private  Integer idCode;
    //发布ID
    private String demandId;
    //投标ID
    private String oemId;
    //物流区CODE
    private String lgcsCode;
    //物流区名
    private String lgcsName;
    //产品一级分类编码
    private String classesCode;
    //产品二级分类编码
    private String machiningCode;
    //品种编码
    private String breedCode;
    //特征编码
    private String featureCode;
    //净重编码
    private String weightCode;
    //产品一级分类名称
    private String classesName;
    //产品二级分类名称
    private String machiningName;
    //品种
    private String breedName;
    //特征
    private String featureName;
    //净重
    private String weightName;
    //发布数量
    private BigDecimal publishNum;
    //卖家编码
    private String slCode;
    //卖家名称
    private String slName;
    //申报数量
    private String applyNum;
    //申报价格
    private String applyPrice;
    //投标状态
    private Integer demandApplyStatus;
    //投标状态
    private String demandApply;
    //卖家编码
    private String pdCode;
    //卖家编码
    private String pdName;

    public Integer getIdCode() {
        return idCode;
    }

    public void setIdCode(Integer idCode) {
        this.idCode = idCode;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

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

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
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

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public BigDecimal getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(BigDecimal publishNum) {
        this.publishNum = publishNum;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public String getApplyPrice() {
        return applyPrice;
    }

    public void setApplyPrice(String applyPrice) {
        this.applyPrice = applyPrice;
    }

    public Integer getDemandApplyStatus() {
        return demandApplyStatus;
    }

    public void setDemandApplyStatus(Integer demandApplyStatus) {
        this.demandApplyStatus = demandApplyStatus;
    }

    public String getDemandApply() {
        return demandApply;
    }

    public void setDemandApply(String demandApply) {
        this.demandApply = demandApply;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }
}
