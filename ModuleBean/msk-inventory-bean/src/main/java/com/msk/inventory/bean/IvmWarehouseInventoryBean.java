package com.msk.inventory.bean;

import com.msk.inventory.entity.IvmWarehouseInventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/9/2.
 */
public class IvmWarehouseInventoryBean extends IvmWarehouseInventory {

    private List<IvmwarehouseInvListBean> sqlList;

    private List<IvmWarehouseInventoryBean> sqlsList;

    public List<IvmwarehouseInvListBean> getSqlList() {
        return sqlList;
    }

    public void setSqlList(List<IvmwarehouseInvListBean> sqlList) {
        this.sqlList = sqlList;
    }


    public List<IvmWarehouseInventoryBean> getSqlsList() {
        return sqlsList;
    }

    public void setSqlsList(List<IvmWarehouseInventoryBean> sqlsList) {
        this.sqlsList = sqlsList;
    }
}
