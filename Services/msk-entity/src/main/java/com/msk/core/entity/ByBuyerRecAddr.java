/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_rec_addr对应的ByBuyerRecAddr</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerRecAddr extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** RECEIVE_ADDR */
    private String receiveAddr;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerRecAddr() {

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
     * <p>RECEIVE_ADDR</p>
     *
     * @return the RECEIVE_ADDR
     */
    public String getReceiveAddr() {
        return receiveAddr;
    }

    /**
     * <p>RECEIVE_ADDR</p>
     *
     * @param receiveAddr RECEIVE_ADDR
     */
    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

}
