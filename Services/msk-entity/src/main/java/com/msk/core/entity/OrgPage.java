/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表org_page对应的OrgPage。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class OrgPage extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** PAGE_CODE */
    private String pageCode;
    /** SYS_CODE */
    private String sysCode;
    /** PAGE_NAME */
    private String pageName;
    /** PAGE_DESC */
    private String pageDesc;
    /** 0:不是,1:是 */
    private String isMenu;
    /** PAGE_URL */
    private String pageUrl;

    /**
     * 排列顺序
     */
    private Integer sort;
    /**
     * <p>默认构造函数。</p>
     */
    public OrgPage() {

    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * <p>PAGE_CODE。</p>
     *
     * @return the PAGE_CODE
     */
    public String getPageCode() {
        return pageCode;
    }

    /**
     * <p>PAGE_CODE。</p>
     *
     * @param pageCode PAGE_CODE。
     */
    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
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
     * <p>PAGE_NAME。</p>
     *
     * @return the PAGE_NAME
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * <p>PAGE_NAME。</p>
     *
     * @param pageName PAGE_NAME。
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * <p>PAGE_DESC。</p>
     *
     * @return the PAGE_DESC
     */
    public String getPageDesc() {
        return pageDesc;
    }

    /**
     * <p>PAGE_DESC。</p>
     *
     * @param pageDesc PAGE_DESC。
     */
    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }

    /**
     * <p>0:不是,1:是。</p>
     *
     * @return the 0:不是,1:是
     */
    public String getIsMenu() {
        return isMenu;
    }

    /**
     * <p>0:不是,1:是。</p>
     *
     * @param isMenu 0:不是,1:是。
     */
    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    /**
     * <p>PAGE_URL。</p>
     *
     * @return the PAGE_URL
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * <p>PAGE_URL。</p>
     *
     * @param pageUrl PAGE_URL。
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

}
