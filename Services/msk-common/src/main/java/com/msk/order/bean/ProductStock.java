package com.msk.order.bean;

import com.msk.core.entity.SoStockSl;

import java.math.BigDecimal;

/**
 * ProductStock
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class ProductStock extends SoStockSl {
    private BigDecimal price;
    private String lgcsCode;
    private String lgcsName;
    private String priceCyclePeriod= "10";
    private String orderLevelCode= "0";
    private String orderLevelName= "0";
    private BigDecimal activeQty = BigDecimal.ZERO;
    private Long pricecycleId;
    /**
     * Getter method for property <tt>orderLevelCode</tt>.
     *
     * @return property value of orderLevelCode
     */
    public String getOrderLevelCode() {
        return orderLevelCode;
    }

    /**
     * Setter method for property <tt>orderLevelCode</tt>.
     *
     * @param orderLevelCode value to be assigned to property orderLevelCode
     */
    public void setOrderLevelCode(String orderLevelCode) {
        this.orderLevelCode = orderLevelCode;
    }

    /**
     * Getter method for property <tt>orderLevelName</tt>.
     *
     * @return property value of orderLevelName
     */
    public String getOrderLevelName() {
        return orderLevelName;
    }

    /**
     * Setter method for property <tt>orderLevelName</tt>.
     *
     * @param orderLevelName value to be assigned to property orderLevelName
     */
    public void setOrderLevelName(String orderLevelName) {
        this.orderLevelName = orderLevelName;
    }

    /**
     * Getter method for property <tt>priceCyclePeriod</tt>.
     *
     * @return property value of priceCyclePeriod
     */
    public String getPriceCyclePeriod() {
        return priceCyclePeriod;
    }

    /**
     * Setter method for property <tt>priceCyclePeriod</tt>.
     *
     * @param priceCyclePeriod value to be assigned to property priceCyclePeriod
     */
    public void setPriceCyclePeriod(String priceCyclePeriod) {
        this.priceCyclePeriod = priceCyclePeriod;
    }

    @Override
    public String getLgcsCode() {
        return lgcsCode;
    }

    @Override
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * Getter method for property <tt>lgcsName</tt>.
     *
     * @return property value of lgcsName
     */
    @Override
    public String getLgcsName() {
        return lgcsName;
    }

    /**
     * Setter method for property <tt>lgcsName</tt>.
     *
     * @param lgcsName value to be assigned to property lgcsName
     */
    @Override
    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    /**
     * Getter method for property <tt>price</tt>.
     *
     * @return property value of price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter method for property <tt>price</tt>.
     *
     * @param price value to be assigned to property price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getActiveQty() {
        return activeQty;
    }

    public void setActiveQty(BigDecimal activeQty) {
        this.activeQty = activeQty;
    }

    public Long getPricecycleId() {
        return pricecycleId;
    }

    public void setPricecycleId(Long pricecycleId) {
        this.pricecycleId = pricecycleId;
    }
}
