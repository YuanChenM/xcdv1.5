package com.msk.order.service.impl;

import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.exception.BusinessException;
import com.msk.order.bean.param.ISO151434RestParam;
import com.msk.order.bean.result.ISO151434RestResult;
import com.msk.order.bean.result.ISO151434SalesRestResult;
import com.msk.order.service.ISO151434Service;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ISO151434_获取上半月分销量接口
 * Created by chu_jian on 2016/8/3.
 */
@Service
public class ISO151434ServiceImpl implements ISO151434Service {

    private static Logger logger = LoggerFactory.getLogger(ISO151434ServiceImpl.class);

    @Autowired
    private BaseJdbcImpl baseJdbc;

    /**
     * 获取上半月分销量
     *
     * @author chu_jian
     */
    @Override
    public ISO151434RestResult getHalfMonthCount(ISO151434RestParam iso151434RestParam) {

        ISO151434RestResult iso151434RestResult = new ISO151434RestResult();
        List<ISO151434SalesRestResult> iso151434SalesListRsResultList = new ArrayList<ISO151434SalesRestResult>();
        /**获取平台类型*/
        int orderSource = iso151434RestParam.getOrderSource();
        /**获取年月*/
        String thisMonth = iso151434RestParam.getThisMonth();
        /**计算出起始时间*/
        Map<String, String> beginAndEndDate = this.getDate(thisMonth);
        /**查询销量*/
        List<Map<String, Object>> resultMapList = this.selectSalesCnt(orderSource, beginAndEndDate);
        for (Map<String, Object> map : resultMapList) {
            ISO151434SalesRestResult iso151434SalesRestResult = new ISO151434SalesRestResult();
            try {
                BeanUtils.populate(iso151434SalesRestResult, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            iso151434SalesListRsResultList.add(iso151434SalesRestResult);
        }
        iso151434RestResult.setSalesList(iso151434SalesListRsResultList);
        return iso151434RestResult;
    }

    /**
     * 调用共通方法获取Sql,查询数据库
     * @param orderSource 订单来源
     * @param beginAndEndDate 开始时间，结束时间
     * @author wang_jianzhou
     */
    private List<Map<String, Object>> selectSalesCnt(int orderSource, Map<String, String> beginAndEndDate) {
        //开始时间
        String beginTime = beginAndEndDate.get("beginTime");
        //结束时间
        String endTime = beginAndEndDate.get("endTime");
        //SqlId
        List<String> sqlIds = new ArrayList<String>();
        sqlIds.add("ISO151434.Search");
        sqlIds.add("ISO151434.GroupBy");
        Map<String,String> sqlMap = SqlUtil.getSqlMapBySqlIds(sqlIds);
        String salePlatformCondition = " AND SALE_PLATFORM = " + orderSource;
        String updTimeCondition = " AND so.UPD_TIME BETWEEN " + "'" + beginTime + "'" +" AND " + "'" + endTime + "' ";
        String sql = sqlMap.get("ISO151434.Search") + salePlatformCondition + updTimeCondition + sqlMap.get("ISO151434.GroupBy");
        logger.debug("查询数据库开始");
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql,null,null,true);
        logger.debug("查询数据库结束");
        List rows = mapList;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Object obj : rows) {
            Map<String, Object> row = (Map<String, Object>) obj;
            resultList.add(row);
        }
        return resultList;
    }

    /**
     * 根据参数“yyyyMM” 计算出上半月起始时间
     *
     * @author chu_jian
     */
    private Map<String, String> getDate(String thisMonth) {
        Map<String, String> beginAndEndDate = new HashMap<String, String>();
        SimpleDateFormat sdfThisMonth = new SimpleDateFormat("yyyyMM");
        try {
            Date date = sdfThisMonth.parse(thisMonth);
            String month = new SimpleDateFormat("yyyy-MM").format(date);
            String beginTime = month + "-01 00:00:00";
            String endTime = month + "-15 23:59:59";
            beginAndEndDate.put("beginTime", beginTime);
            beginAndEndDate.put("endTime", endTime);
        } catch (ParseException e) {
            throw new BusinessException("thisMonth字段格式不正确！");
        }
        return beginAndEndDate;
    }
}
