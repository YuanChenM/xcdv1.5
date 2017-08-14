/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msk.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * <p>表so_return对应的SoReturn。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoReturn extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @OneToMany
    @JoinColumn(name = "returnId")
    private List<SoReturnDetail> soReturnDetailList;
    /** 退货单ID */
    @Id
    private Long returnId;
    /** 退货单编码 */
    private String returnCode;
    /** 订单ID */
    private Long orderId;
    /** 订单编码 */
    private String orderCode;
    /** 买家编码冗余 */
    private String buyerCode;
    /** 买家名称,冗余 */
    private String buyerName;
    /** 卖家编码,冗余 */
    private String sellerCode;
    /** 卖家名称,冗余 */
    private String sellerName;
    /** 订单所属物流区域,冗余 */
    private String districtCode;
    /** 退货单系统来源-CodeMaster
            1-司机PDA，2-云冻品平台，3-云冻品B2B平台 */
    private Integer returnSource;
    /** 退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货 */
    private String returnType;
    /** 退货方式-CodeMaster，1：全部 2：部分 */
    private String returnMode;
    /** 退货总金额 */
    private java.math.BigDecimal returnAmount;
    /** 退货原因ID */
    private Integer returnReason;
    /** 退货申请人 */
    private String applyMan;
    /** 退货申请时间 */
    private java.util.Date applyTime;
    /** 退货备注 */
    private String applyRemark;
    /** 收货人名称 */
    private String receiverName;
    /** 收货人电话 */
    private String receiverTel;
    /** 1:已付款 */
    private String isPaid;
    /** 退款方式（暂留） */
    private String refundMode;
    /** 1:已开票（暂留） */
    private String isInvoice;
    /** 暂留 */
    private String returnActor;
    /** 入库人ID */
    private String inboundManId;
    /** 入库人名称 */
    private String inboundManName;
    /** 入库时间 */
    private java.util.Date inboundTime;
    /** 退货状态，CodeMaster */
    private Integer returnStatus;
    /** 直销员（暂留） */
    private String sellers;
    /** 订单员（暂留） */
    private String orderTaker;
    /** 取消原因 */
    private String cancelReason;
    /** 退货原因照片1 */
    private String image1;
    /** 退货原因照片2 */
    private String image2;
    /** 退货原因照片3 */
    private String image3;
    /** 退货原因照片4 */
    private String image4;
    /** 退货原因照片5 */
    private String image5;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturn() {

    }

    /**
     * <p>退货单ID。</p>
     *
     * @return the 退货单ID
     */
    public Long getReturnId() {
        return returnId;
    }

    /**
     * <p>退货单ID。</p>
     *
     * @param returnId 退货单ID。
     */
    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    /**
     * <p>退货单编码。</p>
     *
     * @return the 退货单编码
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * <p>退货单编码。</p>
     *
     * @param returnCode 退货单编码。
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
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
     * <p>订单编码。</p>
     *
     * @return the 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @param orderCode 订单编码。
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>买家编码冗余。</p>
     *
     * @return the 买家编码冗余
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * <p>买家编码冗余。</p>
     *
     * @param buyerCode 买家编码冗余。
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * <p>买家名称,冗余。</p>
     *
     * @return the 买家名称,冗余
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * <p>买家名称,冗余。</p>
     *
     * @param buyerName 买家名称,冗余。
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * <p>卖家编码,冗余。</p>
     *
     * @return the 卖家编码,冗余
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * <p>卖家编码,冗余。</p>
     *
     * @param sellerCode 卖家编码,冗余。
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * <p>卖家名称,冗余。</p>
     *
     * @return the 卖家名称,冗余
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * <p>卖家名称,冗余。</p>
     *
     * @param sellerName 卖家名称,冗余。
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * <p>订单所属物流区域,冗余。</p>
     *
     * @return the 订单所属物流区域,冗余
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>订单所属物流区域,冗余。</p>
     *
     * @param districtCode 订单所属物流区域,冗余。
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>退货单系统来源-CodeMaster
            1-司机PDA，2-云冻品平台，3-云冻品B2B平台。</p>
     *
     * @return the 退货单系统来源-CodeMaster
            1-司机PDA，2-云冻品平台，3-云冻品B2B平台
     */
    public Integer getReturnSource() {
        return returnSource;
    }

    /**
     * <p>退货单系统来源-CodeMaster
            1-司机PDA，2-云冻品平台，3-云冻品B2B平台。</p>
     *
     * @param returnSource 退货单系统来源-CodeMaster
            1-司机PDA，2-云冻品平台，3-云冻品B2B平台。
     */
    public void setReturnSource(Integer returnSource) {
        this.returnSource = returnSource;
    }

    /**
     * <p>退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货。</p>
     *
     * @return the 退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * <p>退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货。</p>
     *
     * @param returnType 退货单类型-CodeMaster
            1-迟收；2-拒收；3-退货。
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * <p>退货方式-CodeMaster，1：全部 2：部分。</p>
     *
     * @return the 退货方式-CodeMaster，1：全部 2：部分
     */
    public String getReturnMode() {
        return returnMode;
    }

    /**
     * <p>退货方式-CodeMaster，1：全部 2：部分。</p>
     *
     * @param returnMode 退货方式-CodeMaster，1：全部 2：部分。
     */
    public void setReturnMode(String returnMode) {
        this.returnMode = returnMode;
    }

    /**
     * <p>退货总金额。</p>
     *
     * @return the 退货总金额
     */
    public java.math.BigDecimal getReturnAmount() {
        return returnAmount;
    }

    /**
     * <p>退货总金额。</p>
     *
     * @param returnAmount 退货总金额。
     */
    public void setReturnAmount(java.math.BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    /**
     * <p>退货原因ID。</p>
     *
     * @return the 退货原因ID
     */
    public Integer getReturnReason() {
        return returnReason;
    }

    /**
     * <p>退货原因ID。</p>
     *
     * @param returnReason 退货原因ID。
     */
    public void setReturnReason(Integer returnReason) {
        this.returnReason = returnReason;
    }

    /**
     * <p>退货申请人。</p>
     *
     * @return the 退货申请人
     */
    public String getApplyMan() {
        return applyMan;
    }

    /**
     * <p>退货申请人。</p>
     *
     * @param applyMan 退货申请人。
     */
    public void setApplyMan(String applyMan) {
        this.applyMan = applyMan;
    }

    /**
     * <p>退货申请时间。</p>
     *
     * @return the 退货申请时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public java.util.Date getApplyTime() {
        return applyTime;
    }

    /**
     * <p>退货申请时间。</p>
     *
     * @param applyTime 退货申请时间。
     */
    public void setApplyTime(java.util.Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * <p>退货备注。</p>
     *
     * @return the 退货备注
     */
    public String getApplyRemark() {
        return applyRemark;
    }

    /**
     * <p>退货备注。</p>
     *
     * @param applyRemark 退货备注。
     */
    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    /**
     * <p>收货人名称。</p>
     *
     * @return the 收货人名称
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * <p>收货人名称。</p>
     *
     * @param receiverName 收货人名称。
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * <p>收货人电话。</p>
     *
     * @return the 收货人电话
     */
    public String getReceiverTel() {
        return receiverTel;
    }

    /**
     * <p>收货人电话。</p>
     *
     * @param receiverTel 收货人电话。
     */
    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    /**
     * <p>1:已付款。</p>
     *
     * @return the 1:已付款
     */
    public String getIsPaid() {
        return isPaid;
    }

    /**
     * <p>1:已付款。</p>
     *
     * @param isPaid 1:已付款。
     */
    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * <p>退款方式（暂留）。</p>
     *
     * @return the 退款方式（暂留）
     */
    public String getRefundMode() {
        return refundMode;
    }

    /**
     * <p>退款方式（暂留）。</p>
     *
     * @param refundMode 退款方式（暂留）。
     */
    public void setRefundMode(String refundMode) {
        this.refundMode = refundMode;
    }

    /**
     * <p>1:已开票（暂留）。</p>
     *
     * @return the 1:已开票（暂留）
     */
    public String getIsInvoice() {
        return isInvoice;
    }

    /**
     * <p>1:已开票（暂留）。</p>
     *
     * @param isInvoice 1:已开票（暂留）。
     */
    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    /**
     * <p>暂留。</p>
     *
     * @return the 暂留
     */
    public String getReturnActor() {
        return returnActor;
    }

    /**
     * <p>暂留。</p>
     *
     * @param returnActor 暂留。
     */
    public void setReturnActor(String returnActor) {
        this.returnActor = returnActor;
    }

    /**
     * <p>入库人ID。</p>
     *
     * @return the 入库人ID
     */
    public String getInboundManId() {
        return inboundManId;
    }

    /**
     * <p>入库人ID。</p>
     *
     * @param inboundManId 入库人ID。
     */
    public void setInboundManId(String inboundManId) {
        this.inboundManId = inboundManId;
    }

    /**
     * <p>入库人名称。</p>
     *
     * @return the 入库人名称
     */
    public String getInboundManName() {
        return inboundManName;
    }

    /**
     * <p>入库人名称。</p>
     *
     * @param inboundManName 入库人名称。
     */
    public void setInboundManName(String inboundManName) {
        this.inboundManName = inboundManName;
    }

    /**
     * <p>入库时间。</p>
     *
     * @return the 入库时间
     */
    public java.util.Date getInboundTime() {
        return inboundTime;
    }

    /**
     * <p>入库时间。</p>
     *
     * @param inboundTime 入库时间。
     */
    public void setInboundTime(java.util.Date inboundTime) {
        this.inboundTime = inboundTime;
    }

    /**
     * <p>退货状态，CodeMaster。</p>
     *
     * @return the 退货状态，CodeMaster
     */
    public Integer getReturnStatus() {
        return returnStatus;
    }

    /**
     * <p>退货状态，CodeMaster。</p>
     *
     * @param returnStatus 退货状态，CodeMaster。
     */
    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    /**
     * <p>直销员（暂留）。</p>
     *
     * @return the 直销员（暂留）
     */
    public String getSellers() {
        return sellers;
    }

    /**
     * <p>直销员（暂留）。</p>
     *
     * @param sellers 直销员（暂留）。
     */
    public void setSellers(String sellers) {
        this.sellers = sellers;
    }

    /**
     * <p>订单员（暂留）。</p>
     *
     * @return the 订单员（暂留）
     */
    public String getOrderTaker() {
        return orderTaker;
    }

    /**
     * <p>订单员（暂留）。</p>
     *
     * @param orderTaker 订单员（暂留）。
     */
    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
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

    /**
     * <p>退货原因照片1。</p>
     *
     * @return the 退货原因照片1
     */
    public String getImage1() {
        return image1;
    }

    /**
     * <p>退货原因照片1。</p>
     *
     * @param image1 退货原因照片1。
     */
    public void setImage1(String image1) {
        this.image1 = image1;
    }

    /**
     * <p>退货原因照片2。</p>
     *
     * @return the 退货原因照片2
     */
    public String getImage2() {
        return image2;
    }

    /**
     * <p>退货原因照片2。</p>
     *
     * @param image2 退货原因照片2。
     */
    public void setImage2(String image2) {
        this.image2 = image2;
    }

    /**
     * <p>退货原因照片3。</p>
     *
     * @return the 退货原因照片3
     */
    public String getImage3() {
        return image3;
    }

    /**
     * <p>退货原因照片3。</p>
     *
     * @param image3 退货原因照片3。
     */
    public void setImage3(String image3) {
        this.image3 = image3;
    }

    /**
     * <p>退货原因照片4。</p>
     *
     * @return the 退货原因照片4
     */
    public String getImage4() {
        return image4;
    }

    /**
     * <p>退货原因照片4。</p>
     *
     * @param image4 退货原因照片4。
     */
    public void setImage4(String image4) {
        this.image4 = image4;
    }

    /**
     * <p>退货原因照片5。</p>
     *
     * @return the 退货原因照片5
     */
    public String getImage5() {
        return image5;
    }

    /**
     * <p>退货原因照片5。</p>
     *
     * @param image5 退货原因照片5。
     */
    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public List<SoReturnDetail> getSoReturnDetailList() {
        return soReturnDetailList;
    }

    public void setSoReturnDetailList(List<SoReturnDetail> soReturnDetailList) {
        this.soReturnDetailList = soReturnDetailList;
    }
}
