package com.msk.br.bean;

import com.hoperun.core.bean.BasePageParam;

/**
 * 买家标准产品池param
 * <p/>
 * Created by zhao_chen on 2016/06/02.
 */
public class BR121401Param extends BasePageParam {
    //报表id
    private String fileName;
    //报表名称
    private String fileId;

    private String fileServerId;
    //报表开始时间
    private String fileStartTime;
    //报表结束时间
    private String fileEndTime;
    //一级分类名称
    private String classesCode;
    //二级分类名称
    private String machiningCode;
    //买家类型
    private String buyersType;
    //物流区
    private String lgcsAreaName;
    //物流区id
    private String lgcsAreaCode;
    //地区（城市）名称
    private String cityName;
    //地区（城市）id
    private String cityCode;
    //区县
    private String districtName;
    //区县id
    private String districtCode;

    private String fileSuf;

    public String getFileServerId() {
        return fileServerId;
    }

    public void setFileServerId(String fileServerId) {
        this.fileServerId = fileServerId;
    }

    public String getFileSuf() {
        return fileSuf;
    }

    public void setFileSuf(String fileSuf) {
        this.fileSuf = fileSuf;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileStartTime() {
        return fileStartTime;
    }

    public void setFileStartTime(String fileStartTime) {
        this.fileStartTime = fileStartTime;
    }

    public String getFileEndTime() {
        return fileEndTime;
    }

    public void setFileEndTime(String fileEndTime) {
        this.fileEndTime = fileEndTime;
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

    public String getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(String buyersType) {
        this.buyersType = buyersType;
    }
}
