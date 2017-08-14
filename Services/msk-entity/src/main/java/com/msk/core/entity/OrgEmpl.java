/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表org_empl对应的OrgEmpl。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class OrgEmpl extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** EMPL_ID */
    private Long emplId;
    /** EMPL_CODE */
    private String emplCode;
    /** EMPL_NAME */
    private String emplName;
    /** 0:其他,1:男,2:女 */
    private String sex;
    /** BIRTH */
    private String birth;
    /** ENTRY */
    private String entry;
    /** MOBILE */
    private String mobile;
    /** EMIAL */
    private String emial;
    /** QQ */
    private String qq;
    /** PWD */
    private String pwd;
    /** STATUS */
    private Integer status;
    /**
     * <p>默认构造函数。</p>
     */
    public OrgEmpl() {

    }

    /**
     * <p>EMPL_ID。</p>
     *
     * @return the EMPL_ID
     */
    public Long getEmplId() {
        return emplId;
    }

    /**
     * <p>EMPL_ID。</p>
     *
     * @param emplId EMPL_ID。
     */
    public void setEmplId(Long emplId) {
        this.emplId = emplId;
    }

    /**
     * <p>EMPL_CODE。</p>
     *
     * @return the EMPL_CODE
     */
    public String getEmplCode() {
        return emplCode;
    }

    /**
     * <p>EMPL_CODE。</p>
     *
     * @param emplCode EMPL_CODE。
     */
    public void setEmplCode(String emplCode) {
        this.emplCode = emplCode;
    }

    /**
     * <p>EMPL_NAME。</p>
     *
     * @return the EMPL_NAME
     */
    public String getEmplName() {
        return emplName;
    }

    /**
     * <p>EMPL_NAME。</p>
     *
     * @param emplName EMPL_NAME。
     */
    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    /**
     * <p>0:其他,1:男,2:女。</p>
     *
     * @return the 0:其他,1:男,2:女
     */
    public String getSex() {
        return sex;
    }

    /**
     * <p>0:其他,1:男,2:女。</p>
     *
     * @param sex 0:其他,1:男,2:女。
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * <p>BIRTH。</p>
     *
     * @return the BIRTH
     */
    public String getBirth() {
        return birth;
    }

    /**
     * <p>BIRTH。</p>
     *
     * @param birth BIRTH。
     */
    public void setBirth(String birth) {
        this.birth = birth;
    }

    /**
     * <p>ENTRY。</p>
     *
     * @return the ENTRY
     */
    public String getEntry() {
        return entry;
    }

    /**
     * <p>ENTRY。</p>
     *
     * @param entry ENTRY。
     */
    public void setEntry(String entry) {
        this.entry = entry;
    }

    /**
     * <p>MOBILE。</p>
     *
     * @return the MOBILE
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * <p>MOBILE。</p>
     *
     * @param mobile MOBILE。
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * <p>EMIAL。</p>
     *
     * @return the EMIAL
     */
    public String getEmial() {
        return emial;
    }

    /**
     * <p>EMIAL。</p>
     *
     * @param emial EMIAL。
     */
    public void setEmial(String emial) {
        this.emial = emial;
    }

    /**
     * <p>QQ。</p>
     *
     * @return the QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * <p>QQ。</p>
     *
     * @param qq QQ。
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * <p>PWD。</p>
     *
     * @return the PWD
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * <p>PWD。</p>
     *
     * @param pwd PWD。
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * <p>STATUS。</p>
     *
     * @return the STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>STATUS。</p>
     *
     * @param status STATUS。
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}
