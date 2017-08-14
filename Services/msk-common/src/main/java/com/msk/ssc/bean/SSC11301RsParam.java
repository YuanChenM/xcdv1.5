package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.Date;

/**
 * 中标确认书画面param
 * <p/>
 * Created by yuan_zhifei on 2016/06/28.
 */
public class SSC11301RsParam extends BasePageParam {

    //招标项目编号
    private Long bidId;
    //招标项目名称
    private String bidProjectName;
    //中标厂商
    private String bidProducerName;
    //开标日期
    private String bidOpenDate;

    private String purchaserName;

    private String purchaserCode;

    private String supplierCode;

    /** 采购商企业ID*/
    private Long purchaserId;
    /** 供应商企业ID*/
    private Long supplierId;

    // 开标开始日期
    private String startDate;
    // 开标结束日期
    private String endDate;
    //中标确认书状态
    private String bidStatus;

    /** 开标开始日期 */
    private java.util.Date bidStartDate;
    /** 开标结束日期 */
    private java.util.Date bidEndDate;

    private  String purchaser;

    private  String supplierName;

    private  String bidProjectNo;

    /** 一组付款状态*/
    private String[] bidStatusArr;

    //中标确认书弹窗标识
    private String bidFlag;

    public String getBidFlag() {
        return bidFlag;
    }

    public void setBidFlag(String bidFlag) {
        this.bidFlag = bidFlag;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public String getBidProjectName() {
        return bidProjectName;
    }

    public void setBidProjectName(String bidProjectName) {
        this.bidProjectName = bidProjectName;
    }

    public String getBidProducerName() {
        return bidProducerName;
    }

    public void setBidProducerName(String bidProducerName) {
        this.bidProducerName = bidProducerName;
    }

    public String getBidOpenDate() {
        return bidOpenDate;
    }

    public void setBidOpenDate(String bidOpenDate) {
        this.bidOpenDate = bidOpenDate;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Date getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public Date getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPurchaserCode() {
        return purchaserCode;
    }

    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getBidProjectNo() {
        return bidProjectNo;
    }

    public void setBidProjectNo(String bidProjectNo) {
        this.bidProjectNo = bidProjectNo;
    }

    public String[] getBidStatusArr() {
        return bidStatusArr;
    }

    public void setBidStatusArr(String[] bidStatusArr) {
        this.bidStatusArr = bidStatusArr;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
