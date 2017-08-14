package com.msk.batch.ds.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.ds.bean.BSC181101Param;
import com.msk.batch.ds.utils.RestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * BSC181101Logic 业务处理类
 *
 * @author yang_yang
 */
public class BSC181101Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSC181101Logic.class);

    /**
     * PDCODE_LENGTH
     */
    private static int PDCODE_LENGTH = 10;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * JSON Key值
     */
    private interface JsonKey {
        String JSON_RESULT_KEY = "result";
        String JSON_AREA_CODE_KEY = "areaCode";
        String JSON_SUP_LIST_KEY = "supList";
        String JSON_SUP_CODE_KEY = "supCode";
        String JSON_PRODUCT_LIST_KEY = "productList";
        String JSON_PRODUCT_CODE_KEY = "productCode";
        String JSON_CAMP_TYPE_KEY = "campType";
        String JSON_WAY_LIST_KEY = "wayList";
        String JSON_WAY_TYPE_KEY = "wayType";
        String JSON_PASS_DEMAND_KEY = "passDemand";
    }

    /**
     * 计划生产量标准
     */
    private interface PlanGenerationAmountStandard {
        BigDecimal STANDARD_ONE = new BigDecimal(NumberConst.IntDef.INT_ONE_HUNDRED_AND_FIFTY);
    }

    private interface SqlId {
        String SQL_ID_BATCH_EXIST_CHK = "existChk";
        String SQL_ID_BATCH_SAVE_VIRTUAL_STOCK_ACTUAL = "batchSaveVirtualStockActual";
        String SQL_ID_BATCH_SAVE_VIRTUAL_STOCK_PLAN = "batchSaveVirtualStockPlan";
        String SQL_ID_BATCH_FIND_PD_NORMSSTD = "findPdNormsStd";
        String SQL_ID_BATCH_FIND_PD_NORMSSTD_PROD = "findPdNormsStdProd";
    }

    // 初期化产品编号HashMap
    HashMap<String,DsSuppDistNum> planProdInfoMap = new HashMap<String,DsSuppDistNum>();

    /**
     * 设置供应商信息
     *
     * @param param     BSC181101Param
     * @param jsonArray Json Array
     */
    private void setSupplierInfo(BSC181101Param param, JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == NumberConst.IntDef.INT_ZERO) {
            logger.debug("没有供应商信息");
            return;
        }
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject supObject = jsonArray.getJSONObject(i);
            // 供应商编号
            param.setSuppCode(supObject.getString(JsonKey.JSON_SUP_CODE_KEY));
            // 供应商产品
            JSONArray productArray = supObject.getJSONArray(JsonKey.JSON_PRODUCT_LIST_KEY);
            this.setProductInfo(param, productArray);
        }
    }

    private void setProductInfo(BSC181101Param param, JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == NumberConst.IntDef.INT_ZERO) {
            logger.debug("没有供应商对应的产品信息");
            return;
        }
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject productObject = jsonArray.getJSONObject(i);
            param.setPdCode(productObject.getString(JsonKey.JSON_PRODUCT_CODE_KEY));
            param.setBrandType(productObject.getInt(JsonKey.JSON_CAMP_TYPE_KEY));
            JSONArray wayArray = productObject.getJSONArray(JsonKey.JSON_WAY_LIST_KEY);
            this.setWayInfo(param, wayArray);
        }
    }

    /**
     * @param distNumTon
     * @param halfCode
     * @return
     */
    private static BigDecimal[]  createPlanAmount(BigDecimal distNumTon,int halfCode) {
        BigDecimal[] planGenerationAmount = new BigDecimal[NumberConst.IntDef.INT_FIVE];
        int index = NumberConst.IntDef.INT_ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;

        // 获得可以计划生产量次数
        BigDecimal num = DecimalUtil.divide(distNumTon, new BigDecimal(NumberConst.IntDef.INT_THIRTY));
        long yValue=0;
        long zValue = num.longValue() % NumberConst.IntDef.INT_FIVE;
        if(zValue==5 || zValue == 0) {
            zValue = 0;
        } else {
            zValue = NumberConst.IntDef.INT_FIVE-zValue;
        }
        yValue = (num.longValue() + zValue) / NumberConst.IntDef.INT_FIVE ;

        if(zValue == 0) {
                planGenerationAmount[0] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[1] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[2] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[3] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[4] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
        }
        if(zValue == 1) {
                planGenerationAmount[0] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[1] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[2] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[3] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[4] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
        }
        if(zValue == 2) {
                planGenerationAmount[0] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[1] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[2] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[3] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
                planGenerationAmount[4] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
        }
        if(zValue == 3) {
                planGenerationAmount[0] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[1] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[2] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
                planGenerationAmount[3] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
                planGenerationAmount[4] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
        }
        if(zValue == 4) {
                planGenerationAmount[0] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue));
                planGenerationAmount[1] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
                planGenerationAmount[2] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
                planGenerationAmount[3] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
                planGenerationAmount[4] = new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(yValue-1));
        }
        return planGenerationAmount;
    }

    /**
     * @param distNumTon
     * @return
     */
    private static BigDecimal[]  createPlanAmount(BigDecimal distNumTon) {
        BigDecimal[] planGenerationAmount = new BigDecimal[NumberConst.IntDef.INT_FIVE];
        int index = NumberConst.IntDef.INT_ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;
        planGenerationAmount[index++] = BigDecimal.ZERO;

        // 获得可以计划生产量次数
        BigDecimal num = DecimalUtil.divide(distNumTon, new BigDecimal(NumberConst.IntDef.INT_THIRTY));

        int intNum = num.intValue();
        for(int i=1; i<=intNum; i++) {
            planGenerationAmount[i-1] = new BigDecimal(NumberConst.IntDef.INT_THIRTY);
        }

        if(intNum < NumberConst.IntDef.INT_FIVE) {
            planGenerationAmount[intNum] = distNumTon.subtract(new BigDecimal(NumberConst.IntDef.INT_THIRTY).multiply(new BigDecimal(intNum)));
        }

        return planGenerationAmount;
    }

    private List<DsPdVirtualStockPlan> createVirtualStockPlan(DsSuppDistNum suppDistNum) {

        // 数据检查
        String pdCode = suppDistNum.getPdCode();
        suppDistNum.setPdCode(pdCode);
        if (pdCode.length() != PDCODE_LENGTH) {
            logger.info("产品编码不符合规范");
            return null;
        }

        PDInfoParam param = new PDInfoParam();
        param.setPdCode(pdCode);
        PDInfoResult product = RestUtil.getProductInfo(param);

        // 填充包装相关信息
        setPdNormsStd(product);

        // 半旬计划数据作成。
        // 单位为千克
        BigDecimal distNum = suppDistNum.getDistNum();
        // 单位吨
        BigDecimal distNumTon = DecimalUtil.divide(distNum, new BigDecimal(NumberConst.IntDef.INT_THOUSAND));
        BigDecimal[] planGenerationAmount = null;
        // 当期计划生产量≤150吨时
        // 30N÷30＝N（1、2、3、4、5）不足5个补0数据
        if (distNumTon.compareTo(PlanGenerationAmountStandard.STANDARD_ONE) <= NumberConst.IntDef.INT_ZERO) {
            planGenerationAmount = this.createPlanAmount(distNumTon);
        } else {
            planGenerationAmount = this.createPlanAmount(distNumTon, 0);
        }

        List<DsPdVirtualStockPlan> virtualStockPlanList = new ArrayList<DsPdVirtualStockPlan>();
        // 各产品虚拟库存作成
        for (int i=1; i<=4; i++) {
            // 生产期虚拟库存作成
            if(i == 1) {
                for (int j=0; j<5;j++) {
                    String pdStockType = "" + i;
                    String halfCode = "" + (j+1);
                    createOnePlan(virtualStockPlanList,suppDistNum,product,pdStockType,halfCode,planGenerationAmount[j]);
                }
                // 第六个半旬
                createOnePlan(virtualStockPlanList,suppDistNum,product,"1","6",new BigDecimal(0));
            }
            // 待运库虚拟库存作成
            if(i == 2) {
                // 第1个半旬
                createOnePlan(virtualStockPlanList,suppDistNum,product,"2","1",new BigDecimal(0));
                for (int j=0; j<5;j++) {
                    String pdStockType = "" + i;
                    String halfCode = "" + (j+2);
                    createOnePlan(virtualStockPlanList,suppDistNum,product,pdStockType,halfCode,planGenerationAmount[j]);
                }
            }
            // 在途虚拟库存作成
            if(i == 3) {
                // 第1个半旬
                createOnePlan(virtualStockPlanList,suppDistNum,product,"3","1",new BigDecimal(0));
                for (int j=0; j<5;j++) {
                    String pdStockType = "" + i;
                    String halfCode = "" + (j+2);
                    createOnePlan(virtualStockPlanList,suppDistNum,product,pdStockType,halfCode,planGenerationAmount[j]);
                }
            }
            // 入销售库虚拟库存作成
            if(i == 4) {
                // 第1个半旬
                createOnePlan(virtualStockPlanList,suppDistNum,product,"4","1",new BigDecimal(0));
                for (int j=0; j<5;j++) {
                    String pdStockType = "" + i;
                    String halfCode = "" + (j+2);
                    createOnePlan(virtualStockPlanList,suppDistNum,product,pdStockType,halfCode,planGenerationAmount[j]);
                }
            }
        }
        return virtualStockPlanList;
    }

    private void createOnePlan(List<DsPdVirtualStockPlan> virtualStockPlanList,
                                        DsSuppDistNum suppDistNum,
                                        PDInfoResult product,
                                        String pdStockType,
                                        String halfCode,
                                        BigDecimal planGenerationAmount) {
        DsPdVirtualStockPlan virtualStockPlan = new DsPdVirtualStockPlan();
        virtualStockPlan.setSuppDsId(suppDistNum.getSuppDsId());
        virtualStockPlan.setDistMonth(suppDistNum.getDistMonth());
        virtualStockPlan.setLgcsCode(suppDistNum.getLgcsCode());
        virtualStockPlan.setSuppCode(suppDistNum.getSuppCode());
        //获取物流区名称及供应商名称
        ISL231193RsParam param = new ISL231193RsParam();
        List<ISL231193RsResult> list = RestUtil.getEpDateList(param);
        for (ISL231193RsResult rsResult : list) {
            if(!StringUtil.isNullOrEmpty(suppDistNum.getLgcsCode())
                    && suppDistNum.getLgcsCode().equals(rsResult.getLgcsAreaCode())){
                virtualStockPlan.setLgcsName(rsResult.getLgcsAreaName());
            }
            if(!StringUtil.isNullOrEmpty(suppDistNum.getSuppCode())
                    && suppDistNum.getSuppCode().equals(rsResult.getSlCode())){
                virtualStockPlan.setSuppName(rsResult.getEpName());
            }
        }

        virtualStockPlan.setPdStockType(pdStockType);
        virtualStockPlan.setHalfCode(halfCode);
        if(StringUtil.isNullOrEmpty(product.getClassesCode())){
            virtualStockPlan.setClassesCode("");
        }else{
            virtualStockPlan.setClassesCode(product.getClassesCode());
        }
        if(StringUtil.isNullOrEmpty(product.getClassesName())){
            virtualStockPlan.setClassesName("");
        }else{
            virtualStockPlan.setClassesName(product.getClassesName());
        }
        if(StringUtil.isNullOrEmpty(product.getBreedCode())){
            virtualStockPlan.setBreedCode("");
        }else{
            virtualStockPlan.setBreedCode(product.getBreedCode());
        }
        if(StringUtil.isNullOrEmpty(product.getBreedName())){
            virtualStockPlan.setBreedName("");
        }else{
            virtualStockPlan.setBreedName(product.getBreedName());
        }
        if(StringUtil.isNullOrEmpty(product.getFeatureCode())){
            virtualStockPlan.setFeatureCode("");
        }else{
            virtualStockPlan.setFeatureCode(product.getFeatureCode());
        }
        if(StringUtil.isNullOrEmpty(product.getFeatureName())){
            virtualStockPlan.setFeatureName("");
        }else{
            virtualStockPlan.setFeatureName(product.getFeatureName());
        }
        if(StringUtil.isNullOrEmpty(product.getGradeCode())){
            virtualStockPlan.setGradeCode("");
        }else{
            virtualStockPlan.setGradeCode(product.getGradeCode());
        }
        if(StringUtil.isNullOrEmpty(product.getGradeName())){
            virtualStockPlan.setGradeName("");
        }else{
            virtualStockPlan.setGradeName(product.getGradeName());
        }
        if(StringUtil.isNullOrEmpty(product.getNormsCode())){
            virtualStockPlan.setNormsCode("");
        }else{
            virtualStockPlan.setNormsCode(product.getNormsCode());
        }
        if(StringUtil.isNullOrEmpty(product.getNormsName())){
            virtualStockPlan.setNormsName("");
        }else{
            virtualStockPlan.setNormsName(product.getNormsName());
        }
        if(StringUtil.isNullOrEmpty(product.getMachiningCode())){
            virtualStockPlan.setMachiningCode("");
        }else{
            virtualStockPlan.setMachiningCode(product.getMachiningCode());
        }
        if(StringUtil.isNullOrEmpty(product.getMachiningName())){
            virtualStockPlan.setMachiningName("");
        }else{
            virtualStockPlan.setMachiningName(product.getMachiningName());
        }
        if(StringUtil.isNullOrEmpty(product.getWeightCode())){
            virtualStockPlan.setWeightCode("");
        }else{
            virtualStockPlan.setWeightCode(product.getWeightCode());
        }
        if(StringUtil.isNullOrEmpty(product.getWeightName())){
            virtualStockPlan.setWeightName("");
        }else{
            virtualStockPlan.setWeightName(product.getWeightName());
        }
        if(StringUtil.isNullOrEmpty(product.getNormsOut())){
            virtualStockPlan.setOutSpec("");
        }else{
            virtualStockPlan.setOutSpec(product.getNormsOut());
        }
        if(product.getWeightVal() == null){
            virtualStockPlan.setOutNw(new BigDecimal(0));
        }else{
            virtualStockPlan.setOutNw(product.getWeightVal());
        }

        virtualStockPlan.setPdCode(suppDistNum.getPdCode());
        virtualStockPlan.setAdjustDate(suppDistNum.getCrtTime());
        virtualStockPlan.setCrtId(suppDistNum.getCrtId());
        virtualStockPlan.setCrtTime(suppDistNum.getCrtTime());
        virtualStockPlan.setOrigPlanNum(planGenerationAmount);
        virtualStockPlan.setOldPlanNum(planGenerationAmount);
        virtualStockPlan.setNewPlanNum(planGenerationAmount);
        virtualStockPlanList.add(virtualStockPlan);
    }

    private void batchSaveVirtualStockPlan(List<DsPdVirtualStockPlan> virtualStockPlanList) {
        for (DsPdVirtualStockPlan virtualStockPlan : virtualStockPlanList) {
            long virtualStockPlanId = this.commonLogic.maxId("DS_PD_VIRTUAL_STOCK_PLAN", "VIRTUAL_STOCK_PLAN_ID");
            virtualStockPlan.setVirtualStockPlanId(virtualStockPlanId);
            virtualStockPlan.setOrigPlanNum(virtualStockPlan.getOrigPlanNum().multiply(new BigDecimal(NumberConst.IntDef.INT_THOUSAND)));
            virtualStockPlan.setOldPlanNum(virtualStockPlan.getOldPlanNum().multiply(new BigDecimal(NumberConst.IntDef.INT_THOUSAND)));
            virtualStockPlan.setNewPlanNum(virtualStockPlan.getNewPlanNum().multiply(new BigDecimal(NumberConst.IntDef.INT_THOUSAND)));
            try {
                this.save(SqlId.SQL_ID_BATCH_SAVE_VIRTUAL_STOCK_PLAN, virtualStockPlan);
            }catch (Exception e){
                e.printStackTrace();
            }

            long virtualStockActualId = this.commonLogic.maxId("DS_PD_VIRTUAL_STOCK_ACTUAL","VIRTUAL_STOCK_ACTUAL_ID");
            BaseParam param = new BaseParam();
            param.setFilter("virtualStockPlanId",String.valueOf(virtualStockPlanId));
            param.setFilter("virtualStockActualId",String.valueOf(virtualStockActualId));
            try {
                this.save(SqlId.SQL_ID_BATCH_SAVE_VIRTUAL_STOCK_ACTUAL,param);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void setWayInfo(BSC181101Param param, JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == NumberConst.IntDef.INT_ZERO) {
            logger.debug("没有供应商对应的产品信息");
            return;
        }

        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject wayObject = jsonArray.getJSONObject(i);
            param.setDistType(wayObject.getInt(JsonKey.JSON_WAY_TYPE_KEY));
            param.setDistNum(new BigDecimal(wayObject.getDouble(JsonKey.JSON_PASS_DEMAND_KEY)));
            DsSuppDistNum suppDistNum = setScSuppDistNumEntity(param);

            // Debug
            printLog(suppDistNum);

            String planProdInfoKey = suppDistNum.getDistMonth()
                    + suppDistNum.getLgcsCode()
                    + suppDistNum.getSuppCode()
                    + suppDistNum.getPdCode()
                    ;
            // 查询有没有数据，没有插入
            BSC181101Param baseParam = new BSC181101Param();
            baseParam.setFilter("distMonth",suppDistNum.getDistMonth());
            baseParam.setFilter("lgcsCode",String.valueOf(suppDistNum.getLgcsCode()));
            baseParam.setFilter("suppCode",String.valueOf(suppDistNum.getSuppCode()));
            baseParam.setFilter("pdCode",String.valueOf(suppDistNum.getPdCode()));
            baseParam.setFilter("brandType",String.valueOf(suppDistNum.getBrandType()));
            baseParam.setFilter("distType",String.valueOf(suppDistNum.getDistType()));
            if (super.getCount(SqlId.SQL_ID_BATCH_EXIST_CHK, baseParam) == NumberConst.IntDef.INT_ZERO) {
                suppDistNum.setSuppDsId(this.commonLogic.maxId("DS_SUPP_DIST_NUM", "SUPP_DS_ID"));
                try{
                    super.save(suppDistNum);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(planProdInfoMap.containsKey(planProdInfoKey)) {
                    // 存在，数量增加。
                    BigDecimal oldNum = planProdInfoMap.get(planProdInfoKey).getDistNum();
                    oldNum = oldNum.add(suppDistNum.getDistNum());
                    planProdInfoMap.get(planProdInfoKey).setDistNum(oldNum);
                } else {
                    // 不存在，追加作成。
                    planProdInfoMap.put(planProdInfoKey, suppDistNum);
                }
            }
        }
    }

    private void printLog(DsSuppDistNum suppDistNum ){
        StringBuffer log = new StringBuffer("");
        log.append("getDistMonth=" + suppDistNum.getDistMonth());
        log.append(" || getLgcsCode=" + suppDistNum.getLgcsCode());
        log.append(" || getSuppCode=" + suppDistNum.getSuppCode());
        log.append(" || getPdCode=" + suppDistNum.getPdCode());
        log.append(" || getBrandType=" + suppDistNum.getBrandType());
        log.append(" || getDistType=" + suppDistNum.getDistType());
        System.out.println(log.toString());
    }

    /**
     * 数据处理插入DB
     *
     * @param param param
     */
    @Transactional
    public void setSuppDistNum(BaseParam param) {
        logger.info("数据处理开始");
        BSC181101Param scm01Param = (BSC181101Param) param;
        JSONObject jsonObject = scm01Param.getJsonData();
        // 获得JSON Result 结果集合
        JSONArray resultArray = jsonObject.getJSONArray(JsonKey.JSON_RESULT_KEY);
        // Check Result结果是否为空
        if (resultArray == null || resultArray.length() == NumberConst.IntDef.INT_ZERO) {
            logger.debug("没有可用结果可以操作,程序结束.");
            return;
        }
        // 获得系统当前时间
        Date customerDate = DateTimeUtil.getCustomerDate();
        // 设置共通字段
        scm01Param.setActId("Batch");
        scm01Param.setActTime(customerDate);
        scm01Param.setCrtId("Batch");
        scm01Param.setCrtTime(customerDate);
        for (int i = 0; i < resultArray.length(); i++) {
            JSONObject resultObject = resultArray.getJSONObject(i);
            scm01Param.setDistMonth(getDistMonth());

            scm01Param.setLgcsCode(resultObject.getString(JsonKey.JSON_AREA_CODE_KEY));
            JSONArray supArray = resultObject.getJSONArray(JsonKey.JSON_SUP_LIST_KEY);
            this.setSupplierInfo(scm01Param, supArray);
        }

        // 保存计划数据
        Iterator iter = planProdInfoMap.values().iterator();
        while (iter.hasNext()) {
            DsSuppDistNum entry = (DsSuppDistNum) iter.next();
            List<DsPdVirtualStockPlan> virtualStockPlanList = this.createVirtualStockPlan(entry);
            if(virtualStockPlanList != null && virtualStockPlanList.size() > 0) {
                this.batchSaveVirtualStockPlan(virtualStockPlanList);
            }
        }
    }

    /**
     * 获取分销月度
     *
     * @return string
     */
    public String getDistMonth() {
        /*Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        String distMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, calendar.getTime());
        return distMonth.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_SIX);*/
        /**  modify by likai ,override Method 2016-7-28  start */
        //获取分销月度
        Date currentDate = new Date();
        String currentDay = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DD, currentDate);
        // 分销月份
        String distMonth = null;

        if (StringUtil.toInteger(currentDay) >= NumberConst.IntDef.INT_TWENTY_ONE) {
            // 21号开始属于下个分销月度
            Date distMothAdd = DateTimeUtil.addMonth(currentDate,1);
            distMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, distMothAdd);

        } else {
            distMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, currentDate);

        }
        return distMonth;
        /**  modify by likai ,override Method 2016-7-28  end */

    }

    /**
     * 设置传入DB的Entity
     *
     * @param param 传入参数
     * @return ScSuppDistNum
     */
    public DsSuppDistNum setScSuppDistNumEntity(BSC181101Param param) {
        DsSuppDistNum suppDistNum = new DsSuppDistNum();
        suppDistNum.setDistMonth(param.getDistMonth());
        suppDistNum.setLgcsCode(param.getLgcsCode());
        suppDistNum.setPdCode(param.getPdCode());
        suppDistNum.setSuppCode(param.getSuppCode());
        suppDistNum.setBrandType(param.getBrandType());
        suppDistNum.setDistType(param.getDistType());
        suppDistNum.setDistNum(param.getDistNum());
        suppDistNum.setActId(param.getActId());
        suppDistNum.setActTime(param.getActTime());
        suppDistNum.setCrtId(param.getCrtId());
        suppDistNum.setCrtTime(param.getCrtTime());
        suppDistNum.setUpdId(param.getCrtId());
        suppDistNum.setUpdTime(DateTimeUtil.getCustomerDate());
        suppDistNum.setVer(NumberConst.IntDef.INT_ONE);
        return suppDistNum;
    }

    /**
     * 填充产品包装信息
     *
     * @param product PDInfoResult
     */
    public void setPdNormsStd(PDInfoResult product) {
        logger.debug("填充产品包装信息");

        PDInfoParam param = new PDInfoParam();
        param.setClassesCode(product.getClassesCode());
        param.setMachiningCode(product.getMachiningCode());
        param.setBreedCode(product.getBreedCode());
        param.setFeatureCode(product.getFeatureCode());
        param.setWeightCode(product.getWeightCode());

        List<PDInfoResult> list = RestUtil.getPdNormsStd(param);

        if(list != null){
            PDInfoResult productInfo = list.get(0);
            if(productInfo != null){
                product.setNormsCode(productInfo.getNormsCode());
                product.setNormsName(productInfo.getNormsName());
                product.setNormsOut(productInfo.getNormsOut());
                if(productInfo.getNormsKg() != null) {
                    product.setNormsKg(productInfo.getNormsKg());
                } else {
                    product.setNormsKg("0");
                }
            }
        }
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
