/*
 * 2014/09/23 自动生成 新規作成
 * (c) 江苏润和.
 */
package com.msk.bs.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author wang_haichun
 *
 */
public class IBS2101129RsParam implements Serializable{
    private String hkGroupId;
    private List<IBS121306RsBean> houseList;

    public String getHkGroupId() {
        return hkGroupId;
    }

    public void setHkGroupId(String hkGroupId) {
        this.hkGroupId = hkGroupId;
    }

    public List<IBS121306RsBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBS121306RsBean> houseList) {
        this.houseList = houseList;
    }
}
