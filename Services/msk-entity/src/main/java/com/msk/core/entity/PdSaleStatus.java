/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_sale_status对应的PdSaleStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdSaleStatus extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 销售状态编码 */
    private java.lang.String saleStatusCode;
    /** 销售状态名称 */
    private java.lang.String saleStatusName;
    /**
     * <p>默认构造函数。</p>
     */
    public PdSaleStatus() {

    }

    /**
     * <p>销售状态编码。</p>
     *
     * @return the 销售状态编码
     */
    public java.lang.String getSaleStatusCode() {
        return saleStatusCode;
    }

    /**
     * <p>销售状态编码。</p>
     *
     * @param saleStatusCode 销售状态编码。
     */
    public void setSaleStatusCode(java.lang.String saleStatusCode) {
        this.saleStatusCode = saleStatusCode;
    }

    /**
     * <p>销售状态名称。</p>
     *
     * @return the 销售状态名称
     */
    public java.lang.String getSaleStatusName() {
        return saleStatusName;
    }

    /**
     * <p>销售状态名称。</p>
     *
     * @param saleStatusName 销售状态名称。
     */
    public void setSaleStatusName(java.lang.String saleStatusName) {
        this.saleStatusName = saleStatusName;
    }

}
