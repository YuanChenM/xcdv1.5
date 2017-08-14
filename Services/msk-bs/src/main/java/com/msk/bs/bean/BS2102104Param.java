package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;

/**
 * Created by ren_qiang on 2016/8/1.
 */
public class BS2102104Param extends RsPageParam {

    //物流区
    private String lgcsAreaCode;

    //物流区名称
    private String lgcsAreaName;

    //地区编码
    private String cityCode;

    //地区名称
    private  String cityName;

    //买家类型
    private String buyerType;

    //买家类型名称
    private String buyerTypeName;

    //产品一级分类
    private String classesCode;

    //产品一级名称
    private String classesName;

    //产品二级分类
    private  String machiningCode;

    //产品二级名称
    private String machiningName;

    //冻品管家组ID
    private String dpGroupId;

    //冻品管家组名称
    private  String dpGroupName;

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
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

    public String getDpGroupId() {
        return dpGroupId;
    }

    public void setDpGroupId(String dpGroupId) {
        this.dpGroupId = dpGroupId;
    }

    public String getDpGroupName() {
        return dpGroupName;
    }

    public void setDpGroupName(String dpGroupName) {
        this.dpGroupName = dpGroupName;
    }
}
