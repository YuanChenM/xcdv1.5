package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.IvmInventoryByProdBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.entity.IvmInventoryByProd;
import com.msk.inventory.service.IInventoryByProdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/16.
 */
@Service
public class InventoryByProdServiceImpl extends BaseService implements IInventoryByProdService{

    @Transactional
    public void insertOneInventoryByProd(IvmInventoryByProdBean sqlBean) {
        this.save("insertInventoryByProd",sqlBean);
    }

    @Transactional
    public List<IvmInventoryByProdBean> selectInventoryByProdList(IvmInventoryByProdBean sqlBean) {
        List<IvmInventoryByProdBean> inventoryByProdBeanList = new ArrayList<IvmInventoryByProdBean>();
        inventoryByProdBeanList = this.findList("queryInventoryByProdList",sqlBean);
        return inventoryByProdBeanList;
    }

    @Transactional
    public int selectInventoryByProdCount(IvmInventoryByProdBean sqlBean) {
        int pageCount = this.getCount("countInventoryByProd",sqlBean);
        return pageCount;
    }

    @Transactional
    public void updateInventoryByProd(IvmInventoryByProdBean sqlBean) {
        this.modify("updateInventoryByProd",sqlBean);
    }

    @Transactional
    public void touchAllIvProd(IvmInventoryByProdBean sqlBean) {
        this.modify("touchAllIvProd",sqlBean);
    }

    @Transactional
    public void touchIvProdByInbound(IvmInventoryDetailBean sqlBean) {
        this.modify("touchIvProdByInbound",sqlBean);
    }

    @Transactional
    public void touchIvProdByOutbound(IvmInventoryDetailBean sqlBean) {
        this.modify("touchIvProdByOutbound",sqlBean);
    }

    @Transactional
    public void assignProdIvToSl(IvmInventoryByProdBean sqlBean) {
        this.modify("assignProdIvToSl",sqlBean);
    }

    @Transactional
    public BigDecimal getSumQty(IvmInventoryByProdBean sqlBean) {
        return this.findOne("getSumQty",sqlBean);
    }
}
