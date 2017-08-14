package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.bean.IvmSlOnhandLogicBean;
import com.msk.inventory.service.ISlOnhandLogicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/7.
 */
@Service
public class SlOnhandLogicServiceImpl extends BaseService implements ISlOnhandLogicService {
    @Transactional
    public void insertSlOnhandLogic(IvmSlOnhandLogicBean sqlBean) {
        this.save("insertOneSlOnhandLogic", sqlBean);
    }

    @Transactional
    public void updateSlOnhandLogicByCondition(IvmSlOnhandLogicBean sqlBean) {
        this.modify("editOneSlOnhandLogic", sqlBean);
    }

    @Transactional
    public List<IvmSlOnhandLogicBean> getSlOnhandLogicList(IvmSlOnhandLogicBean sqlBean) {
        List<IvmSlOnhandLogicBean> list = this.findList("queryListByCondition", sqlBean);
        return list;
    }

    @Transactional
    public int getCountSlOnhandLogic(IvmSlOnhandLogicBean sqlBean) {
        int pageCount = this.findOne("countByCondition", sqlBean);
        return pageCount;
    }

    @Transactional
    public void cutOnhandQty(IvmSlOnhandLogicBean sqlBean) {
        this.modify("cutOnhandQty",sqlBean);
    }

    @Transactional
    public void addOnhandQty(IvmSlOnhandLogicBean sqlBean) {
        this.modify("addOnhandQty",sqlBean);
    }

    @Transactional
    public void calOnhandQtyForDispatch(IvmInventoryDetailBean sqlBean) {
        this.modify("calOnhandQtyForDispatch",sqlBean);
    }

    @Transactional
    public void calOnhandQtyForUndoDispatch(IvmInventoryDetailBean sqlBean) {
        this.modify("calOnhandQtyForUndoDispatch",sqlBean);
    }
}
