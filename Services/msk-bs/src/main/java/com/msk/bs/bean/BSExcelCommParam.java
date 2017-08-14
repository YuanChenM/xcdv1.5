package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlHouseAccount;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/8/3.
 */
public class BSExcelCommParam extends BaseParam {

    /**所属期的结束时间**/
    private String creationEndTime;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 地区编码 */
    private String cityCode;
    /** 地区名称 */
    private String cityName;
    /**二级分类**/
    private String reclassifyCode;
    /**一级分类**/
    private String categoryCode;
    /**管家一级分类**/
    private  String categoryName;
    /**管家二级分类**/
    private String reclassifyName;

    private String isHk;

    private String classesCode;

    private String machiningCode;

    private String buyerType;

    /**管家ID**/
    private List<SlHouseAccount> slHouseAccountList;

    public String getCreationEndTime() {
        return creationEndTime;
    }

    public void setCreationEndTime(String creationEndTime) {
        this.creationEndTime = creationEndTime;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getReclassifyCode() {
        return reclassifyCode;
    }

    public void setReclassifyCode(String reclassifyCode) {
        this.reclassifyCode = reclassifyCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getReclassifyName() {
        return reclassifyName;
    }

    public void setReclassifyName(String reclassifyName) {
        this.reclassifyName = reclassifyName;
    }

    public String getIsHk() {
        return isHk;
    }

    public void setIsHk(String isHk) {
        this.isHk = isHk;
    }

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

    public List<SlHouseAccount> getSlHouseAccountList() {
        return slHouseAccountList;
    }

    public void setSlHouseAccountList(List<SlHouseAccount> slHouseAccountList) {
        this.slHouseAccountList = slHouseAccountList;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
