package com.msk.inventory.bean;

/**
 * Created by zheng_xu on 2016/8/25.
 */
public class ISO152406PdListParamBean {
    //原发货明细ID，对应发货XML中ORDERLINE
    private String pdCode;
    //库存类型
    private String inventoryStatus;
    //订单占用数量
    private java.math.BigDecimal occupyQty;

    public String getPdCode() {
        return pdCode;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public java.math.BigDecimal getOccupyQty() {
        return occupyQty;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public void setOccupyQty(java.math.BigDecimal occupyQty) {
        this.occupyQty = occupyQty;
    }
}
