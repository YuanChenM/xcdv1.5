package com.msk.order.bean.result;

import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;

/**
 * Created by zhang_qiang1 on 2016/8/19.
 */

public class ISO151409ReturnDetailRestResult extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 退货单明细ID*/
    private Integer returnDetailId;
    /** 产品编号*/
    private String pdCode;
    /** 产品名称*/
    private String pdName;
    /** 产品等级*/
    private Integer pdLevel;
    /** 产品单位*/
    private String unit;
    /** 单件体积*/
    private BigDecimal packingVolume;
    /** 重量*/
    private BigDecimal weight;
    /** 体积*/
    private BigDecimal volume;
    /** 单价*/
    private BigDecimal orderPrice;
    /** 单价所属价盘周期*/
    private String priceCycle;
    /** 退货数量*/
    private Integer returnQty;
    /** 收货数量*/
    private Integer receiveQty;
    /** 退货原因*/
    private String returnReasonCode;
    /** 退货原因描述*/
    private String returnReasonDes;
    /** 原订单明细ID*/
    private Integer orderDetailId;


    public Integer getReturnDetailId() {
        return returnDetailId;
    }

    public void setReturnDetailId(Integer returnDetailId) {
        this.returnDetailId = returnDetailId;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public Integer getPdLevel() {
        return pdLevel;
    }

    public void setPdLevel(Integer pdLevel) {
        this.pdLevel = pdLevel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    public Integer getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Integer returnQty) {
        this.returnQty = returnQty;
    }

    public Integer getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(Integer receiveQty) {
        this.receiveQty = receiveQty;
    }

    public String getReturnReasonCode() {
        return returnReasonCode;
    }

    public void setReturnReasonCode(String returnReasonCode) {
        this.returnReasonCode = returnReasonCode;
    }

    public String getReturnReasonDes() {
        return returnReasonDes;
    }

    public void setReturnReasonDes(String returnReasonDes) {
        this.returnReasonDes = returnReasonDes;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
