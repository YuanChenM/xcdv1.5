package com.msk.stock.bean;


import com.msk.common.bean.RsPageParam;
import com.msk.core.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;


public class ISO151435RsParam extends RsPageParam{

    private String slCode;// 卖家编码

    private String supplyPatform;// 平台类型


    private Integer orderType ;// 订单类型


    private String buyerId;//  买手id


    private String  buyerCode;//买手编码


    private String  buyerName;//  买手名称


    private  String lgcsCode;//  物流区编码



    List<StockSupplier> supplierList=new ArrayList<>();//  供应商编码集合


    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSupplyPatform() {
        return supplyPatform;
    }

    public void setSupplyPatform(String supplyPatform) {
        this.supplyPatform = supplyPatform;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public List<StockSupplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<StockSupplier> supplierList) {
        this.supplierList = supplierList;
    }
}
