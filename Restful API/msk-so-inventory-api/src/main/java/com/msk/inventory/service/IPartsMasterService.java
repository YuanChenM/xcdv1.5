package com.msk.inventory.service;

import com.msk.inventory.bean.IvmPartsMasterBean;
import com.msk.inventory.entity.IvmPartsMaster;

import java.util.List;

/**
 * Created by wangfan on 16/8/15.
 */
public interface IPartsMasterService {

    void insertOneParts(IvmPartsMasterBean sqlBean);

    List<IvmPartsMaster> selectPartsMasterList(IvmPartsMasterBean sqlBean);

    int selectPartsMasterCount(IvmPartsMasterBean sqlBean);

    void updatePartsMaster(IvmPartsMasterBean sqlBean);

    boolean isExistPartsMaster(IvmPartsMasterBean sqlBean);
}
