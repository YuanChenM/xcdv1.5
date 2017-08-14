package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/26.
 */
public class ISO151433RestUndoDispatch extends BaseRestParam {
    private List<ISO151433UndoDispatchParam> invList;
    private String crtId;
    private Date crtTime;
    private String updId;
    private Date updTime;
    private Integer ver;

    public List<ISO151433UndoDispatchParam> getInvList() {
        return invList;
    }

    public void setInvList(List<ISO151433UndoDispatchParam> invList) {
        this.invList = invList;
    }

    public String getCrtId() {
        return crtId;
    }

    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }
}
