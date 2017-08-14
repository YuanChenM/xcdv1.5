package com.msk.order.bean;


import com.msk.order.entity.SoOrder;

/**
 * BaseOrder
 *
 * @author jiang_nan
 * @version 1.0
 *          update gyh
 *          updateTime 2016.1.4
 **/
public class BaseOrder extends SoOrder {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 区域名称 */
    private String districtName;
    /** 是否有退货 */
    private String returnFlgName;
    /** 备注 */
    private String remark;
    /** 订单创建时间 */
    private String orderDate;
    /** 付款类型 */
    private String paymentName;
    /** 发货仓库名称 */
    private String sendWareHouseCode;
    /** 订单来源 */
    private String orderSourceName;
    /**订单类型*/
    private String orderTypeName;
    /**订单状态*/
    private String orderStatusName;
    /**是否开发票*/
    private String needInvoiceName;
    

    /**
     * Get the districtName.
     *
     * @return districtName
     *
     * @author gyh
     */
    public String getDistrictName() {
        return this.districtName;
    }

    /**
     * Set the districtName.
     *
     * @param districtName districtName
     *
     * @author gyh
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * Get the returnFlgName.
     *
     * @return returnFlgName
     *
     * @author gyh
     */
    public String getReturnFlgName() {
        return this.returnFlgName;
    }

    /**
     * Set the returnFlgName.
     *
     * @param returnFlgName returnFlgName
     *
     * @author gyh
     */
    public void setReturnFlgName(String returnFlgName) {
        this.returnFlgName = returnFlgName;
    }

    /**
     * Get the remark.
     *
     * @return remark
     *
     * @author gyh
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * Set the remark.
     *
     * @param remark remark
     *
     * @author gyh
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

  

    /**
     * Get the paymentName.
     *
     * @return paymentName
     *
     * @author gyh
     */
    public String getPaymentName() {
        return this.paymentName;
    }

    /**
     * Set the paymentName.
     *
     * @param paymentName paymentName
     *
     * @author gyh
     */
    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    /**
     * Get the sendWareHouseCode.
     *
     * @return sendWareHouseCode
     *
     * @author gyh
     */
    public String getSendWareHouseCode() {
        return this.sendWareHouseCode;
    }

    /**
     * Set the sendWareHouseCode.
     *
     * @param sendWareHouseCode sendWareHouseCode
     *
     * @author gyh
     */
    public void setSendWareHouseCode(String sendWareHouseCode) {
        this.sendWareHouseCode = sendWareHouseCode;
    }

    /**
     * Get the orderSourceName.
     *
     * @return orderSourceName
     *
     * @author gyh
     */
    public String getOrderSourceName() {
        return this.orderSourceName;
    }

    /**
     * Set the orderSourceName.
     *
     * @param orderSourceName orderSourceName
     *
     * @author gyh
     */
    public void setOrderSourceName(String orderSourceName) {
        this.orderSourceName = orderSourceName;
    }

    /**
     * Get the orderTypeName.
     *
     * @return orderTypeName
     *
     * @author gyh
     */
    public String getOrderTypeName() {
        return this.orderTypeName;
    }

    /**
     * Set the orderTypeName.
     *
     * @param orderTypeName orderTypeName
     *
     * @author gyh
     */
    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    /**
     * Get the orderStatusName.
     *
     * @return orderStatusName
     *
     * @author gyh
     */
    public String getOrderStatusName() {
        return this.orderStatusName;
    }

    /**
     * Set the orderStatusName.
     *
     * @param orderStatusName orderStatusName
     *
     * @author gyh
     */
    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    /**
     * Get the needInvoiceName.
     *
     * @return needInvoiceName
     *
     * @author gyh
     */
    public String getNeedInvoiceName() {
        return this.needInvoiceName;
    }

    /**
     * Set the needInvoiceName.
     *
     * @param needInvoiceName needInvoiceName
     *
     * @author gyh
     */
    public void setNeedInvoiceName(String needInvoiceName) {
        this.needInvoiceName = needInvoiceName;
    }

    /**
     * Get the orderDate.
     *
     * @return orderDate
     *
     * @author gyh
     */
    public String getOrderDate() {
        return this.orderDate;
    }

    /**
     * Set the orderDate.
     *
     * @param orderDate orderDate
     *
     * @author gyh
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

}
