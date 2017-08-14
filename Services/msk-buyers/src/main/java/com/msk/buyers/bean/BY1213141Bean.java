package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerDelivery;

/**
 * Created by zhou_yajun on 2016/7/8.
 */
public class BY1213141Bean extends ByBuyerDelivery {
    private String isFlag;
    /** 设置默认配送地址*/
    private String[] defaultFlg;

    public String getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag;
    }

    /**
     * Getter method for property <tt>defaultFlg</tt>.
     *
     * @return property value of defaultFlg
     */
    public String[] getDefaultFlg() {
        return defaultFlg;
    }

    /**
     * Setter method for property <tt>defaultFlg</tt>.
     *
     * @param defaultFlg value to be assigned to property defaultFlg
     */
    public void setDefaultFlg(String[] defaultFlg) {
        this.defaultFlg = defaultFlg;
    }
}
