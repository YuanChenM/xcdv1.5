package com.msk.org.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.msk.common.entity.BaseEntity;

@Entity(name="OrgAction")
public class OrgAction extends BaseEntity {
    @EmbeddedId
    private OrgActionPrimaryKey actionPrimaryKey;
    private String actionName;
    private String actionType;
    private String actionDesc;

    public OrgActionPrimaryKey getActionPrimaryKey() {
        return actionPrimaryKey;
    }

    public void setActionPrimaryKey(OrgActionPrimaryKey actionPrimaryKey) {
        this.actionPrimaryKey = actionPrimaryKey;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }
}
