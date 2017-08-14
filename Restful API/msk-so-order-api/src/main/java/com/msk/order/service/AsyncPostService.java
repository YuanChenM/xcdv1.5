package com.msk.order.service;

import com.msk.order.bean.param.*;
import com.msk.order.entity.SoOrder;

/**
 * Created by liutao on 2016/9/13.
 */
public interface AsyncPostService {
    /**
     * 占用供应商可用库存
     *
     * @param stockParam
     * @param soOrder
     */
    void occupyStockSpInfo(ISO151414OccupyStockParam stockParam, SoOrder soOrder);

    /**
     * 占用卖家可用库存
     *
     * @param stockParam
     * @param soOrder
     */
    void occupyStockSlInfo(ISO151414OccupyStockParam stockParam, SoOrder soOrder);

    /**
     * @param cpTransactionParam
     */
    void sendCpTransaction(ISO151414CpTransactionParam cpTransactionParam);

    /**
     * @param param
     */
    void sendCpTransactionBy151408Return(ISO151408RestCpTransactionParam param);

    /**
     * @param param
     */
    void sendCpTransactionBy151410Cancel(ISO151410RestCpTransactionParam param);

    /**
     * @param spInvOccupyParam
     */
    void SpOccupyDecreaseBy151410Cancel(ISO151410IRestSpInvOccupyDecreaseParam spInvOccupyParam);

    /**
     * @param slInvOccupyParam
     */
    void SlOccupyDecreaseBy151410Cancel(ISO151410IRestSlInvOccupyDecreaseParam slInvOccupyParam);

    /**
     * 已付款接口调用资金池
     *
     * @param param
     */
    void sendCpRunning(ISO151413RestCpRunningParam param);

    /**
     * 拒收接口调用资金池
     *
     * @param param
     */
    void sendCpRunning(ISO151802RestCpRunningParam param);

    /**
     * 收货接口调用资金池的支付明细
     * @param param
     */
    void sendCpRunning(ISO151415RestCpRunningParam param);

    /**
     * 收货接口调用资金池的记录卖家计费项
     * @param param
     */
    void sendCpSelCharging(ISO151415RestParam param);

    /**
     * 退货入库调用库存模块产品入库
     * @param paramList
     */
    void returnInboundBy151422Return(ISO151422InvRestParamList paramList);

}
