package com.msk.bms.ssc.bean;

import com.hoperun.core.bean.BaseParam;


import java.math.BigDecimal;

/**
 * 买家标准产品池param
 * <p/>
 * Created by zhao_chen on 2016/06/28.
 */
public class SSC11302Param extends BaseParam {

    private String type;
    private String bidProjectNo;

    //中标确认书ID
    private String bidId;
    // 招标项目名称
    private String bidProjectName;
    //中标厂商名称
    private String bidProducerName;

    private String bidOpenDate;

    //开标开始日期
    private String bidStartDate;

    private String bidEndDate;
    /////////////////////////////////////////////
    //产品名称
    private String pdName;

    private String pdCode;

    //等级
    private String gradeCode;
    /** 产品净重编码 */
    private java.lang.String weightCode;
    /** 产品净重名称 */
    private java.lang.String weightName;
    /** 产品箱数 */
    private java.lang.Integer productBox;


    /** 不含包装离岸价 */
    private java.math.BigDecimal fobFreePackage;
    /** 包材成本 */
    private java.math.BigDecimal packageCost;
    /** 含包装离岸价 */
    private java.math.BigDecimal fobIncludePackage;
    /** 干线运费 */
    private java.math.BigDecimal trunkFreight;
    /** 到岸价 */
    private java.math.BigDecimal cif;
    /** 本次结算标准价 */
    private java.math.BigDecimal settkementStandardPrice;
    /** 货值 */
    private java.math.BigDecimal productValue;

    /** 备注 */
    private String remark;

    /** 甲方卖家编码 */
    private String slCode;

    /** 乙方企业ID*/
    private Long supplierId;

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public String getBidProjectName() {
        return bidProjectName;
    }

    public void setBidProjectName(String bidProjectName) {
        this.bidProjectName = bidProjectName;
    }

    public String getBidProducerName() {
        return bidProducerName;
    }

    public void setBidProducerName(String bidProducerName) {
        this.bidProducerName = bidProducerName;
    }

    public String getBidOpenDate() {
        return bidOpenDate;
    }

    public void setBidOpenDate(String bidOpenDate) {
        this.bidOpenDate = bidOpenDate;
    }

    public String getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(String bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public String getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(String bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

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

    public Integer getProductBox() {
        return productBox;
    }

    public void setProductBox(Integer productBox) {
        this.productBox = productBox;
    }

    public BigDecimal getFobFreePackage() {
        return fobFreePackage;
    }

    public void setFobFreePackage(BigDecimal fobFreePackage) {
        this.fobFreePackage = fobFreePackage;
    }

    public BigDecimal getPackageCost() {
        return packageCost;
    }

    public void setPackageCost(BigDecimal packageCost) {
        this.packageCost = packageCost;
    }

    public BigDecimal getFobIncludePackage() {
        return fobIncludePackage;
    }

    public void setFobIncludePackage(BigDecimal fobIncludePackage) {
        this.fobIncludePackage = fobIncludePackage;
    }

    public BigDecimal getTrunkFreight() {
        return trunkFreight;
    }

    public void setTrunkFreight(BigDecimal trunkFreight) {
        this.trunkFreight = trunkFreight;
    }

    public BigDecimal getCif() {
        return cif;
    }

    public void setCif(BigDecimal cif) {
        this.cif = cif;
    }

    public BigDecimal getSettkementStandardPrice() {
        return settkementStandardPrice;
    }

    public void setSettkementStandardPrice(BigDecimal settkementStandardPrice) {
        this.settkementStandardPrice = settkementStandardPrice;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBidProjectNo() {
        return bidProjectNo;
    }

    public void setBidProjectNo(String bidProjectNo) {
        this.bidProjectNo = bidProjectNo;
    }
}
