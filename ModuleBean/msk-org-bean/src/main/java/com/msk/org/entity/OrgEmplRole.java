package com.msk.org.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
public class OrgEmplRole extends BaseEntity{
    @EmbeddedId
    private OrgEmplRolePrimaryKey emplRolePrimaryKey;

    public OrgEmplRolePrimaryKey getEmplRolePrimaryKey() {
        return emplRolePrimaryKey;
    }

    public void setEmplRolePrimaryKey(OrgEmplRolePrimaryKey emplRolePrimaryKey) {
        this.emplRolePrimaryKey = emplRolePrimaryKey;
    }
}
