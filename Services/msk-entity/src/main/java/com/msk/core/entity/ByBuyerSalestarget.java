/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_salestarget对应的ByBuyerSalestarget</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerSalestarget extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** 参考CONSTANT表 */
    private String salesTargetType;
    /** 参考CONSTANT表 */
    private String salesTargetName;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerSalestarget() {

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
     * <p>BUYER_ID</p>
     *
     * @return the BUYER_ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @param buyerId BUYER_ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @return the 参考CONSTANT表
     */
    public String getSalesTargetType() {
        return salesTargetType;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @param salesTargetType 参考CONSTANT表
     */
    public void setSalesTargetType(String salesTargetType) {
        this.salesTargetType = salesTargetType;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @return the 参考CONSTANT表
     */
    public String getSalesTargetName() {
        return salesTargetName;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @param salesTargetName 参考CONSTANT表
     */
    public void setSalesTargetName(String salesTargetName) {
        this.salesTargetName = salesTargetName;
    }

}
