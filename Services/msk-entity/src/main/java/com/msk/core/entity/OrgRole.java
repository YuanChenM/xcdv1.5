/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表org_role对应的OrgRole。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class OrgRole extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** DEPT_ID */
    private Long deptId;
    /** ROLE_ID */
    private Long roleId;
    /** ROLE_NAME */
    private String roleName;
    /** 1:是 */
    private String isKey;
    /**
     * <p>默认构造函数。</p>
     */
    public OrgRole() {

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
     * <p>ROLE_ID。</p>
     *
     * @return the ROLE_ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * <p>ROLE_ID。</p>
     *
     * @param roleId ROLE_ID。
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * <p>ROLE_NAME。</p>
     *
     * @return the ROLE_NAME
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * <p>ROLE_NAME。</p>
     *
     * @param roleName ROLE_NAME。
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * <p>1:是。</p>
     *
     * @return the 1:是
     */
    public String getIsKey() {
        return isKey;
    }

    /**
     * <p>1:是。</p>
     *
     * @param isKey 1:是。
     */
    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }

}
