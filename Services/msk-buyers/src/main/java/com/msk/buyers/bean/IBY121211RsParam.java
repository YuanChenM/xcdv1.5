package com.msk.buyers.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.ByResearchCategory;

import java.math.BigDecimal;

/**
 * IBY121211RsParam.
 *
 * @author zhou_ling
 */
@JsonIgnoreProperties(value = { "crtTime","updTime"})
public class IBY121211RsParam extends ByResearchCategory {

    private static final long serialVersionUID = 1L;
    /** 产品分类CODE */
    private String classesTreeCode;
    /** 明细ID */
    private long detailId;
    /** 产品特征 */
    private String featureCode;
    /** 需求规格 */
    private String demandFeature;
    /** 是否有需求(0:无需求,1:有需求) */
    private String hasDemand;
    /** 单位：箱 */
    private Integer demandQty;
    /** 单位：箱 */
    private Integer orderQty;
    /** 单位：元/箱 */
    private BigDecimal hopePrice;
    /** 配送时间 */
    private String delivery;
    /** 备注 */
    private String remark;

    /**
     * Getter method for property <tt>classesTreeCode</tt>.
     *
     * @return property value of classesTreeCode
     */
    public String getClassesTreeCode() {
        return classesTreeCode;
    }

    /**
     * Setter method for property <tt>classesTreeCode</tt>.
     *
     * @param classesTreeCode value to be assigned to property classesTreeCode
     */
    public void setClassesTreeCode(String classesTreeCode) {
        this.classesTreeCode = classesTreeCode;
    }

    /**
     * Getter method for property <tt>detailId</tt>.
     *
     * @return property value of detailId
     */
    public long getDetailId() {
        return detailId;
    }

    /**
     * Setter method for property <tt>detailId</tt>.
     *
     * @param detailId value to be assigned to property detailId
     */
    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    /**
     * Getter method for property <tt>featureCode</tt>.
     *
     * @return property value of featureCode
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * Setter method for property <tt>featureCode</tt>.
     *
     * @param featureCode value to be assigned to property featureCode
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * Getter method for property <tt>demandFeature</tt>.
     *
     * @return property value of demandFeature
     */
    public String getDemandFeature() {
        return demandFeature;
    }

    /**
     * Setter method for property <tt>demandFeature</tt>.
     *
     * @param demandFeature value to be assigned to property demandFeature
     */
    public void setDemandFeature(String demandFeature) {
        this.demandFeature = demandFeature;
    }

    /**
     * Getter method for property <tt>hasDemand</tt>.
     *
     * @return property value of hasDemand
     */
    public String getHasDemand() {
        return hasDemand;
    }

    /**
     * Setter method for property <tt>hasDemand</tt>.
     *
     * @param hasDemand value to be assigned to property hasDemand
     */
    public void setHasDemand(String hasDemand) {
        this.hasDemand = hasDemand;
    }

    /**
     * Getter method for property <tt>demandQty</tt>.
     *
     * @return property value of demandQty
     */
    public Integer getDemandQty() {
        return demandQty;
    }

    /**
     * Setter method for property <tt>demandQty</tt>.
     *
     * @param demandQty value to be assigned to property demandQty
     */
    public void setDemandQty(Integer demandQty) {
        this.demandQty = demandQty;
    }

    /**
     * Getter method for property <tt>orderQty</tt>.
     *
     * @return property value of orderQty
     */
    public Integer getOrderQty() {
        return orderQty;
    }

    /**
     * Setter method for property <tt>orderQty</tt>.
     *
     * @param orderQty value to be assigned to property orderQty
     */
    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * Getter method for property <tt>hopePrice</tt>.
     *
     * @return property value of hopePrice
     */
    public BigDecimal getHopePrice() {
        return hopePrice;
    }

    /**
     * Setter method for property <tt>hopePrice</tt>.
     *
     * @param hopePrice value to be assigned to property hopePrice
     */
    public void setHopePrice(BigDecimal hopePrice) {
        this.hopePrice = hopePrice;
    }

    /**
     * Getter method for property <tt>delivery</tt>.
     *
     * @return property value of delivery
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * Setter method for property <tt>delivery</tt>.
     *
     * @param delivery value to be assigned to property delivery
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     *
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     *
     * @param remark value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}