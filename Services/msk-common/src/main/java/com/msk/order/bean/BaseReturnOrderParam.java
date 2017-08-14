package com.msk.order.bean;


import com.hoperun.core.bean.BaseParam;

import java.util.Date;

/**
 * BaseReturnOrderParam
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class BaseReturnOrderParam extends BaseParam {
    /**Return Id*/
    private Long returnId;
    /**Return Code*/
    private String returnCode;
    /** 删除标志 */
    private String delFlg;
    /** 创建者ID */
    private String crtId;
    /** 创建日时 */
    private Date crtTime;
    /** 更新者ID */
    private String updId;
    /** 更新日时 */
    private Date updTime;
    /** 版本号 */
    private Integer ver;
    /** 生效者ID */
    private String actId;
    /** 生效日时 */
    private Date actTime;
    /**
     * Getter method for property <tt>returnId</tt>.
     *
     * @return property value of returnId
     */
    public Long getReturnId() {
        return returnId;
    }

    /**
     * Setter method for property <tt>returnId</tt>.
     *
     * @param returnId value to be assigned to property returnId
     */
    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    /**
     * Getter method for property <tt>returnCode</tt>.
     *
     * @return property value of returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * Setter method for property <tt>returnCode</tt>.
     *
     * @param returnCode value to be assigned to property returnCode
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * Get the delFlg.
     *
     * @return delFlg
     *
     * 
     */
    public String getDelFlg() {
        return this.delFlg;
    }

    /**
     * Set the delFlg.
     *
     * @param delFlg delFlg
     *
     * 
     */
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * Get the crtId.
     *
     * @return crtId
     *
     * 
     */
    public String getCrtId() {
        return this.crtId;
    }

    /**
     * Set the crtId.
     *
     * @param crtId crtId
     *
     * 
     */
    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

    /**
     * Get the crtTime.
     *
     * @return crtTime
     *
     * 
     */
    public Date getCrtTime() {
        return this.crtTime;
    }

    /**
     * Set the crtTime.
     *
     * @param crtTime crtTime
     *
     * 
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * Get the updId.
     *
     * @return updId
     *
     * 
     */
    public String getUpdId() {
        return this.updId;
    }

    /**
     * Set the updId.
     *
     * @param updId updId
     *
     * 
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    /**
     * Get the updTime.
     *
     * @return updTime
     *
     * 
     */
    public Date getUpdTime() {
        return this.updTime;
    }

    /**
     * Set the updTime.
     *
     * @param updTime updTime
     *
     * 
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * Get the ver.
     *
     * @return ver
     *
     * 
     */
    public Integer getVer() {
        return this.ver;
    }

    /**
     * Set the ver.
     *
     * @param ver ver
     *
     * 
     */
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    /**
     * Get the actId.
     *
     * @return actId
     *
     * 
     */
    public String getActId() {
        return this.actId;
    }

    /**
     * Set the actId.
     *
     * @param actId actId
     *
     * 
     */
    public void setActId(String actId) {
        this.actId = actId;
    }

    /**
     * Get the actTime.
     *
     * @return actTime
     *
     * 
     */
    public Date getActTime() {
        return this.actTime;
    }

    /**
     * Set the actTime.
     *
     * @param actTime actTime
     *
     * 
     */
    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }
}
