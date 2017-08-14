package com.msk.ds.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.ds.bean.DistSuppChain;
import com.msk.ds.bean.SC181102Param;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zhou_yajun on 2016/1/28.
 */
@Service
public class SC181102Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC181102Logic.class);
    @Autowired
    private DistSuppChainLogic distSuppChainLogic;

    /**
     * SQL Map 中SQL ID定义
     * @author zhou_yajun
     */
    interface SqlId {
        // 所有产品的合计用
        String SQL_GET_PROD_PLAN_SUM_NUM_LIST = "getProdPlanSumNumList";  // 计划
        String SQL_GET_PROD_ACTUAL_SUM_NUM_LIST = "getProdActualSumNumList";  // 实际
        String SQL_GET_PROD_PLAN_SUM_NUM_ALL_LIST = "getProdPlanSumNumAllList";  // 一行计划合计
        String SQL_GET_PROD_ACTUAL_SUM_NUM_ALL_LIST = "getProdActualSumNumAllList";  // 一行实际合计

        // 所有产品信息
        String SQL_GET_PROD_PLAN_NUM_LIST = "getProdPlanList";  // 计划
        String SQL_GET_PROD_ACTUAL_NUM_LIST = "getProdActualList";  // 实际
        String SQL_GET_PROD_PLAN_NUM_ALL_LIST = "getProdPlanNumAllList";  // 一行计划合计
        String SQL_GET_PROD_ACTUAL_NUM_ALL_LIST = "getProdActualNumAllList";  // 一行实际合计
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    /**
     * 获取所有产品的数量合计
     * @param sc181102Param param
     * @return List<DS173306Param>
     */
    @Transactional
    public DistSuppChain getProductNum(SC181102Param sc181102Param){
        BaseParam param = new BaseParam();
        param.setFilter("distMonth",sc181102Param.getDistMonth());
        param.setFilter("areaCode",sc181102Param.getAreaCode());
        param.setFilter("supplierCode",sc181102Param.getSupplierCode());
        param.setFilter("productName", DbUtils.buildLikeCondition(sc181102Param.getProductName(), DbUtils.LikeMode.FRONT));
        // 计划产品数据查询
        List<DistSuppChain> prodPlanList=this.findList(SqlId.SQL_GET_PROD_PLAN_NUM_LIST ,param);
        // 实际产品数据查询
        List<DistSuppChain> prodActualList=this.findList(SqlId.SQL_GET_PROD_ACTUAL_NUM_LIST  ,param);
        // 一行合计计划产品数据查询
        List<DistSuppChain> prodPlanAllList=this.findList(SqlId.SQL_GET_PROD_PLAN_NUM_ALL_LIST ,param);
        // 一行合计实际产品数据查询
        List<DistSuppChain> prodActualAllList=this.findList(SqlId.SQL_GET_PROD_ACTUAL_NUM_ALL_LIST  ,param);

        // 作成画面显示数据
        for(DistSuppChain planObj : prodPlanList){
                // 实际产品数据设定
            for(DistSuppChain prodPlan : prodActualList){
                // 条件相等
                if (planObj.getDistMonth().equals(prodPlan.getDistMonth())
                        &&planObj.getAreaCode().equals(prodPlan.getAreaCode())
                        &&planObj.getSupplierCode().equals(prodPlan.getSupplierCode())
                        &&planObj.getPlanType().equals(prodPlan.getPlanType())
                        &&planObj.getHalfCode().equals(prodPlan.getHalfCode())
                        &&planObj.getProductCode().equals(prodPlan.getProductCode())
                        ) {
                    planObj.setNewActualNum(prodPlan.getNewActualNum());
                }
            }

            // 一行合计计划产品数据查询
            for(DistSuppChain prodPlanAll : prodPlanAllList){
                // 条件相等
                if (planObj.getDistMonth().equals(prodPlanAll.getDistMonth())
                        &&planObj.getAreaCode().equals(prodPlanAll.getAreaCode())
                        &&planObj.getSupplierCode().equals(prodPlanAll.getSupplierCode())
                        &&planObj.getPlanType().equals(prodPlanAll.getPlanType())
                        &&planObj.getProductCode().equals(prodPlanAll.getProductCode())
                        ) {
                    planObj.setSumPlanNum(prodPlanAll.getSumPlanNum());
                    planObj.setSumOrigPlanNum(prodPlanAll.getSumOrigPlanNum());
                }
            }

            // 一行合计实际产品数据查询
            for(DistSuppChain prodActualAll : prodActualAllList){
                // 条件相等
                if (planObj.getDistMonth().equals(prodActualAll.getDistMonth())
                        &&planObj.getAreaCode().equals(prodActualAll.getAreaCode())
                        &&planObj.getSupplierCode().equals(prodActualAll.getSupplierCode())
                        &&planObj.getPlanType().equals(prodActualAll.getPlanType())
                        &&planObj.getProductCode().equals(prodActualAll.getProductCode())
                        ) {
                    planObj.setSumActualNum(prodActualAll.getSumActualNum());
                }
            }
        }

        //根据DB查询的数据，整理出产品数据
        DistSuppChain productInfo = new DistSuppChain();
        //Modify for 2981 at 2016/09/27 by likai Start
        Map<String, DistSuppChain> productListMap = new HashMap<String, DistSuppChain>();
        List<DistSuppChain> productList = new ArrayList<DistSuppChain>();
        for(DistSuppChain planObj : prodPlanList){
            String key = "" + planObj.getAreaCode() + planObj.getSupplierCode() + planObj.getPlanType() + planObj.getProductName();

            if(!productListMap.containsKey(key)) {
                DistSuppChain distSuppChain = new DistSuppChain();
                distSuppChain.setPlanType(planObj.getPlanType());
                distSuppChain.setProductName(planObj.getProductName());
                distSuppChain.setSumPlanNum(planObj.getSumPlanNum());
                distSuppChain.setSumActualNum(planObj.getSumActualNum());
                distSuppChain.setSumOrigPlanNum(planObj.getSumOrigPlanNum());
                productListMap.put(key,distSuppChain);
                productList.add(planObj);
            }
        }
        //Modify for 2981 at 2016/09/27 by likai End
        productInfo.setProductNumList(prodPlanList);
        productInfo.setProductList(productList);
        return productInfo;
    }

    /**
     * 获取所有产品的数量合计的合计
     * @param sc181102Param param
     * @return DS173306Param
     */
    @Transactional
    public DistSuppChain getProductSumNum(SC181102Param sc181102Param){

        // 计划产品数据查询
        List<DistSuppChain> prodPlanSumNumList=this.findList(SqlId.SQL_GET_PROD_PLAN_SUM_NUM_LIST  ,sc181102Param);
        // 实际产品数据查询
        List<DistSuppChain> prodActualSumNumList=this.findList(SqlId.SQL_GET_PROD_ACTUAL_SUM_NUM_LIST   ,sc181102Param);
        // 一行合计计划产品数据查询
        List<DistSuppChain> prodPlanSumNumAllList=this.findList(SqlId.SQL_GET_PROD_PLAN_SUM_NUM_ALL_LIST  ,sc181102Param);
        // 一行合计实际产品数据查询
        List<DistSuppChain> prodActualSumNumAllList=this.findList(SqlId.SQL_GET_PROD_ACTUAL_SUM_NUM_ALL_LIST   ,sc181102Param);

        // 作成画面显示数据
        for(DistSuppChain planObj : prodPlanSumNumList){
            // 实际产品数据设定
            for(DistSuppChain prodPlan : prodActualSumNumList){
                // 条件相等
                if (planObj.getDistMonth().equals(prodPlan.getDistMonth())
                        &&planObj.getAreaCode().equals(prodPlan.getAreaCode())
                        &&planObj.getSupplierCode().equals(prodPlan.getSupplierCode())
                        &&planObj.getPlanType().equals(prodPlan.getPlanType())
                        &&planObj.getHalfCode().equals(prodPlan.getHalfCode())
                        ) {
                    planObj.setSumActualNum(prodPlan.getSumActualNum());
                }
            }

            // 一行合计计划产品数据查询
            for(DistSuppChain prodPlanAll : prodPlanSumNumAllList){
                // 条件相等
                if (planObj.getDistMonth().equals(prodPlanAll.getDistMonth())
                        &&planObj.getAreaCode().equals(prodPlanAll.getAreaCode())
                        &&planObj.getSupplierCode().equals(prodPlanAll.getSupplierCode())
                        &&planObj.getPlanType().equals(prodPlanAll.getPlanType())
                        ) {
                    planObj.setSumSumPlanNum(prodPlanAll.getSumSumPlanNum());
                    planObj.setSumSumOrigPlanNum(prodPlanAll.getSumSumOrigPlanNum());
                }
            }

            // 一行合计实际产品数据查询
            for(DistSuppChain prodActualAll : prodActualSumNumAllList){
                // 条件相等
                if (planObj.getDistMonth().equals(prodActualAll.getDistMonth())
                        &&planObj.getAreaCode().equals(prodActualAll.getAreaCode())
                        &&planObj.getSupplierCode().equals(prodActualAll.getSupplierCode())
                        &&planObj.getPlanType().equals(prodActualAll.getPlanType())
                        ) {
                    planObj.setSumSumActualNum(prodActualAll.getSumSumActualNum());
                }
            }
        }

        //根据DB查询的数据，整理出合计数据的合计
        DistSuppChain productInfoSum = new DistSuppChain();
        List<DistSuppChain> productSumSumNumList = new ArrayList<>();
        for(int i = NumberConst.IntDef.INT_ZERO; i<prodPlanSumNumList.size() - NumberConst.IntDef.INT_ONE; i++){
            DistSuppChain distSuppChain = new DistSuppChain();
            if(i == NumberConst.IntDef.INT_ZERO){
                distSuppChain.setSumSumPlanNum(prodPlanSumNumList.get(NumberConst.IntDef.INT_ZERO).getSumSumPlanNum());
                distSuppChain.setSumSumActualNum(prodPlanSumNumList.get(NumberConst.IntDef.INT_ZERO).getSumSumActualNum());
                distSuppChain.setSumSumOrigPlanNum(prodPlanSumNumList.get(NumberConst.IntDef.INT_ZERO).getSumSumOrigPlanNum());
                distSuppChain.setPlanType(prodPlanSumNumList.get(NumberConst.IntDef.INT_ZERO).getPlanType());
                productSumSumNumList.add(distSuppChain);
            }else {
                if(!prodPlanSumNumList.get(i).getPlanType() .equals(prodPlanSumNumList.get(i + NumberConst.IntDef.INT_ONE).getPlanType())){
                    distSuppChain.setSumSumPlanNum(prodPlanSumNumList.get(i + NumberConst.IntDef.INT_ONE).getSumSumPlanNum());
                    distSuppChain.setSumSumActualNum(prodPlanSumNumList.get(i + NumberConst.IntDef.INT_ONE).getSumSumActualNum());
                    distSuppChain.setSumSumOrigPlanNum(prodPlanSumNumList.get(i + NumberConst.IntDef.INT_ONE).getSumSumOrigPlanNum());
                    distSuppChain.setPlanType(prodPlanSumNumList.get(i + NumberConst.IntDef.INT_ONE).getPlanType());
                    productSumSumNumList.add(distSuppChain);
                }
            }
        }
        productInfoSum.setProductSumSumNumList(productSumSumNumList);
        productInfoSum.setProductSumNumList(prodPlanSumNumList);
        return productInfoSum;
    }
    /**
     * 获取分销月度
     * @param sc181102Param param
     * @return DS173306Param
     */
    public DistSuppChain getDistMonth(SC181102Param sc181102Param){
        logger.info("获取分销月度");
        /**传入对象的参数保留至传出对象*/
        DistSuppChain distSuppChain = new DistSuppChain();
        /*期*/
        List<DistSuppChain> distMonthList = distSuppChainLogic.findDistMonth();
        distSuppChain.setDistMonthList(distMonthList);
        return distSuppChain;
    }

    /**
     * 获取半旬信息
     * @param sc181102Param 传入参数
     * @return
     */
    @Transactional
    public DistSuppChain getHalfName(SC181102Param sc181102Param){
        logger.info("获取半旬信息");
        DistSuppChain distSuppChain = new DistSuppChain();
        //获取crtId对应的slCode
        String slCode = getSlCode(sc181102Param);
        if(!StringUtil.isNullOrEmpty(sc181102Param.getDistMonth())){
            /**
             * 带条件检索
             */
            //获取当前月对应的地区list
            List<DistSuppChain> areaInfoList = distSuppChainLogic.findLogisticsAreaList(sc181102Param.getDistMonth());
            //获取当前月对应的地区的供应商list
            BaseParam param = new BaseParam();
            param.setFilter("distMonth",sc181102Param.getDistMonth());
            param.setFilter("logisAreaCode",sc181102Param.getAreaCode());
            param.setFilter("userType",sc181102Param.getUserType());
            /**为获取供应商自己信息修改如下    2016/6/11*/
//            param.setFilter("loginId",sc181102Param.getCrtId());
            param.setFilter("slCode",slCode); // 登录用户编号

            List<DistSuppChain> suppInfoList = distSuppChainLogic.findSuppInfoList(param);
            //设置返回值参数
            distSuppChain.setProductName(sc181102Param.getProductName());
            distSuppChain.setAreaCode(sc181102Param.getAreaCode());
            distSuppChain.setSupplierCode(sc181102Param.getSupplierCode());
            distSuppChain.setSupplierInfoList(suppInfoList);
            distSuppChain.setAreaInfoList(areaInfoList);
            distSuppChain.setDistMonth(sc181102Param.getDistMonth());
        }else{
            /**
             * 初期化
             */
            String time = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, DateTimeUtil.getCustomerDate());
            String currentDistMonth = distSuppChainLogic.getNowHalfDate(time);
            distSuppChain.setDistMonth(currentDistMonth);
            sc181102Param.setDistMonth(currentDistMonth);
            //获取当前月对应的地区list
            List<DistSuppChain> areaInfoList = distSuppChainLogic.findLogisticsAreaList(currentDistMonth);
            if(CollectionUtils.isNotEmpty(areaInfoList) && areaInfoList.size()>0){
                //获取当前月对应的地区的供应商list
                BaseParam param = new BaseParam();
                param.setFilter("distMonth",currentDistMonth);
                //Modify for #2704 at 2016/09/18 by li_kai1 Start
//                param.setFilter("logisAreaCode",areaInfoList.get(0).getAreaCode());
                param.setFilter("logisAreaCode",sc181102Param.getAreaCode());
                //Modify for #2704 at 2016/09/18 by li_kai1 Start
                param.setFilter("userType",sc181102Param.getUserType());
                /**为获取供应商自己信息修改如下    2016/6/11*/
                //param.setFilter("loginId",sc181102Param.getCrtId());
                param.setFilter("slCode",slCode); // 登录用户编号
                List<DistSuppChain> suppInfoList = distSuppChainLogic.findSuppInfoList(param);

                distSuppChain.setAreaInfoList(areaInfoList);
                distSuppChain.setSupplierInfoList(suppInfoList);
                distSuppChain.setAreaCode(areaInfoList.get(0).getAreaCode());
                if(CollectionUtils.isNotEmpty(suppInfoList) && suppInfoList.size() > 0) {
                    distSuppChain.setSupplierCode(suppInfoList.get(0).getSupplierCode());
                    sc181102Param.setSupplierCode(suppInfoList.get(0).getSupplierCode());
                } else {
                    distSuppChain.setSupplierCode("xxxxx");
                    sc181102Param.setSupplierCode("xxxxx");
                }
                sc181102Param.setAreaCode(areaInfoList.get(0).getAreaCode());
            }
        }
        /*表头日期数据和当前日期所在的半旬*/
//        int year = Integer.parseInt(sc181102Param.getDistMonth().substring(NumberConst.IntDef.INT_ZERO,NumberConst.IntDef.INT_FOUR));
//        int month = Integer.parseInt(sc181102Param.getDistMonth().substring(NumberConst.IntDef.INT_FOUR,NumberConst.IntDef.INT_SIX));
        int year = StringUtil.toInteger(sc181102Param.getDistMonth().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_FOUR));
        int month = StringUtil.toInteger(sc181102Param.getDistMonth().substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX));
        String dataSelect = getCurrentDateStr(month,year);
        String dataSelectBefore = "";
        if(month == NumberConst.IntDef.INT_ONE){
            dataSelectBefore = getCurrentDateStr(NumberConst.IntDef.INT_TWELVE,year - NumberConst.IntDef.INT_ONE);
        }else{
            dataSelectBefore = getCurrentDateStr(month - NumberConst.IntDef.INT_ONE,year);
        }
        distSuppChain.setDataSelect(dataSelect);
        distSuppChain.setDataSelectBefore(dataSelectBefore);
        //当前半旬Code
        distSuppChain.setCurrentHalfCode(distSuppChainLogic.getNowHalfCode(sc181102Param.getDistMonth()));
        /*半旬名称List*/
        distSuppChain.setHalfNameList(distSuppChainLogic.getHalfNameList(sc181102Param.getDistMonth(),NumberConst.IntDef.INT_ZERO));

        return distSuppChain;
    }
    /**
     * 获取下拉框数据
     * @param sc181102Param sc181102Param
     * @return selectList
     */
    @Transactional
    public List<DistSuppChain> getSelectValue(SC181102Param sc181102Param){
        List<DistSuppChain> selectList = new ArrayList<>();
        //获取crtId对应的slCode
        if(!StringUtil.isNullOrEmpty(sc181102Param.getDistMonth()) && !StringUtil.isNullOrEmpty(sc181102Param.getAreaCode())){
            /*供应商*/
            BaseParam param = new BaseParam();
            param.setFilter("distMonth", sc181102Param.getDistMonth());
            param.setFilter("logisAreaCode", sc181102Param.getAreaCode());
            param.setFilter("userType", sc181102Param.getUserType());
            /**为获取供应商自己信息修改如下    2016/6/11*/
//            param.setFilter("loginId",sc181102Param.getCrtId());
            param.setFilter("slCode",getSlCode(sc181102Param)); // 登录用户编号

            selectList = distSuppChainLogic.findSuppInfoList(param);
        }else if(!StringUtil.isNullOrEmpty(sc181102Param.getDistMonth())){
            //区域
            selectList = distSuppChainLogic.findLogisticsAreaList(sc181102Param.getDistMonth());
        }
        return selectList;
    }

    /**
     * 根据crtId获得slCode
     */
    public String getSlCode(SC181102Param sc181102Param) {
        ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
        isl231193RsParam.setSlAccount(sc181102Param.getCrtId());
        ISL231193RsResult isl231193RsResult = RestUtil.queryslEpData(isl231193RsParam);
        return isl231193RsResult.getSlCode();
    }
    /**
     * 获得XX年XX月字符串
     * @param month 月
     * @param year 年
     * @return string
     */
    public String getCurrentDateStr(int month,int year){
        String dataStr = null;
        if(month < NumberConst.IntDef.INT_TEN){
            dataStr = year + "年0" + month + "月";
        }else {
            dataStr = year + "年" + month + "月";
        }
        return dataStr;
    }
};

