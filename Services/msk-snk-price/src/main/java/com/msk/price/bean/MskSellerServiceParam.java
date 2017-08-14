package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

/**
 * OEM供应商：OEM待申报产品一览
 */
public class MskSellerServiceParam extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    //物流区编码
    private String lgcsAreaCode;

    //产品编码
    private String pdCode;

    //现有库存量
    private String stockQty;

    //等级
    private String gradeCode;

    //卖家编码
    private String slCode;

    //卖家产品等级
    private String slQltGradeCode;
    //卖家产品等级
    private String slTncGradeCode;

    //main class
    private String slmainClass;

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlQltGradeCode() {
        return slQltGradeCode;
    }

    public void setSlQltGradeCode(String slQltGradeCode) {
        this.slQltGradeCode = slQltGradeCode;
    }

    public String getSlmainClass() {
        return slmainClass;
    }

    public void setSlmainClass(String slmainClass) {
        this.slmainClass = slmainClass;
    }

    public String getSlTncGradeCode() {
        return slTncGradeCode;
    }

    public void setSlTncGradeCode(String slTncGradeCode) {
        this.slTncGradeCode = slTncGradeCode;
    }
}
