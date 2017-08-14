/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_research_cat_detail对应的ByResearchCatDetail</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByResearchCatDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** DETAIL_ID */
    private Long detailId;
    /** CATEGORY_ID */
    private Long categoryId;
    /** 产品特征 */
    private String featureCode;
    /** 是否为标准目录产品(0:非标准目录产品,1:标准目录产品) */
    private String isStandard;
    /** DEMAND_FEATURE */
    private String demandFeature;
    /** 是否有需求(0:无需求,1:有需求) */
    private String hasDemand;
    /** 单位：箱 */
    private Integer demandQty;
    /** 单位：箱 */
    private Integer orderQty;
    /** 单位：元/箱 */
    private java.math.BigDecimal hopePrice;
    /** DELIVERY */
    private String delivery;
    /** REMARK */
    private String remark;
    /**
     * <p>默认构造函数</p>
     */
    public ByResearchCatDetail() {

    }

    /**
     * <p>DETAIL_ID</p>
     *
     * @return the DETAIL_ID
     */
    public Long getDetailId() {
        return detailId;
    }

    /**
     * <p>DETAIL_ID</p>
     *
     * @param detailId DETAIL_ID
     */
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    /**
     * <p>CATEGORY_ID</p>
     *
     * @return the CATEGORY_ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * <p>CATEGORY_ID</p>
     *
     * @param categoryId CATEGORY_ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * <p>产品特征</p>
     *
     * @return the 产品特征
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征</p>
     *
     * @param featureCode 产品特征
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>是否为标准目录产品(0:非标准目录产品,1:标准目录产品)</p>
     *
     * @return the 是否为标准目录产品(0:非标准目录产品,1:标准目录产品)
     */
    public String getIsStandard() {
        return isStandard;
    }

    /**
     * <p>是否为标准目录产品(0:非标准目录产品,1:标准目录产品)</p>
     *
     * @param isStandard 是否为标准目录产品(0:非标准目录产品,1:标准目录产品)
     */
    public void setIsStandard(String isStandard) {
        this.isStandard = isStandard;
    }

    /**
     * <p>DEMAND_FEATURE</p>
     *
     * @return the DEMAND_FEATURE
     */
    public String getDemandFeature() {
        return demandFeature;
    }

    /**
     * <p>DEMAND_FEATURE</p>
     *
     * @param demandFeature DEMAND_FEATURE
     */
    public void setDemandFeature(String demandFeature) {
        this.demandFeature = demandFeature;
    }

    /**
     * <p>是否有需求(0:无需求,1:有需求)</p>
     *
     * @return the 是否有需求(0:无需求,1:有需求)
     */
    public String getHasDemand() {
        return hasDemand;
    }

    /**
     * <p>是否有需求(0:无需求,1:有需求)</p>
     *
     * @param hasDemand 是否有需求(0:无需求,1:有需求)
     */
    public void setHasDemand(String hasDemand) {
        this.hasDemand = hasDemand;
    }

    /**
     * <p>单位：箱</p>
     *
     * @return the 单位：箱
     */
    public Integer getDemandQty() {
        return demandQty;
    }

    /**
     * <p>单位：箱</p>
     *
     * @param demandQty 单位：箱
     */
    public void setDemandQty(Integer demandQty) {
        this.demandQty = demandQty;
    }

    /**
     * <p>单位：箱</p>
     *
     * @return the 单位：箱
     */
    public Integer getOrderQty() {
        return orderQty;
    }

    /**
     * <p>单位：箱</p>
     *
     * @param orderQty 单位：箱
     */
    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * <p>单位：元/箱</p>
     *
     * @return the 单位：元/箱
     */
    public java.math.BigDecimal getHopePrice() {
        return hopePrice;
    }

    /**
     * <p>单位：元/箱</p>
     *
     * @param hopePrice 单位：元/箱
     */
    public void setHopePrice(java.math.BigDecimal hopePrice) {
        this.hopePrice = hopePrice;
    }

    /**
     * <p>DELIVERY</p>
     *
     * @return the DELIVERY
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * <p>DELIVERY</p>
     *
     * @param delivery DELIVERY
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    /**
     * <p>REMARK</p>
     *
     * @return the REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>REMARK</p>
     *
     * @param remark REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
