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
import com.msk.order.bean.param.ISO151428RestParam;
import com.msk.order.bean.result.ISO151428BuyRecordResult;
import com.msk.order.bean.result.ISO151428RestResult;
import com.msk.order.service.ISO151428Service;

/**
 * ISO151428_购买记录查询接口
 * Created by wang_shuai on 2016/8/24.
 */
@Service
public class ISO151428ServiceImpl implements ISO151428Service {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ISO151428ServiceImpl.class);
    @Autowired
    private BaseJdbcImpl baseJDBC;

    @Override
    public ISO151428RestResult findBuyRecord(ISO151428RestParam iso151428RestParam){
        logger.info("查询购买记录分页信息");
        ISO151428RestResult result = new ISO151428RestResult();
        List<ISO151428BuyRecordResult> resultList = new ArrayList<ISO151428BuyRecordResult>();
        List paramList = new ArrayList();
        paramList.add(iso151428RestParam.getPdCode());
        String countSql = SqlUtil.getSqlBySqlId("ISO151428.findCount");
        List<Map<String,Object>> countList = baseJDBC.queryForListNotCount(countSql,paramList,null,true);
        int count = 0;
        if (countList.size() !=0){
            count = Integer.parseInt(String.valueOf(countList.get(0).get("count")));
        }

        //查询列表详情
        if (count != NumberConstant.IntDef.INT_ZERO){
            String listSql = SqlUtil.getSqlBySqlId("ISO151428.findList");
            if (iso151428RestParam.getPageCount() != NumberConstant.IntDef.INT_ZERO && iso151428RestParam.getPageNo() >= NumberConstant.IntDef.INT_ONE){
                listSql += " LIMIT ?" + paramList.size()+",?" + (paramList.size()+1);
                paramList.add((iso151428RestParam.getPageNo()-1)* iso151428RestParam.getPageCount());
                paramList.add(iso151428RestParam.getPageCount());
            }
            List<Map<String,Object>> mapList = baseJDBC.queryForListNotCount(listSql,paramList,null,true);
            if (!CollectionUtils.isEmpty(mapList)){
                for(Map map:mapList){
                    ISO151428BuyRecordResult iso151428BuyRecordResult = new ISO151428BuyRecordResult();
                    try {
                        BeanUtils.populate(iso151428BuyRecordResult, map);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    resultList.add(iso151428BuyRecordResult);
                }
            }
        }
        result.setBuyRecordList(resultList);
        return result;
    }


}
