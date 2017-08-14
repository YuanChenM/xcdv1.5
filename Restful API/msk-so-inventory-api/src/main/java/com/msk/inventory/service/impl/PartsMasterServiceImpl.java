package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.entity.IvmPartsMaster;
import org.springframework.stereotype.Service;

import com.msk.inventory.bean.IvmPartsMasterBean;
import com.msk.inventory.service.IPartsMasterService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/8/12.
 */
@Service
public class PartsMasterServiceImpl extends BaseService implements IPartsMasterService {
    @Transactional
    public void insertOneParts(IvmPartsMasterBean sqlBean) {
        this.save("insertOnePart", sqlBean);
    }

    @Transactional
    public List<IvmPartsMaster> selectPartsMasterList(IvmPartsMasterBean sqlBean) {
        List<IvmPartsMaster> partsMasterList = new ArrayList<IvmPartsMaster>();
        partsMasterList = this.findList("selectParts", sqlBean);
        return partsMasterList;
    }

    @Transactional
    public int selectPartsMasterCount(IvmPartsMasterBean sqlBean) {
        int pageCount = this.getCount("countParts", sqlBean);
        return pageCount;
    }

    @Transactional
    public void updatePartsMaster(IvmPartsMasterBean sqlBean) {
        this.modify("updatePartInfo", sqlBean);
    }

    @Transactional
    public boolean isExistPartsMaster(IvmPartsMasterBean sqlBean) {
        boolean isExistPartsMaster = false;
        int countNums = this.getCount("countParts", sqlBean);
        if (countNums > 0) {
            isExistPartsMaster = true;
        }
        return isExistPartsMaster;
    }
}
