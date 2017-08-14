package com.msk.order.bean.param;

import com.msk.order.entity.SoOrderDetail;
import com.msk.order.entity.SoOrderShipDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/1.
 */
public class ISO151414OrderDetailParam extends SoOrderDetail {

    /**供应商Code*/
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** 供应商明细信息 */
    private List<SoOrderShipDetail> orderShipDetails;
    /** 产品单价 */
    private BigDecimal orderPrice;
    /** 订单编码 */
    private String orderCode;

    /** 货号 */
    private String skuCode;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public List<SoOrderShipDetail> getOrderShipDetails() {
        return orderShipDetails;
    }

    public void setOrderShipDetails(List<SoOrderShipDetail> orderShipDetails) {
        this.orderShipDetails = orderShipDetails;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

}
