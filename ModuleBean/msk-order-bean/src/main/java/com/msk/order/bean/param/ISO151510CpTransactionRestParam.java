package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.Date;

/**
 * Created by wang_jianzhou on 2016/9/14.
 */
public class ISO151510CpTransactionRestParam extends BaseParam {

    /** 是否新增标识 0：否 1：是*/
    private Integer insertFlg;
    /** 交易编码*/
    private String transCode;
    /** 交易类型 0 主订单 1管理费用清单*/
    private Integer transType;
    /** 操作时间*/
    private Date operateDate;
    /** 平台类型 1:神农客 2:美侍客 3:大促会*/
    private Integer supplyPlatform;
    /** 交易标志 0：正常 1：交易关闭  订单关闭的情况下传1，其余情况传0*/
    private Integer transFlg;
    /** 订单ID*/
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getInsertFlg() {
        return insertFlg;
    }

    public void setInsertFlg(Integer insertFlg) {
        this.insertFlg = insertFlg;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public Integer getSupplyPlatform() {
        return supplyPlatform;
    }

    public void setSupplyPlatform(Integer supplyPlatform) {
        this.supplyPlatform = supplyPlatform;
    }

    public Integer getTransFlg() {
        return transFlg;
    }

    public void setTransFlg(Integer transFlg) {
        this.transFlg = transFlg;
    }

}
