package com.msk.org.bean.result;

import com.msk.common.bean.result.BaseResult;
import com.msk.org.entity.OrgRole;

public class RoleResult extends BaseResult{
    private String deptName;
    private OrgRole role;

    public RoleResult(String deptName, OrgRole role) {
        this.deptName = deptName;
        this.role = role;
    }
    public RoleResult(){

    }
    public String getDeptId() {
        return role.getDeptId();
    }

    public Long getRoleId() {
        return role.getRoleId();
    }

    public String getRoleName() {
        return role.getRoleName();
    }

    public String getIsKey() {
        return role.getIsKey();
    }

    public String getDeptName() {
        return deptName;
    }
}
