package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;

/**
 * ISO151405_产品销量查询接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151405PdListRestResult extends BaseResult {

    /** 产品编码*/
    private String pdCode;
    /** 销量Str*/
    private String salesVolumn;
    /** 销量*/
    private Long salesVolumnLong;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSalesVolumn() {
        return salesVolumn;
    }

    public void setSalesVolumn(String salesVolumn) {
        this.salesVolumn = salesVolumn;
    }

    public Long getSalesVolumnLong() {
        return salesVolumnLong;
    }

    public void setSalesVolumnLong(Long salesVolumnLong) {
        this.salesVolumnLong = salesVolumnLong;
    }
}
