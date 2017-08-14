package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/22.
 */
public class BSO151402StockResult extends BaseEntity{

    /** 销售平台 */
    private String salePlatform;

    /** 卖家或者买手编码 */
    private String slCode;

    /** 产品库存集合 */
    private List<BSO151402StockProductResultInfo> pdStockList;

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public List<BSO151402StockProductResultInfo> getPdStockList() {
        return pdStockList;
    }

    public void setPdStockList(List<BSO151402StockProductResultInfo> pdStockList) {
        this.pdStockList = pdStockList;
    }
}
