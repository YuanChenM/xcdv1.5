package com.msk.buyers.bean;

import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;

/**
 * Created by tao_zhifa on 2016/7/8.
 */
public class BY121401Param extends BasePageParam{

    /** ID */
    private Long id;
    /** 批发市场ID */
    private String marketId;
    /** 批发市场编码 */
    private String marketCode;
    /** 批发市场名称 */
    private String marketName;
    /** 批发市场地址 */
    private String marketAddr;
    /** 批发市场等级 */
    private String marketLevel;
    /** 批发市场等级名称 */
    private String marketLevelName;

    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 物流区名称 */
    private String lgcsAreaName;
    /** 城市(地区)编码 */
    private String cityCode;
    /** 城市(地区)名称 */
    private String cityName;
    /** 总商户数(户) */
    private Integer merchantTotalNo;
    /** 目标买家总数(户) */
    private Integer targetBuyer;
    /** 目标买家年交易额(万元) */
    private java.math.BigDecimal targetAnnualTurnover;
    /** 调研阶段 */
    private String researchPhase;
    /** 调研阶段名称 */
    private String researchPhaseName;
    /** 审批状态 */
    private String marketStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketAddr() {
        return marketAddr;
    }

    public void setMarketAddr(String marketAddr) {
        this.marketAddr = marketAddr;
    }

    public String getMarketLevel() {
        return marketLevel;
    }

    public void setMarketLevel(String marketLevel) {
        this.marketLevel = marketLevel;
    }

    public String getMarketLevelName() {
        return marketLevelName;
    }

    public void setMarketLevelName(String marketLevelName) {
        this.marketLevelName = marketLevelName;
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

    public Integer getMerchantTotalNo() {
        return merchantTotalNo;
    }

    public void setMerchantTotalNo(Integer merchantTotalNo) {
        this.merchantTotalNo = merchantTotalNo;
    }

    public Integer getTargetBuyer() {
        return targetBuyer;
    }

    public void setTargetBuyer(Integer targetBuyer) {
        this.targetBuyer = targetBuyer;
    }

    public BigDecimal getTargetAnnualTurnover() {
        return targetAnnualTurnover;
    }

    public void setTargetAnnualTurnover(BigDecimal targetAnnualTurnover) {
        this.targetAnnualTurnover = targetAnnualTurnover;
    }

    public String getResearchPhase() {
        return researchPhase;
    }

    public void setResearchPhase(String researchPhase) {
        this.researchPhase = researchPhase;
    }

    public String getResearchPhaseName() {
        return researchPhaseName;
    }

    public void setResearchPhaseName(String researchPhaseName) {
        this.researchPhaseName = researchPhaseName;
    }

    public String getMarketStatus() {
        return marketStatus;
    }

    public void setMarketStatus(String marketStatus) {
        this.marketStatus = marketStatus;
    }
}
