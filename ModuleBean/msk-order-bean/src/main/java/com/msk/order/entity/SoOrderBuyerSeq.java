/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>表so_order_buyer_seq对应的SoOrderBuyerSeq。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoOrderBuyerSeq extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 顺序号ID */
    @Id
    private Long seqId;
    /** 卖家ID */
    private String buyerId;
    /** 买家编码 */
    private String buyerCode;
    /** 订单顺序号 */
    private Long buyCount;
    /**
     * <p>默认构造函数。</p>
     */
    public SoOrderBuyerSeq() {

    }

    /**
     * <p>顺序号ID。</p>
     *
     * @return the 顺序号ID
     */
    public Long getSeqId() {
        return seqId;
    }

    /**
     * <p>顺序号ID。</p>
     *
     * @param seqId 顺序号ID。
     */
    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    /**
     * <p>卖家ID。</p>
     *
     * @return the 卖家ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>卖家ID。</p>
     *
     * @param buyerId 卖家ID。
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>买家编码。</p>
     *
     * @return the 买家编码
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * <p>买家编码。</p>
     *
     * @param buyerCode 买家编码。
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * <p>订单顺序号。</p>
     *
     * @return the 订单顺序号
     */
    public Long getBuyCount() {
        return buyCount;
    }

    /**
     * <p>订单顺序号。</p>
     *
     * @param buyCount 订单顺序号。
     */
    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

}
