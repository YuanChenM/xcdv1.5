package com.msk.inventory.bean;

/**
 * Created by wang_fan2 on 2016/8/23.
 */
public class ISO152413ProductParamBean {
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 一级分类编码 */
    private String classesCode;
    /** 品种编码 */
    private String breedCode;
    /** 供应商编码 */
    private String supplierCode;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
}
