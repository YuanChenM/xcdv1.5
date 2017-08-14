/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;

import java.util.Date;

/**
 * <p>表so_sales_ranking对应的SoSalesRanking。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoSalesRanking extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 物流区编号 */
    private String lgcsCode;
    /** 订单等级 */
    private Integer orderLevel;
    /** 产品编码 */
    private String pdCode;
    /** 供应商编码 */
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** 价盘周期 */
    private String priceCycle;
    /** 销售额 */
    private java.math.BigDecimal salesAmount;
    /** 销售排行 */
    private Integer salesRanking;
    /** 分销资格 */
    private Integer distQua;
    /** 轮单次数 */
    private Integer wheelFrequency;

    private java.util.Date historyDate;

    /**
     * <p>默认构造函数。</p>
     */
    public SoSalesRanking() {

    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    /**
     * <p>物流区编号。</p>
     *
     * @return the 物流区编号
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * <p>物流区编号。</p>
     *
     * @param lgcsCode 物流区编号。
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * <p>订单等级。</p>
     *
     * @return the 订单等级
     */
    public Integer getOrderLevel() {
        return orderLevel;
    }

    /**
     * <p>订单等级。</p>
     *
     * @param orderLevel 订单等级。
     */
    public void setOrderLevel(Integer orderLevel) {
        this.orderLevel = orderLevel;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdCode 产品编码。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @return the 供应商编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @param supplierCode 供应商编码。
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @return the 供应商名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @param supplierName 供应商名称。
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @return the 价盘周期
     */
    public String getPriceCycle() {
        return priceCycle;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @param priceCycle 价盘周期。
     */
    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    /**
     * <p>销售额。</p>
     *
     * @return the 销售额
     */
    public java.math.BigDecimal getSalesAmount() {
        return salesAmount;
    }

    /**
     * <p>销售额。</p>
     *
     * @param salesAmount 销售额。
     */
    public void setSalesAmount(java.math.BigDecimal salesAmount) {
        this.salesAmount = salesAmount;
    }

    /**
     * <p>销售排行。</p>
     *
     * @return the 销售排行
     */
    public Integer getSalesRanking() {
        return salesRanking;
    }

    /**
     * <p>销售排行。</p>
     *
     * @param salesRanking 销售排行。
     */
    public void setSalesRanking(Integer salesRanking) {
        this.salesRanking = salesRanking;
    }

    /**
     * <p>分销资格。</p>
     *
     * @return the 分销资格
     */
    public Integer getDistQua() {
        return distQua;
    }

    /**
     * <p>分销资格。</p>
     *
     * @param distQua 分销资格。
     */
    public void setDistQua(Integer distQua) {
        this.distQua = distQua;
    }

    /**
     * <p>轮单次数。</p>
     *
     * @return the 轮单次数
     */
    public Integer getWheelFrequency() {
        return wheelFrequency;
    }

    /**
     * <p>轮单次数。</p>
     *
     * @param wheelFrequency 轮单次数。
     */
    public void setWheelFrequency(Integer wheelFrequency) {
        this.wheelFrequency = wheelFrequency;
    }

}
