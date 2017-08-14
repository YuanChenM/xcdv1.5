package com.msk.comm.bean.param;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackjiang on 16/8/8.
 */
public class BaseParam implements Serializable{
    private Map<String, Object> filterMap = new HashMap<String, Object>();
    /** 删除标志 */
    private String delFlg;
    /** 创建者ID */
    private String crtId;
    /** 创建日时 */
    private Date crtTime;
    /** 更新者ID */
    private String updId;
    /** 更新日时 */
    private Date updTime;
    /** 版本号 */
    private Integer ver;
    /** 生效者ID */
    private String actId;
    /** 生效日时 */
    private Date actTime;

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
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

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public Date getActTime() {
        return actTime;
    }

    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }

    /**
     * Set one filter condition.
     *
     * @param key the filter's key
     * @param value the filter's value
     */
    public void setFilter(String key, String value) {
        this.filterMap.put(key, value);
    }
    public void setFilterObject(String key,Object value){
        this.filterMap.put(key,value);
    }
    /**
     * Get all filters's condition.
     *
     * @return the filterMap
     */
    public Map<String, Object> getFilterMap() {
        return filterMap;
    }

    /**
     * Set all filter conditions.
     *
     * @param filterMap the filterMap to set
     */
    public void setFilterMap(Map<String, Object> filterMap) {
        this.filterMap = filterMap;
    }
}
