package com.msk.order.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msk.common.constant.NumberConstant;
import com.msk.order.bean.param.ISO151427DeliverParam;
import com.msk.order.bean.param.ISO151427RestParam;
import com.msk.order.bean.param.ISO151427SoReturnParam;
import com.msk.order.bean.result.ISO151427RestResult;
import com.msk.order.bean.result.ISO151427SettlementDetailResult;
import com.msk.order.bean.result.ISO151427SoProductResult;
import com.msk.order.service.ISO151427Service;


/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
@Service
public class ISO151427ServiceImpl  implements ISO151427Service {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISO151427ServiceImpl.class);
    @Autowired
    private BaseJdbcImpl baseJDBC;

    @Override
    public ISO151427RestResult findSettlementDetail(ISO151427RestParam iso151427RestParam) {
        logger.debug("查询结算详情信息");
        ISO151427RestResult result = new ISO151427RestResult();
        List<ISO151427SettlementDetailResult> resultList = new ArrayList<ISO151427SettlementDetailResult>();
        String deliverCodeParam = "";
        String returnCodeParam = "";
        List paramList = new ArrayList();

        if (iso151427RestParam.getSettlementStatus().equals("1")){//查询交易成功
            if (iso151427RestParam.getDeliverList() != null && iso151427RestParam.getDeliverList().size() != NumberConstant.IntDef.INT_ZERO){
                for (int i=0;i< iso151427RestParam.getDeliverList().size();i++){
                    deliverCodeParam = deliverCodeParam + ",?" + i;
                }
            }
            returnCodeParam = "''";
            for (ISO151427DeliverParam iso151427DeliverParam : iso151427RestParam.getDeliverList()){
                paramList.add(iso151427DeliverParam.getDeliverCode());
            }
        }else if (iso151427RestParam.getSettlementStatus().equals("2")){//查询退款成功
            if (iso151427RestParam.getReturnList() != null && iso151427RestParam.getReturnList().size() != NumberConstant.IntDef.INT_ZERO){
                for (int i=0;i< iso151427RestParam.getReturnList().size();i++){
                    returnCodeParam = returnCodeParam + ",?" + i;
                }
            }
            deliverCodeParam = "''";
            for (ISO151427SoReturnParam iso151427SoReturnParam : iso151427RestParam.getReturnList()){
                paramList.add(iso151427SoReturnParam.getReturnCode());
            }
        }else if (iso151427RestParam.getSettlementStatus().equals("0")){//查询全部
            if (iso151427RestParam.getDeliverList() != null && iso151427RestParam.getDeliverList().size() != NumberConstant.IntDef.INT_ZERO){
                for (int i=0;i< iso151427RestParam.getDeliverList().size();i++){
                    deliverCodeParam = deliverCodeParam + ",?" + i;
                }
            }
            if (iso151427RestParam.getReturnList() != null && iso151427RestParam.getReturnList().size() != NumberConstant.IntDef.INT_ZERO){
                for (int i=0;i< iso151427RestParam.getReturnList().size();i++){
                    returnCodeParam = returnCodeParam + ",?" + (i + iso151427RestParam.getDeliverList().size());
                }
            }
            for (ISO151427DeliverParam iso151427DeliverParam : iso151427RestParam.getDeliverList()){
                paramList.add(iso151427DeliverParam.getDeliverCode());
            }
            for (ISO151427SoReturnParam iso151427SoReturnParam : iso151427RestParam.getReturnList()){
                paramList.add(iso151427SoReturnParam.getReturnCode());
            }
        }
        if(!deliverCodeParam.equals("") && !deliverCodeParam.equals("''")){
            deliverCodeParam = deliverCodeParam.substring(1);
        }
        if(!returnCodeParam.equals("") && !returnCodeParam.equals("''")){
            returnCodeParam = returnCodeParam.substring(1);
        }
        //查询数量
        String countSql = "select count(1) as count from (SELECT" +
                          " so.ORDER_ID AS orderId," +
                          " so.ORDER_CODE AS orderCode," +
                          " '1' AS settlementStatus" +
                          " FROM" +
                          " so_deliver sod" +
                          " INNER JOIN so_order so ON sod.ORDER_ID = so.ORDER_ID where so.DEL_FLG = 0 and sod.DELIVER_CODE in ("+deliverCodeParam+")" +
                          " UNION" +
                          " SELECT" +
                          " so.ORDER_ID AS orderId," +
                          " so.ORDER_CODE AS orderCode," +
                          " '2' AS settlementStatus" +
                          " FROM" +
                          " so_return sro" +
                          " INNER JOIN so_order so ON sro.ORDER_ID = so.ORDER_ID where so.DEL_FLG = 0 and sro.RETURN_CODE in ("+returnCodeParam+")" +
                          " ) as settlement";
        List<Map<String,Object>> countList = baseJDBC.queryForListNotCount(countSql,paramList,null,true);
        int count = 0;
        if (countList.size() !=0){
            count = Integer.parseInt(String.valueOf(countList.get(0).get("count")));
        }
        //查询列表详情
        if(count != NumberConstant.IntDef.INT_ZERO){
            String listSql = "select * from (SELECT" +
                             " so.ORDER_ID AS orderId," +
                             " so.ORDER_CODE AS orderCode," +
                             " '1' AS settlementStatus" +
                             " FROM" +
                             " so_deliver sod" +
                             " INNER JOIN so_order so ON sod.ORDER_ID = so.ORDER_ID where so.DEL_FLG = 0 and sod.DELIVER_CODE in ("+deliverCodeParam+")" +
                             " UNION" +
                             " SELECT" +
                             " so.ORDER_ID AS orderId," +
                             " so.ORDER_CODE AS orderCode," +
                             " '2' AS settlementStatus" +
                             " FROM" +
                             " so_return sro" +
                             " INNER JOIN so_order so ON sro.ORDER_ID = so.ORDER_ID where so.DEL_FLG = 0 and sro.RETURN_CODE in ("+returnCodeParam+")" +
                             " ) as settlement";
            if (iso151427RestParam.getPageCount() != NumberConstant.IntDef.INT_ZERO && iso151427RestParam.getPageNo() >= NumberConstant.IntDef.INT_ONE){
                listSql += " LIMIT ?"+paramList.size()+",?"+(paramList.size()+1);
                paramList.add((iso151427RestParam.getPageNo()-1)* iso151427RestParam.getPageCount());
                paramList.add(iso151427RestParam.getPageCount());
            }
            List<Map<String,Object>> mapList = baseJDBC.queryForListNotCount(listSql,paramList,null,true);
            //判断查询结果是否为空
            if (!CollectionUtils.isEmpty(mapList)){
                for(Map map:mapList){
                    ISO151427SettlementDetailResult iso151427SettlementDetailResult = new ISO151427SettlementDetailResult();
                    try {
                        BeanUtils.populate(iso151427SettlementDetailResult, map);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    resultList.add(iso151427SettlementDetailResult);
                }
            }
            List<ISO151427SoProductResult> soProducts = new ArrayList<>();

            //查询产品明细
            for (ISO151427SettlementDetailResult settlementDetail : resultList){
                List productParamList = new ArrayList();
                productParamList.add(iso151427RestParam.getSellerId());
                productParamList.add(settlementDetail.getOrderId());
                String productSql = SqlUtil.getSqlBySqlId("ISO151427.findProduct");
                List<Map<String,Object>> productList = baseJDBC.queryForListNotCount(productSql,productParamList,null,true);
                for(Map map:productList){
                    ISO151427SoProductResult iso151427SoProductResult = new ISO151427SoProductResult();
                    try {
                        BeanUtils.populate(iso151427SoProductResult,map);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    soProducts.add(iso151427SoProductResult);
                }
                settlementDetail.setPdList(soProducts);
            }
        }
        result.setResultList(resultList);
        return result;
    }
}
