package com.msk.bs.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang_haichun on 2016/9/14.
 */
public class BS2101107Result implements Serializable{

    private List<BS2102115Bean> hkGroupList;

    public List<BS2102115Bean> getHkGroupList() {
        return hkGroupList;
    }

    public void setHkGroupList(List<BS2102115Bean> hkGroupList) {
        this.hkGroupList = hkGroupList;
    }
}
