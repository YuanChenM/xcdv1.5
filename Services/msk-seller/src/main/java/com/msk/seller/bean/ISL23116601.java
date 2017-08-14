package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by cx on 2016/2/24.
 */
public class ISL23116601 extends BaseEntity{
    /**
     * 产品类别
     */
    private String pdClassesCode;

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

    public String getPdClassesCode() {
        return pdClassesCode;
    }

    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }
}




