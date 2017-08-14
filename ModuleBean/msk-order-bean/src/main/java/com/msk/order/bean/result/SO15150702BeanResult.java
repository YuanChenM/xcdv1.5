package com.msk.order.bean.result;


import com.msk.order.entity.SoOrderDetail;

import java.math.BigDecimal;

/**
 * ISO151506_退货单详情
 * Created by wang_shuai on 2016/8/3.
 */
public class SO15150702BeanResult extends SoOrderDetail {
    /** 订单总金额 */
    private BigDecimal pdPriceAll;
    /** 外包装规格 */
    private String normsOut;
    /**明细状态*/
    private String detailStatusName;
    /**退货标志*/
    private String returnFlg;
    /**分批发货*/
    private String splitDeliveryFlgName;

    private String priceCycleName;

    private BigDecimal netWeightOut;
    //期望配送日
    private String expectedDate;

    private String childOrderId;

    private String childOrderCode;

    //订单类型
    private String orderType;

    //订单状态
    private String orderStatus;

    private BigDecimal oneAllMoney;

    public BigDecimal getPdPriceAll() {
        return pdPriceAll;
    }

    public void setPdPriceAll(BigDecimal pdPriceAll) {
        this.pdPriceAll = pdPriceAll;
    }

    public String getNormsOut() {
        return normsOut;
    }

    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    public String getDetailStatusName() {
        return detailStatusName;
    }

    public void setDetailStatusName(String detailStatusName) {
        this.detailStatusName = detailStatusName;
    }

    public String getReturnFlg() {
        return returnFlg;
    }

    public void setReturnFlg(String returnFlg) {
        this.returnFlg = returnFlg;
    }

    public String getSplitDeliveryFlgName() {
        return splitDeliveryFlgName;
    }

    public void setSplitDeliveryFlgName(String splitDeliveryFlgName) {
        this.splitDeliveryFlgName = splitDeliveryFlgName;
    }

    public String getPriceCycleName() {
        return priceCycleName;
    }

    public void setPriceCycleName(String priceCycleName) {
        this.priceCycleName = priceCycleName;
    }

    public BigDecimal getNetWeightOut() {
        return netWeightOut;
    }

    public void setNetWeightOut(BigDecimal netWeightOut) {
        this.netWeightOut = netWeightOut;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getChildOrderId() {
        return childOrderId;
    }

    public void setChildOrderId(String childOrderId) {
        this.childOrderId = childOrderId;
    }

    public String getChildOrderCode() {
        return childOrderCode;
    }

    public void setChildOrderCode(String childOrderCode) {
        this.childOrderCode = childOrderCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOneAllMoney() {
        return oneAllMoney;
    }

    public void setOneAllMoney(BigDecimal oneAllMoney) {
        this.oneAllMoney = oneAllMoney;
    }
}
