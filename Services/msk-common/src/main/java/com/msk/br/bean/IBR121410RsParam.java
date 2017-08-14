package com.msk.br.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.Date;
import java.util.List;

public class IBR121410RsParam extends BasePageParam {

    private String fileId;
    //
    private String fileServerId;

    private String fileServerIp;

    /** 文件名 */
    private String fileName;
    private String fileSuf;
    /** 报表开始时间 */
    private String fileStartTime;
    /** 报表结束时间 */
    private String fileEndTime;
    /** 文件创建时间 */
    private Date fileCreateTime;
    /** 0：未生成，1：已生成 */
    private String fileStatus;

    private String lgcsAreaCode;

    private String cityCode;

    private String buyerType;

    private String buyerSubType;

    private String classesCode;

    private String machiningCodeU;

    private String marketingsStatus;


    private List<IBR121410RsBean> houseList;
    //判断成功条件
    private String  dataCount;
    //判断文件状态
    private String fileStatusFlag;


    private String lgcsAreaName;
    private String cityName;
    private String classesName;
    private String machiningNameU;
    private boolean flag;
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getDataCount() {
        return dataCount;
    }

    public void setDataCount(String dataCount) {
        this.dataCount = dataCount;
    }

    public String getFileStatusFlag() {
        return fileStatusFlag;
    }

    public void setFileStatusFlag(String fileStatusFlag) {
        this.fileStatusFlag = fileStatusFlag;
    }

    public String getFileServerId() {
        return fileServerId;
    }

    public void setFileServerId(String fileServerId) {
        this.fileServerId = fileServerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuf() {
        return fileSuf;
    }

    public void setFileSuf(String fileSuf) {
        this.fileSuf = fileSuf;
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

    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
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

    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    public String getFileServerIp() {
        return fileServerIp;
    }

    public void setFileServerIp(String fileServerIp) {
        this.fileServerIp = fileServerIp;
    }

    public List<IBR121410RsBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBR121410RsBean> houseList) {
        this.houseList = houseList;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningNameU() {
        return machiningNameU;
    }

    public void setMachiningNameU(String machiningNameU) {
        this.machiningNameU = machiningNameU;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
