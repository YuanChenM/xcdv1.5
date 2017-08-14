package com.msk.order.service;

import com.msk.order.bean.param.ISO151416OrderSearchParam;
import com.msk.order.bean.param.ISO151416SoldProductParam;
import com.msk.order.bean.result.ISO151416OrderSearchResult;
import com.msk.order.bean.result.ISO151416SoldProductResult;

/**
 * Created by liu_tao2 on 2016/8/17.
 */
public interface ISO151416Service {

    /**
     * 订单列表查询接口
     * 买家订单列表查询接口
     * 买手囤货订单列表查询接口
     *
     * @param param
     * @return
     */
    ISO151416OrderSearchResult findOrderDetailList(ISO151416OrderSearchParam param) throws Exception;

    /**
     * 订单明细查询接口
     * 买家订单明细查询接口
     * 买手囤货订单明细查询接口
     *
     * @param param
     * @return
     */
    ISO151416OrderSearchResult findOrderDetail(ISO151416OrderSearchParam param) throws Exception;

    /**
     * 买手销售订单列表查询接口
     * 卖家订单列表查询接口
     *
     * @param param
     * @return
     * @throws Exception
     */
    ISO151416OrderSearchResult findOrderSaleDetailList(ISO151416OrderSearchParam param) throws Exception;

    /**
     * 买手销售订单明细查询接口
     * 卖家订单明细接口
     *
     * @param param
     * @return
     * @throws Exception
     */
    ISO151416OrderSearchResult findOrderSaleDetail(ISO151416OrderSearchParam param) throws Exception;

    void getBuyerIdByBuyerCode(ISO151416OrderSearchParam param);

    /**
     * 卖家已卖出商品查询接口
     * @param param
     * @return
     */
    ISO151416SoldProductResult searchSoldProductList(ISO151416SoldProductParam param) throws Exception;
}
