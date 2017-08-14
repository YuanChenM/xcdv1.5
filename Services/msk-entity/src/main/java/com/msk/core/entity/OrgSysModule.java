/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表org_sys_module对应的OrgSysModule。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class OrgSysModule extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 模块代号 */
    private String sysModule;
    /** 1:员工,2:卖家(供应商),3:买家 */
    private Integer loginUserType;
    /** 模块显示名称 */
    private String sysShowName;
    /** 系统编号 */
    private String sysCode;
    /** 系统主画面URL */
    private String sysMainUrl;
    /**
     * <p>默认构造函数。</p>
     */
    public OrgSysModule() {

    }

    /**
     * <p>模块代号。</p>
     *
     * @return the 模块代号
     */
    public String getSysModule() {
        return sysModule;
    }

    /**
     * <p>模块代号。</p>
     *
     * @param sysModule 模块代号。
     */
    public void setSysModule(String sysModule) {
        this.sysModule = sysModule;
    }

    /**
     * <p>1:员工,2:卖家(供应商),3:买家。</p>
     *
     * @return the 1:员工,2:卖家(供应商),3:买家
     */
    public Integer getLoginUserType() {
        return loginUserType;
    }

    /**
     * <p>1:员工,2:卖家(供应商),3:买家。</p>
     *
     * @param loginUserType 1:员工,2:卖家(供应商),3:买家。
     */
    public void setLoginUserType(Integer loginUserType) {
        this.loginUserType = loginUserType;
    }

    /**
     * <p>模块显示名称。</p>
     *
     * @return the 模块显示名称
     */
    public String getSysShowName() {
        return sysShowName;
    }

    /**
     * <p>模块显示名称。</p>
     *
     * @param sysShowName 模块显示名称。
     */
    public void setSysShowName(String sysShowName) {
        this.sysShowName = sysShowName;
    }

    /**
     * <p>系统编号。</p>
     *
     * @return the 系统编号
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * <p>系统编号。</p>
     *
     * @param sysCode 系统编号。
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    /**
     * <p>系统主画面URL。</p>
     *
     * @return the 系统主画面URL
     */
    public String getSysMainUrl() {
        return sysMainUrl;
    }

    /**
     * <p>系统主画面URL。</p>
     *
     * @param sysMainUrl 系统主画面URL。
     */
    public void setSysMainUrl(String sysMainUrl) {
        this.sysMainUrl = sysMainUrl;
    }

}
