package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.IvmWarehouseInventoryBean;
import com.msk.inventory.service.IWarehouseInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duan_kai on 2016/9/3.
 */
@Service
public class WarehouseInventoryServiceImpl extends BaseService implements IWarehouseInventoryService {
    @Override
    public void insertOne(IvmWarehouseInventoryBean sqlBean) {
        this.save("insertOne", sqlBean);
    }

    @Override
    public void insertByList(IvmWarehouseInventoryBean sqlBean) {
        this.save("insertByList", sqlBean);
    }

    @Override
    public List<IvmWarehouseInventoryBean> selectListByCondition(IvmWarehouseInventoryBean sqlBean) {
        return this.findList("queryListByCondition", sqlBean);
    }

    @Override
    public int countByCondition(IvmWarehouseInventoryBean sqlBean) {
        return this.getCount("countByCondition", sqlBean);
    }

    @Override
    public boolean isExist(IvmWarehouseInventoryBean sqlBean) {
        boolean isExist = false;
        int countNums = this.getCount("countByCondition", sqlBean);
        if (countNums > 0) {
            isExist = true;
        }
        return isExist;
    }
}
