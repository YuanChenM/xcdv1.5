package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * Created by wang_shuai on 2016/4/11.
 */
@XmlRootElement(name = "LINE")
@XmlType(propOrder = {"detailId","inboundBatch","skuCode","unit","orderQty","returnQty","pdPrice","inventoryStatus"})
public class BSO151405DetailResult extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 退货明细ID */
    private Long detailId;
    /** 退货入库批次 */
    private String inboundBatch;
    /** SKU编码 */
    private String skuCode;
    /** 产品单位 */
    private String unit;
    /*原订单数量*/
    private BigDecimal orderQty;
    /** 退货数量 */
    private BigDecimal returnQty;
    /*产品单价*/
    private BigDecimal pdPrice;
    /*inventoryStatus*/
    private String inventoryStatus;
    @XmlElement(name = "RETURNLINE",defaultValue = "")
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
    @XmlElement(name = "INBOUNDBATCH",defaultValue = "")
    public String getInboundBatch() {
        return inboundBatch;
    }

    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }
    @XmlElement(name = "SKUCODE",defaultValue = "")
    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    @XmlElement(name = "UOM",defaultValue = "")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    @XmlElement(name = "QTYORIGINAL",defaultValue = "")
    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }
    @XmlElement(name = "QTYRETUREN",defaultValue = "")
    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }
    @XmlElement(name = "INPUTQTY",defaultValue = "")
    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }
    @XmlElement(name = "INVENTORYSTATUS",defaultValue = "")
    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }
}
