package com.msk.order.service;

import com.msk.order.bean.param.ISO151414BaseOrderParam;
import com.msk.order.bean.param.ISO151414OccupyStockParam;
import com.msk.order.bean.result.ISO151414OrderResult;
import com.msk.order.entity.SoOrder;

/**
 * Created by liu_tao2 on 2016/8/11.
 */
public interface ISO151414Service {

    /**
     * 根据系统编码得到订单来源和销售渠道
     *
     * @param siteCode
     * @param param
     */
    void dealOrderSourceAndSalePlatform(String siteCode, ISO151414BaseOrderParam param);

    /**
     * 根据买家ID得到买家基本信息
     *
     * @param param
     */
    void getBuyerInfoByBuyerId(ISO151414BaseOrderParam param);

    /**
     * 创建分销订单
     *
     * @param param
     * @return
     */
    ISO151414OrderResult createDistributionOrder(ISO151414BaseOrderParam param);

    /**
     * 创建买手囤货订单
     *
     * @param param
     * @return
     */
    ISO151414OrderResult createBuyerOrder(ISO151414BaseOrderParam param);

    /**
     * 创建第三方订单
     *
     * @param param
     * @return
     */
    ISO151414OrderResult createThirdPartyOrder(ISO151414BaseOrderParam param);

    /**
     * 创建第三方买手囤货订单
     *
     * @param param
     * @return
     */
    ISO151414OrderResult createThirdBuyerOrder(ISO151414BaseOrderParam param);

    /**
     * 占用卖家可用库存
     *
     * @param stockParam
     */
    void occupyStockSlInfo(ISO151414OccupyStockParam stockParam,SoOrder soOrder);

    /**
     * 更新订单主表和分批订单表状态
     *
     * @param soOrder
     */
    void modifyOrderStatusByOrderId(SoOrder soOrder);

    /**
     * 占用库存失败 取消资金池信息
     *
     * @param soOrder
     */
    void cancelCpTransaction(SoOrder soOrder);
}
