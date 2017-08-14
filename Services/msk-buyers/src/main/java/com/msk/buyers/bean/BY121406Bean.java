package com.msk.buyers.bean;

import com.msk.core.entity.ByMarketTerminalBasic;

/**
 * Created by yuan_zhifei on 2016/7/8.
 */
public class BY121406Bean extends ByMarketTerminalBasic{
    /**网搜阶段
     * 批发市场性质
     * 批发市场评估等级名称
     * 辐射范围类型名称
     */
    private String netMarketNature;
    private String netMarketLevelName;
    private String netRadiationRangeTypeName;
    /**先期调研阶段
     * 批发市场性质
     * 批发市场评估等级名称
     * 辐射范围类型名称
     */
    private String advanceMarketNature;
    private String advanceMarketLevelName;
    private String advanceRadiationRangeTypeName;
    /**现场稽核阶段
     * 批发市场性质
     * 批发市场评估等级名称
     * 辐射范围类型名称
     */
    private String auditMarketNature;
    private String auditMarketLevelName;
    private String auditRadiationRangeTypeName;

    public String getNetMarketNature() {
        return netMarketNature;
    }

    public void setNetMarketNature(String netMarketNature) {
        this.netMarketNature = netMarketNature;
    }

    public String getNetMarketLevelName() {
        return netMarketLevelName;
    }

    public void setNetMarketLevelName(String netMarketLevelName) {
        this.netMarketLevelName = netMarketLevelName;
    }

    public String getNetRadiationRangeTypeName() {
        return netRadiationRangeTypeName;
    }

    public void setNetRadiationRangeTypeName(String netRadiationRangeTypeName) {
        this.netRadiationRangeTypeName = netRadiationRangeTypeName;
    }

    public String getAdvanceMarketNature() {
        return advanceMarketNature;
    }

    public void setAdvanceMarketNature(String advanceMarketNature) {
        this.advanceMarketNature = advanceMarketNature;
    }

    public String getAdvanceMarketLevelName() {
        return advanceMarketLevelName;
    }

    public void setAdvanceMarketLevelName(String advanceMarketLevelName) {
        this.advanceMarketLevelName = advanceMarketLevelName;
    }

    public String getAdvanceRadiationRangeTypeName() {
        return advanceRadiationRangeTypeName;
    }

    public void setAdvanceRadiationRangeTypeName(String advanceRadiationRangeTypeName) {
        this.advanceRadiationRangeTypeName = advanceRadiationRangeTypeName;
    }

    public String getAuditMarketNature() {
        return auditMarketNature;
    }

    public void setAuditMarketNature(String auditMarketNature) {
        this.auditMarketNature = auditMarketNature;
    }

    public String getAuditMarketLevelName() {
        return auditMarketLevelName;
    }

    public void setAuditMarketLevelName(String auditMarketLevelName) {
        this.auditMarketLevelName = auditMarketLevelName;
    }

    public String getAuditRadiationRangeTypeName() {
        return auditRadiationRangeTypeName;
    }

    public void setAuditRadiationRangeTypeName(String auditRadiationRangeTypeName) {
        this.auditRadiationRangeTypeName = auditRadiationRangeTypeName;
    }
}
