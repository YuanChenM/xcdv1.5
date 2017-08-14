package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@ApiModel(value = "PDInfoResult",description = "result")
public class PDInfoResult extends ProductBean implements Serializable{

    @ApiModelProperty(value = "一级分类编码")
    private String classesCode;

    @ApiModelProperty(value = "一级分类名称")
    private String classesName;

    @ApiModelProperty(value = "二级分类编码")
    private String machiningCode;

    @ApiModelProperty(value = "二级分类名称")
    private String machiningName;

    @ApiModelProperty(value = "品种编码")
    private String breedCode;

    @ApiModelProperty(value = "品种名称")
    private String breedName;

    @ApiModelProperty(value = "特征编码")
    private String featureCode;

    @ApiModelProperty(value = "特征名称")
    private String featureName;

    @ApiModelProperty(value = "净重编码")
    private String weightCode;

    @ApiModelProperty(value = "净重名称")
    private String weightName;

    @ApiModelProperty(value = "净重值")
    private BigDecimal weightVal;

    @ApiModelProperty(value = "等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "等级名称")
    private String gradeName;

    @ApiModelProperty(value = "国家编码")
    private String countryCode;

    @ApiModelProperty(value = "产品编码（位数不固定）")
    private String pdCode;

    @ApiModelProperty(value = "区域编码")
    private String lgcsCode;

    @ApiModelProperty(value = "区域名称")
    private String lgcsName;

    @ApiModelProperty(value = "包装编码")
    private String normsCode;

    @ApiModelProperty(value = "包装名称")
    private String normsName;

    @ApiModelProperty(value = "单个产品净重")
    private String normsSuttle;

    @ApiModelProperty(value = "单个产品规格净重误差范围")
    private String normsError;

    @ApiModelProperty(value = "内包装净重/个数")
    private String normsNumber;

    @ApiModelProperty(value = "内包装尺寸")
    private String normsSize;

    @ApiModelProperty(value = "内包装材质及技术标准")
    private String normsTexture;

    @ApiModelProperty(value = "外包装规格")
    private String normsOut;

    @ApiModelProperty(value = "外包装净重/毛重")
    private String normsKg;

    @ApiModelProperty(value = "外包装尺寸")
    private String normsOutSize;

    @ApiModelProperty(value = "外包装材质及技术标准")
    private String normsOutTexture;

    @ApiModelProperty(value = "包装长度")
    private BigDecimal normsLength;

    @ApiModelProperty(value = "包装宽度")
    private BigDecimal normsWidth;

    @ApiModelProperty(value = "包装高度")
    private BigDecimal normsHeight;

    @ApiModelProperty(value = "包装体积")
    private BigDecimal normsVolume;

    @ApiModelProperty(value = "外包装宽度")
    private BigDecimal netweightOut;

    @ApiModelProperty(value = "外包装毛重数值")
    private String grossweightOut;

    @ApiModelProperty(value = "产品名称")
    private String pdName;

    @ApiModelProperty(value = "产品学名")
    private String scientificName;

    @ApiModelProperty(value = "产品俗名")
    private String localName;

    @ApiModelProperty(value = "产品销售名")
    private String salesName;

    @ApiModelProperty(value = "分类编码")
    private String levelCode;

    @ApiModelProperty(value = "分类名称")
    private String levelName;

    @ApiModelProperty(value = "树形分类编码")
    private String classestreeCode;

    @ApiModelProperty(value = "标准ID")
    private Long standardId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "价盘ID")
    private String pricecycleId;

    @ApiModelProperty(value = "tcProviderId")
    private String tcProviderId;

    @ApiModelProperty(value = "卖家供应商CODE")
    private String providerCode;

    @ApiModelProperty(value = "卖家供应商名称")
    private String providerName;

    @ApiModelProperty(value = "netweightInner")
    private BigDecimal netweightInner;

    @ApiModelProperty(value = "featrueFlag")
    private Integer featrueFlag;

    @ApiModelProperty(value = "salesTarget")
    private String salesTarget;

    @ApiModelProperty(value = "machiningWay")
    private String machiningWay;

    @ApiModelProperty(value = "applyDateTime")
    private String applyDateTime;

    @ApiModelProperty(value = "auditDateTime")
    private String auditDateTime;

    @ApiModelProperty(value = "status")
    private String status;


    /**
     * Getter method for property <tt>normsCode</tt>.
     *
     * @return property value of normsCode
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * Setter method for property <tt>normsCode</tt>.
     *
     * @param normsCode value to be assigned to property normsCode
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * Getter method for property <tt>normsName</tt>.
     *
     * @return property value of normsName
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * Setter method for property <tt>normsName</tt>.
     *
     * @param normsName value to be assigned to property normsName
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * Getter method for property <tt>normsSuttle</tt>.
     *
     * @return property value of normsSuttle
     */
    public String getNormsSuttle() {
        return normsSuttle;
    }

    /**
     * Setter method for property <tt>normsSuttle</tt>.
     *
     * @param normsSuttle value to be assigned to property normsSuttle
     */
    public void setNormsSuttle(String normsSuttle) {
        this.normsSuttle = normsSuttle;
    }

    /**
     * Getter method for property <tt>normsError</tt>.
     *
     * @return property value of normsError
     */
    public String getNormsError() {
        return normsError;
    }

    /**
     * Setter method for property <tt>normsError</tt>.
     *
     * @param normsError value to be assigned to property normsError
     */
    public void setNormsError(String normsError) {
        this.normsError = normsError;
    }

    /**
     * Getter method for property <tt>normsNumber</tt>.
     *
     * @return property value of normsNumber
     */
    public String getNormsNumber() {
        return normsNumber;
    }

    /**
     * Setter method for property <tt>normsNumber</tt>.
     *
     * @param normsNumber value to be assigned to property normsNumber
     */
    public void setNormsNumber(String normsNumber) {
        this.normsNumber = normsNumber;
    }

    /**
     * Getter method for property <tt>normsSize</tt>.
     *
     * @return property value of normsSize
     */
    public String getNormsSize() {
        return normsSize;
    }

    /**
     * Setter method for property <tt>normsSize</tt>.
     *
     * @param normsSize value to be assigned to property normsSize
     */
    public void setNormsSize(String normsSize) {
        this.normsSize = normsSize;
    }

    /**
     * Getter method for property <tt>normsTexture</tt>.
     *
     * @return property value of normsTexture
     */
    public String getNormsTexture() {
        return normsTexture;
    }

    /**
     * Setter method for property <tt>normsTexture</tt>.
     *
     * @param normsTexture value to be assigned to property normsTexture
     */
    public void setNormsTexture(String normsTexture) {
        this.normsTexture = normsTexture;
    }

    /**
     * Getter method for property <tt>normsOut</tt>.
     *
     * @return property value of normsOut
     */
    public String getNormsOut() {
        return normsOut;
    }

    /**
     * Setter method for property <tt>normsOut</tt>.
     *
     * @param normsOut value to be assigned to property normsOut
     */
    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    /**
     * Getter method for property <tt>normsKg</tt>.
     *
     * @return property value of normsKg
     */
    public String getNormsKg() {
        return normsKg;
    }

    /**
     * Setter method for property <tt>normsKg</tt>.
     *
     * @param normsKg value to be assigned to property normsKg
     */
    public void setNormsKg(String normsKg) {
        this.normsKg = normsKg;
    }

    /**
     * Getter method for property <tt>normsOutSize</tt>.
     *
     * @return property value of normsOutSize
     */
    public String getNormsOutSize() {
        return normsOutSize;
    }

    /**
     * Setter method for property <tt>normsOutSize</tt>.
     *
     * @param normsOutSize value to be assigned to property normsOutSize
     */
    public void setNormsOutSize(String normsOutSize) {
        this.normsOutSize = normsOutSize;
    }

    /**
     * Getter method for property <tt>normsOutTexture</tt>.
     *
     * @return property value of normsOutTexture
     */
    public String getNormsOutTexture() {
        return normsOutTexture;
    }

    /**
     * Setter method for property <tt>normsOutTexture</tt>.
     *
     * @param normsOutTexture value to be assigned to property normsOutTexture
     */
    public void setNormsOutTexture(String normsOutTexture) {
        this.normsOutTexture = normsOutTexture;
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

    public BigDecimal getWeightVal() {
        return weightVal;
    }

    public void setWeightVal(BigDecimal weightVal) {
        this.weightVal = weightVal;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPdName() {
        String name = this.classesName + "/" + this.machiningName + "/" + this.breedName + "/" + this.featureName + "/" + this.weightName;
        if(StringUtils.hasLength(this.gradeCode))
        {
            name += "/" + this.gradeName;
        }
        if(name.contains("null"))
            return null;
        return name;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getPdCode() {
        String name = this.classesCode + this.machiningCode + this.breedCode + this.featureCode + this.weightCode;
        if(StringUtils.hasLength(this.gradeCode))
        {
            name += this.gradeCode;
        }
        if(name.contains("null"))
            return null;
        return name;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
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

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public BigDecimal getNormsLength() {
        return normsLength;
    }

    public void setNormsLength(BigDecimal normsLength) {
        this.normsLength = normsLength;
    }

    public BigDecimal getNormsWidth() {
        return normsWidth;
    }

    public void setNormsWidth(BigDecimal normsWidth) {
        this.normsWidth = normsWidth;
    }

    public BigDecimal getNormsHeight() {
        return normsHeight;
    }

    public void setNormsHeight(BigDecimal normsHeight) {
        this.normsHeight = normsHeight;
    }

    public BigDecimal getNormsVolume() {
        return normsVolume;
    }

    public void setNormsVolume(BigDecimal normsVolume) {
        this.normsVolume = normsVolume;
    }

    public BigDecimal getNetweightOut() {
       /* if(!StringUtils.hasLength(this.normsCode) && !StringUtils.hasLength(this.normsOutTexture) && !StringUtils.hasLength(classesCode))
            return BigDecimal.ZERO;*/
        return netweightOut;
    }

    public void setNetweightOut(BigDecimal netweightOut) {
        this.netweightOut = netweightOut;
    }

    public String getGrossweightOut() {
        return grossweightOut;
    }

    public void setGrossweightOut(String grossweightOut) {
        this.grossweightOut = grossweightOut;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getClassestreeCode() {
        return classestreeCode;
    }

    public void setClassestreeCode(String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPricecycleId() {
        return pricecycleId;
    }

    public void setPricecycleId(String pricecycleId) {
        this.pricecycleId = pricecycleId;
    }

    public String getTcProviderId() {
        return tcProviderId;
    }

    public void setTcProviderId(String tcProviderId) {
        this.tcProviderId = tcProviderId;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public BigDecimal getNetweightInner() {
        return netweightInner;
    }

    public void setNetweightInner(BigDecimal netweightInner) {
        this.netweightInner = netweightInner;
    }

    public Integer getFeatrueFlag() {
        return featrueFlag;
    }

    public void setFeatrueFlag(Integer featrueFlag) {
        this.featrueFlag = featrueFlag;
    }

    public String getSalesTarget() {
        return salesTarget;
    }

    public void setSalesTarget(String salesTarget) {
        this.salesTarget = salesTarget;
    }

    public String getMachiningWay() {
        return machiningWay;
    }

    public void setMachiningWay(String machiningWay) {
        this.machiningWay = machiningWay;
    }

    public String getApplyDateTime() {
        return applyDateTime;
    }

    public void setApplyDateTime(String applyDateTime) {
        this.applyDateTime = applyDateTime;
    }

    public String getAuditDateTime() {
        return auditDateTime;
    }

    public void setAuditDateTime(String auditDateTime) {
        this.auditDateTime = auditDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStandardId() {
        return standardId;
    }

    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }
}
