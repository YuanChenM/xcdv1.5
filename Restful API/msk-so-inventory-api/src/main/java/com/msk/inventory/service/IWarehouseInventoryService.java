package com.msk.inventory.service;

import com.msk.inventory.bean.IvmWarehouseInventoryBean;

import java.util.List;

/**
 * Created by duan_kai on 2016/9/3.
 */
public interface IWarehouseInventoryService {

    void insertOne(IvmWarehouseInventoryBean sqlBean);

    void insertByList(IvmWarehouseInventoryBean sqlBean);

    List<IvmWarehouseInventoryBean> selectListByCondition(IvmWarehouseInventoryBean sqlBean);

    int countByCondition(IvmWarehouseInventoryBean sqlBean);

    boolean isExist(IvmWarehouseInventoryBean sqlBean);
}
