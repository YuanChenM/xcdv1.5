package com.msk.price.bean;


import com.hoperun.core.bean.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yang_yang on 2016/6/8
 */
@ApiModel(value = "ISP171183Param", description = "价盘信息req")
public class ISP171183Param extends BasePageParam {

    @ApiModelProperty(value = "产品类别编码")
    private String classesCode;

    @ApiModelProperty(value = "产品二级分类编码")
    private String machiningCode;

    @ApiModelProperty(value = "产品种类编码")
    private String breedCode;

    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;

    @ApiModelProperty(value = "产品净重编码")
    private String weightCode;

    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "物流区编码")
    private String logiAreaCode;

    @ApiModelProperty(value = "商品ID（一级分类2位+二级分类1位+品种2位+特征2位+净重2位+产品等级1位）")
    private String productId;

    @ApiModelProperty(value = "价盘周期编码")
    private String pricePeriod;

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

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }
}
