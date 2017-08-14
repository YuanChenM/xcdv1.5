package com.msk.order.bean.param;

import com.msk.common.bean.param.BasePageParam;
import com.msk.order.bean.result.SO15150801PdStockListResult;

import java.math.BigDecimal;
import java.util.List;

/**
 *SO15150801_选择产品画面参数
 * Created by wang_jianzhou on 2016/8/10.
 */
public class SO15150801Param extends BasePageParam {

    private String pdCode;

    private BigDecimal activeQty;

    private String lgcsCode;

    private BigDecimal orderQty;

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    private List<SO15150801PdStockListResult> productList;

    public List<SO15150801PdStockListResult> getProductList() {
        return productList;
    }

    public void setProductList(List<SO15150801PdStockListResult> productList) {
        this.productList = productList;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public BigDecimal getActiveQty() {
        return activeQty;
    }

    public void setActiveQty(BigDecimal activeQty) {
        this.activeQty = activeQty;
    }
}
