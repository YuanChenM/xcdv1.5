package com.msk.batch.br.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;


public class BBR121406Bean extends BaseEntity {

    /*管家组*/
    private Long hkGroupId;
    /** HK_GROUP_NAME */
    private String hkGroupName;
    /** CLASSES_CODE */
    private String classesCode;
    /** MACHINING_CODE_U */
    private String machiningCodeU;
    /** BUYER_TYPE */
    private String buyerType;
    /** LGCS_AREA_CODE */
    private String lgcsAreaCode;
    /** CITY_CODE */
    private String cityCode;

    /*买家管家最新关系*/
    private Long oByHkRsId;
    private String oHouseCode;
    /** BUYER_ID */
    private String buyerId;
    /** START_TIME */
    private java.util.Date startTime;
    /** END_TIME */
    private java.util.Date endTime;
    /** 1：锁定期 2：专属会员 */
    private String bindStatus;
    /** BIND_TIME */
    private java.util.Date bindTime;

    /*买家买家池关系*/
    /** BUYER_POOL_ID */
    private Long buyerPoolId;
    /** POOL_BUYER_CODE */
    private String poolBuyerCode;
    /** 0：公共买家池，1：锁定期分池，2：专属会员分池 */
    private String buyerPoolType;
    /** 插入时间，买家加入买家池的时间 */
    private java.util.Date joinTime;
    /** 离开时间，买家离开买家池的时间 */
    private java.util.Date leaveTime;

    /*买家管家关系*/
    private Long byHkRsId;
    /** 冻品管家编码 */
    private String houseCode;
    /** 买手ID */
    private String slCode;
    /** 0：无关系，1：锁定期，2：专属会员 */
    private String relationType;
    /** 关系建立时间 */
    private java.util.Date rsStartTime;
    /** 关系结束时间 */
    private java.util.Date rsEndTime;

    /**
     * Getter method for property <tt>hkGroupId</tt>.
     *
     * @return property value of hkGroupId
     */
    public Long getHkGroupId() {
        return hkGroupId;
    }

    /**
     * Setter method for property <tt>hkGroupId</tt>.
     *
     * @param hkGroupId value to be assigned to property hkGroupId
     */
    public void setHkGroupId(Long hkGroupId) {
        this.hkGroupId = hkGroupId;
    }

    /**
     * Getter method for property <tt>hkGroupName</tt>.
     *
     * @return property value of hkGroupName
     */
    public String getHkGroupName() {
        return hkGroupName;
    }

    /**
     * Setter method for property <tt>hkGroupName</tt>.
     *
     * @param hkGroupName value to be assigned to property hkGroupName
     */
    public void setHkGroupName(String hkGroupName) {
        this.hkGroupName = hkGroupName;
    }

    /**
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Getter method for property <tt>machiningCodeU</tt>.
     *
     * @return property value of machiningCodeU
     */
    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    /**
     * Setter method for property <tt>machiningCodeU</tt>.
     *
     * @param machiningCodeU value to be assigned to property machiningCodeU
     */
    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    /**
     * Getter method for property <tt>buyerType</tt>.
     *
     * @return property value of buyerType
     */
    public String getBuyerType() {
        return buyerType;
    }

    /**
     * Setter method for property <tt>buyerType</tt>.
     *
     * @param buyerType value to be assigned to property buyerType
     */
    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    /**
     * Getter method for property <tt>lgcsAreaCode</tt>.
     *
     * @return property value of lgcsAreaCode
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * Setter method for property <tt>lgcsAreaCode</tt>.
     *
     * @param lgcsAreaCode value to be assigned to property lgcsAreaCode
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * Getter method for property <tt>cityCode</tt>.
     *
     * @return property value of cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter method for property <tt>cityCode</tt>.
     *
     * @param cityCode value to be assigned to property cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Getter method for property <tt>startTime</tt>.
     *
     * @return property value of startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     *
     * @param startTime value to be assigned to property startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method for property <tt>endTime</tt>.
     *
     * @return property value of endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter method for property <tt>endTime</tt>.
     *
     * @param endTime value to be assigned to property endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter method for property <tt>bindStatus</tt>.
     *
     * @return property value of bindStatus
     */
    public String getBindStatus() {
        return bindStatus;
    }

    /**
     * Setter method for property <tt>bindStatus</tt>.
     *
     * @param bindStatus value to be assigned to property bindStatus
     */
    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

    /**
     * Getter method for property <tt>bindTime</tt>.
     *
     * @return property value of bindTime
     */
    public Date getBindTime() {
        return bindTime;
    }

    /**
     * Setter method for property <tt>bindTime</tt>.
     *
     * @param bindTime value to be assigned to property bindTime
     */
    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    /**
     * Getter method for property <tt>buyerPoolId</tt>.
     *
     * @return property value of buyerPoolId
     */
    public Long getBuyerPoolId() {
        return buyerPoolId;
    }

    /**
     * Setter method for property <tt>buyerPoolId</tt>.
     *
     * @param buyerPoolId value to be assigned to property buyerPoolId
     */
    public void setBuyerPoolId(Long buyerPoolId) {
        this.buyerPoolId = buyerPoolId;
    }

    /**
     * Getter method for property <tt>poolBuyerCode</tt>.
     *
     * @return property value of poolBuyerCode
     */
    public String getPoolBuyerCode() {
        return poolBuyerCode;
    }

    /**
     * Setter method for property <tt>poolBuyerCode</tt>.
     *
     * @param poolBuyerCode value to be assigned to property poolBuyerCode
     */
    public void setPoolBuyerCode(String poolBuyerCode) {
        this.poolBuyerCode = poolBuyerCode;
    }

    /**
     * Getter method for property <tt>buyerPoolType</tt>.
     *
     * @return property value of buyerPoolType
     */
    public String getBuyerPoolType() {
        return buyerPoolType;
    }

    /**
     * Setter method for property <tt>buyerPoolType</tt>.
     *
     * @param buyerPoolType value to be assigned to property buyerPoolType
     */
    public void setBuyerPoolType(String buyerPoolType) {
        this.buyerPoolType = buyerPoolType;
    }

    /**
     * Getter method for property <tt>joinTime</tt>.
     *
     * @return property value of joinTime
     */
    public Date getJoinTime() {
        return joinTime;
    }

    /**
     * Setter method for property <tt>joinTime</tt>.
     *
     * @param joinTime value to be assigned to property joinTime
     */
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    /**
     * Getter method for property <tt>houseCode</tt>.
     *
     * @return property value of houseCode
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * Setter method for property <tt>houseCode</tt>.
     *
     * @param houseCode value to be assigned to property houseCode
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>relationType</tt>.
     *
     * @return property value of relationType
     */
    public String getRelationType() {
        return relationType;
    }

    /**
     * Setter method for property <tt>relationType</tt>.
     *
     * @param relationType value to be assigned to property relationType
     */
    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    /**
     * Getter method for property <tt>rsStartTime</tt>.
     *
     * @return property value of rsStartTime
     */
    public Date getRsStartTime() {
        return rsStartTime;
    }

    /**
     * Setter method for property <tt>rsStartTime</tt>.
     *
     * @param rsStartTime value to be assigned to property rsStartTime
     */
    public void setRsStartTime(Date rsStartTime) {
        this.rsStartTime = rsStartTime;
    }

    /**
     * Getter method for property <tt>rsEndTime</tt>.
     *
     * @return property value of rsEndTime
     */
    public Date getRsEndTime() {
        return rsEndTime;
    }

    /**
     * Setter method for property <tt>rsEndTime</tt>.
     *
     * @param rsEndTime value to be assigned to property rsEndTime
     */
    public void setRsEndTime(Date rsEndTime) {
        this.rsEndTime = rsEndTime;
    }

    /**
     * Getter method for property <tt>byHkRsId</tt>.
     *
     * @return property value of byHkRsId
     */
    public Long getByHkRsId() {
        return byHkRsId;
    }

    /**
     * Setter method for property <tt>byHkRsId</tt>.
     *
     * @param byHkRsId value to be assigned to property byHkRsId
     */
    public void setByHkRsId(Long byHkRsId) {
        this.byHkRsId = byHkRsId;
    }

    /**
     * Getter method for property <tt>oByHkRsId</tt>.
     *
     * @return property value of oByHkRsId
     */
    public Long getoByHkRsId() {
        return oByHkRsId;
    }

    /**
     * Setter method for property <tt>oByHkRsId</tt>.
     *
     * @param oByHkRsId value to be assigned to property oByHkRsId
     */
    public void setoByHkRsId(Long oByHkRsId) {
        this.oByHkRsId = oByHkRsId;
    }

    /**
     * Getter method for property <tt>oHouseCode</tt>.
     *
     * @return property value of oHouseCode
     */
    public String getoHouseCode() {
        return oHouseCode;
    }

    /**
     * Setter method for property <tt>oHouseCode</tt>.
     *
     * @param oHouseCode value to be assigned to property oHouseCode
     */
    public void setoHouseCode(String oHouseCode) {
        this.oHouseCode = oHouseCode;
    }

    /**
     * Getter method for property <tt>leaveTime</tt>.
     *
     * @return property value of leaveTime
     */
    public Date getLeaveTime() {
        return leaveTime;
    }

    /**
     * Setter method for property <tt>leaveTime</tt>.
     *
     * @param leaveTime value to be assigned to property leaveTime
     */
    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }
}
