package com.msk.buyers.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121403Bean;
import com.msk.buyers.bean.BY121403TotalBean;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsPageParam;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByMarketFood;
import com.msk.core.entity.ByMarketTerminalBasic;
import com.msk.core.entity.ByMarketTerminalByInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 批发市场新增 logic
 * Created by zhou_yajun on 2016/7/8.
 */
@Service
public class BY121403Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121403Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private BY121413Logic by121413Logic;

    interface SqlId {
        String MODIFY_OLD_PHASE = "modifyOldPhase";
        String MODIFY_NEW_PHASE = "modifyNewPhase";
        String SQL_ID_SYNCH_MARKET_INFO = "synchMarketInfo";
        String SQL_ID_UPDATE_MARKET_INFOS = "updateMarketInfos";
        String SQL_ID_DELETE_MARKET_INFO = "deleteMarketInfo";
        String SQL_ID_GET_TOTAL_NUMBER = "getTotalNumber";
        String SQL_ID_MODIFY_MAX_TAGER_BUYER_INFO = "modifyMaxTagerBuyerInfo";
        String SQL_ID_UPDATE_BR_MARKET_INFO="updateBrMarketInfo";
        String SQL_ID_GET_MARKET_INFO_COUNT = "getMarketInfoCount";
        String SQL_ID_DELETE_MARKETS = "deleteMarkets";
        String SQL_FIND_QUERY_MARKER_FOODLIST ="findQueryMarkerFoodList";
        String REG_EXISTENCE = "regExistence";
        String DELETE_MARKET_STATUS = "deleteMarketStatus";

        String DELETE_STORE_INFO = "deleteStoreInfo";
        String DELETE_PD_INFO = "deletePdInfo";
        String DELETE_BY_INFO = "deleteByInfo";
        String DELETE_FILE_INFO = "deleteFileInfo";

    }

    /**
     * 批发市场基本信息编辑
     *
     * @param by121403Bean
     * @return
     */
    @Transactional
    public BY121403Bean marketBasicModify(BY121403Bean by121403Bean, BY121403Bean returnObject) {

        int resultCount;

        // 根据旧阶段和批发市场编码 更新IsPhaseNew
        this.modify(SqlId.MODIFY_OLD_PHASE, by121403Bean);
        // 根据新阶段和批发市场编码 更新IsPhaseNew
        this.modify(SqlId.MODIFY_NEW_PHASE, by121403Bean);

        // 当前调研阶段和旧调研阶段相同时
        if (by121403Bean.getOldResearchPhase().equals(by121403Bean.getResearchPhase())) {
            by121403Bean.setIsPhaseNew("0");
        } else {
            // 当前调研阶段和旧调研阶段不同时
            by121403Bean.setIsPhaseNew("1");
        }
        int updateCount = this.modify(by121403Bean);
        if (updateCount == NumberConst.IntDef.INT_ONE) {
            Long maxId = commonLogic.maxId("by_market_terminal_basic", "ID");
            by121403Bean.setId(maxId);

            resultCount = this.save(by121403Bean);
            if (resultCount == NumberConst.IntDef.INT_ONE) {
                returnObject.setActionType("modify");
                returnObject.setResearchPhase(by121403Bean.getResearchPhase());
                return returnObject;
            }
        }
        throw new BusinessException("编辑失败,未找到该批发市场!");
    }

    /**
     * 根据输入的批发市场名称查询批发市场信息
     *
     * @param by121403Bean
     * @return
     */
    public String findMarketByName(BY121403Bean by121403Bean) {

        logger.info("判断输入的批发市场名称是否已存在");

        BaseParam param = new BaseParam();

        param.setFilter("marketName", by121403Bean.getMarketName());
        param.setFilter("lgcsAreaCode", by121403Bean.getLgcsAreaCode());
        param.setFilter("cityCode", by121403Bean.getCityCode());

        int count = this.getCount(param);

        if (count > NumberConst.IntDef.INT_ZERO) {
            return "输入的批发市场已存在,请重新录入";
        }
        return null;
    }

    /**
     * 处理批发市场买家信息表 数据信息 该处初始化编辑界面时候的操作
     * 批发市场买家信息表 1、是否有数据 ==> 2、是否新数据 ==> 3.1:没有就同步数据 3.2：有就告知前台数据状态（新或者旧）
     * 1 表示需要同步 按钮 0 表示不需要同步按钮
     * 数据库存在数据 数据的前瞻性 根据isMerchantNew判定 为1的时候说明关联表做了更新而INFO表没有处理 当为0的时候表示数据表示最新数据信息
     */
    @Transactional
    public Map<String,String> doWorkMarketInfo(ByMarketTerminalBasic marketTerminalBasic) {
        Map<String,String> map = new HashMap<String,String>();
        BaseParam param = new BaseParam();
        param.setFilter("marketId", marketTerminalBasic.getMarketId());
        int count = super.getPageCount(param);
        if (count > 0) {
            map.put("res","1");
        } else {
            // 数据库没有数据自动同步数据信息 且修改 基础表中isMerchantNew 的字段状态
            map = this.synchMarketInfo(marketTerminalBasic,NumberConst.IntDef.INT_ONE);
        }
        return map;
    }

    /**
     * 手动同步触发
     *
     * @param marketTerminalBasic
     */
    @Transactional
    public Map<String,String> manualSynchMarketInfo(ByMarketTerminalBasic marketTerminalBasic) {
        //BaseParam param = new BaseParam();
        //param.getFilterMap().put("marketId", marketTerminalBasic.getMarketId());
        //this.modify(SqlId.SQL_ID_DELETE_MARKET_INFO, param);
        //不删除数据 直接修改 2016-8-17
        // 2、同步修改数据
        return this.synchMarketInfo(marketTerminalBasic,NumberConst.IntDef.INT_TWO);
    }

    /**
     * 批发市场基本信息表 数据同步处理
     * flag 1：新增, 2 修改
     */
    @Transactional
    public Map<String,String> synchMarketInfo(ByMarketTerminalBasic marketTerminalBasic, int flag) {

        Map<String ,String> reusltMap = new HashMap<String ,String>();
        // 记录所有计算后数据
        List<ByMarketTerminalByInfo> newMarketList = new ArrayList<ByMarketTerminalByInfo>();
        // 记录所有非目标买家数据
        List<ByMarketTerminalByInfo> noTagerMarketList = new ArrayList<ByMarketTerminalByInfo>();

        Map<String, Integer> pdNameCompMap = new HashMap<String, Integer>();

        BaseParam base = new BaseParam();
        base.getFilterMap().put("marketId", marketTerminalBasic.getMarketId());
        List<ByMarketTerminalByInfo> list = super.findList(SqlId.SQL_ID_SYNCH_MARKET_INFO, base);
        int allBuyerNums = 0;
        String maxTagerProdName = "";
        for (ByMarketTerminalByInfo market : list) {
            market.setActTime(marketTerminalBasic.getActTime());
            market.setCrtTime(marketTerminalBasic.getCrtTime());
            market.setUpdTime(marketTerminalBasic.getUpdTime());
            market.setUpdId(marketTerminalBasic.getUpdId());
            market.setActId(marketTerminalBasic.getActId());
            market.setCrtId(marketTerminalBasic.getCrtId());
            if ("1".equals(market.getIsTargetMerchant())) {
                Long maxId = commonLogic.maxId("BY_MARKET_TERMINAL_BY_INFO", "ID");
                market.setId(maxId);
                newMarketList.add(market);
                allBuyerNums += market.getTotalMerchant();
                this.getFinalProductName(pdNameCompMap, market.getSalePd(),market.getTotalMerchant());
            } else {
                // 主动区分8种类型
                this.setSumMarketInfos(noTagerMarketList, market);
            }
        }
        // 剔除数据为空的类型
        for (int i = 0; i < noTagerMarketList.size(); i++) {
            ByMarketTerminalByInfo tgMarket = noTagerMarketList.get(i);
            if (tgMarket.getTotalMerchant() < NumberConst.IntDef.INT_ONE) {
                noTagerMarketList.remove(i);
                i = i - 1;
            }
        }

        Iterator<Map.Entry<String, Integer>> it = pdNameCompMap.entrySet().iterator();
        int temp = 0;
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() >= temp) {
                temp = entry.getValue();
                maxTagerProdName = entry.getKey();
            }
        }
        // 更新基础表中目标买家信息
        this.setMarketBasicInfo(reusltMap,newMarketList, marketTerminalBasic, maxTagerProdName,
                allBuyerNums);

        newMarketList.addAll(noTagerMarketList);
        if (newMarketList.size() > 0) {
            if(flag == 1){
                // 非目标数据处理 新增
                this.batchSave(newMarketList);
            }else{
                // 修改 BY_MARKET_TERMINAL_BY_INFO
                this.modifyByMarketInfo(newMarketList);
            }
            // 新增成功后修改基表数据的状态
            BaseParam param = new BaseParam();
            param.setFilter("marketId", marketTerminalBasic.getMarketId());
            param.setFilter("isMarketNew", "1");
            param.setFilter("isPhaseNew", "1");
            param.getFilterMap().put("isMerchantNew", "0");
            by121413Logic.modifyBasicStatus(param);
            reusltMap.put("res","0");
        } else {
            // 没有获取到计算信息 直接清空INFO表
            this.modify(SqlId.SQL_ID_DELETE_MARKETS,base);
            reusltMap.put("res","1");
        }
        return reusltMap;
    }

    /**
     * 同步修改MarketInfo表信息
     */
    @Transactional
    public void modifyByMarketInfo(List<ByMarketTerminalByInfo> marketList){
        List<ByMarketTerminalByInfo> addList = new ArrayList<ByMarketTerminalByInfo>();
        List<BY121403TotalBean> list = null;

        BaseParam basep = new BaseParam();
        if(marketList.get(0) != null){
            RsPageParam base = new RsPageParam();
            String marketId =marketList.get(0).getMarketId();
            base.setPaging(false);
            base.getFilterMap().put("marketId",marketId);
            basep.getFilterMap().put("marketId",marketId);
            list =  this.findPageList(base);
        }
        for(ByMarketTerminalByInfo market:marketList){
            // 移除数据库已经存在的信息
            for(BY121403TotalBean bean:list){
                if("1".equals(bean.getIsTargetMerchant())){
                    if(bean.getMerchantType().equals(market.getMerchantType()) && bean.getSalePdCode().equals(market.getSalePdCode())){
                        list.remove(bean);
                        break;
                    }
                }else{
                    if(bean.getMerchantType().equals(market.getMerchantType()) && "0".equals(market.getIsTargetMerchant())){
                        list.remove(bean);
                        break;
                    }
                }
            }
            int count = this.getCount(SqlId.SQL_ID_GET_MARKET_INFO_COUNT,market);
            if(count > 0){
                // 修改
                this.modify(SqlId.SQL_ID_UPDATE_BR_MARKET_INFO,market);
            }else{
                addList.add(market);
            }
        }
        if(list != null && list.size() > 0){
            basep.getFilterMap().put("reList",list);
            this.getBaseDao().getSqlSession().update(SqlId.SQL_ID_DELETE_MARKETS,basep);
        }
        if(addList.size() > 0){
            this.batchSave(addList);
        }
    }

    /**
     * 统计产品名称出现次数
     */
    public void getFinalProductName(Map<String, Integer> map, String salePd,int totalMerchant) {
        if (salePd.length() > 0) {
            String sp[] = salePd.split(",");
            for (int index = 0; index < sp.length; index++) {
                if (map.size() > 0 && map.containsKey(sp[index])) {
                    map.put(sp[index], map.get(sp[index]) + totalMerchant);
                } else {
                    map.put(sp[index], totalMerchant);
                }
            }
        }
    }

    /**
     * 同步修改by_market_terminal_basic 表信息
     */
    @Transactional
    public void setMarketBasicInfo(Map<String, String> map,
                                   List<ByMarketTerminalByInfo> newMarketList, ByMarketTerminalBasic marketTerminalBasic, String maxTagerProdName, int allBuyerNums) {

       String marketId =marketTerminalBasic.getMarketId();
        int maxTagetBuyerNums = 0;
        for (ByMarketTerminalByInfo list : newMarketList) {
            if (list.getSalePd().indexOf(maxTagerProdName) > -1) {
                maxTagetBuyerNums = maxTagetBuyerNums + list.getTotalMerchant();
            }
        }
        ByMarketTerminalBasic market = new ByMarketTerminalBasic();
        market.setUpdTime(marketTerminalBasic.getUpdTime());
        market.setUpdId(marketTerminalBasic.getUpdId());

        market.setMarketId(marketId);
        market.setMaxClassBuyerType(maxTagerProdName);
        market.setMaxClassBuyerNum(maxTagetBuyerNums);
        market.setTargetBuyer(allBuyerNums);
        map.put("maxClassBuyerType",maxTagerProdName);
        map.put("maxClassBuyerNum",String.valueOf(maxTagetBuyerNums));
        map.put("targetBuyer",String.valueOf(allBuyerNums));
        this.modify(SqlId.SQL_ID_MODIFY_MAX_TAGER_BUYER_INFO,market);
    }

    /**
     * 批量处理批发市场买家信息表更新
     *
     * @param param
     */
    @Transactional
    public BY121403TotalBean findPageTotal(BasePageParam param) {
        // 查询当前页面统计信息
        BY121403TotalBean currentotalBean = super.findOne(SqlId.SQL_ID_GET_TOTAL_NUMBER, param);
        // 查询所有界面统计信息
        param.setPaging(false);
        param.getFilterMap().put("totalMerchant","");
        BY121403TotalBean allTotalBean = super.findOne(SqlId.SQL_ID_GET_TOTAL_NUMBER, param);
        if (allTotalBean != null) {
            currentotalBean.setTotalAmount(allTotalBean.getCurrentAmount());
            currentotalBean.setTotalNumber(allTotalBean.getCurrentNumber());
        }
        return currentotalBean;
    }

    /**
     * 批量处理批发市场买家信息表更新
     * @param marketList
     */
    @Transactional
    public void batchMarketInfosDo(List<ByMarketTerminalByInfo> marketList, String loginId) {
        for (ByMarketTerminalByInfo market : marketList) {
            market.setUpdId(loginId);
            market.setUpdTime(DateTimeUtil.getCustomerDate());
            this.modify(SqlId.SQL_ID_UPDATE_MARKET_INFOS, market);
        }
    }
    /**
     * 非目标买家信息统计
     * @param tagerMarketList
     * @param market
     */
    public void setSumMarketInfos(List<ByMarketTerminalByInfo> tagerMarketList, ByMarketTerminalByInfo market) {
        if (tagerMarketList.size() == 0) {
            for (int i = 0; i < 8; i++) {
                Long maxId = commonLogic.maxId("by_market_terminal_by_info", "ID");
                ByMarketTerminalByInfo tagerMarket = new ByMarketTerminalByInfo();
                BeanUtils.copyProperties(market, tagerMarket);
                tagerMarket.setId(maxId);
                tagerMarket.setSalePdCode("");
                tagerMarket.setSalePd("");
                // 第一次进入创建初始化对象
                if (i == 0) {
                    tagerMarket.setMerchantName("鲜肉产品买家");
                    tagerMarket.setMerchantType("1");
                    tagerMarketList.add(tagerMarket);
                } else if (i == 1) {
                    tagerMarket.setMerchantName("鲜鱼产品买家");
                    tagerMarket.setMerchantType("2");
                    tagerMarketList.add(tagerMarket);
                } else if (i == 2) {
                    tagerMarket.setMerchantName("活禽产品买家");
                    tagerMarket.setMerchantType("3");
                    tagerMarketList.add(tagerMarket);
                } else if (i == 3) {
                    tagerMarket.setMerchantName("禽蛋产品买家");
                    tagerMarket.setMerchantType("4");
                    tagerMarketList.add(tagerMarket);
                } else if (i == 4) {
                    tagerMarket.setMerchantName("果蔬产品买家");
                    tagerMarket.setMerchantType("5");
                    tagerMarketList.add(tagerMarket);
                } else if (i == 5) {
                    tagerMarket.setMerchantName("豆制品买家");
                    tagerMarket.setMerchantType("6");
                    tagerMarketList.add(tagerMarket);
                } else if (i == 6) {
                    tagerMarket.setMerchantName("其它食品买家");
                    tagerMarket.setMerchantType("7");
                    tagerMarketList.add(tagerMarket);
                } else if (i == 7) {
                    tagerMarket.setMerchantName("非食品买家");
                    tagerMarket.setMerchantType("8");
                    tagerMarketList.add(tagerMarket);
                }
            }
        }
        for (ByMarketTerminalByInfo tagerMarket : tagerMarketList) {
            if (market.getSalePdCode().indexOf(tagerMarket.getMerchantType().toString()) > -1) {
                int total = tagerMarket.getTotalMerchant() + 1;
                tagerMarket.setTotalMerchant(total);
            }
        }

    }
// Add for Bug #2464号 at 2016/09/06 by zhao_chen Start
    /**
     * @param param
     * @return
     */
    public Integer regExistence(BY121403Bean param){
        return super.getCount(SqlId.REG_EXISTENCE,param);
    }

    public  Integer deleteMarketStatus(BY121403Bean param){
        int deleteBasicFlg = super.modify(SqlId.DELETE_MARKET_STATUS,param);
        super.modify(SqlId.DELETE_STORE_INFO,param);
        super.modify(SqlId.DELETE_BY_INFO,param);
        super.modify(SqlId.DELETE_FILE_INFO,param);
        return deleteBasicFlg;
    }


}
