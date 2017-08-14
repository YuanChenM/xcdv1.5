package com.msk.br.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.BrOClaMachiningInfo;

import java.util.Date;
import java.util.List;

/**
 * 冻品管家分销分类买家池营销期公众买家分池买家注册管控
 * <p/>
 * Created by tao_zhifa on 2016/8/8.
 */
public class IBR121409RsParam extends BasePageParam {
    private String fileId;
    /** FILE_SERVER_ID */
    private String fileServerId;
    /** FILE_SERVER_IP */
    private String fileServerIp;
    /** FILE_CREATE_TIME */
    private Date fileCreateTime;
    /** 0：未生成，1：已生成 */
    private String fileStatus;
    /** 1：买家池，2：买家产品池，3：上线状态买家池 */
    private String poolType;
    /** BUYER_TYPE */
    private String buyerType;
    /** BUYER_SUB_TYPE */
    private String buyerSubType;

    /** MACHINING_CODE_U */
    private String machiningCodeU;
    /** MARKETINGS_PERIOD_NAME */
    private String marketingsPeriodName;
    /** MARKET_ID */
    private String marketId;
    /** MARKET_NAME */
    private String marketName;
    //查询条件组合
    private String fileNameGroup;
    //报表开始时间
    private String fileStartTime;
    //报表结束时间
    private String fileEndTime;
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
    //一级分类名称
    private String classesCode;
    //一级分类名称
    private String classesName;
    //二级分类名称
    private String machiningCode;
    //二级分类名称
    private String machiningName;
    //买家上线状态
    private String marketingsStatus;
    //是否覆盖
    private boolean flag;
    private String fileSuf;
    //文件名称
    private String fileName;

    //判断文件状态
    private String fileStatusFlag;
    //判断成功条件
    private String  dataCount;
    private List<IBR121409RsBean> houseList;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileServerId() {
        return fileServerId;
    }

    public void setFileServerId(String fileServerId) {
        this.fileServerId = fileServerId;
    }

    public String getFileServerIp() {
        return fileServerIp;
    }

    public void setFileServerIp(String fileServerIp) {
        this.fileServerIp = fileServerIp;
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

    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    public String getMarketingsPeriodName() {
        return marketingsPeriodName;
    }

    public void setMarketingsPeriodName(String marketingsPeriodName) {
        this.marketingsPeriodName = marketingsPeriodName;
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

    public String getFileStatusFlag() {
        return fileStatusFlag;
    }

    public void setFileStatusFlag(String fileStatusFlag) {
        this.fileStatusFlag = fileStatusFlag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDataCount() {
        return dataCount;
    }

    public void setDataCount(String dataCount) {
        this.dataCount = dataCount;
    }

    public String getFileNameGroup() {
        return fileNameGroup;
    }

    public void setFileNameGroup(String fileNameGroup) {
        this.fileNameGroup = fileNameGroup;
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

    public String getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(String buyersType) {
        this.buyersType = buyersType;
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

    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    public String getFileSuf() {
        return fileSuf;
    }

    public void setFileSuf(String fileSuf) {
        this.fileSuf = fileSuf;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<IBR121409RsBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBR121409RsBean> houseList) {
        this.houseList = houseList;
    }
}



