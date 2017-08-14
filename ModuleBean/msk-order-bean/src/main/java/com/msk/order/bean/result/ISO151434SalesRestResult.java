package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * ISO151434_获取上半月分销量接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151434SalesRestResult extends BaseResult {
    /*产品编码(10位含等级)*/
    private String pdCode;
    /*物流区CODE*/
    private String lgcsCode;
    /*上半月分销量*/
    private Integer salesCnt;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public Integer getSalesCnt() {
        return salesCnt;
    }

    public void setSalesCnt(Integer salesCnt) {
        this.salesCnt = salesCnt;
    }
}
