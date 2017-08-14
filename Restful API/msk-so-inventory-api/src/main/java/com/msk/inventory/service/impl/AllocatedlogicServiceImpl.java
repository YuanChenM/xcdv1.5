package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.IvmAllocatedLogicBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.service.IIvmAllocatedLogicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng_xu on 2016/9/2.
 */
@Service
public class AllocatedlogicServiceImpl extends BaseService implements IIvmAllocatedLogicService {

    @Transactional
    public void insertAllocatedLogic(IvmAllocatedLogicBean sqlBean) {
        this.save("insertOneAllocatedLogic", sqlBean);
    }

    @Transactional
    public void updateAllocatedLogicByCondition(IvmAllocatedLogicBean sqlBean) {
        this.modify("updateByCondition", sqlBean);
    }

    @Transactional
    public List<IvmAllocatedLogicBean> selectAllocatedLogicList(IvmAllocatedLogicBean sqlBean) {
        List<IvmAllocatedLogicBean> allocatedLogic = new ArrayList<IvmAllocatedLogicBean>();
        allocatedLogic = this.findList("queryListByCondition", sqlBean);
        return allocatedLogic;
    }

    @Transactional
    public int getCountAllocatedLogic(IvmAllocatedLogicBean sqlBean) {
        int pageCount = this.findOne("countByCondition", sqlBean);
        return pageCount;
    }

    @Transactional
    public void addAllocatedQty(IvmAllocatedLogicBean sqlBean) {
        this.modify("addAllocatedQty",sqlBean);
    }

    @Transactional
    public void cutAllocatedQty(IvmAllocatedLogicBean sqlBean) {
        this.modify("cutAllocatedQty",sqlBean);
    }

    @Transactional
    public void calAllocatedQtyForDispatch(IvmInventoryDetailBean sqlBean) {
        this.modify("calAllocatedQtyForDispatch",sqlBean);
    }

    @Transactional
    public void calAllocatedQtyForUndoDispatch(IvmInventoryDetailBean sqlBean) {
        this.modify("calAllocatedQtyForUndoDispatch",sqlBean);
    }

}
