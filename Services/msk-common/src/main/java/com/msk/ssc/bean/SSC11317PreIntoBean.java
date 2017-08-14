package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryPreInto;
import com.msk.core.entity.SscDeliveryPrePd;
import com.msk.ds.bean.ISC182207RsParam;

import java.util.List;

/**
 * Created by wang_shuai on 2016/7/8.
 */
public class SSC11317PreIntoBean extends SscDeliveryPreInto {
    /** 实际到货日期 */
    private String arriveDateStr;

    /** 计划到货日期*/
    private String etaStr;

    /** 产品明细ID */
    private Long detailId;

    /** 物流区编码 */
    private String lgcsAreaCode;

    private String lgcsAreaName;

    /** 企业盖章三证名称Str */
    private java.lang.String businessFileNameStr;
    /** 动物检疫合格证明名称Str */
    private java.lang.String quarantineFileNameStr;
    /** 出库清单名称Str */
    private java.lang.String inventoryFileNameStr;
    /** 有效期官方检测报告名称Str */
    private java.lang.String reportFileNameStr;

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    private List<SscDeliveryPrePd> prePdList;

    private List<SSC11317PrePdBean> ssc11317PrePdBeanList;

    public List<SscDeliveryPrePd> getPrePdList() {
        return prePdList;
    }

    public void setPrePdList(List<SscDeliveryPrePd> prePdList) {
        this.prePdList = prePdList;
    }

    public String getArriveDateStr() {
        return arriveDateStr;
    }

    public void setArriveDateStr(String arriveDateStr) {
        this.arriveDateStr = arriveDateStr;
    }

    public List<SSC11317PrePdBean> getSsc11317PrePdBeanList() {
        return ssc11317PrePdBeanList;
    }

    public void setSsc11317PrePdBeanList(List<SSC11317PrePdBean> ssc11317PrePdBeanList) {
        this.ssc11317PrePdBeanList = ssc11317PrePdBeanList;
    }

    public String getEtaStr() {
        return etaStr;
    }

    public void setEtaStr(String etaStr) {
        this.etaStr = etaStr;
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
}
