package com.msk.ssc.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by wang_shuai on 2016/7/11.
 */
public class SSC11317DepartureParam extends BaseParam {
    /** 入库单号*/
    private String receipt;
    /** 备注信息*/
    private String notes;
    /** 销售区域编码*/
    private String lgcsCode;
    /** 供应商编码*/
    private String suppCode;
    /** 供应商名称*/
    private String suppName;
    /** 计划发货时间*/
    private String scheduledDate;
    /** 计划到货时间*/
    private String startReceiptDate;
    /** 发货详细 */
    private List<SSC11317DeparturePd> productList;

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getStartReceiptDate() {
        return startReceiptDate;
    }

    public void setStartReceiptDate(String startReceiptDate) {
        this.startReceiptDate = startReceiptDate;
    }

    public List<SSC11317DeparturePd> getProductList() {
        return productList;
    }

    public void setProductList(List<SSC11317DeparturePd> productList) {
        this.productList = productList;
    }
}
