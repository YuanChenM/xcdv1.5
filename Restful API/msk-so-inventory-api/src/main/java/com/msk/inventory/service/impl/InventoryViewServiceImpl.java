package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.InventoryViewBean;
import com.msk.inventory.service.IInventoryViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by duan_kai on 2016/9/7.
 */
@Service
public class InventoryViewServiceImpl extends BaseService implements IInventoryViewService {

    @Transactional
    public List<InventoryViewBean> querySlPdListByCondition(InventoryViewBean sqlBean) {
        return this.findList("querySlPdListByCondition", sqlBean);
    }

    @Transactional
    public int countSlPdListByCondition(InventoryViewBean sqlBean) {
        return this.getCount("countSlPdListByCondition", sqlBean);
    }

    @Transactional
    public List<InventoryViewBean> queryOwnerPdListByCondition(InventoryViewBean sqlBean) {
        return this.findList("queryOwnerPdListByCondition", sqlBean);
    }

    @Transactional
    public int countOwnerPdListByCondition(InventoryViewBean sqlBean) {
        return this.getCount("countOwnerPdListByCondition", sqlBean);
    }

    @Override
    public BigDecimal getAvailableSumByCondition(InventoryViewBean sqlBean) {
        return this.findOne("getAvailableSumByCondition", sqlBean);
    }

    @Override
    public BigDecimal getOnhandSumByCondition(InventoryViewBean sqlBean) {
        return this.findOne("getOnhandSumByCondition", sqlBean);
    }

    @Transactional
    public List<InventoryViewBean> queryMaxAvQtyPdListByCondition(InventoryViewBean sqlBean) {
        return this.findList("queryMaxAvQtyPdListByCondition", sqlBean);
    }
}
