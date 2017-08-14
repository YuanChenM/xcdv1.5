package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryPlanBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/06/30.
 */
@ApiModel(value = "SSC11304DeliveryPlanBean", description = "SscDeliveryPlanBasic实体的封装对象")
public class SSC11304DeliveryPlanBean extends SscDeliveryPlanBasic {
    @ApiModelProperty(value = "产品名称")
    private String pdName;

    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "产品等级名")
    private String gradeName;

    @ApiModelProperty(value = "到货日期")
    private String arriveDateStr;

    @ApiModelProperty(value = "有效箱数")
    private  Integer effctNum;

    @ApiModelProperty(value = "产品编号")
    private List<String> productCodes;

    @ApiModelProperty(value = "到货箱数")
    private List<Integer> arriveQuts;

    @ApiModelProperty(value = "产品重量")
    private List<BigDecimal> weihtS;


    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
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

    public String getArriveDateStr() {
        return arriveDateStr;
    }

    public void setArriveDateStr(String arriveDateStr) {
        this.arriveDateStr = arriveDateStr;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }

    public List<Integer> getArriveQuts() {
        return arriveQuts;
    }

    public void setArriveQuts(List<Integer> arriveQuts) {
        this.arriveQuts = arriveQuts;
    }

    public List<BigDecimal> getWeihtS() {
        return weihtS;
    }

    public void setWeihtS(List<BigDecimal> weihtS) {
        this.weihtS = weihtS;
    }

    public Integer getEffctNum() {
        return effctNum;
    }

    public void setEffctNum(Integer effctNum) {
        this.effctNum = effctNum;
    }

}