package com.msk.inventory.service;

import com.msk.inventory.bean.IvmInventoryByProdBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.entity.IvmInventoryByProd;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/16.
 */
public interface IInventoryByProdService {

    void insertOneInventoryByProd(IvmInventoryByProdBean sqlBean);

    List<IvmInventoryByProdBean> selectInventoryByProdList(IvmInventoryByProdBean sqlBean);

    int selectInventoryByProdCount(IvmInventoryByProdBean sqlBean);

    void updateInventoryByProd(IvmInventoryByProdBean sqlBean);

    void touchAllIvProd(IvmInventoryByProdBean sqlBean);

    void touchIvProdByInbound(IvmInventoryDetailBean sqlBean);

    void touchIvProdByOutbound(IvmInventoryDetailBean sqlBean);

    void assignProdIvToSl(IvmInventoryByProdBean sqlBean);

    BigDecimal getSumQty(IvmInventoryByProdBean sqlBean);
}
