package com.msk.ssc.bean;

import com.msk.core.entity.SscPackageMaterialInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wang_shuai on 2016/7/1.
 */
@ApiModel(value = "SSC11304PackageBean", description = "SscPackageMaterialInfo实体的封装对象")
public class SSC11304PackageBean extends SscPackageMaterialInfo {
    @ApiModelProperty(value = "产品")
    private String product;

    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "产品等级名称")
    private String gradeName;

    @ApiModelProperty(value = "产品属性码")
    private String productAttrCode;

    @ApiModelProperty(value = "产品类别编码")
    private String classesCode;

    @ApiModelProperty(value = "产品类别名称")
    private String classesName;

    @ApiModelProperty(value = "产品加工类型编码")
    private String machiningCode;

    @ApiModelProperty(value = "产品加工类型名称")
    private String machiningName;

    @ApiModelProperty(value = "产品品种编码")
    private String breedCode;

    @ApiModelProperty(value = "产品品种名称")
    private String breedName;

    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;

    @ApiModelProperty(value = "产品特征名称")
    private String featureName;

    @ApiModelProperty(value = "产品净重编码")
    private String weightCode;

    @ApiModelProperty(value = "产品净重名称")
    private String weightName;

    @ApiModelProperty(value = "产品包装规格编码")
    private String normsCode;

    @ApiModelProperty(value = "产品包装规格名称")
    private String normsName;

    @ApiModelProperty(value = "包材提供方式")
    private String supplyModeName;

    @ApiModelProperty(value = "包材审核方式")
    private String auditMethodName;

    @ApiModelProperty(value = "包材结算方式")
    private String settlementMethodName;

    @ApiModelProperty(value = "总纸箱数")
    private int totalCartons;

    @ApiModelProperty(value = "总内袋数")
    private int totalInnerBags;


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getProductAttrCode() {
        return productAttrCode;
    }

    public void setProductAttrCode(String productAttrCode) {
        this.productAttrCode = productAttrCode;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getSupplyModeName() {
        return supplyModeName;
    }

    public void setSupplyModeName(String supplyModeName) {
        this.supplyModeName = supplyModeName;
    }

    public String getAuditMethodName() {
        return auditMethodName;
    }

    public void setAuditMethodName(String auditMethodName) {
        this.auditMethodName = auditMethodName;
    }

    public String getSettlementMethodName() {
        return settlementMethodName;
    }

    public void setSettlementMethodName(String settlementMethodName) {
        this.settlementMethodName = settlementMethodName;
    }

    public int getTotalCartons() {
        return totalCartons;
    }

    public void setTotalCartons(int totalCartons) {
        this.totalCartons = totalCartons;
    }

    public int getTotalInnerBags() {
        return totalInnerBags;
    }

    public void setTotalInnerBags(int totalInnerBags) {
        this.totalInnerBags = totalInnerBags;
    }

}