package com.msk.order.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.order.bean.param.ISO151426GetSqlParam;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msk.common.constant.NumberConstant;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151426RestParam;
import com.msk.order.bean.result.ISO151426OrderRestResult;
import com.msk.order.bean.result.ISO151426ProductRestResult;
import com.msk.order.bean.result.ISO151426RestResult;
import com.msk.order.service.ISO151426Service;

/**
 * ISO151426_管家订单查询接口
 * Created by wang_shuai on 2016/8/22.
 */
@Service
public class ISO151426ServiceImpl implements ISO151426Service {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISO151426ServiceImpl.class);
    @Autowired
    private BaseJdbcImpl baseJdbc;

    @Override
    public ISO151426RestResult findPageResult(ISO151426RestParam param){
        logger.debug("管家信息查询");
        ISO151426RestResult result = new ISO151426RestResult();
        List<ISO151426OrderRestResult> resultList = new ArrayList<>();

        //查询总数量
        List countParamList = new ArrayList();
        String countSql = SqlUtil.getSqlBySqlId("ISO151426.findCount");
        ISO151426GetSqlParam sqlParam = getFullSql(param,countSql,countParamList,false);
        String sql = sqlParam.getSql();
        List countParamListAll = sqlParam.getParamList();
        List<Map<String,Object>> countList = baseJdbc.queryForListNotCount(sql,countParamListAll,null,true);
        int count = 0;
        if (countList.size() !=0){
            count = Integer.parseInt(String.valueOf(countList.get(0).get("count")));
        }

        if(count != NumberConstant.IntDef.INT_ZERO){
            List paramList = new ArrayList();
            String listSql = SqlUtil.getSqlBySqlId("ISO151426.findList");
            ISO151426GetSqlParam iso151426GetSqlParam = getFullSql(param,listSql,paramList,true);
            String conditionSql = iso151426GetSqlParam.getSql();
            List paramListAll = iso151426GetSqlParam.getParamList();
            List<Map<String,Object>> mapList = baseJdbc.queryForListNotCount(conditionSql,paramListAll,null,true);
            //判断查询结果是否为空
            if (!CollectionUtils.isEmpty(mapList)){
                for(Map map:mapList){
                    ISO151426OrderRestResult iso151426OrderRestResult = new ISO151426OrderRestResult();
                    try {
                        BeanUtils.populate(iso151426OrderRestResult,map);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    resultList.add(iso151426OrderRestResult);
                }
                //查询产品信息
                List<ISO151426ProductRestResult> products = new ArrayList<>();
                for (ISO151426OrderRestResult orderInfo : resultList){
                    List productParamList = new ArrayList();
                    productParamList.add(orderInfo.getOrderId());
                    String productSql = SqlUtil.getSqlBySqlId("ISO151426.findProductList");
                    List<Map<String,Object>> productList = baseJdbc.queryForListNotCount(productSql,productParamList,null,true);
                    for(Map map:productList){
                        ISO151426ProductRestResult iso151426ProductRestResult = new ISO151426ProductRestResult();
                        try {
                            BeanUtils.populate(iso151426ProductRestResult,map);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        products.add(iso151426ProductRestResult);
                    }
                    orderInfo.setProducts(products);
                }

            }
            result.setOrders(resultList);
        }
        return result;
    }

    /**
     * 拼接sql条件,拼接参数
     * @param param
     * @param sql
     * @param pagingFlg
     * @return
     */
    private ISO151426GetSqlParam getFullSql(ISO151426RestParam param,String sql,List paramList,Boolean pagingFlg){
        ISO151426GetSqlParam getSqlParam = new ISO151426GetSqlParam();
        if (!StringUtil.isEmpty(param.getHousekeepingId())){
            sql += " AND SOO.SA_ID=?" + paramList.size();
            paramList.add(param.getHousekeepingId());
        }
        if (!StringUtil.isEmpty(param.getInputParam())){
            sql += " AND (SOO.order_id like ?" + paramList.size() +
                    " OR EXISTS (SELECT * FROM so_order_detail sod WHERE sod.order_id=SOO.order_id AND sod.pd_name like ?"+(paramList.size()+1)+"))";
            paramList.add(param.getInputParam());
            paramList.add(param.getInputParam());
        }
        if (!StringUtil.isEmpty(param.getOrderStatus()) && param.getOrderStatus().equals("1")){
            sql += " AND (SOO.ORDER_STATUS = 2)";
        }
        if (!StringUtil.isEmpty(param.getOrderStatus()) && param.getOrderStatus().equals("2")){
            sql += " AND (SOO.ORDER_STATUS = 3)";
        }
        if (!StringUtil.isEmpty(param.getOrderStatus()) && param.getOrderStatus().equals("3")){
            sql += " AND (SOO.ORDER_STATUS = 5)";
        }
        if (!StringUtil.isEmpty(param.getOrderStatus()) && param.getOrderStatus().equals("4")){
            sql += " AND (SOO.ORDER_STATUS = 6)";
        }
        if (!StringUtil.isEmpty(param.getOrderStatus()) && param.getOrderStatus().equals("5")){
            sql += " AND (SOO.ORDER_STATUS = 99)";
        }
        if (!StringUtil.isEmpty(param.getBeginDate())){
            sql += " AND SOO.CRT_TIME >= ?" + paramList.size();
            paramList.add(param.getBeginDate());
        }
        if (!StringUtil.isEmpty(param.getEndDate())){
            sql += " AND SOO.CRT_TIME <= ?" + paramList.size();
            paramList.add(param.getEndDate());
        }
        if (pagingFlg == true && param.getPageCount() != NumberConstant.IntDef.INT_ZERO && param.getPageNo() >= NumberConstant.IntDef.INT_ONE){
            sql += " LIMIT ?"+paramList.size()+",?"+(paramList.size()+1);
            paramList.add((param.getPageNo()-1)*param.getPageCount());
            paramList.add(param.getPageCount());
        }
        getSqlParam.setSql(sql);
        getSqlParam.setParamList(paramList);
        return getSqlParam;
    }


}
