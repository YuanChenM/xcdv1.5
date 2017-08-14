package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.order.bean.result.ISO151414DeliveryReq;

import com.msk.order.bean.result.ISO151414InvoiceReq;
import com.msk.order.bean.result.ISO151414ReceiverInfo;
import com.msk.order.entity.SoOrder;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/1.
 */
public class ISO151414BaseOrderParam extends SoOrder {
    /**
     * 订单收货人要求
     */
    private ISO151414ReceiverInfo receiverInfo;
    /**
     * 订单配送要求
     */
    private ISO151414DeliveryReq deliveryReq;
    /**
     * 订单发票要求
     */
    private ISO151414InvoiceReq invoiceReq;
    /**
     * 订单产品信息
     */
    private List<ISO151414OrderDetailParam> products;

    private boolean haveBuyerFlag;

    private String buyersId;

    private String buyersCode;

    private String buyersName;

    private Integer buyersType;

    private String sellers;

    /**
     * 专属买手
     */
    private String buyerSaleCode;

    /**
     * 专属买手名称
     */
    private String buyerSaleName;

    public boolean isHaveBuyerFlag() {
        return haveBuyerFlag;
    }

    public void setHaveBuyerFlag(boolean haveBuyerFlag) {
        this.haveBuyerFlag = haveBuyerFlag;
    }

    public String getBuyerSaleCode() {
        return buyerSaleCode;
    }

    public void setBuyerSaleCode(String buyerSaleCode) {
        this.buyerSaleCode = buyerSaleCode;
    }

    public String getBuyerSaleName() {
        return buyerSaleName;
    }

    public void setBuyerSaleName(String buyerSaleName) {
        this.buyerSaleName = buyerSaleName;
    }

    public String getSellers() {
        return sellers;
    }

    public void setSellers(String sellers) {
        this.sellers = sellers;
    }

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    public String getBuyersCode() {
        return buyersCode;
    }

    public void setBuyersCode(String buyersCode) {
        this.buyersCode = buyersCode;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public Integer getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(Integer buyersType) {
        this.buyersType = buyersType;
    }

    public ISO151414ReceiverInfo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(ISO151414ReceiverInfo receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public ISO151414DeliveryReq getDeliveryReq() {
        return deliveryReq;
    }

    public void setDeliveryReq(ISO151414DeliveryReq deliveryReq) {
        this.deliveryReq = deliveryReq;
    }

    public ISO151414InvoiceReq getInvoiceReq() {
        return invoiceReq;
    }

    public void setInvoiceReq(ISO151414InvoiceReq invoiceReq) {
        this.invoiceReq = invoiceReq;
    }

    public List<ISO151414OrderDetailParam> getProducts() {
        return products;
    }

    public void setProducts(List<ISO151414OrderDetailParam> products) {
        this.products = products;
    }
}
