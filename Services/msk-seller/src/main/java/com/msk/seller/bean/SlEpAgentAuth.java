/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.seller.bean;

/**
 * <p>表sl_ep_agent_auth对应的SlEpAgentAuth。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlEpAgentAuth extends com.msk.core.entity.SlEpAgentAuth{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
  
    
    /**
     * <p>默认构造函数。</p>
     */
    public SlEpAgentAuth() {

    }

  
 
   private String epName;
   
   private String slAreaCode;
   // vo属性
   private String onTime;
    /** 生产商编码*/
    private String slCodeManufacture;
    /** 生产商地址*/
    private String licAddr;

    private String authTermBeginString;
    private String authTermEndString;

    /**
     * Getter method for property <tt>authTermBeginString</tt>.
     *
     * @return property value of authTermBeginString
     */
    public String getAuthTermBeginString() {
        return authTermBeginString;
    }

    /**
     * Setter method for property <tt>authTermBeginString</tt>.
     *
     * @param authTermBeginString value to be assigned to property authTermBeginString
     */
    public void setAuthTermBeginString(String authTermBeginString) {
        this.authTermBeginString = authTermBeginString;
    }

    /**
     * Getter method for property <tt>authTermEndString</tt>.
     *
     * @return property value of authTermEndString
     */
    public String getAuthTermEndString() {
        return authTermEndString;
    }

    /**
     * Setter method for property <tt>authTermEndString</tt>.
     *
     * @param authTermEndString value to be assigned to property authTermEndString
     */
    public void setAuthTermEndString(String authTermEndString) {
        this.authTermEndString = authTermEndString;
    }

    public String getOnTime() {
        return this.onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

 

    public String getEpName() {
        return this.epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getSlAreaCode() {
        return this.slAreaCode;
    }

    public void setSlAreaCode(String slAreaCode) {
        this.slAreaCode = slAreaCode;
    }

    /**
     * Getter method for property <tt>slCodeManufacture</tt>.
     *
     * @return property value of slCodeManufacture
     */
    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    /**
     * Setter method for property <tt>slCodeManufacture</tt>.
     *
     * @param slCodeManufacture value to be assigned to property slCodeManufacture
     */
    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    /**
     * Getter method for property <tt>licAddr</tt>.
     *
     * @return property value of licAddr
     */
    public String getLicAddr() {
        return licAddr;
    }

    /**
     * Setter method for property <tt>licAddr</tt>.
     *
     * @param licAddr value to be assigned to property licAddr
     */
    public void setLicAddr(String licAddr) {
        this.licAddr = licAddr;
    }
}
