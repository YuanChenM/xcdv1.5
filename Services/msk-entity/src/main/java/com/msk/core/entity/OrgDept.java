/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表org_dept对应的OrgDept。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class OrgDept extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** DEPT_ID */
    private Long deptId;
    /** DEPT_CODE */
    private String deptCode;
    /** DEPT_NAME */
    private String deptName;
    /** PARENT_CODE */
    private String parentCode;
    /** DEPT_LEVEL */
    private Integer deptLevel;
    /** DEPT_MANAGER */
    private String deptManager;
    /** 0:正常 */
    private Integer status;
    /** DEPT_DESC */
    private String deptDesc;
    /**
     * <p>默认构造函数。</p>
     */
    public OrgDept() {

    }

    /**
     * <p>DEPT_ID。</p>
     *
     * @return the DEPT_ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * <p>DEPT_ID。</p>
     *
     * @param deptId DEPT_ID。
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * <p>DEPT_CODE。</p>
     *
     * @return the DEPT_CODE
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * <p>DEPT_CODE。</p>
     *
     * @param deptCode DEPT_CODE。
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * <p>DEPT_NAME。</p>
     *
     * @return the DEPT_NAME
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * <p>DEPT_NAME。</p>
     *
     * @param deptName DEPT_NAME。
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
     * <p>DEPT_LEVEL。</p>
     *
     * @return the DEPT_LEVEL
     */
    public Integer getDeptLevel() {
        return deptLevel;
    }

    /**
     * <p>DEPT_LEVEL。</p>
     *
     * @param deptLevel DEPT_LEVEL。
     */
    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    /**
     * <p>DEPT_MANAGER。</p>
     *
     * @return the DEPT_MANAGER
     */
    public String getDeptManager() {
        return deptManager;
    }

    /**
     * <p>DEPT_MANAGER。</p>
     *
     * @param deptManager DEPT_MANAGER。
     */
    public void setDeptManager(String deptManager) {
        this.deptManager = deptManager;
    }

    /**
     * <p>0:正常。</p>
     *
     * @return the 0:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>0:正常。</p>
     *
     * @param status 0:正常。
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <p>DEPT_DESC。</p>
     *
     * @return the DEPT_DESC
     */
    public String getDeptDesc() {
        return deptDesc;
    }

    /**
     * <p>DEPT_DESC。</p>
     *
     * @param deptDesc DEPT_DESC。
     */
    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

}
