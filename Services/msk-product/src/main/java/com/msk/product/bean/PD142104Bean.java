package com.msk.product.bean;

/**
 * Created by GYH on 2016/4/20.
 */
public class PD142104Bean extends PD142103Bean {
    private String lotYear;//批次信息年
    private String lotMonth;//批次信息月
    private String lotDate;//批次信息半旬
    private String lotCode;//批次编码
    private String lotPdCode;//产品编码

    /**
     * Getter method for property <tt>lotPdCode</tt>.
     *
     * @return property value of lotPdCode
     */
    public String getLotPdCode() {
        return lotPdCode;
    }

    /**
     * Setter method for property <tt>lotPdCode</tt>.
     *
     * @param lotPdCode value to be assigned to property lotPdCode
     */
    public void setLotPdCode(String lotPdCode) {
        this.lotPdCode = lotPdCode;
    }

    /**
     * Getter method for property <tt>lotCode</tt>.
     *
     * @return property value of lotCode
     */
    public String getLotCode() {
        return lotCode;
    }

    /**
     * Setter method for property <tt>lotCode</tt>.
     *
     * @param lotCode value to be assigned to property lotCode
     */
    public void setLotCode(String lotCode) {
        this.lotCode = lotCode;
    }

    /**
     * Getter method for property <tt>lotYear</tt>.
     *
     * @return property value of lotYear
     */
    public String getLotYear() {
        return lotYear;
    }

    /**
     * Setter method for property <tt>lotYear</tt>.
     *
     * @param lotYear value to be assigned to property lotYear
     */
    public void setLotYear(String lotYear) {
        this.lotYear = lotYear;
    }

    /**
     * Getter method for property <tt>lotMonth</tt>.
     *
     * @return property value of lotMonth
     */
    public String getLotMonth() {
        return lotMonth;
    }

    /**
     * Setter method for property <tt>lotMonth</tt>.
     *
     * @param lotMonth value to be assigned to property lotMonth
     */
    public void setLotMonth(String lotMonth) {
        this.lotMonth = lotMonth;
    }

    /**
     * Getter method for property <tt>lotDate</tt>.
     *
     * @return property value of lotDate
     */
    public String getLotDate() {
        return lotDate;
    }

    /**
     * Setter method for property <tt>lotDate</tt>.
     *
     * @param lotDate value to be assigned to property lotDate
     */
    public void setLotDate(String lotDate) {
        this.lotDate = lotDate;
    }
}
