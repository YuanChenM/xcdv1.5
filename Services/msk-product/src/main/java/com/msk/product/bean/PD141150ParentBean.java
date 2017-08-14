package com.msk.product.bean;

import com.msk.core.entity.PdGnqStdItem;

import java.util.List;

/**
 * Created by fjm on 2016/3/4.
 */
public class PD141150ParentBean extends PdGnqStdItem {
    private static final long serialVersionUID = 1L;

    private List<PD141150Param> gnqList;



    public List<PD141150Param> getGnqList() {
        return gnqList;
    }

    public void setGnqList(List<PD141150Param> gnqList) {
        this.gnqList = gnqList;
    }

}
