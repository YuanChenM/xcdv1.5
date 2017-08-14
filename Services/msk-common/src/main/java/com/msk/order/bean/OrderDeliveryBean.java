package com.msk.order.bean;


import com.msk.core.entity.SoOrderReceiveDemand;

/**
 * OrderDeliveryBean
 * 配送物流信息
 * 
 * @author jiang_nan
 * @version 1.0
 * update gyh
 * updateTime 2015.1.4
 **/
public class OrderDeliveryBean extends SoOrderReceiveDemand {

    private static final long serialVersionUID = 1L;
    /** 配送方式 */
    private String deliveryTypeName;
    /**发送仓库编码*/
    private String sendWarehouseCode;
    /**发送仓库名称*/
    private String sendWarehouseName;
    /**发货时间*/
    private String sendDateTime;
    /**收货时间*/
    private String receiveDateTime;
    /**预计发货时间*/
    private String forecastSendDateTime;
    /**预计收货时间*/
    private String forecastReceiveDateTime;

    /**
     * Get the deliveryTypeName.
     *
     * @return deliveryTypeName
     *
     * @author gyh
     */
    public String getDeliveryTypeName() {
        return this.deliveryTypeName;
    }

    /**
     * Set the deliveryTypeName.
     *
     * @param deliveryTypeName deliveryTypeName
     *
     * @author gyh
     */
    public void setDeliveryTypeName(String deliveryTypeName) {
        this.deliveryTypeName = deliveryTypeName;
    }

    public String getSendWarehouseCode() {
        return sendWarehouseCode;
    }

    public void setSendWarehouseCode(String sendWarehouseCode) {
        this.sendWarehouseCode = sendWarehouseCode;
    }

    public String getSendWarehouseName() {
        return sendWarehouseName;
    }

    public void setSendWarehouseName(String sendWarehouseName) {
        this.sendWarehouseName = sendWarehouseName;
    }

    public String getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(String sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public String getReceiveDateTime() {
        return receiveDateTime;
    }

    public void setReceiveDateTime(String receiveDateTime) {
        this.receiveDateTime = receiveDateTime;
    }

    /**
     * Getter method for property <tt>forecastSendDateTime</tt>.
     *
     * @return property value of forecastSendDateTime
     */
    public String getForecastSendDateTime() {
        return forecastSendDateTime;
    }

    /**
     * Setter method for property <tt>forecastSendDateTime</tt>.
     *
     * @param forecastSendDateTime value to be assigned to property forecastSendDateTime
     */
    public void setForecastSendDateTime(String forecastSendDateTime) {
        this.forecastSendDateTime = forecastSendDateTime;
    }

    /**
     * Getter method for property <tt>forecastReceiveDateTime</tt>.
     *
     * @return property value of forecastReceiveDateTime
     */
    public String getForecastReceiveDateTime() {
        return forecastReceiveDateTime;
    }

    /**
     * Setter method for property <tt>forecastReceiveDateTime</tt>.
     *
     * @param forecastReceiveDateTime value to be assigned to property forecastReceiveDateTime
     */
    public void setForecastReceiveDateTime(String forecastReceiveDateTime) {
        this.forecastReceiveDateTime = forecastReceiveDateTime;
    }
}
