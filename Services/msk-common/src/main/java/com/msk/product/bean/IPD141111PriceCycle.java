package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 
 * IPD141111PriceCycle.产品查询价盘
 *
 * @author zhou_ling
 */
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId", "actTime" })
public class IPD141111PriceCycle extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String orderLevel; // 订单等级等级
     

    private BigDecimal priceOfKg;

    private BigDecimal priceOfBox;

    /**
     * Get the orderLevel.
     *
     * @return orderLevel
     *
     * @author zhou_ling
     */
    public String getOrderLevel() {
        return this.orderLevel;
    }

    /**
     * Set the orderLevel.
     *
     * @param orderLevel orderLevel
     *
     * @author zhou_ling
     */
    public void setOrderLevel(String orderLevel) {
        this.orderLevel = orderLevel;
    }

    /**
     * Getter method for property <tt>priceOfKg</tt>.
     *
     * @return property value of priceOfKg
     */
    public BigDecimal getPriceOfKg() {
        return priceOfKg;
    }

    /**
     * Setter method for property <tt>priceOfKg</tt>.
     *
     * @param priceOfKg value to be assigned to property priceOfKg
     */
    public void setPriceOfKg(BigDecimal priceOfKg) {
        this.priceOfKg = priceOfKg;
    }

    /**
     * Getter method for property <tt>priceOfBox</tt>.
     *
     * @return property value of priceOfBox
     */
    public BigDecimal getPriceOfBox() {
        return priceOfBox;
    }

    /**
     * Setter method for property <tt>priceOfBox</tt>.
     *
     * @param priceOfBox value to be assigned to property priceOfBox
     */
    public void setPriceOfBox(BigDecimal priceOfBox) {
        this.priceOfBox = priceOfBox;
    }
}