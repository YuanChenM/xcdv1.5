package com.msk.inventory.bean;

import com.msk.comm.bean.result.RestPageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/8/25.
 */
public class ISO152412ResultBean extends RestPageResult {

    private String salePlatform;
    private String slCode;

    private List<ISO152412PdStockResultBean> pdStockList = new ArrayList<ISO152412PdStockResultBean>();

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

    public List<ISO152412PdStockResultBean> getPdStockList() {
        return pdStockList;
    }

    public void setPdStockList(List<ISO152412PdStockResultBean> pdStockList) {
        this.pdStockList = pdStockList;
    }
}
