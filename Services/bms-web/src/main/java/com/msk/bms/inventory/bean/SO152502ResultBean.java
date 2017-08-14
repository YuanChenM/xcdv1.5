package com.msk.bms.inventory.bean;

import java.util.List;

import com.msk.common.entity.BaseEntity;

/**
 * Created by wang_fan2 on 2016/9/12.
 */
public class SO152502ResultBean extends BaseEntity {

    private List<SO152502Bean> so152502BeanList;

    public List<SO152502Bean> getSo152502BeanList() {
        return so152502BeanList;
    }

    public void setSo152502BeanList(List<SO152502Bean> so152502BeanList) {
        this.so152502BeanList = so152502BeanList;
    }
}
