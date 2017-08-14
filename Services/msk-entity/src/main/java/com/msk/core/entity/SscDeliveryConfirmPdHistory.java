/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_confirm_pd_history对应的SscDeliveryConfirmPdHistory。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryConfirmPdHistory extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 历史ID */
    private java.lang.Long id;
    /** 三方确认产品明细ID */
    private java.lang.Long confirmDetailId;
    /** 产品重量 */
    private java.math.BigDecimal productQua;
    /** 产品箱数 */
    private java.lang.Integer productConfirmBox;
    /** 本次结算标准价 */
    private java.math.BigDecimal settkementStandardPrice;
    /** 货值 */
    private java.math.BigDecimal productValue;
    /** 创建者姓名 */
    private java.lang.String crtName;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryConfirmPdHistory() {

    }

    /**
     * <p>历史ID。</p>
     *
     * @return the 历史ID
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * <p>历史ID。</p>
     *
     * @param id 历史ID。
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    /**
     * <p>三方确认产品明细ID。</p>
     *
     * @return the 三方确认产品明细ID
     */
    public java.lang.Long getConfirmDetailId() {
        return confirmDetailId;
    }

    /**
     * <p>三方确认产品明细ID。</p>
     *
     * @param confirmDetailId 三方确认产品明细ID。
     */
    public void setConfirmDetailId(java.lang.Long confirmDetailId) {
        this.confirmDetailId = confirmDetailId;
    }

    /**
     * <p>产品重量。</p>
     *
     * @return the 产品重量
     */
    public java.math.BigDecimal getProductQua() {
        return productQua;
    }

    /**
     * <p>产品重量。</p>
     *
     * @param productQua 产品重量。
     */
    public void setProductQua(java.math.BigDecimal productQua) {
        this.productQua = productQua;
    }

    /**
     * <p>产品箱数。</p>
     *
     * @return the 产品箱数
     */
    public java.lang.Integer getProductConfirmBox() {
        return productConfirmBox;
    }

    /**
     * <p>产品箱数。</p>
     *
     * @param productConfirmBox 产品箱数。
     */
    public void setProductConfirmBox(java.lang.Integer productConfirmBox) {
        this.productConfirmBox = productConfirmBox;
    }

    /**
     * <p>本次结算标准价。</p>
     *
     * @return the 本次结算标准价
     */
    public java.math.BigDecimal getSettkementStandardPrice() {
        return settkementStandardPrice;
    }

    /**
     * <p>本次结算标准价。</p>
     *
     * @param settkementStandardPrice 本次结算标准价。
     */
    public void setSettkementStandardPrice(java.math.BigDecimal settkementStandardPrice) {
        this.settkementStandardPrice = settkementStandardPrice;
    }

    /**
     * <p>货值。</p>
     *
     * @return the 货值
     */
    public java.math.BigDecimal getProductValue() {
        return productValue;
    }

    /**
     * <p>货值。</p>
     *
     * @param productValue 货值。
     */
    public void setProductValue(java.math.BigDecimal productValue) {
        this.productValue = productValue;
    }

    /**
     * <p>创建者姓名。</p>
     *
     * @return the 创建者姓名
     */
    public java.lang.String getCrtName() {
        return crtName;
    }

    /**
     * <p>创建者姓名。</p>
     *
     * @param crtName 创建者姓名。
     */
    public void setCrtName(java.lang.String crtName) {
        this.crtName = crtName;
    }

}
