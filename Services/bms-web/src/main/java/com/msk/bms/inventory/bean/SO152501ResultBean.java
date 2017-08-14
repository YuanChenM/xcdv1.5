package com.msk.bms.inventory.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by wang_fan2 on 2016/9/12.
 */
public class SO152501ResultBean extends BasePageParam {

    private List<SO152501Bean> so152501BeanList;

    public List<SO152501Bean> getSo152501BeanList() {
        return so152501BeanList;
    }

    public void setSo152501BeanList(List<SO152501Bean> so152501BeanList) {
        this.so152501BeanList = so152501BeanList;
    }
}
