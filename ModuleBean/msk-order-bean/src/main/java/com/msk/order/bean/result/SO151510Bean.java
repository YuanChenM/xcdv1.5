package com.msk.order.bean.result;

import com.msk.order.entity.SoOrderShip;
import com.msk.order.entity.SoOrderShipDetail;

import java.util.List;

/**
 * SO151510_发货单取消画面前台接口返回参数
 * Created by wang_jianzhou on 2016/8/4.
 */
public class SO151510Bean extends SoOrderShip {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 预计送货时间 */
    private String forecastSendTimeStr;
    /** 预计收货时间 */
    private String forecastReceiveTimeStr;
    /** 付款方式名 */
    private String paymentTypeName;
    /** 运输方式名 */
    private String deliveryTypeName;
    /** 订单发货单状态名 */
    private String shipStatusName;

    /** 订单明细供应商表 */
    private List<SO151510OrderShipDetail> soOrderShipDetails;

    public String getForecastSendTimeStr() {
        return forecastSendTimeStr;
    }

    public void setForecastSendTimeStr(String forecastSendTimeStr) {
        this.forecastSendTimeStr = forecastSendTimeStr;
    }

    public String getForecastReceiveTimeStr() {
        return forecastReceiveTimeStr;
    }

    public void setForecastReceiveTimeStr(String forecastReceiveTimeStr) {
        this.forecastReceiveTimeStr = forecastReceiveTimeStr;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getDeliveryTypeName() {
        return deliveryTypeName;
    }

    public void setDeliveryTypeName(String deliveryTypeName) {
        this.deliveryTypeName = deliveryTypeName;
    }

    public String getShipStatusName() {
        return shipStatusName;
    }

    public void setShipStatusName(String shipStatusName) {
        this.shipStatusName = shipStatusName;
    }

    public List<SO151510OrderShipDetail> getSoOrderShipDetails() {
        return soOrderShipDetails;
    }

    public void setSoOrderShipDetails(List<SO151510OrderShipDetail> soOrderShipDetails) {
        this.soOrderShipDetails = soOrderShipDetails;
    }
}
