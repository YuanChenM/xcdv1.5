/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表comm_config对应的CommConfig。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class CommConfig extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** SYSTEM_KEY */
    private String systemKey;
    /** SYSTEM_VALUE */
    private String systemValue;
    /** SYSTEM_DESC */
    private String systemDesc;
    /**
     * <p>默认构造函数。</p>
     */
    public CommConfig() {

    }

    /**
     * <p>SYSTEM_KEY。</p>
     *
     * @return the SYSTEM_KEY
     */
    public String getSystemKey() {
        return systemKey;
    }

    /**
     * <p>SYSTEM_KEY。</p>
     *
     * @param systemKey SYSTEM_KEY。
     */
    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    /**
     * <p>SYSTEM_VALUE。</p>
     *
     * @return the SYSTEM_VALUE
     */
    public String getSystemValue() {
        return systemValue;
    }

    /**
     * <p>SYSTEM_VALUE。</p>
     *
     * @param systemValue SYSTEM_VALUE。
     */
    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }

    /**
     * <p>SYSTEM_DESC。</p>
     *
     * @return the SYSTEM_DESC
     */
    public String getSystemDesc() {
        return systemDesc;
    }

    /**
     * <p>SYSTEM_DESC。</p>
     *
     * @param systemDesc SYSTEM_DESC。
     */
    public void setSystemDesc(String systemDesc) {
        this.systemDesc = systemDesc;
    }

}
