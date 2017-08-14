/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_sales_ranking_history对应的SoSalesRankingHistory。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoSalesRankingHistory extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 物流区编号 */
    private String lgcsCode;
    /** 历史时间 */
    private java.util.Date historyDate;
    /** ORDER_LEVEL */
    private Integer orderLevel;
    /** PD_CODE */
    private String pdCode;
    /** 供应商编码 */
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** PRICE_CYCLE */
    private String priceCycle;
    /** SALES_AMOUNT */
    private java.math.BigDecimal salesAmount;
    /** SALES_RANKING */
    private Integer salesRanking;
    /** 分销资格 */
    private Integer distQua;
    /** 轮单次数 */
    private Integer wheelFrequency;
    /**
     * <p>默认构造函数。</p>
     */
    public SoSalesRankingHistory() {

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
     * <p>历史时间。</p>
     *
     * @return the 历史时间
     */
    public java.util.Date getHistoryDate() {
        return historyDate;
    }

    /**
     * <p>历史时间。</p>
     *
     * @param historyDate 历史时间。
     */
    public void setHistoryDate(java.util.Date historyDate) {
        this.historyDate = historyDate;
    }

    /**
     * <p>ORDER_LEVEL。</p>
     *
     * @return the ORDER_LEVEL
     */
    public Integer getOrderLevel() {
        return orderLevel;
    }

    /**
     * <p>ORDER_LEVEL。</p>
     *
     * @param orderLevel ORDER_LEVEL。
     */
    public void setOrderLevel(Integer orderLevel) {
        this.orderLevel = orderLevel;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @return the PD_CODE
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @param pdCode PD_CODE。
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
     * <p>PRICE_CYCLE。</p>
     *
     * @return the PRICE_CYCLE
     */
    public String getPriceCycle() {
        return priceCycle;
    }

    /**
     * <p>PRICE_CYCLE。</p>
     *
     * @param priceCycle PRICE_CYCLE。
     */
    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    /**
     * <p>SALES_AMOUNT。</p>
     *
     * @return the SALES_AMOUNT
     */
    public java.math.BigDecimal getSalesAmount() {
        return salesAmount;
    }

    /**
     * <p>SALES_AMOUNT。</p>
     *
     * @param salesAmount SALES_AMOUNT。
     */
    public void setSalesAmount(java.math.BigDecimal salesAmount) {
        this.salesAmount = salesAmount;
    }

    /**
     * <p>SALES_RANKING。</p>
     *
     * @return the SALES_RANKING
     */
    public Integer getSalesRanking() {
        return salesRanking;
    }

    /**
     * <p>SALES_RANKING。</p>
     *
     * @param salesRanking SALES_RANKING。
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
