package com.msk.batch.sp.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.sp.bean.BSP171102Bean;
import com.msk.batch.sp.bean.BSP171102Param;
import com.msk.batch.sp.commUtils.RestUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.consts.CodeMasterConst;
import com.msk.common.logic.CommonLogic;
import com.msk.ds.bean.ISC1891I1RsParam;
import com.msk.ds.bean.ISC1891I1RsResult;
import com.msk.ds.bean.ISC1891I1RsResultInfo;
import com.msk.order.bean.ISO151434OrderRsResult;
import com.msk.order.bean.ISO151434RsParam;
import com.msk.order.bean.ISO151434RsResult;
import com.msk.stock.bean.Stock;
import com.msk.stock.bean.StockResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * BSP171102Logic
 *
 * @author sun_jiaju
 **/
@Service
public class   BSP171102Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSP171102Logic.class);
    /**
     * CommonLogic
     */
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     *  数据处理
     *
     * @return
     */
    @Transactional
    public void dataResolve(BaseParam param) {
        BSP171102Param dataParam = (BSP171102Param) param;
        String thisMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH,
                DateTimeUtil.getCustomerDate());
        String forecastYm = DateTimeUtil.monthAddN(thisMonth, NumberConst.IntDef.INT_ONE);
        dataParam.setThisYm(thisMonth);
        dataParam.setForecastYm(forecastYm);
        // 需求预测月数据逻辑删除
        super.modify(SqlId.SQLID_UPDATE_FORECAST_STOCKSALES, dataParam);
        super.modify(SqlId.SQLID_UPDATE_PD_DEMAND_SELLER, dataParam);

        // 插入需求预测计算用库存及销量
        this.insertSpForecastStocksales(dataParam, thisMonth);

        // 计算预测数量
        List<BSP171102Bean> resultList = super.findList(SqlId.SQLID_SELECT_FORECAST_NUM, dataParam);
        for (BSP171102Bean result : resultList) {
            if(result.getForecastNum() == null){
                throw new BusinessException("存在预测系数未设定！");
            }
        }

        // 插入需求数量预测表
        this.insertSpPdDemandSeller(resultList);
    }

    /**
     * 插入需求预测计算用库存及销量表
     * @param param
     */
    private void insertSpForecastStocksales(BSP171102Param param, String thisMonth)
    {
        // 本月上半月分销量
        List<BSP171102Bean> salesCntList = this.getSalesCnt(thisMonth);

        // 本月上半月末期末库存
        List<BSP171102Bean> stockCntList = this.getStockCnt();

        // 截止本月15日止的已列入供应计划尚未入库的供应量
        List<BSP171102Bean> palnCntList = this.getPlanCnt(thisMonth);

        Map<String, BSP171102Bean> newForecastMap = list2Map(salesCntList, param);
        this.setOtherCnt(newForecastMap, stockCntList, true, param);
        this.setOtherCnt(newForecastMap, palnCntList, false, param);
        List<BSP171102Bean> sfsEntityList = new ArrayList<BSP171102Bean>();
        for (BSP171102Bean entity : newForecastMap.values()) {
            entity.setCrtId("batch");
            entity.setCrtTime(DateTimeUtil.getCustomerDate());
            entity.setUpdId("batch");
            entity.setUpdTime(DateTimeUtil.getCustomerDate());
            entity.setActId("batch");
            entity.setActTime(DateTimeUtil.getCustomerDate());
            sfsEntityList.add(entity);
        }
        if (CollectionUtils.isNotEmpty(sfsEntityList)) {
            int insert_max = NumberConst.IntDef.INT_HUNDRED;
            List<BSP171102Bean> insertList =  new ArrayList<BSP171102Bean>();
            int length= sfsEntityList.size();
            int start = NumberConst.IntDef.INT_ZERO;
            int end = length > insert_max ? insert_max : length;
            while(start < length){
                insertList = sfsEntityList.subList(start,end);
                super.batchSave(insertList);
                start = end;
                end = length > (end + insert_max) ? (end + insert_max) : length;
            }
        }
    }

    /**
     * List to Map
     * @param salesList
     * @return entityMap
     */
    private Map<String, BSP171102Bean> list2Map(List<BSP171102Bean> salesList, BSP171102Param param) {
        Map<String, BSP171102Bean> entityMap = new HashMap<String, BSP171102Bean>();
        if (salesList != null && salesList.size() > NumberConst.IntDef.INT_ZERO) {
            for(BSP171102Bean salesCnt : salesList){
                long maxForecasCondtId = commonLogic.maxId("SP_FORECAST_STOCKSALES", "FORECAST_COND_ID");
                String key = salesCnt.getLgcsCode() + StringConst.VERTICAL + salesCnt.getPdCode();
                salesCnt.setForecastCondId(maxForecasCondtId);
                salesCnt.setGradeCode(salesCnt.getPdCode().substring(NumberConst.IntDef.INT_NINE));
                salesCnt.setForecastYm(param.getForecastYm());
                if(null == salesCnt.getSalesCnt() ){
                    salesCnt.setSalesCnt(BigDecimal.ZERO);
                }
                salesCnt.setPlanCnt(BigDecimal.ZERO);
                entityMap.put(key, salesCnt);
            }
        }
        return entityMap;
    }

    /**
     * 设置本月上半月末期末库存/截止本月15日止的已列入供应计划尚未入库的供应量
     * @param entityMap
     * @param cntList
     * @param stockFlg
     */
    private void setOtherCnt(Map<String, BSP171102Bean> entityMap, List<BSP171102Bean> cntList, boolean stockFlg, BSP171102Param param) {
        if (cntList != null && cntList.size() > NumberConst.IntDef.INT_ZERO) {
            if(stockFlg){
                for(BSP171102Bean stockCnt : cntList){
                    String key = stockCnt.getLgcsCode() + StringConst.VERTICAL + stockCnt.getPdCode();
                    if (entityMap.containsKey(key)){
                        entityMap.get(key).setStockCnt(stockCnt.getStockCnt());
                    } else {
                        long maxForecasCondtId = commonLogic.maxId("SP_FORECAST_STOCKSALES", "FORECAST_COND_ID");
                        stockCnt.setForecastCondId(maxForecasCondtId);
                        stockCnt.setGradeCode(stockCnt.getPdCode().substring(NumberConst.IntDef.INT_NINE));
                        stockCnt.setForecastYm(param.getForecastYm());
                        stockCnt.setSalesCnt(BigDecimal.ZERO);
                        stockCnt.setPlanCnt(BigDecimal.ZERO);
                        entityMap.put(key, stockCnt);
                    }
                }
            } else {
                for(BSP171102Bean planCnt : cntList){
                    String key = planCnt.getLgcsCode() + StringConst.VERTICAL + planCnt.getPdCode();
                    if (entityMap.containsKey(key)){
                        entityMap.get(key).setPlanCnt(planCnt.getPlanCnt());
                    }else {
                        long maxForecasCondtId = commonLogic.maxId("SP_FORECAST_STOCKSALES", "FORECAST_COND_ID");
                        planCnt.setForecastCondId(maxForecasCondtId);
                        planCnt.setGradeCode(planCnt.getPdCode().substring(NumberConst.IntDef.INT_NINE));
                        planCnt.setForecastYm(param.getForecastYm());
                        planCnt.setSalesCnt(BigDecimal.ZERO);
                        if(null == planCnt.getPlanCnt()){
                            planCnt.setPlanCnt(BigDecimal.ZERO);
                        }
                        entityMap.put(key, planCnt);
                    }
                }
            }
        }
    }

    /**
     * 插入需求数量预测表
     * @param resultList
     */
    private void insertSpPdDemandSeller(List<BSP171102Bean> resultList)
    {
        Date nowDate =  DateTimeUtil.getCustomerDate();
        if (CollectionUtils.isNotEmpty(resultList)){
            for (BSP171102Bean result : resultList) {
                long maxForecastId = commonLogic.maxId("SP_PD_DEMAND_SELLER", "FORECAST_ID");
                result.setForecastId(maxForecastId);
                result.setGradeCode(result.getPdCode().substring(NumberConst.IntDef.INT_NINE));
                result.setCrtId("batch");
                result.setCrtTime(nowDate);
                result.setUpdId("batch");
                result.setUpdTime(nowDate);
                result.setActId("batch");
                result.setActTime(nowDate);
            }
            int insert_max = NumberConst.IntDef.INT_HUNDRED;
            List<BSP171102Bean> insertList =  new ArrayList<BSP171102Bean>();
            int length= resultList.size();
            int start = NumberConst.IntDef.INT_ZERO;
            int end = length > insert_max ? insert_max : length;
            while(start < length){
                insertList = resultList.subList(start,end);
                super.getBaseDao().batchInsert(SqlId.SQLID_BATCH_SAVE_PD_DEMAND_SELLER, insertList);
                start = end;
                end = length > (end + insert_max) ? (end + insert_max) : length;
            }
        }
    }

    /**
     * 接口获取本月上半月末期末库存
     */
    private List<BSP171102Bean> getStockCnt()
    {
        List<BSP171102Bean> beanList = new ArrayList<BSP171102Bean>();
        // 调用库存接口
        RsResponse<StockResult> response = RestUtils.getStockCnt();
        if(null != response.getResult()) {
           for (Stock temp : response.getResult().getPdStockList()){
               BSP171102Bean bean = new BSP171102Bean();
               bean.setPdCode(temp.getPdCode());
               bean.setLgcsCode(temp.getLgcsCode());
               bean.setStockCnt(temp.getStockQty().intValue());
               beanList.add(bean);
           }
        }
        return  beanList;
    }

    /**
     * 接口获取本月上半月分销量
     */
    private List<BSP171102Bean> getSalesCnt(String thisMonth)
    {
        List<BSP171102Bean> beanList = new ArrayList<BSP171102Bean>();
        ISO151434RsParam iso151434RsParam = new ISO151434RsParam();
        iso151434RsParam.setThisMonth(thisMonth);
        iso151434RsParam.setOrderSource(CodeMasterConst.SupplyPlatform.SNK);
        RsResponse<ISO151434OrderRsResult> resultList = RestUtils.halfMonthOrder(iso151434RsParam);
        if(resultList.getResult() != null){
            List<ISO151434RsResult> salesList = resultList.getResult().getIso151434RsResultList();
            if(!salesList.isEmpty()){
                for (ISO151434RsResult iso151434RsResult :salesList){
                    BSP171102Bean bsp171102Bean = new BSP171102Bean();
                    bsp171102Bean.setLgcsCode(iso151434RsResult.getDistrictCode());
                    bsp171102Bean.setPdCode(iso151434RsResult.getPdCode());
                    bsp171102Bean.setSalesCnt(iso151434RsResult.getOrderCount());
                    beanList.add(bsp171102Bean);
                }
            }
        }
        return  beanList;
    }

    /**
     * 接口获取截止本月15日止的已列入供应计划尚未入库的供应量
     */
    private List<BSP171102Bean> getPlanCnt(String thisMonth)
    {
        ISC1891I1RsParam isc1891I1RsParam = new ISC1891I1RsParam();
        isc1891I1RsParam.setDistMonth(thisMonth);
        // 1-用于供应链接口处判断
        isc1891I1RsParam.setFlag("1");
        RsResponse<ISC1891I1RsResult> response = RestUtils.queryProductLotInfo(isc1891I1RsParam);
        List<ISC1891I1RsResultInfo> rsResultInfoList = new ArrayList<>();
        List<BSP171102Bean> beanList = new ArrayList<BSP171102Bean>();
        if(response.getResult() != null){
             rsResultInfoList =  response.getResult().getReturnInfos();
        }
        for (ISC1891I1RsResultInfo isc1891I1RsResultInfo : rsResultInfoList){
            BSP171102Bean bsp171102Bean = new BSP171102Bean();
            bsp171102Bean.setPdCode(isc1891I1RsResultInfo.getPdCode());
            bsp171102Bean.setLgcsCode(isc1891I1RsResultInfo.getLgcsCode());
            bsp171102Bean.setPlanCnt(isc1891I1RsResultInfo.getUnStockNum());
            beanList.add(bsp171102Bean);
        }

        return  beanList;
    }
    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_chen
     */
    public interface SqlId {
        static final String SQLID_UPDATE_FORECAST_STOCKSALES = "updateForecastStockSales";
        static final String SQLID_UPDATE_PD_DEMAND_SELLER= "updatePdDemandSeller";
        static final String SQLID_SELECT_FORECAST_NUM = "selectForecastNum";
        static final String SQLID_BATCH_SAVE_PD_DEMAND_SELLER = "batchSavePdDemandSeller";
    }
}
