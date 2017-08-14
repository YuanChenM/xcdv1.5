package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * BSO151403Param
 * @author sunjiaju
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"shipId","shipCode","cancelManId","cancelManName","cancelTimeStr","cancelReason","cancelRemark"})
public class BSO151406Result extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 发货单ID */
    private Long shipId;
    /** 发货单号 */
    private String shipCode;
    /** 取消时间 */
    private String cancelTimeStr;
    /** 发货取消人ID */
    private String cancelManId;
    /** 发货取消人名称 */
    private String cancelManName;
    /** 发货取消原因-CodeMaster */
    private String cancelReason;
    /** 发货取消备注 */
    private String cancelRemark;
    @XmlElement(name = "CANCELTIME",defaultValue = "")
    public String getCancelTimeStr() {
        return cancelTimeStr;
    }

    public void setCancelTimeStr(String cancelTimeStr) {
        this.cancelTimeStr = cancelTimeStr;
    }
    @XmlElement(name = "SHIPID",defaultValue = "")
    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }
    @XmlElement(name = "SHIPCODE",defaultValue = "")
    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }
    @XmlElement(name = "CANCELMANID",defaultValue = "")
    public String getCancelManId() {
        return cancelManId;
    }

    public void setCancelManId(String cancelManId) {
        this.cancelManId = cancelManId;
    }
    @XmlElement(name = "CANCELMANNAME",defaultValue = "")
    public String getCancelManName() {
        return cancelManName;
    }

    public void setCancelManName(String cancelManName) {
        this.cancelManName = cancelManName;
    }
    @XmlElement(name = "CANCELREASON",defaultValue = "")
    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
    @XmlElement(name = "REMARK",defaultValue = "")
    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }
}
