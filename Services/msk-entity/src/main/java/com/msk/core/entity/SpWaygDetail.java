/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sp_wayg_detail对应的SpWaygDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SpWaygDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 通道CODE */
    private Long wayCode;
    /** 通道等级CODE */
    private String waygradeCode;
    /** 通道等级名称 */
    private String waygradeName;
    /** 通道等级箱数下限 */
    private java.math.BigDecimal waygradeStart;
    /** 通道等级箱数上限 */
    private java.math.BigDecimal waygradeEnd;
    /** 通道等级平衡系数 */
    private java.math.BigDecimal waygradePercent;
    /** 对应：普通，大额，大宗，超级大宗 */
    private String sellWayCode;
    /**
     * <p>默认构造函数。</p>
     */
    public SpWaygDetail() {

    }

    /**
     * <p>通道CODE。</p>
     *
     * @return the 通道CODE
     */
    public Long getWayCode() {
        return wayCode;
    }

    /**
     * <p>通道CODE。</p>
     *
     * @param wayCode 通道CODE。
     */
    public void setWayCode(Long wayCode) {
        this.wayCode = wayCode;
    }

    /**
     * <p>通道等级CODE。</p>
     *
     * @return the 通道等级CODE
     */
    public String getWaygradeCode() {
        return waygradeCode;
    }

    /**
     * <p>通道等级CODE。</p>
     *
     * @param waygradeCode 通道等级CODE。
     */
    public void setWaygradeCode(String waygradeCode) {
        this.waygradeCode = waygradeCode;
    }

    /**
     * <p>通道等级名称。</p>
     *
     * @return the 通道等级名称
     */
    public String getWaygradeName() {
        return waygradeName;
    }

    /**
     * <p>通道等级名称。</p>
     *
     * @param waygradeName 通道等级名称。
     */
    public void setWaygradeName(String waygradeName) {
        this.waygradeName = waygradeName;
    }

    /**
     * <p>通道等级箱数下限。</p>
     *
     * @return the 通道等级箱数下限
     */
    public java.math.BigDecimal getWaygradeStart() {
        return waygradeStart;
    }

    /**
     * <p>通道等级箱数下限。</p>
     *
     * @param waygradeStart 通道等级箱数下限。
     */
    public void setWaygradeStart(java.math.BigDecimal waygradeStart) {
        this.waygradeStart = waygradeStart;
    }

    /**
     * <p>通道等级箱数上限。</p>
     *
     * @return the 通道等级箱数上限
     */
    public java.math.BigDecimal getWaygradeEnd() {
        return waygradeEnd;
    }

    /**
     * <p>通道等级箱数上限。</p>
     *
     * @param waygradeEnd 通道等级箱数上限。
     */
    public void setWaygradeEnd(java.math.BigDecimal waygradeEnd) {
        this.waygradeEnd = waygradeEnd;
    }

    /**
     * <p>通道等级平衡系数。</p>
     *
     * @return the 通道等级平衡系数
     */
    public java.math.BigDecimal getWaygradePercent() {
        return waygradePercent;
    }

    /**
     * <p>通道等级平衡系数。</p>
     *
     * @param waygradePercent 通道等级平衡系数。
     */
    public void setWaygradePercent(java.math.BigDecimal waygradePercent) {
        this.waygradePercent = waygradePercent;
    }

    /**
     * <p>对应：普通，大额，大宗，超级大宗。</p>
     *
     * @return the 对应：普通，大额，大宗，超级大宗
     */
    public String getSellWayCode() {
        return sellWayCode;
    }

    /**
     * <p>对应：普通，大额，大宗，超级大宗。</p>
     *
     * @param sellWayCode 对应：普通，大额，大宗，超级大宗。
     */
    public void setSellWayCode(String sellWayCode) {
        this.sellWayCode = sellWayCode;
    }

}
