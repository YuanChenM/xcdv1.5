package com.msk.inventory.service;

import com.msk.inventory.bean.IvmInfoDefBean;
import com.msk.inventory.entity.IvmInfoDef;

import java.util.List;

/**
 * Created by duan_kai on 2016/8/31.
 */
public interface IInfoDefService {

    void insertOne(IvmInfoDefBean sqlBean);

    List<IvmInfoDefBean> selectListByCondition(IvmInfoDefBean sqlBean);

    int countByCondition(IvmInfoDefBean sqlBean);

    void updateOneInfoDef(IvmInfoDefBean sqlBean);

    boolean isExist(IvmInfoDefBean sqlBean);

    void synInfoDefList(List<IvmInfoDefBean> sqlBeanList);
}
