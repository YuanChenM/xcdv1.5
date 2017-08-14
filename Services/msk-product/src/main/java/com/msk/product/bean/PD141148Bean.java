package com.msk.product.bean;

import com.msk.core.entity.PdMctStd;

import java.util.List;

/**
 * PD141146Bean.
 *
 * @author XHY
 */
public class PD141148Bean extends PdMctStd {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String mctStdItemName;
    /** 合格  */
    private String [] mctOkValArray;//合格值
    /** 不合格 */
    private String [] mctNgValArray;//不合格值
    /**
     * idArray
     */
    private String [] mctStdItemIdArray;

    private List<PD141148MctProBean> mctProList;


    /**
     * Getter method for property <tt>mctProList</tt>.
     *
     * @return property value of mctProList
     */
    public List<PD141148MctProBean> getMctProList() {
        return mctProList;
    }

    /**
     * Setter method for property <tt>mctProList</tt>.
     *
     * @param mctProList value to be assigned to property mctProList
     */
    public void setMctProList(List<PD141148MctProBean> mctProList) {
        this.mctProList = mctProList;
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
     * Getter method for property <tt>mctOkValArray</tt>.
     *
     * @return property value of mctOkValArray
     */
    public String[] getMctOkValArray() {
        return mctOkValArray;
    }

    /**
     * Setter method for property <tt>mctOkValArray</tt>.
     *
     * @param mctOkValArray value to be assigned to property mctOkValArray
     */
    public void setMctOkValArray(String[] mctOkValArray) {
        this.mctOkValArray = mctOkValArray;
    }

    /**
     * Getter method for property <tt>mctNgValArray</tt>.
     *
     * @return property value of mctNgValArray
     */
    public String[] getMctNgValArray() {
        return mctNgValArray;
    }

    /**
     * Setter method for property <tt>mctNgValArray</tt>.
     *
     * @param mctNgValArray value to be assigned to property mctNgValArray
     */
    public void setMctNgValArray(String[] mctNgValArray) {
        this.mctNgValArray = mctNgValArray;
    }

    /**
     * Getter method for property <tt>mctStdItemIdArray</tt>.
     *
     * @return property value of mctStdItemIdArray
     */
    public String[] getMctStdItemIdArray() {
        return mctStdItemIdArray;
    }

    /**
     * Setter method for property <tt>mctStdItemIdArray</tt>.
     *
     * @param mctStdItemIdArray value to be assigned to property mctStdItemIdArray
     */
    public void setMctStdItemIdArray(String[] mctStdItemIdArray) {
        this.mctStdItemIdArray = mctStdItemIdArray;
    }
}
