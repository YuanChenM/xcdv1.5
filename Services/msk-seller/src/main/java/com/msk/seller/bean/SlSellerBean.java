package com.msk.seller.bean;

import com.msk.core.entity.SlSeller;

/**
 * Created by Administrator on 2016/4/1.
 */
public class SlSellerBean extends SlSeller {


    //企业名称
    private String epName;

    /**
     * Getter method for property <tt>epName</tt>.
     *
     * @return property value of epName
     */
    public String getEpName() {
        return epName;
    }

    /**
     * Setter method for property <tt>epName</tt>.
     *
     * @param epName value to be assigned to property epName
     */
    public void setEpName(String epName) {
        this.epName = epName;
    }
}
