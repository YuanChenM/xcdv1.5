package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/15.
 */
public class ISO151414StockRestResult extends BaseResult{

    /** 销售平台 */
    private String salePlatform;

    /** 卖家或者买手编码 */
    private String slCode;

    /** 产品库存集合 */
    private List<ISO151414ProductStockInfo> pdStockList;

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

    public List<ISO151414ProductStockInfo> getPdStockList() {
        return pdStockList;
    }

    public void setPdStockList(List<ISO151414ProductStockInfo> pdStockList) {
        this.pdStockList = pdStockList;
    }
}
