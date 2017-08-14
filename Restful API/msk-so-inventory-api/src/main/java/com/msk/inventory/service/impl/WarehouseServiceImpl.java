package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.IvmWarehouseBean;
import com.msk.inventory.service.IWarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duan_kai on 2016/9/1.
 */
@Service
public class WarehouseServiceImpl extends BaseService implements IWarehouseService {

    @Override
    public void insertOne(IvmWarehouseBean sqlBean) {
        this.save("insertOne", sqlBean);
    }

    @Override
    public List<IvmWarehouseBean> selectListByCondition(IvmWarehouseBean sqlBean) {
        return this.findList("queryListByCondition", sqlBean);
    }

    @Override
    public int countByCondition(IvmWarehouseBean sqlBean) {
        return this.getCount("countByCondition", sqlBean);
    }

    @Override
    public void updateOneWarehouse(IvmWarehouseBean sqlBean) {
        this.modify("editOne", sqlBean);
    }

    @Override
    public boolean isExist(IvmWarehouseBean sqlBean) {
        boolean isExist = false;
        int countNums = this.getCount("countByCondition", sqlBean);
        if (countNums > 0) {
            isExist = true;
        }
        return isExist;
    }

}
