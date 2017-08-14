package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SL241105Param
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class SL241105Param extends BaseParam{
    /**生产商*/
    private String manufacturer;
    /**产品类型*/
    private String classesCode;
    /**产品品种*/
    private String breedCode;
    /**产品编码*/
    private String pdCode;
    /**包装标准审核*/
    private String check;
    /**监控人审核*/
    private String checkResult;
    /**原因*/
    private String reasonValue;

    /**
     * *获得reasonValue
     **/
    public String getReasonValue() {
        return reasonValue;
    }

    /**
     * *设置reasonValue
     **/
    public void setReasonValue(String reasonValue) {
        this.reasonValue = reasonValue;
    }

    /**
     * *获得manufacturer
     **/
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * *设置manufacturer
     **/
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * *获得classesCode
     **/
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * *设置classesCode
     **/
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * *获得breedCode
     **/
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * *设置breedCode
     **/
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * *获得pdCode
     **/
    public String getPdCode() {
        return pdCode;
    }

    /**
     * *设置pdCode
     **/
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * *获得check
     **/
    public String getCheck() {
        return check;
    }

    /**
     * *设置check
     **/
    public void setCheck(String check) {
        this.check = check;
    }

    /**
     * *获得checkResult
     **/
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * *设置checkResult
     **/
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
}
