package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;

/**
 * ISO151802_现场退货数据接收接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151802RestShipDetailParam extends BaseParam {
    private Long shipDetailId;
    private Long shipId;
    private String supplierCode;
    private String skuCode;
    private BigDecimal returnQty;
    private Integer detailReasonID;
    private String detailReasonName;
    private String deliverCode;

    public String getDeliverCode()
    {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode)
    {
        this.deliverCode = deliverCode;
    }

    public Long getShipId()
    {
        return shipId;
    }

    public void setShipId(Long shipId)
    {
        this.shipId = shipId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public Long getShipDetailId() {
        return shipDetailId;
    }

    public void setShipDetailId(Long shipDetailId) {
        this.shipDetailId = shipDetailId;
    }
}
