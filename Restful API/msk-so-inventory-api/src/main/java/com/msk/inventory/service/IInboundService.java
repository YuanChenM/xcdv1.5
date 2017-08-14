package com.msk.inventory.service;

import com.msk.inventory.bean.IvmInventoryDetailBean;

import java.util.List;

/**
 * Created by wangfan on 2016/8/17.
 */
public interface IInboundService {

    void doInboundForDetail(IvmInventoryDetailBean sqlBean) throws Exception;

    void receiveInboundData(List<IvmInventoryDetailBean> ivDetailList) throws Exception;
}
