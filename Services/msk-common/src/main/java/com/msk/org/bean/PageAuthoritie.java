package com.msk.org.bean;


import com.msk.common.base.BaseBean;

/**
 * PageAuthoritie
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class PageAuthoritie extends BaseBean {
    /**页面编码*/
    private String pageCode;
    /**页面名称*/
    private String pageName;
    /**页面URL*/
    private String pageUrl;

    /**
     * Getter method for property <tt>pageCode</tt>.
     *
     * @return property value of pageCode
     */
    public String getPageCode() {
        return pageCode;
    }

    /**
     * Setter method for property <tt>pageCode</tt>.
     *
     * @param pageCode value to be assigned to property pageCode
     */
    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    /**
     * Getter method for property <tt>pageName</tt>.
     *
     * @return property value of pageName
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * Setter method for property <tt>pageName</tt>.
     *
     * @param pageName value to be assigned to property pageName
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * Getter method for property <tt>pageUrl</tt>.
     *
     * @return property value of pageUrl
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Setter method for property <tt>pageUrl</tt>.
     *
     * @param pageUrl value to be assigned to property pageUrl
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
