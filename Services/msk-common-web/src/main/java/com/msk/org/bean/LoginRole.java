package com.msk.org.bean;

import com.msk.common.base.BaseBean;

import java.util.List;


/**
 * 登录用户角色
 * 
 * @author jiang_nan
 */
public class LoginRole extends BaseBean {

    private static final long serialVersionUID = 1L;
    /** 角色NO */
    private String roleNo;
    /** 员工编号 */
    private String emplNo;
    /**ORG No*/
    private String orgNo;
    private String orleName;
    private String orgName;

    /**
     * *获得orgNo
     **/
    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    /**
     * *获得orleName
     **/
    public String getOrleName() {
        return orleName;
    }

    public void setOrleName(String orleName) {
        this.orleName = orleName;
    }

    /**
     * *获得orgName
     **/
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return the roleNo
     */
    public String getRoleNo() {
        return roleNo;
    }

    /**
     * @param roleNo the roleNo to set
     */
    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    /**
     * @return the emplNo
     */
    public String getEmplNo() {
        return emplNo;
    }

    /**
     * @param emplNo the emplNo to set
     */
    public void setEmplNo(String emplNo) {
        this.emplNo = emplNo;
    }

    /** 角色可访问资源 */
    private List<LoginRoleAuthority> roleAuthoritieList;



}
