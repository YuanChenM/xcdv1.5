package com.msk.seller.bean;

/**
 * Created by Administrator on 2016/2/29.
 */
public class ISL231180RsResult {
    //企业ID
    private Long epId;
    //卖家ID
    private String slCode;

    /**
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
