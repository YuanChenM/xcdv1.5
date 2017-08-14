package com.msk.product.bean;

import com.msk.core.entity.PdTspStd;

/**
 * Created by fjm on 2016/3/3.
 */
public class PD141152Param extends PdTspStd {

    private String tspStdItemName;
    /**
     * 合格
     */
    private String[] tspOkValArray;//合格值
    /**
     * 不合格
     */
    private String[] tspNgValArray;//不合格值

    /**
     * idArray
     */
    private String[] tspItemIdArray;


    public String getTspStdItemName() {
        return tspStdItemName;
    }

    public void setTspStdItemName(String tspStdItemName) {
        this.tspStdItemName = tspStdItemName;
    }

    public String[] getTspOkValArray() {
        return tspOkValArray;
    }

    public void setTspOkValArray(String[] tspOkValArray) {
        this.tspOkValArray = tspOkValArray;
    }

    public String[] getTspNgValArray() {
        return tspNgValArray;
    }

    public void setTspNgValArray(String[] tspNgValArray) {
        this.tspNgValArray = tspNgValArray;
    }

    public String[] getTspItemIdArray() {
        return tspItemIdArray;
    }

    public void setTspItemIdArray(String[] tspItemIdArray) {
        this.tspItemIdArray = tspItemIdArray;
    }
}
