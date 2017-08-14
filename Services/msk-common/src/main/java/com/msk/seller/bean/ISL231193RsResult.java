package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SoSalesRanking;

import java.util.List;

/**
 * Created by zhangchi on 2016/5/9.
 */
public class ISL231193RsResult extends BaseEntity {

    /**卖家ID*/
    private String slCode;

    /**卖家显示ID*/
    private String slCodeDis;

    /**卖家Account*/
    private String slAccount;

    /**产品质量标准定级*/
    private  Integer slQltGradeCode;

    /** 产品技术标准定级(加工质量标准) */
    private Integer slTncGradeCode;

    /**产品Code*/
    private String pdCode;

    /** 企业id */
    private String epId;

    /** 企业名称 */
    private String epName;

    /** 物流区编码 */
    private String lgcsAreaCode;

    /** 物流区名称 */
    private String lgcsAreaName;

    /** 卖家主分类 */
    private String slmainClass;

    private List<ISL231193RsResult> isl231193RsList;

    /** 卖家产品 */
    private List<SlProductBean> slProductList;

    private List<SoSalesRanking> soSalesRankingList;

    /** 买手账户和基本信息集合 */
    List<ISL231193RsResult> buyershopList;

    public List<ISL231193RsResult> getBuyershopList() {
        return buyershopList;
    }

    public void setBuyershopList(List<ISL231193RsResult> buyershopList) {
        this.buyershopList = buyershopList;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public Integer getSlQltGradeCode() {
        return slQltGradeCode;
    }

    public void setSlQltGradeCode(Integer slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
    }

    public String getSlmainClass() {
        return slmainClass;
    }

    public void setSlmainClass(String slmainClass) {
        this.slmainClass = slmainClass;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public List<SlProductBean> getSlProductList() {
        return slProductList;
    }

    public void setSlProductList(List<SlProductBean> slProductList) {
        this.slProductList = slProductList;
    }

    public List<ISL231193RsResult> getIsl231193RsList() {
        return isl231193RsList;
    }

    public void setIsl231193RsList(List<ISL231193RsResult> isl231193RsList) {
        this.isl231193RsList = isl231193RsList;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public List<SoSalesRanking> getSoSalesRankingList() {
        return soSalesRankingList;
    }

    public void setSoSalesRankingList(List<SoSalesRanking> soSalesRankingList) {
        this.soSalesRankingList = soSalesRankingList;
    }

    public Integer getSlTncGradeCode() {
        return slTncGradeCode;
    }

    public void setSlTncGradeCode(Integer slTncGradeCode) {
        this.slTncGradeCode = slTncGradeCode;
    }
}
