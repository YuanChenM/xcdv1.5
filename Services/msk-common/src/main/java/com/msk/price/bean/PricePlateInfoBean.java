package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by peng_hao on 2016/6/6.
 */
public class PricePlateInfoBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /*产品编码*/
    private String pdCode;

    /*箱价（元/箱）*/
    private BigDecimal wayGradePriceBox;


    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }


    public BigDecimal getWayGradePriceBox() {
        return wayGradePriceBox;
    }

    public void setWayGradePriceBox(BigDecimal wayGradePriceBox) {
        this.wayGradePriceBox = wayGradePriceBox;
    }
}
