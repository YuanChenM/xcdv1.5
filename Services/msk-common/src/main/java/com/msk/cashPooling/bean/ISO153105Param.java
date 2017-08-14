package com.msk.cashPooling.bean;

import com.msk.common.bean.RsPageParam;

/**
 * ISO153105Param
 * zhang_chi
 */
public class ISO153105Param{

    private static final long serialVersionUID = 1L;

    /** 买家ID */
    private String buyerBillId;

    /** 页面编号 */
    private String srcPage;

    /** 请求Code */
    private String templateCode;

    public String getSrcPage() {
        return srcPage;
    }

    public void setSrcPage(String srcPage) {
        this.srcPage = srcPage;
    }

    public String getBuyerBillId() {
        return buyerBillId;
    }

    public void setBuyerBillId(String buyerBillId) {
        this.buyerBillId = buyerBillId;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
