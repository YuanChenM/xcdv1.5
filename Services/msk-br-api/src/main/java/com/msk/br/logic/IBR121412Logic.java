package com.msk.br.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.*;
import com.msk.bs.bean.IBS2101114Bean;
import com.msk.bs.bean.IBS2101114Param;
import com.msk.bs.bean.IBS2101114Result;
import com.msk.bs.bean.IBS2101132RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.ChangeCycle;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.ChangeCycleUtils;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrOOrderInfo;
import com.msk.core.entity.SlBsBuyer;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 根据订单数据同步买家上线状态
 */
@Service
public class IBR121412Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121412Logic.class);

    interface SqlId {
        static String QUERY_BUYER_MARKET_STATUS_AND_ORDER = "queryBuyerMarketStatusAndOrder";
        static String MODIFY_CURRENT_MARKET_STATUS = "modifyCurrentMarketStatus";
        static String ADD_MARKET_STATUS_HISTORY = "addMarketingStatusHistory";
        static String COUNT_ORDER_BY_ORDER_TIME = "countOrderByOrderTime";
        static String FIND_CURRENT_MARKET_STATUS_BY_BUYER_ID = "findCurrentMarketStatusByBuyerId";
        static String MODIFY_MARKET_STATUS_BY_BUYER_ID = "modifyMarketStatusByBuyerId";
        static String FIND_CURRENT_BUYER_CODE_BY_ID = "findCurrentBuyerCodeByBuyerId";
        static String MODIFY_BUYER_POOL_CODE = "modifyBuyerPoolCode";
        static String ADD_MARKET_STATUS_PLAN = "addMarketingStatusPlan";
        static String QUERY_BUYER_MARKET_STATUS_CHANGE_PLAN = "queryBuyerMarketStatusChangePlan";
        static String MODIFY_BASIC_MARKET_STATUS_BY_BUYER_ID = "modifyBasicMarketStatusByBuyerId";
        static String MODIFY_MARKET_STATUS_PLAN_BY_BUYER_ID = "modifyMarketStatusPlanByBuyerId";
        static String QUERY_MARKET_STATUS_MODIFY_TIME_MAX = "queryMarketStatusModifyTimeMax";
        static String FIND_STATUS_CHANGE_PLAN_BY_BUYER_ID = "findStatusChangePlanByBuyerId";
        static String FIND_CURRENT_ORDER_INFO_BY_BUYER_ID = "findCurrentOrderInfoByBuyerId";
    }

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    private static final String FORMAT_DATE_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    @Transactional
    public List<IBR121412RsBean> synMarketingStatusByOrder(BaseParam param) {
        List<IBR121412RsBean> synBuyerList = new ArrayList<>();
        List<IBR121412RsBean> statusAndOrderList = this.findList(SqlId.QUERY_BUYER_MARKET_STATUS_AND_ORDER, param);
        if (!CollectionUtils.isEmpty(statusAndOrderList)) {
            for (IBR121412RsBean buyer : statusAndOrderList) {
                logger.info("Batch404: start buyerId="+buyer.getBuyerId());
                buyer.setActId(param.getActId());
                buyer.setActTime(param.getActTime());
                buyer.setUpdId(param.getUpdId());
                buyer.setUpdTime(param.getUpdTime());
                buyer.setCrtId(param.getCrtId());
                buyer.setCrtTime(param.getCrtTime());
                judgeBuyerMarketStatus(buyer, synBuyerList);
                logger.info("Batch404: end");
            }
        }
        return synBuyerList;
    }

    /**
     * 判断买家上线状态
     *
     * @param buyer
     * @param synBuyerList
     */
    private void judgeBuyerMarketStatus(IBR121412RsBean buyer, List<IBR121412RsBean> synBuyerList) {
        switch (buyer.getMarketingsStatus()) {
            // 预注册
            case BuyersConstant.MarketingsStatus.PreRegister:
                preRegisterBuyer(buyer, synBuyerList);
                break;
            // 激活期
            case BuyersConstant.MarketingsStatus.ActivePeriod:
                activePeriodBuyer(buyer, synBuyerList);
                break;
            // 稳定期
            case BuyersConstant.MarketingsStatus.StablePeriodCentral:
                stablePeriodBuyer(buyer, synBuyerList);
                break;
            case BuyersConstant.MarketingsStatus.StablePeriodStandard:
                stablePeriodBuyer(buyer, synBuyerList);
                break;
            // 预警期
            case BuyersConstant.MarketingsStatus.EarlyWarnPeriod:
                earlyWarnPeriodBuyer(buyer, synBuyerList);
                break;
            // 销售期公众买家
            case BuyersConstant.MarketingsStatus.SalePublicBuyers:
                salePublicBuyer(buyer, synBuyerList);
                break;
            // 停业
            case BuyersConstant.MarketingsStatus.OutBusiness:
                outBusinessBuyer(buyer, synBuyerList);
                break;
        }
    }

    /**
     * 停业买家操作
     * @param buyer
     * @param synBuyerList
     */
    @Transactional
    private void outBusinessBuyer(IBR121412RsBean buyer, List<IBR121412RsBean> synBuyerList) {
        logger.info("Batch404: 停业买家");
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", buyer.getBuyerId());
        IBR121412RsBean history = this.findOne(SqlId.FIND_CURRENT_MARKET_STATUS_BY_BUYER_ID, param);
        if(history != null && history.getModifyTime() != null){
            logger.info("Batch404: 履历表存在该买家有效数据");
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_YYYYMMDD_HHMMSS);
            String outDate = format.format(history.getModifyTime());
            param.setFilter("outDate", outDate);
            BrOOrderInfo orderInfo = this.findOne(SqlId.FIND_CURRENT_ORDER_INFO_BY_BUYER_ID, param);
            if(orderInfo != null && orderInfo.getOrderTime() != null){
                logger.info("Batch404: 该买家有有效订单;停业时间：" + outDate);
                // 判断之前状态是否为未营销成功
                IBR121412RsBean synBuyer = null;
                if(history.getOldStatusBreed() != null){
                    if(history.getOldStatusBreed().equals(BuyersConstant.MarketingsStatus.NoMarket)){ // 未营销成功
                        logger.info("Batch404: 停业之前的状态为未营销成功");
                        synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.OutBusiness, BuyersConstant.MarketingsStatus.NoMarket);
                        if (null != synBuyer) {
                            synBuyerList.add(synBuyer);
                        }
                        addMarketingStatusHistory(synBuyer);
                    }else{
                        synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.OutBusiness, BuyersConstant.MarketingsStatus.ActivePeriod);

                        // 修改首次订单时间
                        synBuyer.setFirstOrderTime(orderInfo.getOrderTime());
                        addMarketingStatusPlan(synBuyer, 1);
                    }
                }else {
                    logger.info("Batch404: 停业之前的状态为空，默认为非未营销成功");
                    synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.OutBusiness, BuyersConstant.MarketingsStatus.ActivePeriod);

                    // 修改首次订单时间
                    synBuyer.setFirstOrderTime(orderInfo.getOrderTime());
                    addMarketingStatusPlan(synBuyer, 1);
                }

            }else {
                logger.info("Batch404: 该买家没有有效订单");
            }
        }else {
            logger.info("Batch404: 履历表不存在该买家有效数据");
            addNewMarketingsStatusHistory(buyer);
        }
    }
    /**
     * 预注册买家操作
     *
     * @param buyer
     */
    @Transactional
    private void preRegisterBuyer(IBR121412RsBean buyer, List<IBR121412RsBean> synBuyerList) {
        logger.info("Batch404: 预注册买家");
        if (buyer.getOrderCount() >= NumberConst.IntDef.INT_ONE) {
            logger.info("Batch404: 该买家存在有效订单");
            // 更新新老上线状态名称
            IBR121412RsBean synBuyer
                    = assignBean(buyer, BuyersConstant.MarketingsStatus.PreRegister, BuyersConstant.MarketingsStatus.ActivePeriod);
            // 新增上线状态更改计划
            addMarketingStatusPlan(synBuyer, NumberConst.IntDef.INT_ONE);
        }
    }

    /**
     * 激活期买家操作
     *
     * @param buyer
     * @param synBuyerList
     */
    @Transactional(readOnly = true)
    private void activePeriodBuyer(IBR121412RsBean buyer, List<IBR121412RsBean> synBuyerList) {
        logger.info("Batch404: 激活期买家");
        IBR121412RsBean synBuyer = null;
        ChangeCycle cycle = null;
        // 最后一次下单日开始，连续四个自然半旬未下单，变为预警期
        Date lastOrderDate = buyer.getLastOrderTime();
        cycle = ChangeCycleUtils.getChangeCycleByRange(lastOrderDate, 1, 4);
        String startTime = cycle.getStartDateStr() + " 00:00:00";
        String endTime = cycle.getEndDateStr() + " 23:59:59";

        // 判断结束时间是否小于当前时间
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(cycle.getEndDate());
        Calendar nowCalendar = Calendar.getInstance();

        IBS2101114Bean slBsBuyer = getBindStatusByBuyerId(buyer.getBuyerId());
        if(slBsBuyer == null){// 判断：与冻品管家手动解绑，变为销售期公众买家 bindStatus=1
            logger.info("Batch404: 没有查询到该买家的专属管家信息");
            synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.ActivePeriod, BuyersConstant.MarketingsStatus.SalePublicBuyers);
            //添加到需要同步上线状态的买家列表中
            if (null != synBuyer) {
                synBuyerList.add(synBuyer);
            }
            addMarketingStatusHistory(synBuyer);
        }else if(endCalendar.before(nowCalendar)){ // 判断是否有订单
            int orderCount = getOrderCount(buyer.getBuyerId(), startTime, endTime);
            //logger.info("Batch404: 订单数量：" + orderCount + "; 开始时间：" + startTime + "; 结束时间：" + endTime);
            if (orderCount == NumberConst.IntDef.INT_ZERO) {
                synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.ActivePeriod, BuyersConstant.MarketingsStatus.EarlyWarnPeriod);
                //添加到需要同步上线状态的买家列表中
                if (null != synBuyer) {
                    synBuyerList.add(synBuyer);
                }
                addMarketingStatusHistory(synBuyer);
            }
        }else {
            // 激活期生效日后，连续两个自然半旬均下单的第三个自然半旬首日
            // 或连续六个自然半旬中任意三个半旬下单的第七个自然半旬首日 变为稳定期
            int haveOrderCount = NumberConst.IntDef.INT_ZERO;
            // 激活期生效日
            Date modifyTime = getStartTimeToDate(buyer);
            // 记录查询到第几个自然半旬
            int i;
            for (i = NumberConst.IntDef.INT_ONE; i < NumberConst.IntDef.INT_SEVEN; i++) {
                cycle = ChangeCycleUtils.getChangeCycle(modifyTime, i);
                int count = getOrderCount(buyer.getBuyerId(), cycle.getStartDateStr() + " 00:00:00", cycle.getEndDateStr() + " 23:59:59");
                logger.info("Batch404: 第" + i + "个自然半旬的订单数：" + count + "; 开始时间："
                        + cycle.getStartDateStr() + " 00:00:00" + "; 结束时间：" + cycle.getEndDateStr() + " 23:59:59");
                if (count > NumberConst.IntDef.INT_ZERO) {
                    haveOrderCount++;
                    // 六个自然半旬内已经有三个半旬有订单
                    if (haveOrderCount == NumberConst.IntDef.INT_THREE) {
                        break;
                    }
                }
                // 前两个自然半旬均有订单
                if (i == NumberConst.IntDef.INT_TWO && haveOrderCount == NumberConst.IntDef.INT_TWO) {
                    break;
                }
            }
            // 连续两个自然半旬均下单
            if (i == 2) {
                logger.info("Batch404: 连续两个自然半旬均下单");
                synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.ActivePeriod, BuyersConstant.MarketingsStatus.StablePeriodStandard);

                // 修改首次订单时间
                synBuyer.setFirstOrderTime(cycle.getStartDate());
                addMarketingStatusPlan(synBuyer, NumberConst.IntDef.INT_ONE);
            } else if (haveOrderCount == 3) { // 六个自然半旬内有三个自然半旬下单
                logger.info("Batch404: 六个自然半旬内有三个自然半旬下单");
                synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.ActivePeriod, BuyersConstant.MarketingsStatus.StablePeriodStandard);

                // 修改首次订单时间
                synBuyer.setFirstOrderTime(cycle.getStartDate());
                addMarketingStatusPlan(synBuyer, NumberConst.IntDef.INT_SEVEN - i);
            }
        }
    }

    /**
     * 稳定期买家操作
     *
     * @param buyer
     * @param synBuyerList
     */
    @Transactional(readOnly = true)
    private void stablePeriodBuyer(IBR121412RsBean buyer, List<IBR121412RsBean> synBuyerList) {
        logger.info("Batch404: 稳定期买家");
        IBR121412RsBean synBuyer = null;
        // 判断：与冻品管家手动解绑，变为销售期公众买家
        IBS2101114Bean slBsBuyer = getBindStatusByBuyerId(buyer.getBuyerId());
        if (slBsBuyer == null) {
            logger.info("Batch404: 没有查询到该买家的专属管家信息");
            synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.StablePeriodStandard, BuyersConstant.MarketingsStatus.SalePublicBuyers);
            //添加到需要同步上线状态的买家列表中
            if (null != synBuyer) {
                synBuyerList.add(synBuyer);
            }
            addMarketingStatusHistory(synBuyer);
        } else {
            // 从最后一个下单开始，连续四个自然半旬未下单,变为预警期
            ChangeCycle cycle = ChangeCycleUtils.getChangeCycleByRange(buyer.getLastOrderTime(), 1, 4);
            String startTime = cycle.getStartDateStr();
            String endTime = cycle.getEndDateStr();

            // 判断结束时间是否小于当前时间
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(cycle.getEndDate());
            Calendar nowCalendar = Calendar.getInstance();
            if(endCalendar.before(nowCalendar)){
                int orderCount = getOrderCount(buyer.getBuyerId(), startTime, endTime);
                //logger.info("Batch404: 订单数量："+orderCount + "; 开始时间：" + startTime + "; 结束时间：" + endTime);
                if (orderCount == NumberConst.IntDef.INT_ZERO) {
                    synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.StablePeriodStandard, BuyersConstant.MarketingsStatus.EarlyWarnPeriod);
                    addMarketingStatusHistory(synBuyer);
                }
                if (null != synBuyer) {
                    synBuyerList.add(synBuyer);
                }
            }else{
                logger.info("Batch404: 距离最后一次下单不足四个自然半旬");
            }
        }
    }

    /**
     * 预警期会员操作
     *
     * @param buyer
     * @param synBuyerList
     */
    @Transactional(readOnly = true)
    private void earlyWarnPeriodBuyer(IBR121412RsBean buyer, List<IBR121412RsBean> synBuyerList) {
        logger.info("Batch404: 预警期买家");
        IBR121412RsBean synBuyer = null;
        // 判断：与冻品管家手动解绑，变为销售期公众买家 bindStatus=1
        IBS2101114Bean slBsBuyer = getBindStatusByBuyerId(buyer.getBuyerId());
        if (slBsBuyer == null) { // 没有冻品管家
            logger.info("Batch404: 没有查询到该买家的专属管家信息");
            synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.StablePeriodStandard, BuyersConstant.MarketingsStatus.SalePublicBuyers);
            //添加到需要同步上线状态的买家列表中
            if (null != synBuyer) {
                synBuyerList.add(synBuyer);
            }
            addMarketingStatusHistory(synBuyer);
        } else {
            // 有下订单（预警期生效日开始至今）
            // 预警期生效日
            String modifyTime = getStartTimeToString(buyer);
            logger.info("Batch404: 预警期生效日：" + modifyTime);
//            String currentTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, new Date()) + " 23:59:59";
            String currentTime = DateTimeUtil.formatDate(FORMAT_DATE_YYYYMMDD_HHMMSS, new Date());
            // 下订单数量
            int orderCount = getOrderCount(buyer.getBuyerId(), modifyTime, currentTime);
            //logger.info("Batch404: 订单数量："+orderCount + "; 开始时间：" + modifyTime + "; 结束时间：" + currentTime);
            if (orderCount >= NumberConst.IntDef.INT_ONE) {
                synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.EarlyWarnPeriod, BuyersConstant.MarketingsStatus.ActivePeriod);
                // 预警期有下订单，则LastOrderTime肯定是在预警期下的订单时间
                synBuyer.setFirstOrderTime(buyer.getLastOrderTime());
                addMarketingStatusPlan(synBuyer, NumberConst.IntDef.INT_ONE);
            } else { // 是否连续6个自然半旬没有下订单（预警期生效开始）
                BaseParam param = new BaseParam();
                param.setFilter("buyerId", buyer.getBuyerId());
                IBR121412RsBean history = this.findOne(SqlId.FIND_CURRENT_MARKET_STATUS_BY_BUYER_ID, param);
                if (null != history && history.getModifyTime() != null) {
                    ChangeCycle cycle = ChangeCycleUtils.getChangeCycleByRange(history.getModifyTime(), 1, 6);
                    logger.info("Batch404: 履历表不为空");
                    Calendar firstOrderCalendar = Calendar.getInstance();
                    firstOrderCalendar.setTime(cycle.getEndDate());
                    Calendar nowCalendar = Calendar.getInstance();
                    if (firstOrderCalendar.before(nowCalendar)) {
                        orderCount = getOrderCount(buyer.getBuyerId(), cycle.getStartDateStr(), cycle.getEndDateStr());
//                        logger.info("Batch404: 订单数量："+orderCount + "; 开始时间："
//                                + cycle.getStartDateStr() + "; 结束时间：" + cycle.getEndDateStr());
                        if (orderCount == NumberConst.IntDef.INT_ZERO) {
                            // 与管家解绑
                            unbindRelagtion(buyer.getBuyerId());
                            // 一种情况是处理成功，还有一种情况是没有查询到
                            synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.EarlyWarnPeriod, BuyersConstant.MarketingsStatus.SalePublicBuyers);
                            addMarketingStatusHistory(synBuyer);
                            synBuyerList.add(synBuyer);
                        }
                    }
                }else{
                    logger.info("Batch404: 履历表为空");
                    addNewMarketingsStatusHistory(buyer);
                }
            }
        }
    }

    /**
     * 销售期公众买家操作
     *
     * @param buyer
     * @param synBuyerList
     */
    @Transactional(readOnly = true)
    private void salePublicBuyer(IBR121412RsBean buyer, List<IBR121412RsBean> synBuyerList) {
        logger.info("Batch404: 销售期公众买家");
        // 公共买家重新绑定管家后，下订单。上线状态变更为激活期
        IBS2101114Bean slBsBuyer = getBindStatusByBuyerId(buyer.getBuyerId());
        if (slBsBuyer != null && slBsBuyer.getStartTime() != null) { // 有绑定管家
            logger.info("Batch404: 买家拥有专属管家");
            String firstOrderStr = DateTimeUtil.formatDate(FORMAT_DATE_YYYYMMDD_HHMMSS, slBsBuyer.getStartTime());
            Calendar nowCalendar = Calendar.getInstance();
            String endTimeStr = DateTimeUtil.formatDate(FORMAT_DATE_YYYYMMDD_HHMMSS, nowCalendar.getTime());

            int orderCount = getOrderCount(buyer.getBuyerId(), firstOrderStr, endTimeStr);
            //logger.info("Batch404: 订单数量："+orderCount + "; 开始时间："
                    //+ firstOrderStr + "; 结束时间：" + endTimeStr);
            if (orderCount > NumberConst.IntDef.INT_ZERO) {
                IBR121412RsBean synBuyer = assignBean(buyer, BuyersConstant.MarketingsStatus.SalePublicBuyers, BuyersConstant.MarketingsStatus.ActivePeriod);

                // synBuyer.setFirstOrderTime()应该为重新绑定管家后的第一个订单日
                synBuyer.setFirstOrderTime(nowCalendar.getTime());
                addMarketingStatusPlan(synBuyer, NumberConst.IntDef.INT_ONE);
            }
        }else
        {
            logger.info("Batch404: 没有查询到该买家的专属管家信息");
        }
    }

    /**
     * 上线状态变更
     *
     * @param buyer
     */
    @Transactional
    private void addMarketingStatusHistory(IBR121412RsBean buyer) {
        logger.info("Batch404: 上线状态变更");
        if (null != buyer) {
            logger.info("Batch404: 上线状态变更开始");
            this.modify(SqlId.MODIFY_CURRENT_MARKET_STATUS, buyer);
            Long maxId = commonLogic.maxId("br_buyer_marketing_status_history", "HISTORY_ID");
            buyer.setHistoryId(maxId);
            buyer.setModifyTime(DateTimeUtil.getCustomerDate());
            buyer.setActTime(DateTimeUtil.getCustomerDate());
            buyer.setCrtTime(DateTimeUtil.getCustomerDate());
            buyer.setUpdTime(DateTimeUtil.getCustomerDate());
            this.save(SqlId.ADD_MARKET_STATUS_HISTORY, buyer);
            logger.info("Batch404: 上线状态变更结束");
        }
    }

    /**
     * 新增上线状态变更计划
     *
     * @param buyer
     */
    private void addMarketingStatusPlan(IBR121412RsBean buyer, int amount) {
        logger.info("Batch404: 新增上线计划");
        BaseParam param = new BaseParam();
        if (buyer != null) {
            param.setFilter("buyerId", buyer.getBuyerId());
            // 判断计划表中是否已存在需要变更
            List<IBR121412RsBean> plan = this.findList(SqlId.FIND_STATUS_CHANGE_PLAN_BY_BUYER_ID, param);
            if (CollectionUtils.isEmpty(plan)) {
                logger.info("Batch404: 新增上线计划-开始");
                Long maxId = commonLogic.maxId("br_buyer_marketing_status_plan", "PLAN_ID");
                buyer.setPlanId(maxId);
                // 根据首次下订单日查询
                ChangeCycle cycle = ChangeCycleUtils.getChangeCycle(buyer.getFirstOrderTime(), amount);
                buyer.setEffectTime(cycle.getStartDate());
                buyer.setUpdTime(DateTimeUtil.getCustomerDate());
                buyer.setCrtTime(DateTimeUtil.getCustomerDate());
                buyer.setActTime(DateTimeUtil.getCustomerDate());
                this.save(SqlId.ADD_MARKET_STATUS_PLAN, buyer);
                logger.info("Batch404: 新增上线计划-结束");
            }else{
                logger.info("Batch404: 计划表中存在该买家未生效的变更计划");
            }
        }
    }

    /**
     * 赋值：买家（新、老）上线状态
     *
     * @param buyer
     * @param oldStatus
     * @param newStatus
     * @return
     */
    private IBR121412RsBean assignBean(IBR121412RsBean buyer, String oldStatus, String newStatus) {
        buyer.setBuyerId(buyer.getBuyerId());
        buyer.setCrtId(buyer.getCrtId());
        buyer.setActId(buyer.getActId());
        buyer.setUpdId(buyer.getUpdId());
        buyer.setOldStatusClass(buyer.getNewStatusClass());
        buyer.setOldStatusClassName(buyer.getNewStatusClassName());
        buyer.setOldStatusBreed(oldStatus);
        buyer.setOldStatusBreedName(buyer.getNewStatusBreedName());
        buyer.setMarketingsStatus(newStatus);
        buyer.setNewStatusBreed(newStatus);
        // new
        if (newStatus.equals(BuyersConstant.MarketingsStatus.EarlyWarnPeriod)) {
            buyer.setNewStatusClass("2");
            buyer.setNewStatusClassName("销售期");
            buyer.setNewStatusBreedName("预警期会员");
            buyer.setMarketingsStatusName("预警期会员");
        }
        if (newStatus.equals(BuyersConstant.MarketingsStatus.StablePeriodStandard)) {
            buyer.setNewStatusClass("2");
            buyer.setNewStatusClassName("销售期");
            buyer.setNewStatusBreedName("稳定期标准会员");
            buyer.setMarketingsStatusName("稳定期标准会员");
        }
        if (newStatus.equals(BuyersConstant.MarketingsStatus.ActivePeriod)) {
            buyer.setNewStatusClass("2");
            buyer.setNewStatusClassName("销售期");
            buyer.setNewStatusBreedName("激活期会员");
            buyer.setMarketingsStatusName("激活期会员");
        }
        if (newStatus.equals(BuyersConstant.MarketingsStatus.SalePublicBuyers)) {
            buyer.setNewStatusClass("2");
            buyer.setNewStatusClassName("销售期");
            buyer.setNewStatusBreedName("销售期公众买家");
            buyer.setMarketingsStatusName("销售期公众买家");
        }
        if (newStatus.equals(BuyersConstant.MarketingsStatus.NoMarket)){
            buyer.setNewStatusClass("1");
            buyer.setNewStatusClassName("营销期");
            buyer.setNewStatusBreedName("未营销成功买家");
            buyer.setMarketingsStatusName("未营销成功买家");
        }
        return buyer;
    }

    /**
     * 根据起止时间，获取订单数量
     *
     * @param buyerId
     * @param startTime
     * @param endTime
     * @return
     */
    private int getOrderCount(String buyerId, String startTime, String endTime) {
        int orderCount = 0;
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", buyerId);
        param.setFilter("orderStartTime", startTime);
        param.setFilter("orderEndTime", endTime);
        orderCount = this.getCount(SqlId.COUNT_ORDER_BY_ORDER_TIME, param);
        logger.info("Batch404: 订单数量："+orderCount + "; 开始时间：" + startTime + "; 结束时间：" + endTime);
        return orderCount;
    }

    /**
     * 根据计划更改买家上线状态
     */
    @Transactional
    public void updateBuyerMarketingStatus() {
        logger.info("Batch404: 根据计划表更改买家上线状态");
        BaseParam param = new BaseParam();
        String nowDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, new Date());
        param.setFilter("nowDate", nowDate);
        List<IBR121412RsBean> byPlanList = this.findList(SqlId.QUERY_BUYER_MARKET_STATUS_CHANGE_PLAN, param);
        if (!CollectionUtils.isEmpty(byPlanList)) {
            logger.info("Batch404: 根据计划表更改买家上线状态 - 开始");
            for (IBR121412RsBean buyer : byPlanList) {
                // 更新和新增：买家上线状态履历表 br_buyer_marketing_status_history
                addMarketingStatusHistory(buyer);
                // 更新：买家信息表 买家上线状态 br_o_buyer_info
                this.modify(SqlId.MODIFY_MARKET_STATUS_BY_BUYER_ID, buyer);
                // 更新：买家基本信息表 by_buyer_basic_info
                this.modify(SqlId.MODIFY_BASIC_MARKET_STATUS_BY_BUYER_ID, buyer);
                // 更新：买家上线状态变更计划表 br_buyer_marketing_status_history DEL_FLG=1
                this.modify(SqlId.MODIFY_MARKET_STATUS_PLAN_BY_BUYER_ID, buyer);
            }
            // 更新：买家买家池关系表 编码 br_buyer_pool_relationship
            modifyBuyerPoolCode(byPlanList);
            logger.info("Batch404: 根据计划表更改买家上线状态 - 结束");
        }else{
            logger.info("Batch404: 当前没有可生效的计划");
        }
    }

    /**
     * 日期格式化
     *
     * @param buyer
     * @return String
     */
    private String getStartTimeToString(IBR121412RsBean buyer) {
        Date d = getStartTimeToDate(buyer);
        String modifyTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, d);
        return modifyTime + " 00:00:00";
    }

    /**
     * 根据买家查询买家最新上线状态变更时间
     * @param buyer
     * @return Date
     */
    private Date getStartTimeToDate(IBR121412RsBean buyer) {
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", buyer.getBuyerId());
        IBR121412RsBean history = this.findOne(SqlId.QUERY_MARKET_STATUS_MODIFY_TIME_MAX, param);
        if(history != null && history.getModifyTime() != null){
            return history.getModifyTime();
        }else {
            history = addNewMarketingsStatusHistory(buyer);
            return history.getModifyTime();
        }
    }

    /**
     * 为老用户新增上线状态履历
     * @param buyer
     * @return
     */
    private IBR121412RsBean addNewMarketingsStatusHistory(IBR121412RsBean buyer){
        logger.info("Batch404: 为老用户新增上线状态履历");
        Long maxId = commonLogic.maxId("br_buyer_marketing_status_history", "HISTORY_ID");
        buyer.setHistoryId(maxId);
        buyer.setModifyTime(DateTimeUtil.getCustomerDate());
        buyer.setActTime(DateTimeUtil.getCustomerDate());
        buyer.setCrtTime(DateTimeUtil.getCustomerDate());
        buyer.setUpdTime(DateTimeUtil.getCustomerDate());
        buyer.setNewStatusBreed(buyer.getMarketingsStatus());
        buyer.setNewStatusBreedName(buyer.getMarketingsStatusName());
        // 为老用户添加新履历，新旧状态均为当前状态
        buyer = assignBean(buyer, buyer.getMarketingsStatus(), buyer.getMarketingsStatus());
        int i = this.save(SqlId.ADD_MARKET_STATUS_HISTORY, buyer);
        if(i > 0){
            logger.info("Batch404: 为老用户新增上线状态履历成功");
        }else{
            logger.info("Batch404: 为老用户新增上线状态履历失败");
        }
        return buyer;
    }

    /**
     * 根据买家ID查询买家归属管家信息
     * 通过bs/searchHouseInfo查询买家管家信息
     */
    private IBS2101114Bean getBindStatusByBuyerId(String buyerId){
        logger.info("Batch404: 根据买家ID查询买家归属管家信息");
        IBS2101114Param param = new IBS2101114Param();
        List<String> idList = new ArrayList<>();
        idList.add(buyerId);
        param.setBuyerIdList(idList);
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = SystemServerManager.BsServerManage.getSearchHouseInfo();
        RsResponse<IBS2101114Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101114Result>>() {
        });
        List<IBS2101114Bean> slBsBuyerList = response.getResult().getSlBsBuyerList();
        if(slBsBuyerList != null && slBsBuyerList.size() > 0){
            for(IBS2101114Bean bean : slBsBuyerList){
                if(bean.getFlag().equals("0")){
                    logger.info("Batch404: 有专属管家 flag=0");
                    return bean;
                }
            }
            logger.info("Batch404: 没有专属管家 flag=1");
            return null;
        }else{
            logger.info("Batch404: 管家信息为空");
            return null;
        }

    }

    /**
     * 批量根据买家ID更新买家上线状态
     */
    @Transactional
    public void modifyMarketStatusList(List<IBR121412RsBean> buyerList) {
        for (IBR121412RsBean buyer : buyerList) {
            this.modify(SqlId.MODIFY_MARKET_STATUS_BY_BUYER_ID, buyer);
        }
    }

    /**
     * 根据买家ID更新买家买家池编码
     *
     * @param buyerList
     */
    @Transactional
    public void modifyBuyerPoolCode(List<IBR121412RsBean> buyerList) {
        BaseParam param = new BaseParam();
        for (IBR121412RsBean buyer : buyerList) {
            param.setFilter("buyerId", buyer.getBuyerId());
            IBR121412RsBean buyerCode = this.findOne(SqlId.FIND_CURRENT_BUYER_CODE_BY_ID, param);
            if(buyerCode != null && buyerCode.getBuyerCode() != null){
                String buyerPoolCode = buyerCode.getBuyerCode();
                if (buyerPoolCode.length() >= 15) {
                    buyerPoolCode = buyerPoolCode.substring(0, 15);
                }
                param.setFilter("buyerPoolCode", buyerPoolCode);
                param.setUpdTime(buyer.getUpdTime());
                param.setUpdId(buyer.getUpdId());
                this.modify(SqlId.MODIFY_BUYER_POOL_CODE, param);
            }
        }
    }

    /**
     * 强制解除买家和管家关系
     *
     * @param buyerId
     */
    public void unbindRelagtion(String buyerId) {
        logger.info("Batch404: 强制解除买家和管家关系 -- 开始");
        IBS2101132RsParam sbb = new IBS2101132RsParam();
        List<String> buyerIds = new ArrayList<>();
        buyerIds.add(buyerId);
        sbb.setBuyerIds(buyerIds);
        RsRequest<BaseParam> slBsBuyer = new RsRequest<>();
        slBsBuyer.setSiteCode("1");
        slBsBuyer.setAuth("MSK00001");
        slBsBuyer.setLoginId("Batch");
        slBsBuyer.setParam(sbb);

        String bsUrl = SystemServerManager.BsServerManage.getUnbindRelation();
        RsResponse result = RestClientUtil.post(bsUrl, slBsBuyer, new TypeReference<RsResponse<Integer>>() {
        });
        logger.info("Batch404: " + result.getMessage());
        logger.info("Batch404: 强制解除买家和管家关系 -- 结束");

    }
}
