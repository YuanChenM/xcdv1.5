package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryOrderBasic;

/**
 * Created by wang_shuai on 2016/7/6.
 */
public class SSC11314DeliveryConfirmParam extends SscDeliveryOrderBasic {
    /** 发货确认单ID */
    private Long deliveryConfirmId;
    /** 发货确认单编号 */
    private String deliveryConfirmCode;
    /** 采购方确认状态 */
    private String byConfirmStatus;
    /** 采购方确认原因 */
    private String byConfirmReason;
    /** 仓库方确认状态 */
    private String whConfirmStatus;
    /** 仓库方确认原因 */
    private String whConfirmReason;
    /** 生产方确认状态 */
    private String pdConfirmStatus;
    /** 生产方确认原因 */
    private String pdConfirmReason;
    /** 发货确认单状态 */
    private String deliveryConfirmStatus;

    public Long getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    public void setDeliveryConfirmId(Long deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }

    public String getDeliveryConfirmCode() {
        return deliveryConfirmCode;
    }

    public void setDeliveryConfirmCode(String deliveryConfirmCode) {
        this.deliveryConfirmCode = deliveryConfirmCode;
    }

    public String getByConfirmStatus() {
        return byConfirmStatus;
    }

    public void setByConfirmStatus(String byConfirmStatus) {
        this.byConfirmStatus = byConfirmStatus;
    }

    public String getByConfirmReason() {
        return byConfirmReason;
    }

    public void setByConfirmReason(String byConfirmReason) {
        this.byConfirmReason = byConfirmReason;
    }

    public String getWhConfirmStatus() {
        return whConfirmStatus;
    }

    public void setWhConfirmStatus(String whConfirmStatus) {
        this.whConfirmStatus = whConfirmStatus;
    }

    public String getWhConfirmReason() {
        return whConfirmReason;
    }

    public void setWhConfirmReason(String whConfirmReason) {
        this.whConfirmReason = whConfirmReason;
    }

    public String getPdConfirmStatus() {
        return pdConfirmStatus;
    }

    public void setPdConfirmStatus(String pdConfirmStatus) {
        this.pdConfirmStatus = pdConfirmStatus;
    }

    public String getPdConfirmReason() {
        return pdConfirmReason;
    }

    public void setPdConfirmReason(String pdConfirmReason) {
        this.pdConfirmReason = pdConfirmReason;
    }

    public String getDeliveryConfirmStatus() {
        return deliveryConfirmStatus;
    }

    public void setDeliveryConfirmStatus(String deliveryConfirmStatus) {
        this.deliveryConfirmStatus = deliveryConfirmStatus;
    }
}
