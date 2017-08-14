package com.msk.ds.bean;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by zhang_xi on 2016/3/9.
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"receipt","notes","scheduledDate","startReceiptDate","detailList"})
public class SC182102XmlParam {

    /** 入库单号*/
    String receipt;
    /** 卖家编码*/
    String consignee;
    /** 卖家名称*/
    String name;
    /** 销售区域*/
    String area;
    /** 供应商编码*/
    String company;
    /** 供应商名称*/
    String companyName;
    /** 备注信息*/
    String notes;
    /** 计划发货时间*/
    String scheduledDate;
    /** 计划到货时间*/
    String startReceiptDate;

    /** 发货详细 */
    List<SC182102XmlDetailParam> detailList;

    /**
     * @return the detailList
     */
    @XmlElementWrapper(name = "LINES")
    @XmlElement(name = "LINE")
    public List<SC182102XmlDetailParam> getDetailList() {
        return detailList;
    }
    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<SC182102XmlDetailParam> detailList) {
        this.detailList = detailList;
    }

    /**
     * @return the receipt
     */
    @XmlElement(name ="RECEIPT",defaultValue = "")
    public String getReceipt() {
        return receipt;
    }
    /**
     * @param receipt the receipt to set
     */
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
    /**
     * @return the scheduledDate
     */
    @XmlElement(name ="SCHEDULEDDATE",defaultValue = "")
    public String getScheduledDate() {
        return scheduledDate;
    }
    /**
     * @param scheduledDate the scheduledDate to set
     */
    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
    /**
     * @return the startReceiptDate
     */
    @XmlElement(name ="STARTRECEIPTDATE",defaultValue = "")
    public String getStartReceiptDate() {
        return startReceiptDate;
    }
    /**
     * @param startReceiptDate the startReceiptDate to set
     */
    public void setStartReceiptDate(String startReceiptDate) {
        this.startReceiptDate = startReceiptDate;
    }
    @XmlTransient
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlTransient
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    @XmlTransient
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    @XmlTransient
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @XmlElement(name ="NOTES",defaultValue = "")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
