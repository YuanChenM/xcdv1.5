package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;


/**
 * 管家旗下订单销售总额信息.
 *
 * @author sjj
 */
public class ISO151406HouseAccountSalesResult extends BaseResult {
    // 管家ID
    private String houseCode;
    // 物流区编码
    private String lgcsAreaCode;
    // 产品一级分类
    private String pdClassCode;
    // 销售额（万元）
    private String sales;
    // 销售额
    private BigDecimal bigDecimalSales;

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getPdClassCode() {
        return pdClassCode;
    }

    public void setPdClassCode(String pdClassCode) {
        this.pdClassCode = pdClassCode;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public BigDecimal getBigDecimalSales() {
        return bigDecimalSales;
    }

    public void setBigDecimalSales(BigDecimal bigDecimalSales) {
        this.bigDecimalSales = bigDecimalSales;
    }
}