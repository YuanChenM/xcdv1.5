package com.msk.seller.bean;

import com.msk.core.entity.SlPdTncStdNew;

/**
 * SL241117Param
 *
 * @author gyh
 * @version 1.0
 **/
public class SL241117Param extends SlPdTncStdNew{
    //等级选择
    private String agreeFlg;
    //值1数组
    private String[] contentArray;
    //同意数组
    private String[] checkArray;
    //与上边匹配的指标id数组
    private String[] pdTncStdItemIdArray;
    private String slShowName;

    /**
     * Getter method for property <tt>slShowName</tt>.
     *
     * @return property value of slShowName
     */
    public String getSlShowName() {
        return slShowName;
    }

    /**
     * Setter method for property <tt>slShowName</tt>.
     *
     * @param slShowName value to be assigned to property slShowName
     */
    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * Getter method for property <tt>agreeFlg</tt>.
     *
     * @return property value of agreeFlg
     */
    @Override
    public String getAgreeFlg() {
        return agreeFlg;
    }

    /**
     * Setter method for property <tt>agreeFlg</tt>.
     *
     * @param agreeFlg value to be assigned to property agreeFlg
     */
    @Override
    public void setAgreeFlg(String agreeFlg) {
        this.agreeFlg = agreeFlg;
    }

    /**
     * Getter method for property <tt>contentArray</tt>.
     *
     * @return property value of contentArray
     */
    public String[] getContentArray() {
        return contentArray;
    }

    /**
     * Setter method for property <tt>contentArray</tt>.
     *
     * @param contentArray value to be assigned to property contentArray
     */
    public void setContentArray(String[] contentArray) {
        this.contentArray = contentArray;
    }

    /**
     * Getter method for property <tt>checkArray</tt>.
     *
     * @return property value of checkArray
     */
    public String[] getCheckArray() {
        return checkArray;
    }

    /**
     * Setter method for property <tt>checkArray</tt>.
     *
     * @param checkArray value to be assigned to property checkArray
     */
    public void setCheckArray(String[] checkArray) {
        this.checkArray = checkArray;
    }

    /**
     * Getter method for property <tt>pdTncStdItemIdArray</tt>.
     *
     * @return property value of pdTncStdItemIdArray
     */
    public String[] getPdTncStdItemIdArray() {
        return pdTncStdItemIdArray;
    }

    /**
     * Setter method for property <tt>pdTncStdItemIdArray</tt>.
     *
     * @param pdTncStdItemIdArray value to be assigned to property pdTncStdItemIdArray
     */
    public void setPdTncStdItemIdArray(String[] pdTncStdItemIdArray) {
        this.pdTncStdItemIdArray = pdTncStdItemIdArray;
    }
}
