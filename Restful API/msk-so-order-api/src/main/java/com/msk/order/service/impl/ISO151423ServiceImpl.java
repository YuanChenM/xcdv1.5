package com.msk.order.service.impl;

import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.exception.BusinessException;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151423RestParam;
import com.msk.order.bean.result.ISO151423OrdersRestResult;
import com.msk.order.bean.result.ISO151423RestResult;
import com.msk.order.service.ISO151423Service;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 卖家，买手，管家快捷信息查询接口实现类
 * Created by wang_jianzhou on 2016/8/22.
 */
@Service
public class ISO151423ServiceImpl implements ISO151423Service {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151423ServiceImpl.class);

    @Autowired
    private BaseJdbcImpl baseJdbc;

    @Override
    public ISO151423RestResult getSellerResultInfo(ISO151423RestParam param, String type) {

        ISO151423RestResult result = new ISO151423RestResult();
        List<ISO151423OrdersRestResult> orders = new ArrayList<ISO151423OrdersRestResult>();
        if (StringUtil.isEmpty(param.getSellers())) {
            throw new BusinessException("参数不正确");
        }
        orders = this.getOrdersBySellers(param, type);
        result = this.dealResult(result, orders);
        return result;
    }

    /**
     * 处理返回结果
     *
     * @param result 返回结果（初始化）
     * @param orders 数据库查询结果
     */
    public ISO151423RestResult dealResult(ISO151423RestResult result, List<ISO151423OrdersRestResult> orders) {
        logger.info("返回结果");
        // 平台状态：待付款
        String obligation = "";
        Integer obSum = NumberConstant.IntDef.INT_ZERO;
        // 平台状态：待发货
        String waitSend = "";
        Integer waitSum = NumberConstant.IntDef.INT_ZERO;
        // 平台状态：待收货
        String waitReceive = "";
        Integer sendSum = NumberConstant.IntDef.INT_ZERO;

        if (!CollectionUtils.isEmpty(orders)) {
            for (ISO151423OrdersRestResult order : orders) {
                if (order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.OBLIGATION
                        || order.getOrderStatusNum()== OrderCodeMasterDef.OrderStatus.NEW
                    ) {
                    obligation = String.valueOf(NumberConstant.IntDef.INT_ONE);
                    obSum = order.getStatusQty() + obSum;
                }
                  if(order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.PAYMENT ||
                  order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.WAIT_DISTRIBUTION ||
                  order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.CONFIRM ||
                  order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.WAIT_SEND ||
                  order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.DISTRIBUTION_FAIL){
                  waitSend = String.valueOf(NumberConstant.IntDef.INT_TWO);
                  waitSum = waitSum + order.getStatusQty();
                  }

                if (order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.ALL_SHIPMENT
                        ||order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.PARTIAL_RECEIPT
                        ||order.getOrderStatusNum() == OrderCodeMasterDef.OrderStatus.PARTIAL_SHIPMENT) {
                    waitReceive = String.valueOf(NumberConstant.IntDef.INT_THREE);
                    sendSum = order.getStatusQty() + sendSum;
                }
            }
        }
        List<ISO151423OrdersRestResult> newOrders = new ArrayList<ISO151423OrdersRestResult>();
        modifyResultInfo(obligation, obSum, newOrders);
        modifyResultInfo(waitSend, waitSum, newOrders);
        modifyResultInfo(waitReceive, sendSum, newOrders);
        result.setOrders(newOrders);
        return result;
    }

    /**
     * 修改订单集合，修改相应的平台状态和状态数量
     *
     * @param statusStr 平台状态
     * @param statusQty 各种状态的数量
     * @param orders 重新初始化的订单集合
     */
    public void modifyResultInfo(String statusStr, Integer statusQty, List<ISO151423OrdersRestResult> orders) {

        if (!StringUtil.isEmpty(statusStr)) {
            ISO151423OrdersRestResult newResult = new ISO151423OrdersRestResult();
            //statusStr = statusStr.substring(NumberConstant.IntDef.INT_ZERO, statusStr.length() - NumberConstant.IntDef.INT_ONE);
            newResult.setOrderStatus(statusStr);
            newResult.setStatusQty(statusQty);
            orders.add(newResult);
        }
    }

    /**
     * 对象转换
     *
     * @param param 接口入参
     */
    public List<ISO151423OrdersRestResult> getOrdersBySellers(ISO151423RestParam param, String type) {
        logger.info("查询开始");
        List<ISO151423OrdersRestResult> orders = new ArrayList<ISO151423OrdersRestResult>();
        List<String> parameters = new ArrayList<String>();
        //取Sql
        Map<String,String> sqlMap = this.getSql();
        String sql = "";
        if (type.equals("seller")) {
            parameters.add(param.getSellers());
            String sqlCondition = " AND sosd.SUPPLIER_CODE = ?" + NumberConstant.IntDef.INT_ZERO;
            sql = sqlMap.get("ISO151423.getSellerOrdersFront") + sqlMap.get("ISO151423.getSellerOrdersMiddle") +sqlCondition + sqlMap.get("ISO151423.getSellerOrdersBottom");
        }

        if (type.equals("buyer")) {
            parameters.add(param.getSellers());
            String sqlFrontCondition = " AND sso.SELLER_CODE = ?"  + NumberConstant.IntDef.INT_ZERO +" ";
            String sqlBottomCondition = " AND soo.BUYER_CODE = ?" + NumberConstant.IntDef.INT_ZERO +" ";
            sql = sqlMap.get("ISO151423.getBuyerOrdersFront") + sqlMap.get("ISO151423.getBuyerOrdersMiddleFront") + sqlFrontCondition + sqlMap.get("ISO151423.getBuyerOrdersMiddleBottom")
                    + sqlBottomCondition + sqlMap.get("ISO151423.getBuyerOrdersBottom");
        }

        if (type.equals("housekeeping")) {
            parameters.add(param.getSellers());
            String sqlCondition = " AND so.SA_ID = ?" + NumberConstant.IntDef.INT_ZERO + " GROUP BY so.ORDER_STATUS" ;
            sql = sqlMap.get("ISO151423.getHousekeepingOrders") + sqlCondition;
        }

        // 查询数据库
        List<Map<String, Object>> ordersList = getResult(sql,parameters);
        for (Map<String, Object> map : ordersList) {
            ISO151423OrdersRestResult bean = new ISO151423OrdersRestResult();
            try {
                BeanUtils.populate(bean, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            orders.add(bean);
        }
        logger.info("查询结束");
        return orders;
    }

    /**
     * 查询数据库
     *
     * @param sql sql语句
     */
    public List<Map<String, Object>> getResult(String sql,List<String> parameters) {
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql,parameters,null,true);
        List rows = mapList;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Object obj : rows) {
            Map<String, Object> row = (Map<String, Object>) obj;
            resultList.add(row);
        }
        return resultList;
    }

    /**
     * 取XML中sql
     *
     * @return Map
     */
    @Transactional
    public Map<String,String> getSql(){
        //SqlIds
        List<String> sqlIds = new ArrayList<String>();
        sqlIds.add("ISO151423.getSellerOrdersFront");
        sqlIds.add("ISO151423.getSellerOrdersMiddle");
        sqlIds.add("ISO151423.getSellerOrdersBottom");
        sqlIds.add("ISO151423.getBuyerOrdersFront");
        sqlIds.add("ISO151423.getBuyerOrdersMiddleFront");
        sqlIds.add("ISO151423.getBuyerOrdersMiddleBottom");
        sqlIds.add("ISO151423.getBuyerOrdersBottom");
        sqlIds.add("ISO151423.getHousekeepingOrders");
        //调用共通方法取Sql
        Map<String,String> map = SqlUtil.getSqlMapBySqlIds(sqlIds);
        return map;
    }

}
