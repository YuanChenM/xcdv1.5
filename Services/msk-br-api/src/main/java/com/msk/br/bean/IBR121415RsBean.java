package com.msk.br.bean;

import com.msk.common.base.BaseBean;

import java.util.Date;

/**
 * Created by tao_zhifa on 2016/10/24.
 */
public class IBR121415RsBean extends BaseBean {
    /**
     * 参考产品类别
     */
    private String classCode;
    /**
     * CLASS_NAME
     */
    private String className;
    /**
     * 产品二级分类编码集
     */
    private String machiningCode;
    private String machiningCodeU;
    private String lgcsAreaCode;
    private String cityCode;
    private String superiorType;
    private Long id;
    private String buyerCode;
    /** 买手店主键 */
    private String slCode;
    /**
     * BUYER_POOL_ID
     */
    private Long buyerPoolId;
    /**
     * POOL_BUYER_CODE
     */
    private String poolBuyerCode;
    /**
     * 0：公共买家池，1：锁定期分池，2：专属会员分池
     */
    private String buyerPoolType;
    /**
     * 0：未分配，1：已分配
     */
    private String isDistribution;
    /**
     * 插入时间，买家加入买家池的时间
     */
    private java.util.Date joinTime;
    /**
     * 删除时间，买家离开买家池的时间
     */
    private java.util.Date leaveTime;

    /**
     * BUYER_ID
     */
    private String buyerId;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
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

    public String getSuperiorType() {
        return superiorType;
    }

    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public Long getBuyerPoolId() {
        return buyerPoolId;
    }

    public void setBuyerPoolId(Long buyerPoolId) {
        this.buyerPoolId = buyerPoolId;
    }

    public String getPoolBuyerCode() {
        return poolBuyerCode;
    }

    public void setPoolBuyerCode(String poolBuyerCode) {
        this.poolBuyerCode = poolBuyerCode;
    }

    public String getBuyerPoolType() {
        return buyerPoolType;
    }

    public void setBuyerPoolType(String buyerPoolType) {
        this.buyerPoolType = buyerPoolType;
    }

    public String getIsDistribution() {
        return isDistribution;
    }

    public void setIsDistribution(String isDistribution) {
        this.isDistribution = isDistribution;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
