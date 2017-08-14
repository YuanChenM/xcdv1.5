/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表org_sys对应的OrgSys。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class OrgSys extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** SYS_CODE */
    private String sysCode;
    /** SYS_NAME */
    private String sysName;
    /** SYS_LEVEL */
    private Integer sysLevel;
    /** PARENT_CODE */
    private String parentCode;
    /** SYS_DESC */
    private String sysDesc;
    /**
     * <p>默认构造函数。</p>
     */
    public OrgSys() {

    }

    /**
     * <p>SYS_CODE。</p>
     *
     * @return the SYS_CODE
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * <p>SYS_CODE。</p>
     *
     * @param sysCode SYS_CODE。
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    /**
     * <p>SYS_NAME。</p>
     *
     * @return the SYS_NAME
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * <p>SYS_NAME。</p>
     *
     * @param sysName SYS_NAME。
     */
    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    /**
     * <p>SYS_LEVEL。</p>
     *
     * @return the SYS_LEVEL
     */
    public Integer getSysLevel() {
        return sysLevel;
    }

    /**
     * <p>SYS_LEVEL。</p>
     *
     * @param sysLevel SYS_LEVEL。
     */
    public void setSysLevel(Integer sysLevel) {
        this.sysLevel = sysLevel;
    }

    /**
     * <p>PARENT_CODE。</p>
     *
     * @return the PARENT_CODE
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * <p>PARENT_CODE。</p>
     *
     * @param parentCode PARENT_CODE。
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * <p>SYS_DESC。</p>
     *
     * @return the SYS_DESC
     */
    public String getSysDesc() {
        return sysDesc;
    }

    /**
     * <p>SYS_DESC。</p>
     *
     * @param sysDesc SYS_DESC。
     */
    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }

}
