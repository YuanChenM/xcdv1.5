package com.msk.ds.bean;

import com.msk.common.bean.RsPageParam;

import java.math.BigDecimal;

/**
 * xu_wei on 2016/03/24.
 */
public class ISC1892I1RsParam extends RsPageParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public String getDistMonth() {
        return distMonth;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getHalfCode() {
        return halfCode;
    }

    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

    public String getStockMemo() {
        return stockMemo;
    }

    public void setStockMemo(String stockMemo) {
        this.stockMemo = stockMemo;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public int getPdStockType() {
        return pdStockType;
    }

    public void setPdStockType(int pdStockType) {
        this.pdStockType = pdStockType;
    }
    public Long getDeliveryStockId() {
        return deliveryStockId;
    }

    public void setDeliveryStockId(Long deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }
    /**
     * 分销月度
     */
    private String distMonth;
    /**
     * 物流区编号
     */
    private String lgcsCode;
    /**
     * 地区编码
     */
    private String areaCode;
    /**
     * 供应商编号
     */
    private String suppCode;
    /**
     * 半旬码
     */
    private String halfCode;
    /**
     * 备注
     */
    private String stockMemo;
    /**
     * 实际入库时间
     */
    private String inputDate;
    /**
     * 产品库存类型
     */
    private int pdStockType;
    /**
     * 发货入库管理ID
     */
    private Long deliveryStockId;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public BigDecimal getReceiveActualNum() {
        return receiveActualNum;
    }

    public void setReceiveActualNum(BigDecimal receiveActualNum) {
        this.receiveActualNum = receiveActualNum;
    }

    /**
     * 产品编码
     */

    private String pdCode;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * 库号
     */

    private String sku;
    /**
     * 实际收货数量
     */
    private BigDecimal receiveActualNum;

    /** #1919 add by likai on 2016/8/16 start */
    //发货入库明细ID  (暂未用到,后期会调整)
    private Integer receiptLine;
    //采购入库批次  (暂未用到,后期会调整)
    private String purchaseNo;
    //库号(根据文档和美的福保持一致)
    private String skuCode;
    /** #1919 add by likai on 2016/8/16 end */

    public Integer getReceiptLine() {
        return receiptLine;
    }

    public void setReceiptLine(Integer receiptLine) {
        this.receiptLine = receiptLine;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
}
