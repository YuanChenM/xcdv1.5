package com.msk.config.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by shi_yuxi on 2016/5/4.
 */
public class TreeBean implements Serializable{

    private String id;

    private String name;

    private String value;

    private String pId;

    private String comment;

    private String type;

    private Map<String, String> map;

    private List<TreeBean> list;

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<TreeBean> getList() {
        return list;
    }

    public void setList(List<TreeBean> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}
