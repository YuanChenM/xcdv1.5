package com.msk.buyers.bean;

import com.msk.core.entity.ByMarketFoodBasic;

/**
 * Created by yuan_zhifei on 2016/7/8.
 */
public class BY121412Bean extends ByMarketFoodBasic{
    /**网搜阶段
     * 批发市场性质
     * 批发市场评估等级名称
     * 辐射范围类型名称
     */
    private String netMarketNatureName;
    private String netRadiationRangeTypeName;
    /**先期调研阶段
     * 批发市场性质
     * 批发市场评估等级名称
     * 辐射范围类型名称
     */
    private String advanceMarketNatureName;
    private String advanceRadiationRangeTypeName;
    /**现场稽核阶段
     * 批发市场性质
     * 批发市场评估等级名称
     * 辐射范围类型名称
     */
    private String auditMarketNatureName;
    private String auditRadiationRangeTypeName;

    public String getNetMarketNatureName() {
        return netMarketNatureName;
    }

    public void setNetMarketNatureName(String netMarketNatureName) {
        this.netMarketNatureName = netMarketNatureName;
    }

    public String getNetRadiationRangeTypeName() {
        return netRadiationRangeTypeName;
    }

    public void setNetRadiationRangeTypeName(String netRadiationRangeTypeName) {
        this.netRadiationRangeTypeName = netRadiationRangeTypeName;
    }

    public String getAdvanceMarketNatureName() {
        return advanceMarketNatureName;
    }

    public void setAdvanceMarketNatureName(String advanceMarketNatureName) {
        this.advanceMarketNatureName = advanceMarketNatureName;
    }

    public String getAdvanceRadiationRangeTypeName() {
        return advanceRadiationRangeTypeName;
    }

    public void setAdvanceRadiationRangeTypeName(String advanceRadiationRangeTypeName) {
        this.advanceRadiationRangeTypeName = advanceRadiationRangeTypeName;
    }

    public String getAuditMarketNatureName() {
        return auditMarketNatureName;
    }

    public void setAuditMarketNatureName(String auditMarketNatureName) {
        this.auditMarketNatureName = auditMarketNatureName;
    }

    public String getAuditRadiationRangeTypeName() {
        return auditRadiationRangeTypeName;
    }

    public void setAuditRadiationRangeTypeName(String auditRadiationRangeTypeName) {
        this.auditRadiationRangeTypeName = auditRadiationRangeTypeName;
    }
}
