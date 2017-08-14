package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseParam;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/13.
 */
public class SO152501ResultBean extends BaseParam {
    private List<SO152501Bean> so152501BeanList;

    public List<SO152501Bean> getSo152501BeanList() {
        return so152501BeanList;
    }

    public void setSo152501BeanList(List<SO152501Bean> so152501BeanList) {
        this.so152501BeanList = so152501BeanList;
    }
}
