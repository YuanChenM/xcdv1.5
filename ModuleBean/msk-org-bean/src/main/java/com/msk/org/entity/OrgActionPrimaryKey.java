package com.msk.org.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrgActionPrimaryKey implements Serializable{
    @Column(name = "PAGE_CODE")
    private String pageCode;
    @Column(name = "ACTION_CODE")
    private String actionCode;

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }
}
