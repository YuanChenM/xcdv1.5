package com.msk.product.bean;

import com.msk.core.entity.PdSftStd;

/**
 * PD141120Bean.
 *
 * @author gyh
 */
public class PD141151Param extends PdSftStd {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String sftStdItemName;
    /**
     * 合格
     */
    private String[] sftOkValArray;//合格值
    /**
     * 不合格
     */
    private String[] sftNgValArray;//不合格值

    /**
     * idArray
     */
    private String[] sftItemIdArray;


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
     * Getter method for property <tt>sftStdItemName</tt>.
     *
     * @return property value of sftStdItemName
     */
    public String getSftStdItemName() {
        return sftStdItemName;
    }

    /**
     * Setter method for property <tt>sftStdItemName</tt>.
     *
     * @param sftStdItemName value to be assigned to property sftStdItemName
     */
    public void setSftStdItemName(String sftStdItemName) {
        this.sftStdItemName = sftStdItemName;
    }

    /**
     * Getter method for property <tt>sftOkValArray</tt>.
     *
     * @return property value of sftOkValArray
     */
    public String[] getSftOkValArray() {
        return sftOkValArray;
    }

    /**
     * Setter method for property <tt>sftOkValArray</tt>.
     *
     * @param sftOkValArray value to be assigned to property sftOkValArray
     */
    public void setSftOkValArray(String[] sftOkValArray) {
        this.sftOkValArray = sftOkValArray;
    }

    /**
     * Getter method for property <tt>sftNgValArray</tt>.
     *
     * @return property value of sftNgValArray
     */
    public String[] getSftNgValArray() {
        return sftNgValArray;
    }

    /**
     * Setter method for property <tt>sftNgValArray</tt>.
     *
     * @param sftNgValArray value to be assigned to property sftNgValArray
     */
    public void setSftNgValArray(String[] sftNgValArray) {
        this.sftNgValArray = sftNgValArray;
    }


}
