package com.msk.so.bean;

import com.msk.common.base.BaseBean;
import com.msk.core.entity.SoCpSelCharging;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by li_huiqian on 2016/9/5.
 */
public class SOCp153112Bean extends BaseBean {

    /** 表名 */
    private String tb;

    /** 主键 */
    private Long id;

    /** 订单号 */
    private String transCode;

    /** 卖家计费明细ID */
    private Long sellerBillId;

    /** 退款编码 */
    private String refundCode;

    /** 退款金额 */
    private BigDecimal refundAmount;

    /** 退款时间 */
    private Date refundTime;

    /** 退回费用类型 */
    private Integer refundType;

    /** 退款时间字符串表现形 */
    private String refundTimeStr;

    /** 退回费用类型字符串表现形 */
    private String refundTypeStr;

    /** 0：不重新发货 1：重新发货（针对拒收退款） */
    private String reShipFlg;

    /** 计费项类型为买家时检索 */
    private String payerId;

    /** 计费项类型是卖家时检索 */
    private String payeeId;

    /** 备注 */
    private String remark;

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Long getSellerBillId() {
        return sellerBillId;
    }

    public void setSellerBillId(Long sellerBillId) {
        this.sellerBillId = sellerBillId;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public String getRefundTimeStr() {
        return refundTimeStr;
    }

    public void setRefundTimeStr(String refundTimeStr) {
        this.refundTimeStr = refundTimeStr;
    }

    public String getRefundTypeStr() {
        return refundTypeStr;
    }

    public void setRefundTypeStr(String refundTypeStr) {
        this.refundTypeStr = refundTypeStr;
    }

    public String getReShipFlg() {
        return reShipFlg;
    }

    public void setReShipFlg(String reShipFlg) {
        this.reShipFlg = reShipFlg;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
