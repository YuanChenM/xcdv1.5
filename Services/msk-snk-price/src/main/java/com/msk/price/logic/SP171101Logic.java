package com.msk.price.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171101Bean;
import com.msk.price.bean.SP171101Param;
import com.msk.price.bean.SP171101RsParam;
import com.msk.price.utils.CommRestUtil;
import com.msk.stock.bean.Stock;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * zhao_chen1
 */
@Service
    public class SP171101Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171101Logic.class);
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获得产品基础信息
     * @param sp171101Param
     * @return result
     */
    @Transactional(readOnly = true)
    public PageResult<SP171101Bean> findSP171101List(SP171101Param sp171101Param) {
        logger.info("获取产品信息开始");
       //调取产品接口信息
        PageResult<SP171101Bean> result = new PageResult<SP171101Bean>();
        SP171101RsParam sp171101RsParam = new SP171101RsParam();
        //类别
        if(!StringUtil.isEmpty(((String) sp171101Param.getFilterMap().get("classes")).trim())){
            sp171101RsParam.setClassesName(((String)sp171101Param.getFilterMap().get("classes")).trim());
        }
        //品名
        if(!StringUtil.isEmpty(((String)sp171101Param.getFilterMap().get("breed")).trim())){
            sp171101RsParam.setBreedName(((String) sp171101Param.getFilterMap().get("breed")).trim());
        }
        //配料
        if(!StringUtil.isEmpty(((String)sp171101Param.getFilterMap().get("machining")).trim())){
            sp171101RsParam.setMachiningName(((String)sp171101Param.getFilterMap().get("machining")).trim());
        }
        //规格
        if(!StringUtil.isEmpty(((String)sp171101Param.getFilterMap().get("feature")).trim())){
            sp171101RsParam.setFeatureName(((String)sp171101Param.getFilterMap().get("feature")).trim());
        }
        //产品编码
        if(!StringUtil.isEmpty(((String)sp171101Param.getFilterMap().get("pdTypeCode")).trim())){
            sp171101RsParam.setPdCode(((String)sp171101Param.getFilterMap().get("pdTypeCode")).trim());
        }
        //区域名称
        String lgcsCode =  StringUtil.toSafeString(sp171101Param.getFilterMap().get("lgcsName"));
        if(!StringUtil.isEmpty(lgcsCode)){
            String[] lgcsCodes = lgcsCode.split(StringConst.COMMA);
            sp171101Param.getFilterMap().put("lgcsCodes", lgcsCodes);
            sp171101RsParam.setLgcsCode(((String) sp171101Param.getFilterMap().get("lgcsName")).trim());
        }
        sp171101RsParam.setPageNo(sp171101Param.getStartPos()/sp171101Param.getPageSize()+NumberConst.IntDef.INT_ONE);
        sp171101RsParam.setPageCount(sp171101Param.getPageSize());

        DbUtils.buildLikeCondition(sp171101Param, "pdName", DbUtils.LikeMode.FRONT);
        List<SP171101Bean> pdInfo =this.findPageList(sp171101Param,SP171101Bean.class);
        logger.info("获取产品信息结束");
        result = getResultInfo(pdInfo, sp171101Param,sp171101RsParam);
        return result;
    }

    /**
     * 获取库存信息
     * @param sp171101Param
     * @return sp171101Beans
     */
    public List<SP171101Bean> getStockInfo(List<SP171101Bean> pdInfo,SP171101Param sp171101Param,SP171101RsParam sp171101RsParam)
    {
        logger.info("调取库存接口信息开始");
        //循环pd产品信息，作为参数集合调取库存
        List<SP171101Bean> sp171101Beans = new ArrayList<SP171101Bean>();
        for(SP171101Bean proInfo : pdInfo) {
            proInfo.setPublishTotalNum((String)sp171101Param.getFilterMap().get("publishTotalNum"));
            proInfo.setSalePlatform("1");
            sp171101Beans.add(proInfo);
        }
        sp171101RsParam.setPdList(sp171101Beans);
        List<Stock> stockCntList =  CommRestUtil.getStockInfo(sp171101RsParam,sp171101Beans);
        if(CollectionUtils.isEmpty(stockCntList)){
            for(SP171101Bean bean : sp171101Beans){
                bean.setStockCnt(new BigDecimal(NumberConst.IntDef.INT_ZERO));
            }
            return sp171101Beans;
        }

        for (SP171101Bean bean : sp171101Beans){
            BigDecimal stockQty = new BigDecimal(NumberConst.IntDef.INT_ZERO);
            for(Stock stock : stockCntList){
                if(stock == null)
                {
                    continue;
                }
                String pdTypeCode = bean.getPdTypeCode();
                String pdCode = stock.getPdTypeCode();
                String lgcsCode = bean.getLgcsCode();
                String pdLgcsCode = stock.getLgcsCode();
                if( pdTypeCode.equals(pdCode) && lgcsCode.equals(pdLgcsCode)){
                    stockQty =  DecimalUtil.add(stock.getPdTypeSumStock(), stockQty);
                }
            }

            //箱数转成吨数，箱数*净重/1000
            BigDecimal weight = new BigDecimal(NumberConst.IntDef.INT_ZERO);
            if(!StringUtil.isNullOrEmpty(bean.getWeight()) && !bean.getWeight().equals(StringConst.MIDDLE_LINE)){
                int num = bean.getWeight().length();
                for(int i = 0 ; i < bean.getWeight().length() ; i++){ //循环遍历字符串
                    if(Character.isLetter(bean.getWeight().charAt(i))){     //用char包装类中的判断数字的方法判断每一个字符
                        num = i;
                        break;
                    }
                }
                weight = new BigDecimal(bean.getWeight().substring(NumberConst.IntDef.INT_ZERO,num));
                stockQty = DecimalUtil.divide(DecimalUtil.multiply(stockQty,weight),new BigDecimal(NumberConst.IntDef.INT_THOUSAND));
            }

            bean.setStockCnt(stockQty);
        }

        return sp171101Beans;
    }

    /**
     * 整合接口和数据库数据,显示在页面
     * @param sp171101Param
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171101Bean> getResultInfo(List<SP171101Bean> pdInfo,SP171101Param sp171101Param,SP171101RsParam sp171101RsParam){
        PageResult<SP171101Bean> result = new PageResult<SP171101Bean>();
        result.setRecordsTotal(this.getPageCount(sp171101Param));
        if(CollectionUtils.isEmpty(pdInfo)){
            result.setData(new ArrayList<SP171101Bean>());
            return result;
        }
        List<SP171101Bean> sp171101Beans = getStockInfo( pdInfo, sp171101Param,sp171101RsParam);
        for(SP171101Bean sp171101Bean : sp171101Beans){
            sp171101Bean.setDemandYearMonth(sp171101Param.getFilterMap().get("cycle").toString());
            BaseParam baseParam = new BaseParam();
            baseParam.getFilterMap().put("demandYearMonth", sp171101Bean.getDemandYearMonth());
            baseParam.getFilterMap().put("lgcsCode", sp171101Bean.getLgcsCode());
            baseParam.getFilterMap().put("pdTypeCode", sp171101Bean.getPdTypeCode());
            //获取预计需求量
            String forecastNumStr = (String)this.findObject(SqlId.SQL_ID_GET_FORECAST_NUM, baseParam);
            if(!StringUtil.isNullOrEmpty(forecastNumStr) && !forecastNumStr.equals("-")){
                BigDecimal forecastNum = new BigDecimal(forecastNumStr);

                //箱数转成吨数，箱数*净重/1000
                BigDecimal weight = new BigDecimal(NumberConst.IntDef.INT_ZERO);
                if(!StringUtil.isNullOrEmpty(sp171101Bean.getWeight()) && !sp171101Bean.getWeight().equals(StringConst.MIDDLE_LINE)){
                    int num = sp171101Bean.getWeight().length();
                    for(int i = 0 ; i < sp171101Bean.getWeight().length() ; i++){ //循环遍历字符串
                        if(Character.isLetter(sp171101Bean.getWeight().charAt(i))){     //用char包装类中的判断数字的方法判断每一个字符
                            num = i;
                            break;
                        }
                    }
                    weight = new BigDecimal(sp171101Bean.getWeight().substring(NumberConst.IntDef.INT_ZERO,num));
                    forecastNumStr = DecimalUtil.divide(DecimalUtil.multiply(forecastNum,weight),
                            new BigDecimal(NumberConst.IntDef.INT_THOUSAND)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                }
            }

            sp171101Bean.setForecastNum(forecastNumStr);

            SP171101Bean bean = this.findOne(SqlId.SQL_ID_GET_PUBULISH_TOTAL_NUM, sp171101Bean);
            //本期需求
            if(bean != null){
                sp171101Bean.setPublishTotalNum(bean.getPublishTotalNum());
                sp171101Bean.setPublishId(bean.getPublishId());
            }else {
                sp171101Bean.setPublishTotalNum("未发布");
            }
        }
        result.setData(sp171101Beans);
        return result;
    }

    /**
     * 获取当前默认申报周期
     * @return sp171101Bean
     */
    public SP171101Bean getNowpublishYmList(){
        logger.info("获取系统默认周期开始");
        SP171101Bean sp171101Bean = new SP171101Bean();
        String nowYearMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH,DateTimeUtil.getCustomerDate());
        String nextYearMonth = DateTimeUtil.monthAddN(nowYearMonth,NumberConst.IntDef.INT_ONE);
        Date date = DateTimeUtil.parseDate(nextYearMonth,DateTimeUtil.FORMAT_YEAR_MONTH);
        String demandMonth  = nextYearMonth.substring(NumberConst.IntDef.INT_FOUR,NumberConst.IntDef.INT_SIX);
        String  demandStartDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,DateTimeUtil.firstDay(date));
        String  demandEndDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,DateTimeUtil.lastDay(date));
        sp171101Bean.setDemandYearMonth(nextYearMonth);
        String demandYearMonthShow = "第"+demandMonth+"期"+demandStartDate+"-"+demandEndDate;
        sp171101Bean.setLimitWriteDate(nowYearMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX) + "月16日 - " + nowYearMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX) + "月20日");
        sp171101Bean.setDemandYearMonthShow(demandYearMonthShow);
        logger.info("获取系统默认周期结束");
        return sp171101Bean;
    }

    /**
     * 获取申报周期
     * @param param
     * @return sp171101Beans
     */
    @Transactional(readOnly = true)
       public List<SP171101Bean> publishYmList(SP171101Param param ){
           logger.info("需求发布年月周期取得开始");
           List<SP171101Bean> sp171101Beans = super.findList(SqlId.FIND_DEMAND_YEARMONTH_LIST,null);
           String nowYearMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH,DateTimeUtil.getCustomerDate());
           String nextYearMonth = DateTimeUtil.monthAddN(nowYearMonth,NumberConst.IntDef.INT_ONE);
           if(CollectionUtils.isEmpty(sp171101Beans)){
               sp171101Beans.add(getNowpublishYmList());
               return sp171101Beans;
           }
           boolean flag = false;
           for(SP171101Bean sp171101Bean : sp171101Beans){
               sp171101Bean.setNowYearMonth(nowYearMonth);
               String demandYearMonth = sp171101Bean.getDemandYearMonth();
               if(demandYearMonth.equals(nextYearMonth)){
                   flag = true;
               }
               Date date = DateTimeUtil.parseDate(demandYearMonth,DateTimeUtil.FORMAT_YEAR_MONTH);
               String demandMonth  = demandYearMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX);
               String  demandStartDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, DateTimeUtil.firstDay(date));
               String  demandEndDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,DateTimeUtil.lastDay(date));
               sp171101Bean.setDemandYearMonthShow("第" + demandMonth + "期 " + demandStartDate + "-" + demandEndDate);
               String lastMonth = DateTimeUtil.getLastMonth(demandYearMonth);
               sp171101Bean.setLimitWriteDate(lastMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX) + "月16日 - " + lastMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX) + "月20日");
           }
           if(!flag){
               sp171101Beans.add(NumberConst.IntDef.INT_ZERO,getNowpublishYmList());

           }

           if (!StringUtil.isEmpty(param.getDemandYearmonth())){
               getYearMonthResult(sp171101Beans,param);
           }

           logger.info("需求发布年月周期取得结束");
           return sp171101Beans;
        }

    /**
     * 详细页面保留周期条件进入list页面
     * @param sp171101Beans
     * @param param
     */
    public void getYearMonthResult(List<SP171101Bean> sp171101Beans,SP171101Param param){
        String demandYearMonthFirst = param.getDemandYearmonth();
        SP171101Bean bean = new SP171101Bean();
        int intFlag = NumberConst.IntDef.INT_ZERO;
        int indexFlag = NumberConst.IntDef.INT_ZERO;
        int delIndex = NumberConst.IntDef.INT_ZERO;
        for (SP171101Bean sp171101Bean : sp171101Beans){
            String demandYearMonth = sp171101Bean.getDemandYearMonth();
            indexFlag++;
            if(demandYearMonth.equals(demandYearMonthFirst)){
                bean = sp171101Bean;
                intFlag++;
                delIndex = indexFlag - NumberConst.IntDef.INT_ONE;
            }
        }

        if(intFlag > NumberConst.IntDef.INT_ZERO){
            sp171101Beans.remove(delIndex);
            sp171101Beans.add(NumberConst.IntDef.INT_ZERO,bean);
        }
    }

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        String SQL_ID_GET_FORECAST_NUM = "getForecastNum";
        String SQL_ID_GET_PUBULISH_TOTAL_NUM="getPublishTotalNum";
        String FIND_DEMAND_YEARMONTH_LIST = "findYearMonthList";
    }

}
