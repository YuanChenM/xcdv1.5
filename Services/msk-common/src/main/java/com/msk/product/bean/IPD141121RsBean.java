package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

/**
 * 产品技术标准接口
 * IPD141121RsResult
 *
 * @author xhy
 */
public class IPD141121RsBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 加工技术标准项目ID */
    private String mctStdItemId;

    private String mctStdItemName;
    /** 优良值(预留未用) */
    private String mctGoodVal;
    /** 合格值 */
    private String mctOkVal;
    /** 不合格值 */
    private String mctNgVal;

    /**
     * Getter method for property <tt>mctStdItemId</tt>.
     *
     * @return property value of mctStdItemId
     */
    public String getMctStdItemId() {
        return mctStdItemId;
    }

    /**
     * Setter method for property <tt>mctStdItemId</tt>.
     *
     * @param mctStdItemId value to be assigned to property mctStdItemId
     */
    public void setMctStdItemId(String mctStdItemId) {
        this.mctStdItemId = mctStdItemId;
    }

    /**
     * Getter method for property <tt>mctStdItemName</tt>.
     *
     * @return property value of mctStdItemName
     */
    public String getMctStdItemName() {
        return mctStdItemName;
    }

    /**
     * Setter method for property <tt>mctStdItemName</tt>.
     *
     * @param mctStdItemName value to be assigned to property mctStdItemName
     */
    public void setMctStdItemName(String mctStdItemName) {
        this.mctStdItemName = mctStdItemName;
    }

    /**
     * Getter method for property <tt>mctGoodVal</tt>.
     *
     * @return property value of mctGoodVal
     */
    public String getMctGoodVal() {
        return mctGoodVal;
    }

    /**
     * Setter method for property <tt>mctGoodVal</tt>.
     *
     * @param mctGoodVal value to be assigned to property mctGoodVal
     */
    public void setMctGoodVal(String mctGoodVal) {
        this.mctGoodVal = mctGoodVal;
    }

    /**
     * Getter method for property <tt>mctOkVal</tt>.
     *
     * @return property value of mctOkVal
     */
    public String getMctOkVal() {
        return mctOkVal;
    }

    /**
     * Setter method for property <tt>mctOkVal</tt>.
     *
     * @param mctOkVal value to be assigned to property mctOkVal
     */
    public void setMctOkVal(String mctOkVal) {
        this.mctOkVal = mctOkVal;
    }

    /**
     * Getter method for property <tt>mctNgVal</tt>.
     *
     * @return property value of mctNgVal
     */
    public String getMctNgVal() {
        return mctNgVal;
    }

    /**
     * Setter method for property <tt>mctNgVal</tt>.
     *
     * @param mctNgVal value to be assigned to property mctNgVal
     */
    public void setMctNgVal(String mctNgVal) {
        this.mctNgVal = mctNgVal;
    }
}