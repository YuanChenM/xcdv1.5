/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_sub_order_detail对应的SoSubOrderDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoSubOrderDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 分批订单明细ID */
    private Long subOrderDetailId;
    /** 分批订单ID */
    private Long subOrderId;
    /** 订单ID */
    private Long orderId;
    /** 订单明细ID */
    private Long orderDetailId;
    /** 订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单 */
    private Integer orderDetailType;
    /** 订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单 */
    private Integer orderDetailLevel;
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 产品单价 */
    private java.math.BigDecimal pdPrice;
    /** 价盘周期 */
    private String priceCycle;
    /** 期望配送日 */
    private java.util.Date proDate;
    /** 订单数量 */
    private java.math.BigDecimal orderQty;
    /** 发货数量 */
    private java.math.BigDecimal sendQty;
    /** 收货数量 */
    private java.math.BigDecimal receiveQty;
    /** 取消数量 */
    private java.math.BigDecimal cancelQty;
    /** 退货数量 */
    private java.math.BigDecimal returnQty;
    /** 拒收数量 */
    private java.math.BigDecimal rejectionQty;
    /** 发货时间 */
    private java.util.Date sendTime;
    /** 收货时间 */
    private java.util.Date receivedTime;
    /** 明细状态,CodeMaster */
    private Integer detailStatus;
    /** 取消原因 */
    private String cancelReason;
    /**
     * <p>默认构造函数。</p>
     */
    public SoSubOrderDetail() {

    }

    /**
     * <p>分批订单明细ID。</p>
     *
     * @return the 分批订单明细ID
     */
    public Long getSubOrderDetailId() {
        return subOrderDetailId;
    }

    /**
     * <p>分批订单明细ID。</p>
     *
     * @param subOrderDetailId 分批订单明细ID。
     */
    public void setSubOrderDetailId(Long subOrderDetailId) {
        this.subOrderDetailId = subOrderDetailId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @return the 分批订单ID
     */
    public Long getSubOrderId() {
        return subOrderId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @param subOrderId 分批订单ID。
     */
    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>订单明细ID。</p>
     *
     * @return the 订单明细ID
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * <p>订单明细ID。</p>
     *
     * @param orderDetailId 订单明细ID。
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * <p>订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单。</p>
     *
     * @return the 订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单
     */
    public Integer getOrderDetailType() {
        return orderDetailType;
    }

    /**
     * <p>订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单。</p>
     *
     * @param orderDetailType 订单明细类型-CodeMaster
            1:正常订单 2:非正常订单 3:促销订单。
     */
    public void setOrderDetailType(Integer orderDetailType) {
        this.orderDetailType = orderDetailType;
    }

    /**
     * <p>订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单。</p>
     *
     * @return the 订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单
     */
    public Integer getOrderDetailLevel() {
        return orderDetailLevel;
    }

    /**
     * <p>订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单。</p>
     *
     * @param orderDetailLevel 订单明细等级-CodeMaster
            1:标准订单,2:大额订单,3:大宗订单,4:超级大宗订单。
     */
    public void setOrderDetailLevel(Integer orderDetailLevel) {
        this.orderDetailLevel = orderDetailLevel;
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
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdName 产品名称。
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * <p>产品单价。</p>
     *
     * @return the 产品单价
     */
    public java.math.BigDecimal getPdPrice() {
        return pdPrice;
    }

    /**
     * <p>产品单价。</p>
     *
     * @param pdPrice 产品单价。
     */
    public void setPdPrice(java.math.BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
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
     * <p>期望配送日。</p>
     *
     * @return the 期望配送日
     */
    public java.util.Date getProDate() {
        return proDate;
    }

    /**
     * <p>期望配送日。</p>
     *
     * @param proDate 期望配送日。
     */
    public void setProDate(java.util.Date proDate) {
        this.proDate = proDate;
    }

    /**
     * <p>订单数量。</p>
     *
     * @return the 订单数量
     */
    public java.math.BigDecimal getOrderQty() {
        return orderQty;
    }

    /**
     * <p>订单数量。</p>
     *
     * @param orderQty 订单数量。
     */
    public void setOrderQty(java.math.BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * <p>发货数量。</p>
     *
     * @return the 发货数量
     */
    public java.math.BigDecimal getSendQty() {
        return sendQty;
    }

    /**
     * <p>发货数量。</p>
     *
     * @param sendQty 发货数量。
     */
    public void setSendQty(java.math.BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    /**
     * <p>收货数量。</p>
     *
     * @return the 收货数量
     */
    public java.math.BigDecimal getReceiveQty() {
        return receiveQty;
    }

    /**
     * <p>收货数量。</p>
     *
     * @param receiveQty 收货数量。
     */
    public void setReceiveQty(java.math.BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    /**
     * <p>取消数量。</p>
     *
     * @return the 取消数量
     */
    public java.math.BigDecimal getCancelQty() {
        return cancelQty;
    }

    /**
     * <p>取消数量。</p>
     *
     * @param cancelQty 取消数量。
     */
    public void setCancelQty(java.math.BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    /**
     * <p>退货数量。</p>
     *
     * @return the 退货数量
     */
    public java.math.BigDecimal getReturnQty() {
        return returnQty;
    }

    /**
     * <p>退货数量。</p>
     *
     * @param returnQty 退货数量。
     */
    public void setReturnQty(java.math.BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    /**
     * <p>拒收数量。</p>
     *
     * @return the 拒收数量
     */
    public java.math.BigDecimal getRejectionQty() {
        return rejectionQty;
    }

    /**
     * <p>拒收数量。</p>
     *
     * @param rejectionQty 拒收数量。
     */
    public void setRejectionQty(java.math.BigDecimal rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    /**
     * <p>发货时间。</p>
     *
     * @return the 发货时间
     */
    public java.util.Date getSendTime() {
        return sendTime;
    }

    /**
     * <p>发货时间。</p>
     *
     * @param sendTime 发货时间。
     */
    public void setSendTime(java.util.Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * <p>收货时间。</p>
     *
     * @return the 收货时间
     */
    public java.util.Date getReceivedTime() {
        return receivedTime;
    }

    /**
     * <p>收货时间。</p>
     *
     * @param receivedTime 收货时间。
     */
    public void setReceivedTime(java.util.Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * <p>明细状态,CodeMaster。</p>
     *
     * @return the 明细状态,CodeMaster
     */
    public Integer getDetailStatus() {
        return detailStatus;
    }

    /**
     * <p>明细状态,CodeMaster。</p>
     *
     * @param detailStatus 明细状态,CodeMaster。
     */
    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    /**
     * <p>取消原因。</p>
     *
     * @return the 取消原因
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * <p>取消原因。</p>
     *
     * @param cancelReason 取消原因。
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

}
