package com.msk.br.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.Date;

/**
 * Created by tao_zhifa on 2016/7/26.
 */
public class BR121406RsParam extends BasePageParam{
    private String fileId;
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
    /** 1：买家池，2：买家产品池，3：上线状态买家池 */
    private String poolType;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 地区编码 */
    private String cityCode;
    /** 买家类型 */
    private String buyerType;
    /** BUYER_SUB_TYPE */
    private String buyerSubType;
    /** 一级产品编码 */
    private String classesCode;
    /** 二级产品编码 */
    private String machiningCode;
    private String machiningCodeU;
    private String marketingsPeriodName;
    /** 上线状态 */
    private String marketingsStatus;
    /** 市场ID */
    private String marketId;
    /** 市场名称 */
    private String marketName;
    //是否覆盖
    private boolean flag;
    //判断成功条件
    private String  dataCount;
    //判断文件状态
    private String fileStatusFlag;
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileStatusFlag() {
        return fileStatusFlag;
    }

    public void setFileStatusFlag(String fileStatusFlag) {
        this.fileStatusFlag = fileStatusFlag;
    }

    public String getDataCount() {
        return dataCount;
    }

    public void setDataCount(String dataCount) {
        this.dataCount = dataCount;
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

    public String getPoolType() {
        return poolType;
    }

    public void setPoolType(String poolType) {
        this.poolType = poolType;
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

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMarketingsPeriodName() {
        return marketingsPeriodName;
    }

    public void setMarketingsPeriodName(String marketingsPeriodName) {
        this.marketingsPeriodName = marketingsPeriodName;
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

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getFileServerIp() {
        return fileServerIp;
    }

    public void setFileServerIp(String fileServerIp) {
        this.fileServerIp = fileServerIp;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
