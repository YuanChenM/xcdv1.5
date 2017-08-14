package com.msk.inventory.service;

import com.msk.inventory.bean.IvmInventoryDetailBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfan on 2016/8/18.
 */
public interface IOutboundService {

    void doOutboundForDetail(IvmInventoryDetailBean condition, BigDecimal outQty, IvmInventoryDetailBean outboundInfo) throws Exception;

    void pickInboundData(List<IvmInventoryDetailBean> ivDetailList, String outboundBy, Date outboundTime) throws Exception;

}
