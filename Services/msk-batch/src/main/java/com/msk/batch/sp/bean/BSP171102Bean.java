package com.msk.batch.sp.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sun_jiaju
 */
public class BSP171102Bean extends BaseEntity {
    /**
     * 预测条件ID
     */
    private Long forecastCondId;
    /**
     * 产品编码
     */
    private String pdCode;
    /**
     * 等级编码
     */
    private String gradeCode;
    /**
     * 物流区CODE
     */
    private String lgcsCode;
    /**
     * 需求预测年月
     */
    private String forecastYm;
    /**
     * 本月上半月分销量
     */
    private BigDecimal salesCnt;
    /**
     * 本月上半月末期末库存
     */
    private int stockCnt;
    /**
     * 截止本月15日止的已列入供应计划尚未入库的供应量
     */
    private BigDecimal planCnt;
    /**
     * 需求预测量
     */
    private BigDecimal forecastNum;
    /**
     * SeqNo
     */
    private Long seqNo;
    /**
     * 下月度库存平衡系数
     */
    private BigDecimal securityRatio;
    /**
     * 下月度销量平衡系数
     */
    private BigDecimal sellForecastRatio;
    /**
     * 预测ID
     */
    private Long forecastId;

    public Long getForecastCondId() {
        return forecastCondId;
    }

    public void setForecastCondId(Long forecastCondId) {
        this.forecastCondId = forecastCondId;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getForecastYm() {
        return forecastYm;
    }

    public void setForecastYm(String forecastYm) {
        this.forecastYm = forecastYm;
    }

    public BigDecimal getSalesCnt() {
        return salesCnt;
    }

    public void setSalesCnt(BigDecimal salesCnt) {
        this.salesCnt = salesCnt;
    }

    public int getStockCnt() {
        return stockCnt;
    }

    public void setStockCnt(int stockCnt) {
        this.stockCnt = stockCnt;
    }

    public BigDecimal getPlanCnt() {
        return planCnt;
    }

    public void setPlanCnt(BigDecimal planCnt) {
        this.planCnt = planCnt;
    }

    public BigDecimal getForecastNum() {
        return forecastNum;
    }

    public void setForecastNum(BigDecimal forecastNum) {
        this.forecastNum = forecastNum;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public BigDecimal getSecurityRatio() {
        return securityRatio;
    }

    public void setSecurityRatio(BigDecimal securityRatio) {
        this.securityRatio = securityRatio;
    }

    public BigDecimal getSellForecastRatio() {
        return sellForecastRatio;
    }

    public void setSellForecastRatio(BigDecimal sellForecastRatio) {
        this.sellForecastRatio = sellForecastRatio;
    }

    public Long getForecastId() {
        return forecastId;
    }

    public void setForecastId(Long forecastId) {
        this.forecastId = forecastId;
    }
}
