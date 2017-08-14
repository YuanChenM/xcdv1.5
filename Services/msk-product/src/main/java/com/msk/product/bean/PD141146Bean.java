package com.msk.product.bean;

import com.msk.core.entity.PdOrgStd;

/**
 * PD141146Bean.
 *
 * @author XHY
 */
public class PD141146Bean extends PdOrgStd {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String orgStdItemName;
    /** 优良  */
    private String [] orgGoodValArray;
    /** 一般 */
    private String [] orgNormalValArray;
    /** 差 */
    private String [] orgBadValArray;

    /**
     * idArray
     */
    private String [] orgStdItemIdArray;

    /**
     * 获得orgStdItemIdArray
     */
    public String[] getOrgStdItemIdArray() {
        return orgStdItemIdArray;
    }

    /**
     * 设置orgStdItemIdArray
     */
    public void setOrgStdItemIdArray(String[] orgStdItemIdArray) {
        this.orgStdItemIdArray = orgStdItemIdArray;
    }

    /**
     * 获得orgGoodValArray
     */
    public String[] getOrgGoodValArray() {
        return orgGoodValArray;
    }

    /**
     * 设置orgGoodValArray
     */
    public void setOrgGoodValArray(String[] orgGoodValArray) {
        this.orgGoodValArray = orgGoodValArray;
    }

    /**
     * 获得orgNormalValArray
     */
    public String[] getOrgNormalValArray() {
        return orgNormalValArray;
    }

    /**
     * 设置orgNormalValArray
     */
    public void setOrgNormalValArray(String[] orgNormalValArray) {
        this.orgNormalValArray = orgNormalValArray;
    }

    /**
     * 获得orgBadValArray
     */
    public String[] getOrgBadValArray() {
        return orgBadValArray;
    }

    /**
     * 设置orgBadValArray
     */
    public void setOrgBadValArray(String[] orgBadValArray) {
        this.orgBadValArray = orgBadValArray;
    }

    /**
     * 获得orgStdItemName
     */
    public String getOrgStdItemName() {
        return orgStdItemName;
    }

    /**
     * 设置orgStdItemName
     */
    public void setOrgStdItemName(String orgStdItemName) {
        this.orgStdItemName = orgStdItemName;
    }
}
