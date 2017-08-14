package com.msk.bs.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.List;

/**
 * Created by ren_qiang on 2016/8/4.
 */
public class BR121305Param extends BasePageParam {

    //物流区
    private String lgcsAreaCode;
    //地区
    private String cityCode;
    //买家一级类型
    private String buyerType;
    //买家二级类型
    private String buyerSubType;
    //产品一级分类编码
    private String classesCode;
    //产品二级分类编码
    private String machiningCode;
    //所属期开始yyyyMMdd
    private String termStart;
    //所属期结束yyyyMMdd
    private String termEnd;
    //冻品管家组ID
    private String hkGroupId;
    //冻品管家组名称
    private String hkGroupName;
    //冻品管家主键信息
    private List<BR121305Param> houseList;
    /**所属期的开始时间**/
    private String creationStartTime;
    /**所属期的结束时间**/
    private String creationEndTime;

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

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getBuyerSubType() {
        return buyerSubType;
    }

    public void setBuyerSubType(String buyerSubType) {
        this.buyerSubType = buyerSubType;
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

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }

    public List<BR121305Param> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<BR121305Param> houseList) {
        this.houseList = houseList;
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

    public String getCreationStartTime() {
        return creationStartTime;
    }

    public void setCreationStartTime(String creationStartTime) {
        this.creationStartTime = creationStartTime;
    }

    public String getCreationEndTime() {
        return creationEndTime;
    }

    public void setCreationEndTime(String creationEndTime) {
        this.creationEndTime = creationEndTime;
    }
}
