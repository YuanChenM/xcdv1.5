package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryOrderPd;

import java.math.BigDecimal;

/**
 * Created by yang_yang
 */
public class SSC1130601RsBean extends SscDeliveryOrderPd {

    /** 产品名称 */
    private String productName;
    /** 不含运费结算标准价 */
    private String standardPriceStr;
    /** 发货箱数合计 */
    private BigDecimal sumProductBox;
    /** 发货数量合计 */
    private BigDecimal sumProductQua;
    /** 结算标准价合计 */
    private BigDecimal sumStandardPrice;
    /** 合计 */
    private BigDecimal sumProductValue;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStandardPriceStr() {
        return standardPriceStr;
    }

    public void setStandardPriceStr(String standardPriceStr) {
        this.standardPriceStr = standardPriceStr;
    }

    public BigDecimal getSumProductBox() {
        return sumProductBox;
    }

    public void setSumProductBox(BigDecimal sumProductBox) {
        this.sumProductBox = sumProductBox;
    }

    public BigDecimal getSumProductQua() {
        return sumProductQua;
    }

    public void setSumProductQua(BigDecimal sumProductQua) {
        this.sumProductQua = sumProductQua;
    }

    public BigDecimal getSumStandardPrice() {
        return sumStandardPrice;
    }

    public void setSumStandardPrice(BigDecimal sumStandardPrice) {
        this.sumStandardPrice = sumStandardPrice;
    }

    public BigDecimal getSumProductValue() {
        return sumProductValue;
    }

    public void setSumProductValue(BigDecimal sumProductValue) {
        this.sumProductValue = sumProductValue;
    }
}
