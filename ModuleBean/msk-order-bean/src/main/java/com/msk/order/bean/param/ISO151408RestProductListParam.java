package com.msk.order.bean.param;


import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;

/**
 * 创建退货单Param.
 *
 * @author zhangqiang1
 */
public class ISO151408RestProductListParam extends BaseParam {
    // 供货明细ID
    private Long shipDetailId;
    // 供应商编码
    private String supplierCode;
    // 产品编码
    private String pdCode;
    // SKU编码
    private String skuCode;
    // 退货数量
    private BigDecimal returnQty;
    // 退货明细原因
    private Integer detailReasonID;
    // 退货明细原因名称
    private String detailReasonName;

    public Long getShipDetailId() {
        return shipDetailId;
    }

    public void setShipDetailId(Long shipDetailId) {
        this.shipDetailId = shipDetailId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public Integer getDetailReasonID() {
        return detailReasonID;
    }

    public void setDetailReasonID(Integer detailReasonID) {
        this.detailReasonID = detailReasonID;
    }

    public String getDetailReasonName() {
        return detailReasonName;
    }

    public void setDetailReasonName(String detailReasonName) {
        this.detailReasonName = detailReasonName;
    }
}
