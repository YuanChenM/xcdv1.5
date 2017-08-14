package com.msk.ds.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.CommConstant;
import com.msk.ds.bean.DistSuppChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhou_yajun on 2016/2/22.
 */
public class DistSuppChainLogic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC183102Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    /**
     * SQL Map 中SQL ID定义
     * @author gyh
     */
    interface SqlId {
        //获取分销月度List
        String SQL_GET_DIST_MONTH_LIST = "findDistMonthList";
        //获取物流区域List
        String SQL_GET_LOGIS_AREA_LIST = "findLogisAreaList";
        //获取供应商信息List
        String SQL_GET_SUPP_INFO_LIST = "findSuppInfoList";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    /**
     * 获取分销月份
     * @return 分销月份
     */
    @Transactional(readOnly = true)
    public List<DistSuppChain> findDistMonth(){
        BaseParam param = new BaseParam();
        int nowYear = DateTimeUtil.getYear(DateTimeUtil.getCustomerDate());
        param.setFilter("distYear", DbUtils.buildLikeCondition(String.valueOf(nowYear), DbUtils.LikeMode.FRONT));
        param.setFilter("distBeforeYear",DbUtils.buildLikeCondition(String.valueOf(nowYear - NumberConst.IntDef.INT_ONE), DbUtils.LikeMode.FRONT));
        List<DistSuppChain> distMonthList = this.findList(SqlId.SQL_GET_DIST_MONTH_LIST,param);
        return distMonthList;
    }
    /**
     * 获取分销月份下的物流区域
     * @param distMonth
     * @return logisArea
     */
    @Transactional(readOnly = true)
    public List<DistSuppChain> findLogisticsAreaList(String distMonth){
        BaseParam param = new BaseParam();
        param.setFilter("distMonth",distMonth);
        List<DistSuppChain> logisticsAreaList = this.findList(SqlId.SQL_GET_LOGIS_AREA_LIST,param);
        return logisticsAreaList;
    }
    /**
     * 获取分销月份和物流区域下的供应商信息
     * @param param
     * @return suppInfo
     */
    @Transactional(readOnly = true)
    public List<DistSuppChain> findSuppInfoList(BaseParam param){
        List<DistSuppChain> suppInfoList = this.findList(SqlId.SQL_GET_SUPP_INFO_LIST,param);
        List<DistSuppChain> rtnList = new ArrayList();

        Map<String, Object> paramMap = param.getFilterMap();
        String userType = (String)paramMap.get("userType");
        /**90~98行做了调整，如需要，请参考旧工程。*/
        String slCode = (String)paramMap.get("slCode");
        if(!StringUtil.isNullOrEmpty(userType) && CommCodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(userType) ) {
            // 只获取自己的数据
            for(DistSuppChain suppInfoObj : suppInfoList){
                // 根据LoginID获取登录用户Code
                if(!StringUtil.isNullOrEmpty(slCode) && slCode.equals(suppInfoObj.getSupplierCode())) {
                    rtnList.add(suppInfoObj);
                }
            }
        } else {
            rtnList = suppInfoList;
        }
        return rtnList;
    }
    /**
     * 获取生产计划类型List
     * @return planTypeList
     */
    public List<CommConstant> findPlanTypeList(){
        Map<String,String> pdStockTypeMap = CodeMasterManager.findCodeMasterMap("PdStockType");
        List<CommConstant> list = new ArrayList<CommConstant>();
        for (String key:pdStockTypeMap.keySet()){
            CommConstant c = new CommConstant();
            c.setConstantName(pdStockTypeMap.get(key));
            c.setConstantValue(key);
            list.add(c);
        }
       // return this.commonLogic.findConstantList("PdStockType");
        return list;
    }
    /**
     * 获取半旬List
     * @param distMonth 分销月度
     * @param halfCode 当前半旬(不需要时请传0)
     * @return halfNameList
     */
    public List<DistSuppChain> getHalfNameList(String distMonth, int halfCode){
        List<DistSuppChain> halfNameList = new ArrayList<>();
        int year = Integer.parseInt(distMonth.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_FOUR));
        int month = Integer.parseInt(distMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX));
        for(int i = halfCode + NumberConst.IntDef.INT_ONE;i < NumberConst.IntDef.INT_SEVEN;i++){
            DistSuppChain productHalf = new DistSuppChain();
            String halfName = "";
            if(i <= NumberConst.IntDef.INT_TWO){
                if(month == NumberConst.IntDef.INT_ONE){
                    halfName = getHalfName(NumberConst.IntDef.INT_TWELVE,i,year - NumberConst.IntDef.INT_ONE);
                }else {
                    halfName = getHalfName(month - NumberConst.IntDef.INT_ONE,i,year);
                }
            }else{
                halfName = getHalfName(month,i,year);
            }
            productHalf.setHalfCode(String.valueOf(i));
            productHalf.setHalfName(halfName);
            halfNameList.add(productHalf);
        }
        return halfNameList;
    }
    /**
     * 获得半旬的名称
     * @param month 月
     * @param half 第几个半旬
     * @param year 年
     * @return 半旬字符串
     */
    public String getHalfName(int month,int half,int year){
        //List<CommConstant> halfCodeType = this.commonLogic.findConstantList("HalfCodeType");
        Map<String,String> halfCodeTypeMap = CodeMasterManager.findCodeMasterMap("HalfCodeType");
        List<CommConstant> halfCodeType = new ArrayList<>();
        for (String key:halfCodeTypeMap.keySet()){
            CommConstant c = new CommConstant();
            c.setConstantName(halfCodeTypeMap.get(key));
            c.setConstantValue(key);
            halfCodeType.add(c);
        }
        String halfName = "";
        String monthStr = "";
        if(month < NumberConst.IntDef.INT_TEN){
            monthStr = "0" + month;
        }else{
            monthStr = String.valueOf(month);
        }
        Date date = DateTimeUtil.parseDate(year+"_"+month,"yyyy_MM");
        int lastDay = DateTimeUtil.getDayOfMonth(DateTimeUtil.lastDay(date));
        for (int i = 0; i < halfCodeType.size(); i++) {
            if(Integer.parseInt(halfCodeType.get(i).getConstantValue()) == half){
                if(half == 2){
                    halfName = monthStr + "月" + halfCodeType.get(i).getConstantName().replace("N",String.valueOf(lastDay));
                    break;
                }else {
                    halfName = monthStr + "月" + halfCodeType.get(i).getConstantName();
                    break;
                }
            }
        }
        return halfName;
    }
    /**
     * 获取当前日期对应的半旬Code
     * @return nowHalfCode
     */
    public int getNowHalfCode(String distMonth){
        int year = Integer.parseInt(distMonth.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_FOUR));
        int month = Integer.parseInt(distMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX));
        int nowHalfCode = NumberConst.IntDef.INT_HUNDRED;
        int nowYear = DateTimeUtil.getYear(DateTimeUtil.getCustomerDate());
        int nowMonth = DateTimeUtil.getMonthOfYear(DateTimeUtil.getCustomerDate());
        int nowDay = DateTimeUtil.getDayOfMonth(DateTimeUtil.getCustomerDate());
        if(year == nowYear){
            if(month == nowMonth){
                if(nowDay >= NumberConst.IntDef.INT_ONE && nowDay <= NumberConst.IntDef.INT_FIVE){
                    nowHalfCode = NumberConst.IntDef.INT_THREE;
                }else if(nowDay >= NumberConst.IntDef.INT_SIX && nowDay <= NumberConst.IntDef.INT_TEN){
                    nowHalfCode = NumberConst.IntDef.INT_FOUR;
                }else if(nowDay >= NumberConst.IntDef.INT_ELEVEN && nowDay <= NumberConst.IntDef.INT_FIFTEEN){
                    nowHalfCode = NumberConst.IntDef.INT_FIVE;
                }else if(nowDay >= NumberConst.IntDef.INT_SIXTEEN && nowDay <= NumberConst.IntDef.INT_TWENTY){
                    nowHalfCode = NumberConst.IntDef.INT_SIX;
                }
            }else if(month == nowMonth + NumberConst.IntDef.INT_ONE){
                if(nowDay >= NumberConst.IntDef.INT_TWENTY_ONE && nowDay <= NumberConst.IntDef.INT_TWENTY_FIVE){
                    nowHalfCode = NumberConst.IntDef.INT_ONE;
                }else if(nowDay >= NumberConst.IntDef.INT_TWENTY_SIX){
                    nowHalfCode = NumberConst.IntDef.INT_TWO;
                }
            }
        }else if(year == nowYear + NumberConst.IntDef.INT_ONE){
            if(month == NumberConst.IntDef.INT_ONE && nowMonth == NumberConst.IntDef.INT_TWELVE){
                if(nowDay >= NumberConst.IntDef.INT_TWENTY_ONE && nowDay <= NumberConst.IntDef.INT_TWENTY_FIVE){
                    nowHalfCode = NumberConst.IntDef.INT_ONE;
                }else if(nowDay >= NumberConst.IntDef.INT_TWENTY_SIX){
                    nowHalfCode = NumberConst.IntDef.INT_TWO;
                }
            }
        }
        return nowHalfCode;
    }

    /**
     * 根据日期转成对应的分销月份(规则：每月的21号作为下个月的开始
     * 例如：20151221 —》 201601 ）
     * @param distMonth 日期Format（yyyyMMdd）
     * @return 对应的半旬 （yyyyMM）
     */
    public String getNowHalfDate(String distMonth) {
        String halfYm = "";
        if(!StringUtil.isNullOrEmpty(distMonth) && distMonth.length() == 8) {
            int year = Integer.parseInt(distMonth.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_FOUR));
            int month = Integer.parseInt(distMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX));
            int day = Integer.parseInt(distMonth.substring(NumberConst.IntDef.INT_SIX, NumberConst.IntDef.INT_EIGHT));

            if (day >= NumberConst.IntDef.INT_TWENTY_ONE) {
                if (month >= NumberConst.IntDef.INT_TWELVE) {
                    year = year + 1;
                    month = 1;
                } else {
                    month = month + 1;
                }
            }
            //返回格式为：yyyyMM的值
            halfYm = String.format("%04d", year) + String.format("%02d", month);
        }
        return halfYm;
    }
}
