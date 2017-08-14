package com.msk.print.bean;

/**
 * ISO153105Param
 * zhang_chi
 */
public class ISO153202Param {

    private static final long serialVersionUID = 1L;



    /** 买家ID */
    private String sellerBillId;

    /** 页面编号 */
    private String srcPage;

    public String getSrcPage() {
        return srcPage;
    }

    public void setSrcPage(String srcPage) {
        this.srcPage = srcPage;
    }

    public String getSellerBillId() {
        return sellerBillId;
    }

    public void setSellerBillId(String sellerBillId) {
        this.sellerBillId = sellerBillId;
    }
}
