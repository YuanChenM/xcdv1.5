/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_market_terminal_br_sale_pd对应的ByMarketTerminalBrSalePd</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByMarketTerminalBrSalePd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** STORE_ID */
    private Long storeId;
    /** 买家店铺号 */
    private String buyerStoreNo;
    /** 销售产品编码 */
    private String salePdCode;
    /** 销售产品名称 */
    private String salePd;
    /**
     * <p>默认构造函数</p>
     */
    public ByMarketTerminalBrSalePd() {

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
     * <p>STORE_ID</p>
     *
     * @return the STORE_ID
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * <p>STORE_ID</p>
     *
     * @param storeId STORE_ID
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * <p>买家店铺号</p>
     *
     * @return the 买家店铺号
     */
    public String getBuyerStoreNo() {
        return buyerStoreNo;
    }

    /**
     * <p>买家店铺号</p>
     *
     * @param buyerStoreNo 买家店铺号
     */
    public void setBuyerStoreNo(String buyerStoreNo) {
        this.buyerStoreNo = buyerStoreNo;
    }

    /**
     * <p>销售产品编码</p>
     *
     * @return the 销售产品编码
     */
    public String getSalePdCode() {
        return salePdCode;
    }

    /**
     * <p>销售产品编码</p>
     *
     * @param salePdCode 销售产品编码
     */
    public void setSalePdCode(String salePdCode) {
        this.salePdCode = salePdCode;
    }

    /**
     * <p>销售产品名称</p>
     *
     * @return the 销售产品名称
     */
    public String getSalePd() {
        return salePd;
    }

    /**
     * <p>销售产品名称</p>
     *
     * @param salePd 销售产品名称
     */
    public void setSalePd(String salePd) {
        this.salePd = salePd;
    }

}
