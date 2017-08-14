package com.msk.batch.br.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by jiang_tengfei on 2016/6/12.
 */
public class BBR12140201Bean extends BaseEntity {

    //买家地区编码
    private String buyerCityCode;
    //买家地区名称
    private String buyerCityName;
    //买家类型
    private String buyerType;
    //产品加工类型编码
    private String machiningCode;
    //产品加工类型名称
    private String machiningName;
    //产品分类名称
    private String classesName;
    //产品分类
    private String classesCode;
    //买家县区编码
    private String buyerDistrictCode;
    //买家县区名称
    private String buyerDistrictName;
    //物流区编码
    private String buyerLgcsAreaCode;
    //物流地区名称
    private String buyerLgcsAreaName;
    //月份
    private String month;
    //1物流区 2地区 3区县 4物流区菜场 5地区菜场
    private int flag;

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getBuyerCityCode() {
        return buyerCityCode;
    }

    public void setBuyerCityCode(String buyerCityCode) {
        this.buyerCityCode = buyerCityCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getBuyerDistrictCode() {
        return buyerDistrictCode;
    }

    public void setBuyerDistrictCode(String buyerDistrictCode) {
        this.buyerDistrictCode = buyerDistrictCode;
    }

    public String getBuyerLgcsAreaCode() {
        return buyerLgcsAreaCode;
    }

    public void setBuyerLgcsAreaCode(String buyerLgcsAreaCode) {
        this.buyerLgcsAreaCode = buyerLgcsAreaCode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getBuyerCityName() {
        return buyerCityName;
    }

    public void setBuyerCityName(String buyerCityName) {
        this.buyerCityName = buyerCityName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getBuyerDistrictName() {
        return buyerDistrictName;
    }

    public void setBuyerDistrictName(String buyerDistrictName) {
        this.buyerDistrictName = buyerDistrictName;
    }

    public String getBuyerLgcsAreaName() {
        return buyerLgcsAreaName;
    }

    public void setBuyerLgcsAreaName(String buyerLgcsAreaName) {
        this.buyerLgcsAreaName = buyerLgcsAreaName;
    }
}
