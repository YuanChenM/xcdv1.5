/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.bean;

import java.math.BigDecimal;

import com.msk.comm.entity.BaseEntity;

/**
 *
 */
public class IvmWarehouseResultBean extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 卖家编码*/
    private String  consignee;
    /** 销售区域*/
    private String area;
    /** 供应商编码*/
    private String supplierCode;
    /** 产品编码*/
    private String pdCode;
    /** 库存数量*/
    private BigDecimal quantity;
    /** 原卖家编码*/
    private String sourceSlCode;

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getSourceSlCode() {
        return sourceSlCode;
    }

    public void setSourceSlCode(String sourceSlCode) {
        this.sourceSlCode = sourceSlCode;
    }
}
