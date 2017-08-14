package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;

/**
 * Created by wang_jianzhou on 2016/8/17.
 */
public class ISO151422ProductRestParam extends BaseParam{

    /**
     * 退货明细ID
     */
    private Long detailId;
    /**
     * 退货入库批次，退货入库单中INBOUNDBATCH
     */
    private String inboundBatch;
    /**
     * SKU编码
     */
    private String skuCode;
    /**
     * 入库数量
     */
    private BigDecimal inboundQty;
    /**
     * 退货单id
     */
    private Long returnId;
    /**
     * 退货明细状态
     */
    private Integer detailStatus;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getInboundBatch() {
        return inboundBatch;
    }

    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getInboundQty() {
        return inboundQty;
    }

    public void setInboundQty(BigDecimal inboundQty) {
        this.inboundQty = inboundQty;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }
}
