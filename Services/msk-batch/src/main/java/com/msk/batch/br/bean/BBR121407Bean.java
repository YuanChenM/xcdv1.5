package com.msk.batch.br.bean;

import com.msk.core.entity.BrOHkInfo;

import java.util.Date;


public class BBR121407Bean extends BrOHkInfo {

    //管家已绑定买家数
    private int byCount;

    //买家管家关系ID
    private Long byHkRsId;
    //绑定买家ID
    private String buyerId;
    //绑定开始时间
    private Date bindStartTime;
    //绑定结束时间
    private Date bindEndTime;
    //买家管家关系
    private String relationType;

    //产品一级分类
    private String classesCode;
    //产品二级分类
    private String machiningCodeU;
    //物流区编码
    private String lgcsAreaCode;
    //城市编码
    private String cityCode;
    //买家类型
    private String buyerType;
    //买家池分池
    private String buyerPoolType;
    //绑定开始时间
    private Date joinTime;
    //绑定结束时间
    private Date leaveTime;
    /**
     * Getter method for property <tt>byCount</tt>.
     *
     * @return property value of byCount
     */
    public int getByCount() {
        return byCount;
    }

    /**
     * Setter method for property <tt>byCount</tt>.
     *
     * @param byCount value to be assigned to property byCount
     */
    public void setByCount(int byCount) {
        this.byCount = byCount;
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
     * Getter method for property <tt>bindStartTime</tt>.
     *
     * @return property value of bindStartTime
     */
    public Date getBindStartTime() {
        return bindStartTime;
    }

    /**
     * Setter method for property <tt>bindStartTime</tt>.
     *
     * @param bindStartTime value to be assigned to property bindStartTime
     */
    public void setBindStartTime(Date bindStartTime) {
        this.bindStartTime = bindStartTime;
    }

    /**
     * Getter method for property <tt>bindEndTime</tt>.
     *
     * @return property value of bindEndTime
     */
    public Date getBindEndTime() {
        return bindEndTime;
    }

    /**
     * Setter method for property <tt>bindEndTime</tt>.
     *
     * @param bindEndTime value to be assigned to property bindEndTime
     */
    public void setBindEndTime(Date bindEndTime) {
        this.bindEndTime = bindEndTime;
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
     * Getter method for property <tt>lgcsAreaCode</tt>.
     *
     * @return property value of lgcsAreaCode
     */
    @Override
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * Setter method for property <tt>lgcsAreaCode</tt>.
     *
     * @param lgcsAreaCode value to be assigned to property lgcsAreaCode
     */
    @Override
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * Getter method for property <tt>cityCode</tt>.
     *
     * @return property value of cityCode
     */
    @Override
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter method for property <tt>cityCode</tt>.
     *
     * @param cityCode value to be assigned to property cityCode
     */
    @Override
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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
