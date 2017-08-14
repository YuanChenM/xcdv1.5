package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * 卖家库存占用中的产品
 * Created by zhang_qiang1 on 2016/9/12.
 */
public class ISO151410IRestSlInvProductParam extends BaseParam {

    private String pdCode;//原发货明细ID，对应发货XML中ORDERLINE
    private String supplierCode;
    private String skuCode;
    private String inboundBatch;
    private String inventoryStatus;//库存类型，CodeMaster
    private Integer decreaseQty;//订单占用减少数量


    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Integer getDecreaseQty() {
        return decreaseQty;
    }

    public void setDecreaseQty(Integer decreaseQty) {
        this.decreaseQty = decreaseQty;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getInboundBatch() {
        return inboundBatch;
    }

    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }
}
