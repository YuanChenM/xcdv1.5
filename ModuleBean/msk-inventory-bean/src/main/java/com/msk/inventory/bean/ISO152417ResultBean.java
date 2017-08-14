package com.msk.inventory.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zheng_xu on 2016/9/12.
 */
public class ISO152417ResultBean implements Serializable {
    // 有过库存的供应商
    private List<ISO152417SupplierBean> sellers;

    public List<ISO152417SupplierBean> getSellers() {
        return sellers;
    }

    public void setSellers(List<ISO152417SupplierBean> sellers) {
        this.sellers = sellers;
    }
}
