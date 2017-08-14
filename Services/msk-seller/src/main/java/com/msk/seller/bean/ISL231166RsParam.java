package com.msk.seller.bean;

import java.util.List;

/**
 * Created by cx on 2016/2/24.
 */
public class ISL231166RsParam {
    /**
     * 产品类别List
     */
    private List<PdClassesCode> pdClassesCodeList;

    /**
     * 创建者ID
     */
    private String crtId;

    /**
     * 卖家编码
     */
    private String slCode;
    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getCrtId() {
        return crtId;
    }

    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

    public List<PdClassesCode> getPdClassesCodeList() {
        return pdClassesCodeList;
    }

    public void setPdClassesCodeList(List<PdClassesCode> pdClassesCodeList) {
        this.pdClassesCodeList = pdClassesCodeList;
    }

}




