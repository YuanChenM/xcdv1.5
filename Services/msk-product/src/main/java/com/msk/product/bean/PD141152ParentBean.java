package com.msk.product.bean;

import com.msk.core.entity.PdTspStdItem;

import java.util.List;

/**
 * Created by fjm on 2016/3/3.
 */
public class PD141152ParentBean extends PdTspStdItem {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private List<PD141152Param> tspList;

    public List<PD141152Param> getTspList() {
        return tspList;
    }

    public void setTspList(List<PD141152Param> tspList) {
        this.tspList = tspList;
    }
}
