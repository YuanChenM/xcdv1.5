package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SscInvoiceRequest;

import java.util.List;

/**
 * Created by ding_guangjian on 2016/8/3.
 */
public class SSC11323Bean extends SscInvoiceRequest {
//时间String格式
    private String contractActDateStr;
    private String invoiceDateStr;
    private String requestTimeStr;
    //合同状态
    private String contractStatus;
    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }



    public String getContractActDateStr() {
        return contractActDateStr;
    }

    public void setContractActDateStr(String contractActDateStr) {
        this.contractActDateStr = contractActDateStr;
    }

    public String getInvoiceDateStr() {
        return invoiceDateStr;
    }

    public void setInvoiceDateStr(String invoiceDateStr) {
        this.invoiceDateStr = invoiceDateStr;
    }

    public String getRequestTimeStr() {
        return requestTimeStr;
    }

    public void setRequestTimeStr(String requestTimeStr) {
        this.requestTimeStr = requestTimeStr;
    }
}
