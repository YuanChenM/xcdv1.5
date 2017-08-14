package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by sun_jiaju on 2016/5/9.
 */
public class SP171104Bean extends BaseEntity {
    // 是否同意档案卡
    private int isAgree;
    // 是否确认
    private int isConfirm;
    // 申报数量
    private BigDecimal applyNum;
    // 分配数量
    private BigDecimal passNum;

    // 类型明细
    private String ratioTypeDetail;
    // 类型值
    private BigDecimal ratioTypeDetailVal;
    // 等级占比1
    private BigDecimal gradeRatio1;
    // 等级占比2
    private BigDecimal gradeRatio2;
    // 等级占比3
    private BigDecimal gradeRatio3;
    // 发布总数量
    private BigDecimal publishTotalNum;
    // 企业名称
    private String epName;

    // 卖家编码
    private  String sellerCode;
    // 区域编码
    private  String districtCode;
    // 销售量
    private  BigDecimal orderCount;
    // 销售量所占百分比
    private  BigDecimal percent;

    public BigDecimal getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(BigDecimal applyNum) {
        this.applyNum = applyNum;
    }

    public BigDecimal getPassNum() {
        return passNum;
    }

    public void setPassNum(BigDecimal passNum) {
        this.passNum = passNum;
    }

    public String getRatioTypeDetail() {
        return ratioTypeDetail;
    }

    public void setRatioTypeDetail(String ratioTypeDetail) {
        this.ratioTypeDetail = ratioTypeDetail;
    }

    public BigDecimal getRatioTypeDetailVal() {
        return ratioTypeDetailVal;
    }

    public void setRatioTypeDetailVal(BigDecimal ratioTypeDetailVal) {
        this.ratioTypeDetailVal = ratioTypeDetailVal;
    }

    public BigDecimal getGradeRatio1() {
        return gradeRatio1;
    }

    public void setGradeRatio1(BigDecimal gradeRatio1) {
        this.gradeRatio1 = gradeRatio1;
    }

    public BigDecimal getGradeRatio2() {
        return gradeRatio2;
    }

    public void setGradeRatio2(BigDecimal gradeRatio2) {
        this.gradeRatio2 = gradeRatio2;
    }

    public BigDecimal getGradeRatio3() {
        return gradeRatio3;
    }

    public void setGradeRatio3(BigDecimal gradeRatio3) {
        this.gradeRatio3 = gradeRatio3;
    }

    public BigDecimal getPublishTotalNum() {
        return publishTotalNum;
    }

    public void setPublishTotalNum(BigDecimal publishTotalNum) {
        this.publishTotalNum = publishTotalNum;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public int getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(int isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public BigDecimal getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(BigDecimal orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
