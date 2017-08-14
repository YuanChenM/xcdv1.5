package com.msk.inventory.service;

import com.msk.inventory.bean.IvmAllocatedLogicBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.entity.IvmAllocatedLogic;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/2.
 */
public interface IIvmAllocatedLogicService {

    void insertAllocatedLogic(IvmAllocatedLogicBean sqlBean);

    void updateAllocatedLogicByCondition(IvmAllocatedLogicBean sqlBean);

    List<IvmAllocatedLogicBean> selectAllocatedLogicList(IvmAllocatedLogicBean sqlBean);

    int getCountAllocatedLogic(IvmAllocatedLogicBean sqlBean);

    void addAllocatedQty(IvmAllocatedLogicBean sqlBean);

    void cutAllocatedQty(IvmAllocatedLogicBean sqlBean);

    void calAllocatedQtyForDispatch(IvmInventoryDetailBean sqlBean);

    void calAllocatedQtyForUndoDispatch(IvmInventoryDetailBean sqlBean);
}
