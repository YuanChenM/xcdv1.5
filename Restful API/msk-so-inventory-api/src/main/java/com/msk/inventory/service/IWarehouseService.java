package com.msk.inventory.service;

import com.msk.inventory.bean.IvmWarehouseBean;

import java.util.List;

/**
 * Created by duan_kai on 2016/9/1.
 */
public interface IWarehouseService {

    void insertOne(IvmWarehouseBean sqlBean);

    List<IvmWarehouseBean> selectListByCondition(IvmWarehouseBean sqlBean);

    int countByCondition(IvmWarehouseBean sqlBean);

    void updateOneWarehouse(IvmWarehouseBean sqlBean);

    boolean isExist(IvmWarehouseBean sqlBean);
}
