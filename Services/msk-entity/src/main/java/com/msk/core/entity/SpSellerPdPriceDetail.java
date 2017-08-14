/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sp_seller_pd_price_detail对应的SpSellerPdPriceDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SpSellerPdPriceDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** PRICE_DETAIL_ID */
    private Long priceDetailId;
    /** PRICE_ID */
    private Long priceId;
    /** WAY_CODE */
    private String wayCode;
    /** WAYGRADE_CODE */
    private String waygradeCode;
    /** WAYGRADE_NAME */
    private String waygradeName;
    /** WAYGRADE_PRICE */
    private java.math.BigDecimal waygradePrice;
    /** WAYGRADE_PRICEMIN */
    private java.math.BigDecimal waygradePricemin;
    /** WAYGRADE_PRICEMAX */
    private java.math.BigDecimal waygradePricemax;
    /** WAYGRADE_PRICE_RATIO */
    private java.math.BigDecimal waygradePriceRatio;
    /** IS_VALID */
    private Integer isValid;
    /**
     * <p>默认构造函数。</p>
     */
    public SpSellerPdPriceDetail() {

    }

    /**
     * <p>PRICE_DETAIL_ID。</p>
     *
     * @return the PRICE_DETAIL_ID
     */
    public Long getPriceDetailId() {
        return priceDetailId;
    }

    /**
     * <p>PRICE_DETAIL_ID。</p>
     *
     * @param priceDetailId PRICE_DETAIL_ID。
     */
    public void setPriceDetailId(Long priceDetailId) {
        this.priceDetailId = priceDetailId;
    }

    /**
     * <p>PRICE_ID。</p>
     *
     * @return the PRICE_ID
     */
    public Long getPriceId() {
        return priceId;
    }

    /**
     * <p>PRICE_ID。</p>
     *
     * @param priceId PRICE_ID。
     */
    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    /**
     * <p>WAY_CODE。</p>
     *
     * @return the WAY_CODE
     */
    public String getWayCode() {
        return wayCode;
    }

    /**
     * <p>WAY_CODE。</p>
     *
     * @param wayCode WAY_CODE。
     */
    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    /**
     * <p>WAYGRADE_CODE。</p>
     *
     * @return the WAYGRADE_CODE
     */
    public String getWaygradeCode() {
        return waygradeCode;
    }

    /**
     * <p>WAYGRADE_CODE。</p>
     *
     * @param waygradeCode WAYGRADE_CODE。
     */
    public void setWaygradeCode(String waygradeCode) {
        this.waygradeCode = waygradeCode;
    }

    /**
     * <p>WAYGRADE_NAME。</p>
     *
     * @return the WAYGRADE_NAME
     */
    public String getWaygradeName() {
        return waygradeName;
    }

    /**
     * <p>WAYGRADE_NAME。</p>
     *
     * @param waygradeName WAYGRADE_NAME。
     */
    public void setWaygradeName(String waygradeName) {
        this.waygradeName = waygradeName;
    }

    /**
     * <p>WAYGRADE_PRICE。</p>
     *
     * @return the WAYGRADE_PRICE
     */
    public java.math.BigDecimal getWaygradePrice() {
        return waygradePrice;
    }

    /**
     * <p>WAYGRADE_PRICE。</p>
     *
     * @param waygradePrice WAYGRADE_PRICE。
     */
    public void setWaygradePrice(java.math.BigDecimal waygradePrice) {
        this.waygradePrice = waygradePrice;
    }

    /**
     * <p>WAYGRADE_PRICEMIN。</p>
     *
     * @return the WAYGRADE_PRICEMIN
     */
    public java.math.BigDecimal getWaygradePricemin() {
        return waygradePricemin;
    }

    /**
     * <p>WAYGRADE_PRICEMIN。</p>
     *
     * @param waygradePricemin WAYGRADE_PRICEMIN。
     */
    public void setWaygradePricemin(java.math.BigDecimal waygradePricemin) {
        this.waygradePricemin = waygradePricemin;
    }

    /**
     * <p>WAYGRADE_PRICEMAX。</p>
     *
     * @return the WAYGRADE_PRICEMAX
     */
    public java.math.BigDecimal getWaygradePricemax() {
        return waygradePricemax;
    }

    /**
     * <p>WAYGRADE_PRICEMAX。</p>
     *
     * @param waygradePricemax WAYGRADE_PRICEMAX。
     */
    public void setWaygradePricemax(java.math.BigDecimal waygradePricemax) {
        this.waygradePricemax = waygradePricemax;
    }

    /**
     * <p>WAYGRADE_PRICE_RATIO。</p>
     *
     * @return the WAYGRADE_PRICE_RATIO
     */
    public java.math.BigDecimal getWaygradePriceRatio() {
        return waygradePriceRatio;
    }

    /**
     * <p>WAYGRADE_PRICE_RATIO。</p>
     *
     * @param waygradePriceRatio WAYGRADE_PRICE_RATIO。
     */
    public void setWaygradePriceRatio(java.math.BigDecimal waygradePriceRatio) {
        this.waygradePriceRatio = waygradePriceRatio;
    }

    /**
     * <p>IS_VALID。</p>
     *
     * @return the IS_VALID
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * <p>IS_VALID。</p>
     *
     * @param isValid IS_VALID。
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

}
