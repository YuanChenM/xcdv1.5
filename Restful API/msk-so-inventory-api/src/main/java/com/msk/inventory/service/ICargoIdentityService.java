package com.msk.inventory.service;

import com.msk.inventory.bean.IvmCargoIdentityBean;
import com.msk.inventory.entity.IvmCargoIdentity;

import java.util.Date;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/15.
 */
public interface ICargoIdentityService {

    void insertOneCargoIdentity(IvmCargoIdentityBean sqlBean);

    List<IvmCargoIdentity> selectCargoIdentityList(IvmCargoIdentityBean sqlBean);

    int selectCargoIdentityCount(IvmCargoIdentityBean sqlBean);

    void updateCargoIdentity(IvmCargoIdentityBean sqlBean);

    String getLoadNo(Date time);
}
