package com.msk.order.bean.param;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liu_tao2 on 2016/8/8.
 */
public class ISO151416OrderListSearchParam extends ISO151416OrderSearchParam {

    /** 订单类型 */
    private String orderType;
    /** 订单状态 */
    private String orderStatus;
    /** 订单开始时间 */
    private Date orderTimeFrom;
    /** 订单结束时间 */
    private Date orderTimeTo;
    /** 订单来源 */
    private String orderSource;
    /** 订单区域 */
    private String districtCode;
    /** 付款类型 */
    private Integer paymentType;
    /** 订单金额下限 */
    private BigDecimal orderAmountMin;
    /** 订单金额上限 */
    private BigDecimal orderAmountMax;
    /** 订单等级 */
    private String orderLevel;
    /** 是否开票 */
    private Integer needInvoice;
    /** 退货标志 */
    private String returnFlg;
    /** 是否自配送 */
    private Integer selfDeliveryFlg;
    /** 是否分批发货 */
    private Integer splitDeliveryFlg;
    /** 订单员 */
    private String orderTaker;

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

    public Date getOrderTimeFrom() {
        return orderTimeFrom;
    }

    public void setOrderTimeFrom(Date orderTimeFrom) {
        this.orderTimeFrom = orderTimeFrom;
    }

    public Date getOrderTimeTo() {
        return orderTimeTo;
    }

    public void setOrderTimeTo(Date orderTimeTo) {
        this.orderTimeTo = orderTimeTo;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getOrderAmountMin() {
        return orderAmountMin;
    }

    public void setOrderAmountMin(BigDecimal orderAmountMin) {
        this.orderAmountMin = orderAmountMin;
    }

    public BigDecimal getOrderAmountMax() {
        return orderAmountMax;
    }

    public void setOrderAmountMax(BigDecimal orderAmountMax) {
        this.orderAmountMax = orderAmountMax;
    }

    public String getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(String orderLevel) {
        this.orderLevel = orderLevel;
    }

    public Integer getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(Integer needInvoice) {
        this.needInvoice = needInvoice;
    }

    public String getReturnFlg() {
        return returnFlg;
    }

    public void setReturnFlg(String returnFlg) {
        this.returnFlg = returnFlg;
    }

    public Integer getSelfDeliveryFlg() {
        return selfDeliveryFlg;
    }

    public void setSelfDeliveryFlg(Integer selfDeliveryFlg) {
        this.selfDeliveryFlg = selfDeliveryFlg;
    }

    public Integer getSplitDeliveryFlg() {
        return splitDeliveryFlg;
    }

    public void setSplitDeliveryFlg(Integer splitDeliveryFlg) {
        this.splitDeliveryFlg = splitDeliveryFlg;
    }

    public String getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }
}
