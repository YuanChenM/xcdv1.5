package com.msk.inventory.bean;

import java.io.Serializable;
import java.util.List;

import com.msk.comm.bean.param.BaseParam;

/**
 * Created by wang_fan2 on 2016/9/9.
 */
public class ISO152411RsReslutBean extends BaseParam implements Serializable {
    private List<ISO152411SlBean> slList;

    public List<ISO152411SlBean> getSlList() {
        return slList;
    }

    public void setSlList(List<ISO152411SlBean> slList) {
        this.slList = slList;
    }
}
