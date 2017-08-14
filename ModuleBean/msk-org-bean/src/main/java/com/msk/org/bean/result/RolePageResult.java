package com.msk.org.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * Created by jackjiang on 16/9/5.
 */
public class RolePageResult extends BaseResult{
    private String pageCode;
    private String pageName;
    private String actionCode;
    private String actionName;
    private List<RolePageResult> children;

    public RolePageResult(String pageCode, String pageName, String actionCode, String actionName) {
        this.pageCode = pageCode;
        this.pageName = pageName;
        this.actionCode = actionCode;
        this.actionName = actionName;
    }

    public RolePageResult(String pageCode, String pageName) {
        this.pageCode = pageCode;
        this.pageName = pageName;
    }

    public List<RolePageResult> getChildren() {
        return children;
    }

    public void setChildren(List<RolePageResult> children) {
        this.children = children;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
