package com.msk.order.service.impl;

import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.exception.BusinessException;
import com.msk.common.constant.NumberConstant;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151405PdCodeListRestParam;
import com.msk.order.bean.param.ISO151405RestParam;
import com.msk.order.bean.result.*;
import com.msk.order.service.ISO151405Service;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ISO151405_产品销量查询接口
 * Created by chu_jian on 2016/8/3.
 */
@Service
public class ISO151405ServiceImpl implements ISO151405Service{

    private static Logger logger = LoggerFactory.getLogger(ISO151405ServiceImpl.class);

    @Autowired
    private BaseJdbcImpl baseJdbc;


    /**
     * 产品销量查询
     *
     * @param param param
     * @return 返回结果
     * @author chu_jian
     */
    @Override
    @Transactional
    public List<ISO151405PdListRestResult> getPdSalesVolumn(ISO151405RestParam param){
        logger.debug("产品销量查询接口");
        List<ISO151405PdListRestResult> iso151405PdListRestResults = new ArrayList<ISO151405PdListRestResult>();
        List<ISO151405PdCodeListRestParam> iso151405PdCodeListRsParamList = param.getPdCodeList();
        //check一次性传入的产品List不能超过20
        if (iso151405PdCodeListRsParamList.size() > NumberConstant.IntDef.INT_NINETEEN) {
            throw new BusinessException("pdCodeListSize :" + iso151405PdCodeListRsParamList.size() + ",请一次传30个以内的产品编码!");
        }

        for(ISO151405PdCodeListRestParam pdCodeListRsParam : param.getPdCodeList()){
            if(StringUtil.isEmpty(pdCodeListRsParam.getPdCode())){
                throw new BusinessException("产品编码没有传值！");
            }
            //根据传入的PdCode查询新老产品销量
            List<ISO151405PdListRestResult> results = this.dealAndQuerySql(pdCodeListRsParam.getPdCode());
            iso151405PdListRestResults.addAll(results);
        }
        //处理返回结果
        if (param.getIsReturnFuzzy()!= null && param.getIsReturnFuzzy() == NumberConstant.IntDef.INT_ZERO){
            for(ISO151405PdListRestResult result : iso151405PdListRestResults){
                if (result.getSalesVolumnLong() > param.getFuzzyValueLimit()){
                    result.setSalesVolumn(">" + result.getSalesVolumnLong()/10000 + "万");
                }else{
                    result.setSalesVolumn(result.getSalesVolumnLong().toString());
                }
                result.setSalesVolumnLong(null);
            }
        } else{
            for(ISO151405PdListRestResult result : iso151405PdListRestResults){
                result.setSalesVolumn(result.getSalesVolumnLong().toString());
                result.setSalesVolumnLong(null);
            }
        }
        return iso151405PdListRestResults;
    }

    /**
     * 产品销量查询，根据传入的pdCode查询数据库新老产品的销量
     *
     * @param pdCode pdCode
     * @return 返回结果
     */
    @Transactional
    private List<ISO151405PdListRestResult> dealAndQuerySql(String pdCode){
        String condition = "'" + pdCode + "'";
        String sql = "";
        List<ISO151405PdListRestResult> iso151405PdListRestResults = new ArrayList<ISO151405PdListRestResult>();
        Map<String,String> sqlMap = this.getSql();
        sql = sqlMap.get("ISO151405.getSalesVolumnAndPdCode") + condition + sqlMap.get("ISO151405.detailIfo") + condition + sqlMap.get("ISO151405.leftFiveCondition")
                + sqlMap.get("ISO151405.union") + condition + sqlMap.get("ISO151405.historyIfo") + condition + sqlMap.get("ISO151405.leftFiveCondition") + sqlMap.get("ISO151405.tableAlias");

        //得到查询结果
        List<Map<String, Object>> salesVolumeList = getResult(sql);
        //集合转换
        for (Map<String, Object> map : salesVolumeList) {
            ISO151405PdListRestResult bean = new ISO151405PdListRestResult();
            try {
                BeanUtils.populate(bean, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            iso151405PdListRestResults.add(bean);
        }
        return iso151405PdListRestResults;
    }

    /**
     * 查询数据库
     *
     * @param sql sql语句
     */
    @Transactional
    public List<Map<String, Object>> getResult(String sql){
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql,null,null,true);
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
    public Map<String,String> getSql() {
        List<String> sqlIds = new ArrayList<String>();
        sqlIds.add("ISO151405.getSalesVolumnAndPdCode");
        sqlIds.add("ISO151405.detailIfo");
        sqlIds.add("ISO151405.leftFiveCondition");
        sqlIds.add("ISO151405.union");
        sqlIds.add("ISO151405.historyIfo");
        sqlIds.add("ISO151405.tableAlias");
        Map<String,String> map = SqlUtil.getSqlMapBySqlIds(sqlIds);
        return map;
    }

}
