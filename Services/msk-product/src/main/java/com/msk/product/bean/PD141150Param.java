package com.msk.product.bean;

import com.msk.core.entity.PdGnqStd;

/**
 * Created by air on 2016/3/4.
 */
public class PD141150Param extends PdGnqStd {
    private String gnqStdItemName;
    /**
     * 合格
     */
    private String[] gnqOkValArray;//合格值
    /**
     * 不合格
     */
    private String[] gnqNgValArray;//不合格值

    /**
     * idArray
     */
    private String[] gnqItemIdArray;


    public String getGnqStdItemName() {
        return gnqStdItemName;
    }

    public void setGnqStdItemName(String gnqStdItemName) {
        this.gnqStdItemName = gnqStdItemName;
    }

    public String[] getGnqOkValArray() {
        return gnqOkValArray;
    }

    public void setGnqOkValArray(String[] gnqOkValArray) {
        this.gnqOkValArray = gnqOkValArray;
    }

    public String[] getGnqNgValArray() {
        return gnqNgValArray;
    }

    public void setGnqNgValArray(String[] gnqNgValArray) {
        this.gnqNgValArray = gnqNgValArray;
    }

    public String[] getGnqItemIdArray() {
        return gnqItemIdArray;
    }

    public void setGnqItemIdArray(String[] gnqItemIdArray) {
        this.gnqItemIdArray = gnqItemIdArray;
    }
}
