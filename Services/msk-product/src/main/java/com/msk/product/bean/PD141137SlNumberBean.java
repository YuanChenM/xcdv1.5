package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/2/23.
 */
public class PD141137SlNumberBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String slCodeName;
    private String staockQtyName;
    private BigDecimal stockQty;

    /**
     * Getter method for property <tt>slCodeName</tt>.
     *
     * @return property value of slCodeName
     */
    public String getSlCodeName() {
        return slCodeName;
    }

    /**
     * Setter method for property <tt>slCodeName</tt>.
     *
     * @param slCodeName value to be assigned to property slCodeName
     */
    public void setSlCodeName(String slCodeName) {
        this.slCodeName = slCodeName;
    }

    /**
     * Getter method for property <tt>staockQtyName</tt>.
     *
     * @return property value of staockQtyName
     */
    public String getStaockQtyName() {
        return staockQtyName;
    }

    /**
     * Setter method for property <tt>staockQtyName</tt>.
     *
     * @param staockQtyName value to be assigned to property staockQtyName
     */
    public void setStaockQtyName(String staockQtyName) {
        this.staockQtyName = staockQtyName;
    }

    /**
     * Getter method for property <tt>stockQty</tt>.
     *
     * @return property value of stockQty
     */
    public BigDecimal getStockQty() {
        return stockQty;
    }

    /**
     * Setter method for property <tt>stockQty</tt>.
     *
     * @param stockQty value to be assigned to property stockQty
     */
    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }
}
