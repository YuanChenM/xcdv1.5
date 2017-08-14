package com.msk.batch.order.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.order.bean.BSO151401Param;
import com.msk.batch.order.commUtils.BatchCommRestUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.OrderCodeMasterDef;
import com.msk.common.utils.DecimalUtil;
import com.msk.core.entity.SoSalesRanking;
import com.msk.price.bean.PriceCycleParam;
import com.msk.price.bean.PriceCycleResult;
import com.msk.price.utils.PriceCycleUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by liutao on 2016/9/7.
 */
@Service
public class BSO151401Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151402Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 销售排行
     */
    @Transactional
    public void saveSalesRanking(BaseParam baseParam) {
        //查询现有销售排行
        List<SoSalesRanking> soSalesRankingList = super.findList(SqlId.SQL_ID_GET_SALES_RANKING_LIST, null);

        if (!CollectionUtils.isEmpty(soSalesRankingList)) {
            for (SoSalesRanking soSalesRanking : soSalesRankingList) {
                soSalesRanking.setHistoryDate(soSalesRanking.getCrtTime());
                soSalesRanking.setCrtId("BSO151401Batch");
                soSalesRanking.setCrtTime(DateTimeUtil.getCustomerDate());
                super.save(SqlId.SQL_ID_SAVE_SALES_RANKING_HISTORY, soSalesRanking);
            }
        }
        BSO151401Param param = (BSO151401Param) baseParam;
        //得到当前价盘周期参数
        PriceCycleResult nowPriceResult = this.getNowPriceCycleInfo(param);
        PriceCycleResult lastOnePriceResult = this.getLastOnePriceCycleInfo(param);

        //查询所有有过库存的供应商列表
        param.setSalePlatform(OrderCodeMasterDef.SalePlatform.YDP);
        param.setInventoryStatus(31);
        List<SoSalesRanking> stockRankingList = BatchCommRestUtils.getAllStockInfo(param);

        //查询新增卖家对应产品信息 当前价盘周期的
        param.setStartPriceCycle(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, nowPriceResult.getStartDate()));
        param.setEndPriceCycle(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, nowPriceResult.getEndDate()));
        List<SoSalesRanking> newSellerRankingList = BatchCommRestUtils.getNewSellerInfo(param);
//        //查询现有价盘周期内，参与分销的供应商信息
//        List<SoSalesRanking> distributionRankingList = BatchCommRestUtils.getDistributionInfo(param);

        //查询上上一价盘周期内，供应商供货数据明细
        param.setLastOnePriceCycle(lastOnePriceResult.getCycleCode());
        List<SoSalesRanking> orderRankingList = getOrderLevelRankingList(param);
        List<SoSalesRanking> allOrderRankingList = super.findList(SqlId.SQL_ID_GET_ALL_ORDER_SALE_RANKING_INFO, param);
        //得到最终的销售排行数据
        List<SoSalesRanking> salesRankingList = new ArrayList<>();
        this.removeRepeatRanking(newSellerRankingList, stockRankingList);
        this.removeRepeatRanking(allOrderRankingList, stockRankingList);
        if (!CollectionUtils.isEmpty(newSellerRankingList)) {
            salesRankingList.addAll(newSellerRankingList);
        }
        if (!CollectionUtils.isEmpty(allOrderRankingList)) {
            salesRankingList.addAll(allOrderRankingList);
        }

        if (!CollectionUtils.isEmpty(stockRankingList)) {
            salesRankingList.addAll(stockRankingList);
        }
        //得到多有的分销资格
        param.setSellers(salesRankingList);
        List<SoSalesRanking> distQuaRankingList = BatchCommRestUtils.getAllDistQua(param);

        //设置新卖家分销资格
        dealDistQua(distQuaRankingList, newSellerRankingList);
        if(!CollectionUtils.isEmpty(newSellerRankingList)){
            Collections.sort(newSellerRankingList, new DistQuaComparator());
        }

        //level 1
        List<SoSalesRanking> levelOneRanking = getTotalRanking(NumberConst.IntDef.INT_ONE, newSellerRankingList, stockRankingList, allOrderRankingList, distQuaRankingList, orderRankingList);
        //level 2
        List<SoSalesRanking> levelTwoRanking = getTotalRanking(NumberConst.IntDef.INT_TWO, newSellerRankingList, stockRankingList, allOrderRankingList, distQuaRankingList, orderRankingList);
        //level 3
        List<SoSalesRanking> levelThreeRanking = getTotalRanking(NumberConst.IntDef.INT_THREE, newSellerRankingList, stockRankingList, allOrderRankingList, distQuaRankingList, orderRankingList);
        //level 4
        List<SoSalesRanking> levelFourRanking = getTotalRanking(NumberConst.IntDef.INT_FOUR, newSellerRankingList, stockRankingList, allOrderRankingList, distQuaRankingList, orderRankingList);

        this.remove(param);
        this.saveSalesRankingInfo(levelOneRanking, NumberConst.IntDef.INT_ONE, nowPriceResult.getCycleCode());
        this.saveSalesRankingInfo(levelTwoRanking, NumberConst.IntDef.INT_TWO, nowPriceResult.getCycleCode());
        this.saveSalesRankingInfo(levelThreeRanking, NumberConst.IntDef.INT_THREE, nowPriceResult.getCycleCode());
        this.saveSalesRankingInfo(levelFourRanking, NumberConst.IntDef.INT_FOUR, nowPriceResult.getCycleCode());
    }

    /**
     * 得到订单中有效的订单销售排行数据
     *
     * @param param
     * @return
     */
    @Transactional
    public List<SoSalesRanking> getOrderLevelRankingList(BSO151401Param param) {
        List<SoSalesRanking> orderRankingList = super.findList(SqlId.SQL_ID_GET_ORDER_SALE_RANKING_INFO, param);
        List<SoSalesRanking> newOrderRankingList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderRankingList)) {
            for (SoSalesRanking orderRanking : orderRankingList) {
                if (!checkEqualRankingByLevel(newOrderRankingList, orderRanking)) {
                    newOrderRankingList.add(orderRanking);
                }
            }
        }
        return newOrderRankingList;
    }

    /**
     * 检查并更新同一产品同一供应商同一等级的不同销售金额的合并
     *
     * @param rankingList
     * @param ranking
     * @return
     */
    @Transactional
    public boolean checkEqualRankingByLevel(List<SoSalesRanking> rankingList, SoSalesRanking ranking) {
        if (CollectionUtils.isEmpty(rankingList)) {
            return false;
        }
        for (SoSalesRanking soSalesRanking : rankingList) {
            if (soSalesRanking.getSupplierCode().equals(ranking.getSupplierCode())
                    && soSalesRanking.getLgcsCode().equals(ranking.getLgcsCode())
                    && soSalesRanking.getPdCode().equals(ranking.getPdCode())
                    && soSalesRanking.getOrderLevel().equals(ranking.getOrderLevel())) {
                soSalesRanking.setSalesAmount(DecimalUtil.add(soSalesRanking.getSalesAmount(), ranking.getSalesAmount()));
                return true;
            }
        }
        return false;
    }

    /**
     * 保存排行数据
     *
     * @param levelRanking
     * @param orderLevel
     */
    @Transactional
    public void saveSalesRankingInfo(List<SoSalesRanking> levelRanking, Integer orderLevel, String priceCycle) {
        if (CollectionUtils.isEmpty(levelRanking)) {
            return;
        }
        Map<String, List<SoSalesRanking>> resultMap = this.getMapByKey(levelRanking);
        for (String sortKey : resultMap.keySet()) {
            List<SoSalesRanking> rankingList = resultMap.get(sortKey);
            modifyEntity(rankingList, orderLevel, DateTimeUtil.getCustomerDate(), priceCycle);
            insertRanking(rankingList);
        }
    }

    /**
     * 遍历基本信息
     *
     * @param salesRankingList
     * @param orderLevel
     */
    @Transactional
    public void modifyEntity(List<SoSalesRanking> salesRankingList, Integer orderLevel, Date crtTime, String priceCycle) {
        for (SoSalesRanking salesRanking : salesRankingList) {
            salesRanking.setOrderLevel(orderLevel);
            salesRanking.setDelFlg(SystemConst.DelFlg.ENABLE);
            salesRanking.setCrtId("BSO151401Batch");
            salesRanking.setCrtTime(crtTime);
            salesRanking.setWheelFrequency(0);
            salesRanking.setPriceCycle(priceCycle);
            salesRanking.setVer(1);
        }
    }

    /**
     * 销售排行表（批量插入）
     *
     * @param rankingList
     */
    @Transactional
    public void insertRanking(List<SoSalesRanking> rankingList) {
        int insert_max = 100;
        //一次只能插入100条数据
        if (!CollectionUtils.isEmpty(rankingList)) {
            List<SoSalesRanking> insertList = new ArrayList<>();
            int length = rankingList.size();
            int start = 0;
            int end = length > insert_max ? insert_max : length;
            while (start < length) {
                insertList = rankingList.subList(start, end);
                super.batchSave(insertList);
                start = end;
                end = length > (end + insert_max) ? (end + insert_max) : length;
            }
        }
    }

    /**
     * 得到排行数据
     *
     * @param orderLevel
     * @param newSellerRankingList
     * @param stockRankingList
     * @param allOrderRankingList
     * @param distQuaRankingList
     * @param orderRankingList
     * @return
     */
    @Transactional
    public List<SoSalesRanking> getTotalRanking(Integer orderLevel, List<SoSalesRanking> newSellerRankingList, List<SoSalesRanking> stockRankingList, List<SoSalesRanking> allOrderRankingList, List<SoSalesRanking> distQuaRankingList, List<SoSalesRanking> orderRankingList) {
        Map<String, List<SoSalesRanking>> levelRankingMap = getLevelOrderRanking(orderLevel, allOrderRankingList, orderRankingList);
        List<SoSalesRanking> levelStockRanking = getStockRanking(levelRankingMap, stockRankingList, distQuaRankingList);
        return getLevelRanking(newSellerRankingList, levelRankingMap, levelStockRanking, distQuaRankingList);
    }

    /**
     * 得到每个等级的排行数据
     *
     * @param newSellerRankingList
     * @param levelRankingMap
     * @param levelStockRanking
     * @param distQuaRankingList
     * @return
     */
    @Transactional
    public List<SoSalesRanking> getLevelRanking(List<SoSalesRanking> newSellerRankingList, Map<String, List<SoSalesRanking>> levelRankingMap, List<SoSalesRanking> levelStockRanking, List<SoSalesRanking> distQuaRankingList) {
        List<SoSalesRanking> levelAllRanking = new ArrayList<>();
        if (!CollectionUtils.isEmpty(newSellerRankingList)) {
            levelAllRanking.addAll(newSellerRankingList);
        }

        if (!CollectionUtils.isEmpty(levelRankingMap.get("haveAmount"))) {
            dealDistQua(distQuaRankingList, levelRankingMap.get("haveAmount"));
            levelAllRanking.addAll(levelRankingMap.get("haveAmount"));
        }
        if (!CollectionUtils.isEmpty(levelStockRanking)) {
            dealDistQua(distQuaRankingList, levelStockRanking);
            levelAllRanking.addAll(levelStockRanking);
        }
        return levelAllRanking;
    }

    /**
     * 每个等级的库存排行信息
     *
     * @param levelRankingMap
     * @param stockRankingList
     * @param distQuaRankingList
     * @return
     */
    @Transactional
    public List<SoSalesRanking> getStockRanking(Map<String, List<SoSalesRanking>> levelRankingMap, List<SoSalesRanking> stockRankingList, List<SoSalesRanking> distQuaRankingList) {

        List<SoSalesRanking> newStockRankingList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(levelRankingMap.get("noAmount"))) {
            newStockRankingList.addAll(levelRankingMap.get("noAmount"));
        }
        if (!CollectionUtils.isEmpty(stockRankingList)) {
            newStockRankingList.addAll(stockRankingList);
        }

        if (!CollectionUtils.isEmpty(newStockRankingList)) {
            dealDistQua(distQuaRankingList, newStockRankingList);
            Collections.sort(newStockRankingList, new DistQuaComparator());
        }

        return newStockRankingList;
    }

    /**
     * 得到分销资格
     *
     * @param distQuaRankingList
     * @param salesRankingList
     */
    @Transactional
    public void dealDistQua(List<SoSalesRanking> distQuaRankingList, List<SoSalesRanking> salesRankingList) {
        if (!CollectionUtils.isEmpty(salesRankingList)) {
            Iterator<SoSalesRanking> it = salesRankingList.iterator();
            while (it.hasNext()) {
                SoSalesRanking salesRanking = it.next();
                boolean delFlg = false;
                for (SoSalesRanking distQuaRanking : distQuaRankingList) {
                    if (salesRanking.getSupplierCode().equals(distQuaRanking.getSupplierCode())
                            && salesRanking.getLgcsCode().equals(distQuaRanking.getLgcsCode())) {
                        if (distQuaRanking.getDistQua() == NumberConst.IntDef.INT_ZERO) {
                            salesRankingList.remove(salesRanking);
                            break;
                        }
                        delFlg = true;
                        salesRanking.setDistQua(distQuaRanking.getDistQua());
                    }
                }
                if (!delFlg){
                    it.remove();
                }
            }
        }
    }

    /**
     * 根据产品编码和物流区进行分类
     *
     * @param salesRankingList
     * @return
     */
    @Transactional
    public Map<String, List<SoSalesRanking>> getMapByKey(List<SoSalesRanking> salesRankingList) {
        Map<String, List<SoSalesRanking>> pdMap = new HashMap<>();
        //根据产品编码和物流区进行分类
        for (SoSalesRanking sell : salesRankingList) {
            String key = getKey(sell);
            if (pdMap.containsKey(key)) {
                pdMap.get(key).add(sell);
            } else {
                List<SoSalesRanking> l = new ArrayList<SoSalesRanking>();
                l.add(sell);
                pdMap.put(key, l);
            }
        }
        return pdMap;
    }

    /**
     * 根据产品编码和物流区进行分类
     *
     * @param sell
     * @return
     */
    @Transactional
    public String getKey(SoSalesRanking sell) {
        if (null != sell) {
            return sell.getPdCode() + "|" + sell.getLgcsCode();
        }
        return null;
    }

    /**
     * 根据订单等级得到相应等级的排行数据
     *
     * @param orderLevel
     * @param allOrderRankingList
     * @param orderRankingList
     * @return
     */
    @Transactional
    public Map<String, List<SoSalesRanking>> getLevelOrderRanking(Integer orderLevel, List<SoSalesRanking> allOrderRankingList, List<SoSalesRanking> orderRankingList) {
        Map<String, List<SoSalesRanking>> resultMap = new HashMap<>();

        List<SoSalesRanking> levelOrderRankingList = new ArrayList<>();
        List<SoSalesRanking> lastOrderRankingList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(allOrderRankingList)) {
            for (SoSalesRanking allOrderRanking : allOrderRankingList) {
                boolean levelFlag = false;
                SoSalesRanking newRanking = new SoSalesRanking();
                for (SoSalesRanking orderRanking : orderRankingList) {
                    if (orderRanking.getOrderLevel() == orderLevel
                            && checkEqualRanking(allOrderRanking, orderRanking)) {
                        BeanUtils.copyProperties(orderRanking, newRanking);
                        levelOrderRankingList.add(newRanking);
                        levelFlag = true;
                        break;
                    }
                }
                if (!levelFlag) {
                    BeanUtils.copyProperties(allOrderRanking, newRanking);
                    newRanking.setOrderLevel(orderLevel);
                    newRanking.setSalesAmount(null);
                    lastOrderRankingList.add(newRanking);
                }
            }
        }
        resultMap.put("haveAmount", levelOrderRankingList);
        resultMap.put("noAmount", lastOrderRankingList);
        return resultMap;
    }

    /**
     * 去除重复排行数据
     *
     * @param compareRankings 被比较的集合
     * @param removeRankings  需要去除重复数据的集合
     */
    @Transactional
    public void removeRepeatRanking(List<SoSalesRanking> compareRankings, List<SoSalesRanking> removeRankings) {
        if (CollectionUtils.isEmpty(compareRankings) || CollectionUtils.isEmpty(removeRankings)) {
            return;
        }
        for (SoSalesRanking compareRanking : compareRankings) {
            for (SoSalesRanking removeRanking : removeRankings) {
                if (checkEqualRanking(compareRanking, removeRanking)) {
                    removeRankings.remove(removeRanking);
                    break;
                }
            }
        }
    }

    /**
     * 检查是否属于同一供应
     * 同一物流区
     * 同一产品编码
     *
     * @param ranking1
     * @param ranking2
     * @return
     */
    @Transactional
    public boolean checkEqualRanking(SoSalesRanking ranking1, SoSalesRanking ranking2) {
        return ranking1.getSupplierCode().equals(ranking2.getSupplierCode())
                && ranking1.getLgcsCode().equals(ranking2.getLgcsCode())
                && ranking1.getPdCode().equals(ranking2.getPdCode());
    }

    /**
     * 获得当前价盘属性
     *
     * @param param
     * @return
     */
    @Transactional
    public PriceCycleResult getNowPriceCycleInfo(BSO151401Param param) {
        PriceCycleParam preParam = new PriceCycleParam();
        preParam.setCurrentDate(param.getCurrentDate());
        PriceCycleUtil.getPriceCycle(preParam);
        PriceCycleResult cycle = PriceCycleUtil.getPriceCycle(preParam);
        return cycle;
    }

    /**
     * 获得上上个价盘属性
     *
     * @param param
     * @return
     */
    @Transactional
    public PriceCycleResult getLastOnePriceCycleInfo(BSO151401Param param) {
        PriceCycleResult cycleInfo = getNowPriceCycleInfo(param);
        return this.getCycle(cycleInfo.getStartDate(), -NumberConst.IntDef.INT_TEN);
    }

    /**
     * 根据day得到目前价盘周期的前几个价盘周期信息
     *
     * @param date
     * @param day
     * @return
     */
    @Transactional
    public PriceCycleResult getCycle(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);

        PriceCycleParam preParam = new PriceCycleParam();
        preParam.setCurrentDate(calendar.getTime());
        PriceCycleResult cycle = PriceCycleUtil.getPriceCycle(preParam);
        return cycle;

    }

    public interface SqlId {
        static final String SQL_ID_GET_SALES_RANKING_LIST = "getSalesRankingList";
        static final String SQL_ID_SAVE_SALES_RANKING_HISTORY = "saveSalesRankingHistory";
        static final String SQL_ID_GET_ORDER_SALE_RANKING_INFO = "getOrderSaleRankingInfo";
        static final String SQL_ID_GET_ALL_ORDER_SALE_RANKING_INFO = "getAllOrderSaleRankingInfo";
    }

    //按分销资格排序
    @Transactional
    class DistQuaComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            SoSalesRanking s1 = (SoSalesRanking) o1;
            SoSalesRanking s2 = (SoSalesRanking) o2;
            return s2.getDistQua().compareTo(s1.getDistQua());
        }
    }
}
