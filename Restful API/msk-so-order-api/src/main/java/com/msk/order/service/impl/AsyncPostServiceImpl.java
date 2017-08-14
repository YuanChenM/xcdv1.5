package com.msk.order.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.base.AsyncPostCallBack;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.utils.RestClientUtils;
import com.msk.order.bean.param.*;
import com.msk.order.bean.result.*;
import com.msk.order.bean.result.Void;
import com.msk.order.entity.SoOrder;
import com.msk.order.service.AsyncPostService;
import com.msk.order.service.ISO151410Service;
import com.msk.order.service.ISO151413Service;
import com.msk.order.service.ISO151414Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liutao on 2016/9/13.
 */
@Service
public class AsyncPostServiceImpl implements AsyncPostService {

    private  Logger logger = LoggerFactory.getLogger(AsyncPostServiceImpl.class);

    @Autowired
    private ISO151414Service iso151414Service;

    @Autowired
    private ISO151410Service iso151410Service;

    @Autowired
    private ISO151413Service iso151413Service;
    /**
     * 占用供应商可用库存
     *
     * @param stockParam
     * @param soOrder
     */
    @Override
    public void occupyStockSpInfo(ISO151414OccupyStockParam stockParam, final SoOrder soOrder) {
        logger.info("占用供应商可用库存开始");
        final RestRequest<ISO151414OccupyStockParam> request = new RestRequest<>();
        request.setParam(stockParam);
//        String url = "";//   /api/v1/so/spInv/orderOccupy
        String url = SystemServerManager.SoInventoryServerManager.getAllocateOwnerInventory();
        TypeReference<RestResponse<ISO151414OccupyStockResult>> tTypeReference = new TypeReference<RestResponse<ISO151414OccupyStockResult>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<ISO151414OccupyStockResult>>() {
            @Override
            public void callBack(RestResponse<ISO151414OccupyStockResult> result) {
                SoOrder order = new SoOrder();
                BeanUtils.copyProperties(soOrder, order);
                if (result.getStatus().equals("S")) {
                    if (soOrder.getPaymentType() == NumberConstant.IntDef.INT_ONE) {
                        order.setOrderStatus(OrderCodeMasterDef.OrderStatus.OBLIGATION);
                    } else {
                        order.setOrderStatus(OrderCodeMasterDef.OrderStatus.CONFIRM);
                    }
                } else {
                    //回调资金池信息取消
                    iso151414Service.cancelCpTransaction(order);
                    order.setOrderStatus(OrderCodeMasterDef.OrderStatus.CANCEL);
                }
                iso151414Service.modifyOrderStatusByOrderId(order);
            }
        }, tTypeReference);
        logger.info("占用供应商可用库存结束");
    }

    /**
     * 占用卖家可用库存
     *
     * @param stockParam
     * @param soOrder
     */
    @Override
    public void occupyStockSlInfo(ISO151414OccupyStockParam stockParam, final SoOrder soOrder) {
        logger.info("占用卖家可用库存开始");
        final RestRequest<ISO151414OccupyStockParam> request = new RestRequest<>();
        request.setLoginId(soOrder.getCrtId());
        request.setParam(stockParam);
//        String url = "";//   /api/v1/so/slInv/orderOccupy
        String url = SystemServerManager.SoInventoryServerManager.getAllocateSlInventory();
        TypeReference<RestResponse<ISO151414OccupyStockResult>> tTypeReference = new TypeReference<RestResponse<ISO151414OccupyStockResult>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<ISO151414OccupyStockResult>>() {
            @Override
            public void callBack(RestResponse<ISO151414OccupyStockResult> result) {
                SoOrder order = new SoOrder();
                BeanUtils.copyProperties(soOrder, order);
                if (result.getStatus().equals("F")) {
                    //回调资金池信息取消
                    order.setOrderStatus(OrderCodeMasterDef.OrderStatus.CANCEL);
                    iso151414Service.cancelCpTransaction(order);
                } else {
                    if (soOrder.getPaymentType() == NumberConstant.IntDef.INT_ONE) {
                        order.setOrderStatus(OrderCodeMasterDef.OrderStatus.OBLIGATION);
                    } else {
                        order.setOrderStatus(OrderCodeMasterDef.OrderStatus.WAIT_DISTRIBUTION);
                    }
                }
                iso151414Service.modifyOrderStatusByOrderId(order);
            }
        }, tTypeReference);
        logger.info("占用卖家可用库存结束");
    }

    @Override
    public void sendCpTransaction(ISO151414CpTransactionParam cpTransactionParam) {
        logger.info("异步调用资金池接口开始");
        RestRequest<ISO151414CpTransactionParam> request = new RestRequest<ISO151414CpTransactionParam>();
        request.setLoginId(cpTransactionParam.getCrtId());
        request.setParam(cpTransactionParam);
        String url = SystemServerManager.SoCashPoolingServerManage.getTransaction();// /cp/transaction
        TypeReference<RestResponse<com.msk.order.bean.result.Void>> tTypeReference = new TypeReference<RestResponse<Void>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<Void>>() {
            @Override
            public void callBack(RestResponse<Void> resultClass) {

            }
        }, tTypeReference);
        logger.info("异步调用资金池接口结束");
    }



    /**
     *
     * 退货时调用资金池交易明细
     *
     * @param param
     */
    public  void sendCpTransactionBy151408Return(ISO151408RestCpTransactionParam param) {
        logger.info("退货时调用资金池交易明细开始");
        RestRequest<ISO151408RestCpTransactionParam> request = new RestRequest<ISO151408RestCpTransactionParam>();
        request.setAuth("MSK00001");
        request.setLoginId(param.getCrtId());
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoCashPoolingServerManage.getTransaction();
        TypeReference<RestResponse<Void>> tTypeReference = new TypeReference<RestResponse<Void>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<Void>>() {
            @Override
            public void callBack(RestResponse<Void> resultClass) {
            }
        }, tTypeReference);
        logger.info("退货时调用资金池交易明细结束");
    }


    /**
     * 整单取消时调用资金池交易明细
     *
     * @param param
     */
    public  void sendCpTransactionBy151410Cancel(ISO151410RestCpTransactionParam param) {
        logger.info("整单取消时调用资金池交易明细开始");
        RestRequest<ISO151410RestCpTransactionParam> request = new RestRequest<ISO151410RestCpTransactionParam>();
        request.setAuth("MSK00001");
        request.setLoginId(param.getCrtId());
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoCashPoolingServerManage.getTransaction();
        TypeReference<RestResponse<Void>> tTypeReference = new TypeReference<RestResponse<Void>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<Void>>() {
            @Override
            public void callBack(RestResponse<Void> resultClass) {
            }
        }, tTypeReference);
        logger.info("整单取消时调用资金池交易明细结束");
    }




    /**
     * 占用供应商可用库存减少 整单取消使用
     *
     * @param spInvOccupyParam
     */
    public  void SpOccupyDecreaseBy151410Cancel(ISO151410IRestSpInvOccupyDecreaseParam spInvOccupyParam) {
        final RestRequest<ISO151410IRestSpInvOccupyDecreaseParam> request = new RestRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId(spInvOccupyParam.getUpdId());
        request.setSiteCode("1");
        request.setParam(spInvOccupyParam);
        String url = SystemServerManager.SoInventoryServerManager.getUndoAllocateOwnerInventory();
      /*  String url ="http://192.168.1.101:8888/msk-inventory/api/inv/spOrderOccupy/decrease";*/
        TypeReference<RestResponse<ISO151410RestInvDecreaseResult>> tTypeReference = new TypeReference<RestResponse<ISO151410RestInvDecreaseResult>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<ISO151410RestInvDecreaseResult>>() {
            @Override
            public void callBack(RestResponse<ISO151410RestInvDecreaseResult> result) {
                if (result.getStatus().equals(SystemConstant.RsStatus.SUCCESS)) {
                    String orderId = request.getParam().getOrderId();
                    iso151410Service.setSubOrderCanceledStatusByOrderId(orderId);
                }
            }
        }, tTypeReference);
    }


    /**
     * 占用卖家可用库存减少  整单取消使用
     *
     * @param slInvOccupyParam
     */
    public  void SlOccupyDecreaseBy151410Cancel(ISO151410IRestSlInvOccupyDecreaseParam slInvOccupyParam) {
        final RestRequest<ISO151410IRestSlInvOccupyDecreaseParam> request = new RestRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId(slInvOccupyParam.getUpdId());
        request.setSiteCode("1");
        request.setParam(slInvOccupyParam);
        String url = SystemServerManager.SoInventoryServerManager.getUndoAllocateSlInventory();
        TypeReference<RestResponse<ISO151410RestInvDecreaseResult>> tTypeReference = new TypeReference<RestResponse<ISO151410RestInvDecreaseResult>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<ISO151410RestInvDecreaseResult>>() {
            @Override
            public void callBack(RestResponse<ISO151410RestInvDecreaseResult> result) {
                if (result.getStatus().equals(SystemConstant.RsStatus.SUCCESS)) {
                    String orderId = request.getParam().getOrderId();
                    iso151410Service.setSubOrderCanceledStatusByOrderId(orderId);
                }
            }
        }, tTypeReference);
    }


    /**
     * 调用库存入库
     *
     * @param paramList
     */
    public void returnInboundBy151422Return(ISO151422InvRestParamList paramList){
        final RestRequest<ISO151422InvRestParamList> request = new RestRequest<ISO151422InvRestParamList>();
        request.setAuth("WMS00001");
        request.setSiteCode("903");
        request.setLoginId(paramList.getUpdId());
        request.setParam(paramList);
        String url = SystemServerManager.SoInventoryServerManager.getInboundInventory();
        TypeReference<RestResponse<ISO151422InvRestParamList>> tTypeReference = new TypeReference<RestResponse<ISO151422InvRestParamList>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<ISO151422InvRestParamList>>() {
            @Override
            public void callBack(RestResponse<ISO151422InvRestParamList> response){
            }
        },tTypeReference);
    }
    /**
     * 已付款接口调用资金池
     *
     * @param param
     */
    @Override
    public void sendCpRunning(ISO151413RestCpRunningParam param) {
        logger.info("已付款接口调用资金池用开始");
        RestRequest<ISO151413RestCpRunningParam> request = new RestRequest<ISO151413RestCpRunningParam>();
        request.setAuth("MSK00001");
        request.setLoginId(param.getCrtId());
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoCashPoolingServerManage.getRunning();

        TypeReference<RestResponse<ISO151413RestCpResult>> tTypeReference = new TypeReference<RestResponse<ISO151413RestCpResult>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<ISO151413RestCpResult>>() {
            @Override
            public void callBack(RestResponse<ISO151413RestCpResult> result) {
                if (result.getStatus().equals(SystemConstant.RsStatus.SUCCESS)) {
                    // 支付成功回调
                    iso151413Service.modifyStatus(result.getResult().getTransCode());
                }
            }
        }, tTypeReference);
        logger.info("已付款接口调用资金池结束");
    }

    /**
     * 拒收接口调用资金池
     *
     * @param param
     */
    @Override
    public void sendCpRunning(ISO151802RestCpRunningParam param) {
        logger.info("拒收调用资金池接口开始");
        RestRequest<ISO151802RestCpRunningParam> request = new RestRequest<ISO151802RestCpRunningParam>();
        request.setAuth("MSK00001");
        request.setLoginId(param.getCrtId());
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoCashPoolingServerManage.getRunning();
        TypeReference<RestResponse<Void>> tTypeReference = new TypeReference<RestResponse<Void>>() {
        };
        RestClientUtils.asyncPost(url, request, new AsyncPostCallBack<RestResponse<Void>>() {
            @Override
            public void callBack(RestResponse<Void> resultClass) {
            }
        }, tTypeReference);
        logger.info("拒收调用资金池接口结束");
    }
    /**
     * 收货接口调用支付明细
     * @param param
     */
    @Override
    public void sendCpRunning(ISO151415RestCpRunningParam param){
        logger.info("收货接口调用支付明细开始");
        RestRequest<ISO151415RestCpRunningParam> request = new RestRequest<ISO151415RestCpRunningParam>();
        request.setAuth("MSK00001");
        request.setSiteCode("101");
        request.setLoginId(param.getUpdId());
        request.setParam(param);
        //调用资金池支付明细接口
        String url = SystemServerManager.SoCashPoolingServerManage.getRunning();
        TypeReference<RestResponse<ISO151415BaseCpResult>> tTypeReference = new TypeReference<RestResponse<ISO151415BaseCpResult>>() {
        };
        RestClientUtils.asyncPost(url,request, new AsyncPostCallBack<RestResponse<ISO151415BaseCpResult>>() {
            @Override
            public void callBack(RestResponse<ISO151415BaseCpResult> resultClass) {

            }
        }, tTypeReference);
        logger.info("收货接口调用支付明细结束");
    }
    /**
     * 收货接口调用资金池的记录卖家计费项
     * @param param
     */
    @Override
    public void sendCpSelCharging(ISO151415RestParam param){
        logger.info("收货接口调用记录卖家计费项开始");
        RestRequest<ISO151415RestParam> request = new RestRequest<ISO151415RestParam>();
        request.setAuth("MSK00001");
        request.setSiteCode("101");
        request.setLoginId(param.getUpdId());
        request.setParam(param);
        //调用资金池提供接口
        String url = SystemServerManager.SoCashPoolingServerManage.getSellerCharging();
        TypeReference<RestResponse<ISO151415BaseCpParam>> tTypeReference = new TypeReference<RestResponse<ISO151415BaseCpParam>>() {
        };
        RestClientUtils.asyncPost(url,request, new AsyncPostCallBack<RestResponse<ISO151415BaseCpParam>>() {
            @Override
            public void callBack(RestResponse<ISO151415BaseCpParam> resultClass) {

            }
        },tTypeReference);
        logger.info("收货接口调用记录卖家计费项结束");

    }
}
