package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by ren_qiang on 2016/8/1.
 */
public class BS2102104Bean extends BaseEntity {   //物流区
    private String lgcsAreaCode;
    //物流区名称
    private String lgcsAreaName;
    //地区
    private String cityCode;
    //地区名称
    private String cityName;
    //买家一级类型
    private String buyerType;
    //买家一级类型名称
    private String buyerTypeName;
    //买家二级类型
    private String buyerSubType;
    //买家二级类型名称
    private String buyerSubTypeName;
    //产品一级分类编码
    private String classesCode;
    //产品一级分类名称
    private String classesName;
    //产品二级分类编码
    private String machiningCode;
    //产品二级分类名称
    private String machiningName;
    //冻品管家组ID
    private String hkGroupId;
    //冻品管家组名称
    private String hkGroupName;

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

    public String getBuyerSubType() {
        return buyerSubType;
    }

    public void setBuyerSubType(String buyerSubType) {
        this.buyerSubType = buyerSubType;
    }

    public String getBuyerSubTypeName() {
        return buyerSubTypeName;
    }

    public void setBuyerSubTypeName(String buyerSubTypeName) {
        this.buyerSubTypeName = buyerSubTypeName;
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

    public String getHkGroupId() {
        return hkGroupId;
    }

    public void setHkGroupId(String hkGroupId) {
        this.hkGroupId = hkGroupId;
    }

    public String getHkGroupName() {
        return hkGroupName;
    }

    public void setHkGroupName(String hkGroupName) {
        this.hkGroupName = hkGroupName;
    }
}
