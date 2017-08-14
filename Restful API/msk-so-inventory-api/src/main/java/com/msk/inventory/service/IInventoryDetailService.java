package com.msk.inventory.service;

import com.msk.inventory.bean.IvmInventoryDetailBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangfan on 2016/8/15.
 */
public interface IInventoryDetailService {

    List<IvmInventoryDetailBean> selectInventoryDetailList(IvmInventoryDetailBean sqlBean);

    int selectInventoryDetailCount(IvmInventoryDetailBean sqlBean);

    IvmInventoryDetailBean getOneSortInventoryDetail(IvmInventoryDetailBean sqlBean);

    BigDecimal getSumQtyFromIvDetail(IvmInventoryDetailBean sqlBean);

    void insertOneInventoryDetail(IvmInventoryDetailBean sqlBean);

    void receiveByInbound(IvmInventoryDetailBean sqlBean);

    void receiveByInboundDetailAndQty(long inboundId, long inboundDetailId, BigDecimal qty);

    void putawayByInbound(IvmInventoryDetailBean sqlBean);

    void delIvDetailByInboundId(IvmInventoryDetailBean sqlBean);

    void updateIvDetailFlag(IvmInventoryDetailBean sqlBean);

    void updateIvFlagByCondition(IvmInventoryDetailBean condition,BigDecimal requestQty, String newFlag, String reason, String operator);

    void splitIvDetail(IvmInventoryDetailBean sqlBean);

    void outboundIvDetail(IvmInventoryDetailBean sqlBean);

    void outboundInventoryByQty(IvmInventoryDetailBean condition, BigDecimal requestQty, IvmInventoryDetailBean outboundInfo);

    void dispatchByOutbound(IvmInventoryDetailBean sqlBean);

    void undoDispatchIvDetail(IvmInventoryDetailBean sqlBean);

    void undoOutboundIvDetail(IvmInventoryDetailBean sqlBean);

    void deliverByOutbound(IvmInventoryDetailBean sqlBean);

    void deliverByOutboundDetailAndQty(long outboundId, String outboundNo, long outboundDetailId, BigDecimal qty, String operator);

}
