package com.msk.org.bean;

import java.io.Serializable;

public class PageAuthority implements Serializable{
    private String pageCode;
    private String url;

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
