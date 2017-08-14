package com.msk.inventory.bean;

import com.msk.inventory.entity.IvmInventoryDetail;
import org.springframework.util.StringUtils;

/**
 * Created by wangfan on 16/8/15.
 */
public class IvmInventoryDetailBean extends IvmInventoryDetail {

    /** PM_DESC */
    private String pmDesc;
    /** PD_CODE */
    private String pdCode;
    private String pdName;
    /** SKU_CODE */
    private String skuCode;
    /** PM_NAME */
    private String pmName;

    private String pmCode;

    private String logicArea;
    private String logicAreaName;

    private String platform;
    private String platformName;

    private String slType;

    private String slId;
    private String slName;

    private String innerBatch;

    private String fromRecvDate;

    private String toRecvDate;

    private String fromRecvTime;

    private String toRecvTime;

    private String fromExpirationDate;

    private String toExpirationDate;

    private String fromDispatchedDate;

    private String toDispatchedTime;

    private String fromDispatchedTime;

    private String toDispatchedDate;

    private String fromDeliverTime;

    private  String toDeliverTime;

    private String fromFlagCTime;

    private String toFlagCTime;

    private String requestQty;

    private String ownerName;
    private String supplierName;

    public String getRequestQty() {
        return requestQty;
    }

    public void setRequestQty(String requestQty) {
        this.requestQty = requestQty;
    }

    public String getPmDesc() {
        return pmDesc;
    }

    public void setPmDesc(String pmDesc) {
        this.pmDesc = pmDesc;
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

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getLogicArea() {
        return logicArea;
    }

    public void setLogicArea(String logicArea) {
        this.logicArea = logicArea;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSlType() {
        return slType;
    }

    public void setSlType(String slType) {
        this.slType = slType;
    }

    public String getInnerBatch() {
        return innerBatch;
    }

    public void setInnerBatch(String innerBatch) {
        this.innerBatch = innerBatch;
    }

    public String getFromRecvDate() {
        return fromRecvDate;
    }

    public void setFromRecvDate(String fromRecvDate) {
        this.fromRecvDate = fromRecvDate;
    }

    public String getToRecvDate() {
        return toRecvDate;
    }

    public void setToRecvDate(String toRecvDate) {
        this.toRecvDate = toRecvDate;
    }

    public String getFromRecvTime() {
        return fromRecvTime;
    }

    public void setFromRecvTime(String fromRecvTime) {
        this.fromRecvTime = fromRecvTime;
    }

    public String getToRecvTime() {
        return toRecvTime;
    }

    public void setToRecvTime(String toRecvTime) {
        this.toRecvTime = toRecvTime;
    }

    public String getFromExpirationDate() {
        return fromExpirationDate;
    }

    public void setFromExpirationDate(String fromExpirationDate) {
        this.fromExpirationDate = fromExpirationDate;
    }

    public String getToExpirationDate() {
        return toExpirationDate;
    }

    public void setToExpirationDate(String toExpirationDate) {
        this.toExpirationDate = toExpirationDate;
    }

    public String getFromDispatchedDate() {
        return fromDispatchedDate;
    }

    public void setFromDispatchedDate(String fromDispatchedDate) {
        this.fromDispatchedDate = fromDispatchedDate;
    }

    public String getToDispatchedTime() {
        return toDispatchedTime;
    }

    public void setToDispatchedTime(String toDispatchedTime) {
        this.toDispatchedTime = toDispatchedTime;
    }

    public String getFromDispatchedTime() {
        return fromDispatchedTime;
    }

    public void setFromDispatchedTime(String fromDispatchedTime) {
        this.fromDispatchedTime = fromDispatchedTime;
    }

    public String getToDispatchedDate() {
        return toDispatchedDate;
    }

    public void setToDispatchedDate(String toDispatchedDate) {
        this.toDispatchedDate = toDispatchedDate;
    }

    public String getFromDeliverTime() {
        return fromDeliverTime;
    }

    public void setFromDeliverTime(String fromDeliverTime) {
        this.fromDeliverTime = fromDeliverTime;
    }

    public String getToDeliverTime() {
        return toDeliverTime;
    }

    public void setToDeliverTime(String toDeliverTime) {
        this.toDeliverTime = toDeliverTime;
    }

    public String getFromFlagCTime() {
        return fromFlagCTime;
    }

    public void setFromFlagCTime(String fromFlagCTime) {
        this.fromFlagCTime = fromFlagCTime;
    }

    public String getToFlagCTime() {
        return toFlagCTime;
    }

    public void setToFlagCTime(String toFlagCTime) {
        this.toFlagCTime = toFlagCTime;
    }

    @Override
    public String getPmCode() {
        return pmCode;
    }

    @Override
    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getLogicAreaName() {
        return logicAreaName;
    }

    public void setLogicAreaName(String logicAreaName) {
        this.logicAreaName = logicAreaName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String getBeloneXml() {
        String tempStr = (StringUtils.isEmpty(this.logicArea) ? "" : "<AREA>" + this.logicArea + "</AREA>")
                + (StringUtils.isEmpty(this.platform) ? "" : "<PLT>" + this.platform + "</PLT>")
                + (StringUtils.isEmpty(this.slType) ? "" : "<SLT>" + this.slType + "</SLT>")
                + (StringUtils.isEmpty(this.slId) ? "" : "<SLID>" + this.slId + "</SLID>");
        return tempStr;
    }

    @Override
    public String getInExternalXml() {
        String tempStr = (StringUtils.isEmpty(this.innerBatch) ? "" : "<INBAT>" + this.innerBatch + "</INBAT>");
        return tempStr;
    }
}


