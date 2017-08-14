package com.msk.seller.bean;

import com.msk.core.entity.PdOrgStd;

import java.util.List;

/**
 * SL241122Param.
 *
 * @author gyh
 */
public class SL241122Param {
    private String slCode;
    private Integer standardId;
    private Integer slPdId;
    private String[] orgStdItemIdArray;//原种种源标准指标
    private String[] orgValArray;//同意标志
    private String[] fedStdItemIdArray;//原种饲养标准指标
    private String[] fedValArray;//同意标志
    private String[] sftItemIdArray;//安全标准
    private String[] sftValArray;//同意标志
    private String[] tspItemIdArray;//储存运输标准
    private String[] tspValArray;//同意标志
    private String[] gnqItemIdArray;//通用质量标准
    private String[] gnqValArray;//同意标志

    /**
     * Getter method for property <tt>orgValArray</tt>.
     *
     * @return property value of orgValArray
     */
    public String[] getOrgValArray() {
        return orgValArray;
    }

    /**
     * Setter method for property <tt>orgValArray</tt>.
     *
     * @param orgValArray value to be assigned to property orgValArray
     */
    public void setOrgValArray(String[] orgValArray) {
        this.orgValArray = orgValArray;
    }

    /**
     * Getter method for property <tt>fedValArray</tt>.
     *
     * @return property value of fedValArray
     */
    public String[] getFedValArray() {
        return fedValArray;
    }

    /**
     * Setter method for property <tt>fedValArray</tt>.
     *
     * @param fedValArray value to be assigned to property fedValArray
     */
    public void setFedValArray(String[] fedValArray) {
        this.fedValArray = fedValArray;
    }

    /**
     * Getter method for property <tt>sftValArray</tt>.
     *
     * @return property value of sftValArray
     */
    public String[] getSftValArray() {
        return sftValArray;
    }

    /**
     * Setter method for property <tt>sftValArray</tt>.
     *
     * @param sftValArray value to be assigned to property sftValArray
     */
    public void setSftValArray(String[] sftValArray) {
        this.sftValArray = sftValArray;
    }

    /**
     * Getter method for property <tt>tspValArray</tt>.
     *
     * @return property value of tspValArray
     */
    public String[] getTspValArray() {
        return tspValArray;
    }

    /**
     * Setter method for property <tt>tspValArray</tt>.
     *
     * @param tspValArray value to be assigned to property tspValArray
     */
    public void setTspValArray(String[] tspValArray) {
        this.tspValArray = tspValArray;
    }

    /**
     * Getter method for property <tt>gnqValArray</tt>.
     *
     * @return property value of gnqValArray
     */
    public String[] getGnqValArray() {
        return gnqValArray;
    }

    /**
     * Setter method for property <tt>gnqValArray</tt>.
     *
     * @param gnqValArray value to be assigned to property gnqValArray
     */
    public void setGnqValArray(String[] gnqValArray) {
        this.gnqValArray = gnqValArray;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public Integer getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    /**
     * Getter method for property <tt>slPdId</tt>.
     *
     * @return property value of slPdId
     */
    public Integer getSlPdId() {
        return slPdId;
    }

    /**
     * Setter method for property <tt>slPdId</tt>.
     *
     * @param slPdId value to be assigned to property slPdId
     */
    public void setSlPdId(Integer slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * Getter method for property <tt>orgStdItemIdArray</tt>.
     *
     * @return property value of orgStdItemIdArray
     */
    public String[] getOrgStdItemIdArray() {
        return orgStdItemIdArray;
    }

    /**
     * Setter method for property <tt>orgStdItemIdArray</tt>.
     *
     * @param orgStdItemIdArray value to be assigned to property orgStdItemIdArray
     */
    public void setOrgStdItemIdArray(String[] orgStdItemIdArray) {
        this.orgStdItemIdArray = orgStdItemIdArray;
    }

    /**
     * Getter method for property <tt>fedStdItemIdArray</tt>.
     *
     * @return property value of fedStdItemIdArray
     */
    public String[] getFedStdItemIdArray() {
        return fedStdItemIdArray;
    }

    /**
     * Setter method for property <tt>fedStdItemIdArray</tt>.
     *
     * @param fedStdItemIdArray value to be assigned to property fedStdItemIdArray
     */
    public void setFedStdItemIdArray(String[] fedStdItemIdArray) {
        this.fedStdItemIdArray = fedStdItemIdArray;
    }

    /**
     * Getter method for property <tt>sftItemIdArray</tt>.
     *
     * @return property value of sftItemIdArray
     */
    public String[] getSftItemIdArray() {
        return sftItemIdArray;
    }

    /**
     * Setter method for property <tt>sftItemIdArray</tt>.
     *
     * @param sftItemIdArray value to be assigned to property sftItemIdArray
     */
    public void setSftItemIdArray(String[] sftItemIdArray) {
        this.sftItemIdArray = sftItemIdArray;
    }

    /**
     * Getter method for property <tt>tspItemIdArray</tt>.
     *
     * @return property value of tspItemIdArray
     */
    public String[] getTspItemIdArray() {
        return tspItemIdArray;
    }

    /**
     * Setter method for property <tt>tspItemIdArray</tt>.
     *
     * @param tspItemIdArray value to be assigned to property tspItemIdArray
     */
    public void setTspItemIdArray(String[] tspItemIdArray) {
        this.tspItemIdArray = tspItemIdArray;
    }

    /**
     * Getter method for property <tt>gnqItemIdArray</tt>.
     *
     * @return property value of gnqItemIdArray
     */
    public String[] getGnqItemIdArray() {
        return gnqItemIdArray;
    }

    /**
     * Setter method for property <tt>gnqItemIdArray</tt>.
     *
     * @param gnqItemIdArray value to be assigned to property gnqItemIdArray
     */
    public void setGnqItemIdArray(String[] gnqItemIdArray) {
        this.gnqItemIdArray = gnqItemIdArray;
    }
}
