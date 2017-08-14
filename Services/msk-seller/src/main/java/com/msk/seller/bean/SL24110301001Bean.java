package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by Administrator on 2016/5/4.
 */
public class SL24110301001Bean extends BaseEntity {

    /** 企业ID*/
    private String epId;
    /** 企业名称*/
    private String epName;
    /** 企业；主类型*/
    private String slMainClass;

    /**
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId
     */
    public String getEpId() {
        return epId;
    }

    /**
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId
     */
    public void setEpId(String epId) {
        this.epId = epId;
    }

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

    /**
     * Getter method for property <tt>slMainClass</tt>.
     *
     * @return property value of slMainClass
     */
    public String getSlMainClass() {
        return slMainClass;
    }

    /**
     * Setter method for property <tt>slMainClass</tt>.
     *
     * @param slMainClass value to be assigned to property slMainClass
     */
    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }
}
