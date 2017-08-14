/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_market_food_by_info对应的ByMarketFoodByInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByMarketFoodByInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** 菜场ID */
    private String marketId;
    /** 商户名称 */
    private String merchantName;
    /** 商户类型(1:单一产品买家2:组合产品买家3:综合产品买家) */
    private String merchantType;
    /** 商户户数 */
    private Integer totalMerchant;
    /** 年销售额（万元） */
    private java.math.BigDecimal annualTurnover;
    /** 是否目标商户 */
    private String isTargetMerchant;
    /**
     * <p>默认构造函数</p>
     */
    public ByMarketFoodByInfo() {

    }

    /**
     * <p>ID</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>菜场ID</p>
     *
     * @return the 菜场ID
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * <p>菜场ID</p>
     *
     * @param marketId 菜场ID
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * <p>商户名称</p>
     *
     * @return the 商户名称
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * <p>商户名称</p>
     *
     * @param merchantName 商户名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * <p>商户类型(1:单一产品买家2:组合产品买家3:综合产品买家)</p>
     *
     * @return the 商户类型(1:单一产品买家2:组合产品买家3:综合产品买家)
     */
    public String getMerchantType() {
        return merchantType;
    }

    /**
     * <p>商户类型(1:单一产品买家2:组合产品买家3:综合产品买家)</p>
     *
     * @param merchantType 商户类型(1:单一产品买家2:组合产品买家3:综合产品买家)
     */
    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    /**
     * <p>商户户数</p>
     *
     * @return the 商户户数
     */
    public Integer getTotalMerchant() {
        return totalMerchant;
    }

    /**
     * <p>商户户数</p>
     *
     * @param totalMerchant 商户户数
     */
    public void setTotalMerchant(Integer totalMerchant) {
        this.totalMerchant = totalMerchant;
    }

    /**
     * <p>年销售额（万元）</p>
     *
     * @return the 年销售额（万元）
     */
    public java.math.BigDecimal getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * <p>年销售额（万元）</p>
     *
     * @param annualTurnover 年销售额（万元）
     */
    public void setAnnualTurnover(java.math.BigDecimal annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    /**
     * <p>是否目标商户</p>
     *
     * @return the 是否目标商户
     */
    public String getIsTargetMerchant() {
        return isTargetMerchant;
    }

    /**
     * <p>是否目标商户</p>
     *
     * @param isTargetMerchant 是否目标商户
     */
    public void setIsTargetMerchant(String isTargetMerchant) {
        this.isTargetMerchant = isTargetMerchant;
    }

}
