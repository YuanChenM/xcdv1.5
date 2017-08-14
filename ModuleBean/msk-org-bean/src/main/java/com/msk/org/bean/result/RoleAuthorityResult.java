package com.msk.org.bean.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msk.org.entity.OrgSystem;

import java.util.List;

/**
 * Created by jackjiang on 16/9/4.
 */
public class RoleAuthorityResult {
    private String systemCode;
    private String systemName;
    private String moduleCode;
    private String moduleName;
    private String pageCode;
    private String pageName;
    private String actionCode;
    private String actionName;
    private List<OrgSystem> systemModuleList;

    public RoleAuthorityResult(String systemCode, String systemName){
        this.systemCode = systemCode;
        this.systemName = systemName;
    }

    public List<OrgSystem> getSystemModuleList() {
        return systemModuleList;
    }

    public void setSystemModuleList(List<OrgSystem> systemModuleList) {
        this.systemModuleList = systemModuleList;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }


    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

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
