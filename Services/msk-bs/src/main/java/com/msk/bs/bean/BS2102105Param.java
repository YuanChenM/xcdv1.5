package com.msk.bs.bean;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yang_chunyan on 2016/8/1.
 */
public class BS2102105Param extends BasePageParam {
    /**
     * 冻品管家组ID*
     */
    private String hkGroupId;
    /**
     * 买手店ID*
     */
    private String slCode;
    /**
     * 冻品管家ID*
     */
    private String houseCode;
    /**
     * 备注*
     */
    private String flag6;
    /**
     * 区域编码*
     */
    private String lgcsAreaCode;
    /**
     * 区域名称*
     */
    private String lgcsAreaName;
    /**
     * 地区编码*
     */
    private String cityCode;
    /**
     * 地区名称*
     */
    private String cityName;
    /**
     * 买家类型*
     */
    private String buyerType;
    /**
     * 买家类型名*
     */
    private String buyerTypeName;
    /**二级分类**/
    private String reclassifyCode;
    /**一级分类**/
    private String categoryCode;
    /**一级编码**/
    private String classesCode;
    /**一级名称**/
    private String classesName;
    /**二级编码**/
    private String machiningCode;
    /**二级名称**/
    private String machiningName;
    /**
     * 冻品管家组名*
     */
    private String hkGroupName;

    private List<Long> hkGroupIds;
    /**冻品管家离开冻品管家组的时间**/
    private Date leaveTime;
    /**
     * 冻品管家主键集合*
     */
    private List<BS210110501Param> houseList;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public List<BS210110501Param> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<BS210110501Param> houseList) {
        this.houseList = houseList;
    }

    public String getFlag6() {
        return flag6;
    }

    public void setFlag6(String flag6) {
        this.flag6 = flag6;
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

    public String getHkGroupName() {
        return hkGroupName;
    }

    public void setHkGroupName(String hkGroupName) {
        this.hkGroupName = hkGroupName;
    }

    public List<Long> getHkGroupIds() {
        return hkGroupIds;
    }

    public void setHkGroupIds(List<Long> hkGroupIds) {
        this.hkGroupIds = hkGroupIds;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }
}