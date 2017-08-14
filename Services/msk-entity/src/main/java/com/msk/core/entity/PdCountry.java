/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_country对应的PdCountry。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdCountry extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 国家编码 */
    private java.lang.String countryCode;
    /** 国家名称 */
    private java.lang.String countryName;
    /**
     * <p>默认构造函数。</p>
     */
    public PdCountry() {

    }

    /**
     * <p>国家编码。</p>
     *
     * @return the 国家编码
     */
    public java.lang.String getCountryCode() {
        return countryCode;
    }

    /**
     * <p>国家编码。</p>
     *
     * @param countryCode 国家编码。
     */
    public void setCountryCode(java.lang.String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * <p>国家名称。</p>
     *
     * @return the 国家名称
     */
    public java.lang.String getCountryName() {
        return countryName;
    }

    /**
     * <p>国家名称。</p>
     *
     * @param countryName 国家名称。
     */
    public void setCountryName(java.lang.String countryName) {
        this.countryName = countryName;
    }

}
