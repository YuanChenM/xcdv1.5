package com.msk.inventory.service;

import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.bean.IvmSlOnhandLogicBean;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/7.
 */
public interface ISlOnhandLogicService {

    void insertSlOnhandLogic(IvmSlOnhandLogicBean sqlBean);

    void updateSlOnhandLogicByCondition(IvmSlOnhandLogicBean sqlBean);

    List<IvmSlOnhandLogicBean> getSlOnhandLogicList(IvmSlOnhandLogicBean sqlBean);

    int getCountSlOnhandLogic(IvmSlOnhandLogicBean sqlBean);

    void cutOnhandQty(IvmSlOnhandLogicBean sqlBean);

    void addOnhandQty(IvmSlOnhandLogicBean sqlBean);

    void calOnhandQtyForDispatch(IvmInventoryDetailBean sqlBean);

    void calOnhandQtyForUndoDispatch(IvmInventoryDetailBean sqlBean);
}
