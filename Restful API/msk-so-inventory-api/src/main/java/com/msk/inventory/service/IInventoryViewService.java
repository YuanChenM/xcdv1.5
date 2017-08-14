package com.msk.inventory.service;

import com.msk.inventory.bean.InventoryViewBean;

import java.math.BigDecimal;
import java.util.List;

public interface IInventoryViewService {

    List<InventoryViewBean> querySlPdListByCondition(InventoryViewBean sqlBean);

    int countSlPdListByCondition(InventoryViewBean sqlBean);

    List<InventoryViewBean> queryOwnerPdListByCondition(InventoryViewBean sqlBean);

    int countOwnerPdListByCondition(InventoryViewBean sqlBean);

    BigDecimal getAvailableSumByCondition(InventoryViewBean sqlBean);

    BigDecimal getOnhandSumByCondition(InventoryViewBean sqlBean);

    List<InventoryViewBean> queryMaxAvQtyPdListByCondition(InventoryViewBean sqlBean);

}
