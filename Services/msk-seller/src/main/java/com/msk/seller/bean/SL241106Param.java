package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SL241106Param
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class SL241106Param extends BaseParam {
    /** 生产商 */
    private String manufacturer;
    /** 产品类型 */
    private String classesCode;
    /** 产品品种 */
    private String breedCode;
    /** 产品编码 */
    private String pdCode;
    /** 包装标准审核 */
    private String check;
    /** 监控人审核 */
    private String checkResult;
    /** 原因 */
    private String reasonValue;

    /**
     * Getter method for property <tt>manufacturer</tt>.
     *
     * @return property value of manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Setter method for property <tt>manufacturer</tt>.
     *
     * @param manufacturer value to be assigned to property manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Getter method for property <tt>breedCode</tt>.
     *
     * @return property value of breedCode
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * Setter method for property <tt>breedCode</tt>.
     *
     * @param breedCode value to be assigned to property breedCode
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * Getter method for property <tt>pdCode</tt>.
     *
     * @return property value of pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * Setter method for property <tt>pdCode</tt>.
     *
     * @param pdCode value to be assigned to property pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * Getter method for property <tt>check</tt>.
     *
     * @return property value of check
     */
    public String getCheck() {
        return check;
    }

    /**
     * Setter method for property <tt>check</tt>.
     *
     * @param check value to be assigned to property check
     */
    public void setCheck(String check) {
        this.check = check;
    }

    /**
     * Getter method for property <tt>checkResult</tt>.
     *
     * @return property value of checkResult
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * Setter method for property <tt>checkResult</tt>.
     *
     * @param checkResult value to be assigned to property checkResult
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    /**
     * Getter method for property <tt>reasonValue</tt>.
     *
     * @return property value of reasonValue
     */
    public String getReasonValue() {
        return reasonValue;
    }

    /**
     * Setter method for property <tt>reasonValue</tt>.
     *
     * @param reasonValue value to be assigned to property reasonValue
     */
    public void setReasonValue(String reasonValue) {
        this.reasonValue = reasonValue;
    }
}
