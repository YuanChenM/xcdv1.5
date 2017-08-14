package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryPreInto;

import java.math.BigDecimal;

/**
 * Created by xia_xiaojie on 2016/7/8.
 */
public class SSC11316Bean extends SscDeliveryPreInto {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /*
     * ssc_contract_basic表字段
     */

    private String contractName;        //合同名称

    /*
     * ssc_delivery_confirm_basic表字段
     */

    private Long deliveryId;            //发货订单ID


    /*
     * 附加字段字段
     */

    private String actualArriveDate;    //实际到货日期
    private String expectDeliveryDate;  //预计发货日期
    private String expectArriveDate;    //预计到货日期
    private String intoStoreCode;       //入库单号

    /** 发货订单编号 */
    private String deliveryCode;

    private BigDecimal weights;

    /** 企业盖章三证是否图片标识 */
    private boolean businessFileFlag;
    /** 动物检疫合格证明是否图片标识 */
    private boolean quarantineFileFlag;
    /** 出库清单是否图片标识 */
    private boolean inventoryFileFlag;
    /** 有效期官方检测报告是否图片标识 */
    private boolean reportFileFlag;
    /** 企业盖章三证名称Str */
    private java.lang.String businessFileNameStr;
    /** 动物检疫合格证明名称Str */
    private java.lang.String quarantineFileNameStr;
    /** 出库清单名称Str */
    private java.lang.String inventoryFileNameStr;
    /** 有效期官方检测报告名称Str */
    private java.lang.String reportFileNameStr;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 物流区名称 */
    private String lgcsAreaName;

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getActualArriveDate() {
        return actualArriveDate;
    }

    public void setActualArriveDate(String actualArriveDate) {
        this.actualArriveDate = actualArriveDate;
    }

    public String getExpectDeliveryDate() {
        return expectDeliveryDate;
    }

    public void setExpectDeliveryDate(String expectDeliveryDate) {
        this.expectDeliveryDate = expectDeliveryDate;
    }

    public String getExpectArriveDate() {
        return expectArriveDate;
    }

    public void setExpectArriveDate(String expectArriveDate) {
        this.expectArriveDate = expectArriveDate;
    }

    public String getIntoStoreCode() {
        return intoStoreCode;
    }

    public void setIntoStoreCode(String intoStoreCode) {
        this.intoStoreCode = intoStoreCode;
    }

    public BigDecimal getWeights() {
        return weights;
    }

    public void setWeights(BigDecimal weights) {
        this.weights = weights;
    }

    public boolean isBusinessFileFlag() {
        return businessFileFlag;
    }

    public void setBusinessFileFlag(boolean businessFileFlag) {
        this.businessFileFlag = businessFileFlag;
    }

    public boolean isQuarantineFileFlag() {
        return quarantineFileFlag;
    }

    public void setQuarantineFileFlag(boolean quarantineFileFlag) {
        this.quarantineFileFlag = quarantineFileFlag;
    }

    public boolean isInventoryFileFlag() {
        return inventoryFileFlag;
    }

    public void setInventoryFileFlag(boolean inventoryFileFlag) {
        this.inventoryFileFlag = inventoryFileFlag;
    }

    public boolean isReportFileFlag() {
        return reportFileFlag;
    }

    public void setReportFileFlag(boolean reportFileFlag) {
        this.reportFileFlag = reportFileFlag;
    }

    public String getBusinessFileNameStr() {
        return businessFileNameStr;
    }

    public void setBusinessFileNameStr(String businessFileNameStr) {
        this.businessFileNameStr = businessFileNameStr;
    }

    public String getQuarantineFileNameStr() {
        return quarantineFileNameStr;
    }

    public void setQuarantineFileNameStr(String quarantineFileNameStr) {
        this.quarantineFileNameStr = quarantineFileNameStr;
    }

    public String getInventoryFileNameStr() {
        return inventoryFileNameStr;
    }

    public void setInventoryFileNameStr(String inventoryFileNameStr) {
        this.inventoryFileNameStr = inventoryFileNameStr;
    }

    public String getReportFileNameStr() {
        return reportFileNameStr;
    }

    public void setReportFileNameStr(String reportFileNameStr) {
        this.reportFileNameStr = reportFileNameStr;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }
}
