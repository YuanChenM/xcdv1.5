package com.msk.batch.br.bean;

import com.msk.core.entity.BrPdPoolInfo;

/**
 * Created by jiang_tengfei on 2016/6/7.
 * 产品标准池Bean
 */
public class BBR12140202Bean extends BrPdPoolInfo {

    //排名
    private int raking;
    //产品类型
    private String prodType;
    //需求等级
    private String demandLevel;

    /** CLA_ID */
    private Long claId;
    /** CLASSES_CODE */
    private String classCode;
    /** CLASSES_NAME */
    private String className;
    /** MACHINING_CODE */
    private String machinCode;
    /** MACHINING_NAME */
    private String machinName;
    /** SCIENTIFIC_NAME */
    private String scienceName;
    /** LOCAL_NAME */
    private String traditionalName;
    /** SALES_NAME */
    private String saleName;
    /** PLACE_ORIGIN */
    private String placeOrigin;
    /** PLACE_CURRENT */
    private String placeCurrent;
    /** SOURCE */
    private String source;
    /** CHILD_TYPE */
    private String childType;
    /** FEED_TYPE */
    private String feedType;
    /** FEED_PERIOD */
    private String feedPeriod;

    public int getRaking() {
        return raking;
    }

    public void setRaking(int raking) {
        this.raking = raking;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getDemandLevel() {
        return demandLevel;
    }

    public void setDemandLevel(String demandLevel) {
        this.demandLevel = demandLevel;
    }

    public Long getClaId() {
        return claId;
    }

    public void setClaId(Long claId) {
        this.claId = claId;
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public String getPlaceCurrent() {
        return placeCurrent;
    }

    public void setPlaceCurrent(String placeCurrent) {
        this.placeCurrent = placeCurrent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getFeedPeriod() {
        return feedPeriod;
    }

    public void setFeedPeriod(String feedPeriod) {
        this.feedPeriod = feedPeriod;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMachinCode() {
        return machinCode;
    }

    public void setMachinCode(String machinCode) {
        this.machinCode = machinCode;
    }

    public String getMachinName() {
        return machinName;
    }

    public void setMachinName(String machinName) {
        this.machinName = machinName;
    }

    public String getScienceName() {
        return scienceName;
    }

    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    public String getTraditionalName() {
        return traditionalName;
    }

    public void setTraditionalName(String traditionalName) {
        this.traditionalName = traditionalName;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }
}
