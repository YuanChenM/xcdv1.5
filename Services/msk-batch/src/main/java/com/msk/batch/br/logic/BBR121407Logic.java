package com.msk.batch.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.br.bean.BBR121407Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrHkGroup;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * BBR121407Logic
 *
 * @author zhou_yajun
 */
@Service
public class BBR121407Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121407Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId{
        static String FIND_HK_GROUP = "findHkGroup";
        static String FIND_HK_SETTING_BY_GROUP_ID = "findHkSettingByGroupId";
        static String FIND_PUBLIC_BUYER_POOL = "findPublicBuyerPool";
        static String COUNT_CURRENT_BUYER = "countCurrentBuyer";
        static String FIND_BIND_BUYER_BY_HK = "findBindBuyerByHk";
        static String MODIFY_BUYER_HK_RS_BY_HK = "modifyBuyerHkRsByHk";
        static String MODIFY_BUYER_POOL_RS_BY_BUYER_ID = "modifyBuyerPoolRsByBuyerId";
        static String FIND_RELIEVE_HK_BY_BUYER_ID = "findRelieveHkByBuyerId";
        static String FIND_HK_SETTING_BY_HK = "findHkSettingByHk";
        static String ADD_HK_BUYER_RELATION_SHIP = "addHkBuyerRelationShip";
    }

    public void synHkBuyerRelationShip(BaseParam param){
        //获取管家组数据
        List<BrHkGroup> hkGroupList = this.findList(SqlId.FIND_HK_GROUP,param);
        if(!CollectionUtils.isEmpty(hkGroupList)){
            hkGroupOperate(hkGroupList,param);
        }
    }

    /**
     * 根据管家组ID获取该管家组下的所有管家及管家的配置
     * @param hkGroupList
     * @param param
     */
    public void hkGroupOperate(List<BrHkGroup> hkGroupList,BaseParam param){
        for (BrHkGroup hkGroup:hkGroupList){
            param.setFilter("hkGroupId", StringUtil.toSafeString(hkGroup.getHkGroupId()));
            param.setFilter("classesCode",hkGroup.getClassesCode());
            param.setFilter("machiningCodeU",hkGroup.getMachiningCodeU());
            param.setFilter("lgcsAreaCode",hkGroup.getLgcsAreaCode());
            param.setFilter("cityCode",hkGroup.getCityCode());
            param.setFilter("buyerType",hkGroup.getBuyerType());
            //获取管家组下所有管家以及管家的配置
            List<BBR121407Bean> hkInfoList = findHkSettingInfoByGroupId(param);
            if(!CollectionUtils.isEmpty(hkInfoList)){
                hkInfoSettingOperate(hkInfoList,param);
            }
        }
    }

    /**
     * 获取管家当前绑定的专属会员买家数和锁定期买家数
     * 并根据管家配置判断该管家是否可以分配买家
     * @param hkInfoList
     * @param param
     */
    @Transactional
    public void hkInfoSettingOperate(List<BBR121407Bean> hkInfoList,BaseParam param){
        int unBindBuyerHkCount = 0;
        List<BBR121407Bean> removeBuyerHkList = new ArrayList<>();
        List<BBR121407Bean> needBindBuyerHkList = new ArrayList<>();
        List<BBR121407Bean> bindBuyerList = new ArrayList<>();
        Calendar currentCalendar = Calendar.getInstance();
        Calendar bindEndTime = Calendar.getInstance();
        BBR121407Bean modifyByHkRs = null;
        for (BBR121407Bean hkInfo:hkInfoList){
            param.setFilter("slCode",hkInfo.getSlCode());
            param.setFilter("houseCode",hkInfo.getHouseCode());
            //获取管家绑定的所有买家及绑定开始时间结束时间
            bindBuyerList = findBindBuyerByHk(param);
            if(!CollectionUtils.isEmpty(bindBuyerList)){
                for (BBR121407Bean bindBuyer:bindBuyerList){
                    bindEndTime.setTime(bindBuyer.getBindEndTime());
                    //如果当前时间超过绑定最大日期,则自动解除绑定,买家回到公共买家池
                    if(currentCalendar.after(bindEndTime)){
                        modifyByHkRs = new BBR121407Bean();
                        modifyByHkRs.setBuyerId(bindBuyer.getBuyerId());
                        modifyByHkRs.setSlCode(hkInfo.getSlCode());
                        modifyByHkRs.setHouseCode(hkInfo.getHouseCode());
                        modifyByHkRs.setBindEndTime(DateTimeUtil.getCustomerDate());
                        modifyByHkRs.setRelationType("0");
                        modifyByHkRs.setUpdId(param.getUpdId());
                        modifyByHkRs.setUpdTime(param.getUpdTime());
                        modifyByHkRs.setClassesCode(StringUtil.toSafeString(param.getFilterMap().get("classesCode")));
                        modifyByHkRs.setMachiningCodeU(StringUtil.toSafeString(param.getFilterMap().get("machiningCodeU")));
                        modifyByHkRs.setLgcsAreaCode(StringUtil.toSafeString(param.getFilterMap().get("lgcsAreaCode")));
                        modifyByHkRs.setCityCode(StringUtil.toSafeString(param.getFilterMap().get("cityCode")));
                        modifyByHkRs.setBuyerType(StringUtil.toSafeString(param.getFilterMap().get("buyerType")));
                        modifyByHkRs.setBuyerPoolType("0");
                        modifyByHkRs.setLeaveTime(DateTimeUtil.getCustomerDate());
                        //更新买家管家关系
                        this.modify(SqlId.MODIFY_BUYER_HK_RS_BY_HK,modifyByHkRs);
                        removeBuyerHkList.add(modifyByHkRs);
                        //更新买家池分池
                        this.modify(SqlId.MODIFY_BUYER_POOL_RS_BY_BUYER_ID,modifyByHkRs);
                    }
                }
            }
            //专属会员买家数
            param.setFilter("bindStatus","2");
            int currentVipBuyer = countCurrentBuyers(param);
            //锁定期会员买家数
            param.setFilter("bindStatus","1");
            int currentLockBuyer = countCurrentBuyers(param);
            //如果专属会员达到上限则不分配买家
            if(currentVipBuyer > 0 && currentVipBuyer >= hkInfo.getVipMaxBuyers()){
                continue;
            }
            //如果锁定期买家达到上限则不分配买家
            if(currentVipBuyer > 0 && currentLockBuyer >= hkInfo.getLockMaxBuyers()){
                continue;
            }
            needBindBuyerHkList.add(hkInfo);
            unBindBuyerHkCount = unBindBuyerHkCount + (hkInfo.getLockMaxBuyers() - currentLockBuyer);
        }
        //TODO 调用接口更新买手表中管家和买家关系

        //分配买家
        distributeBuyerToHk(needBindBuyerHkList,unBindBuyerHkCount,param);
    }

    /**
     * 给管家分配买家
     * @param needBindBuyerHkList
     * @param param
     */
    @Transactional
    public void distributeBuyerToHk(List<BBR121407Bean> needBindBuyerHkList,int unBindBuyerHkCount,BaseParam param){
        int publicBuyer;
        //获取公共买家池买家
        List<BBR121407Bean> publicBuyerPoolList = this.findList(SqlId.FIND_PUBLIC_BUYER_POOL,param);
        List<BBR121407Bean> synHkBindBuyer = new ArrayList<>();
        publicBuyer = publicBuyerPoolList.size();
        int unDistributeHkCount = unBindBuyerHkCount;

        BBR121407Bean needBindBuyerHkSetting = new BBR121407Bean();
        BBR121407Bean unBindHk = new BBR121407Bean();
        BBR121407Bean bindBuyer = new BBR121407Bean();

        Calendar currentDate = Calendar.getInstance();
        while (unDistributeHkCount > 0 && publicBuyer > 0){
            if(!CollectionUtils.isEmpty(needBindBuyerHkList)){
                for (BBR121407Bean needBindBuyerHk: needBindBuyerHkList){
                    //获取管家的配置信息,判断该管家是否专属会员和锁定期会员达到上限
                    param.setFilter("slCode",needBindBuyerHk.getSlCode());
                    param.setFilter("houseCode", needBindBuyerHk.getHouseCode());
                    needBindBuyerHkSetting = findHkSettingInfoByHk(param);
                    //专属会员买家数
                    param.setFilter("bindStatus", "2");
                    int currentVipBuyer = countCurrentBuyers(param);
                    //锁定期会员买家数
                    param.setFilter("bindStatus", "1");
                    int currentLockBuyer = countCurrentBuyers(param);
                    //如果专属会员达到上限则不分配买家
                    if(null == needBindBuyerHkSetting || currentVipBuyer >= needBindBuyerHkSetting.getVipMaxBuyers()){
                        continue;
                    }
                    //如果锁定期买家达到上限则不分配买家
                    if(null == needBindBuyerHkSetting || currentLockBuyer >= needBindBuyerHkSetting.getLockMaxBuyers()){
                        continue;
                    }
                    currentDate.add(Calendar.DAY_OF_MONTH,needBindBuyerHkSetting.getMarketingMaxDays());
                    if(!CollectionUtils.isEmpty(publicBuyerPoolList)){
                        for(BBR121407Bean publicBuyerPool:publicBuyerPoolList){
                            //获取该买家最近解除绑定的管家
                            param.setFilter("buyerId",publicBuyerPool.getBuyerId());
                            unBindHk = this.findOne(SqlId.FIND_RELIEVE_HK_BY_BUYER_ID,param);
                            if(null != unBindHk){
                                if(publicBuyerPool.getSlCode().equals(needBindBuyerHk.getSlCode()) && publicBuyerPool.getHouseCode().equals(needBindBuyerHk.getHouseCode())){
                                    continue;
                                }
                            }
                            bindBuyer = new BBR121407Bean();
                            bindBuyer.setByHkRsId(this.commonLogic.maxId("br_buyer_hk_relationship","ID"));
                            bindBuyer.setBuyerId(publicBuyerPool.getBuyerId());
                            bindBuyer.setSlCode(needBindBuyerHk.getSlCode());
                            bindBuyer.setHouseCode(needBindBuyerHk.getHouseCode());
                            bindBuyer.setBindStartTime(DateTimeUtil.getCustomerDate());


                            bindBuyer.setBindEndTime(currentDate.getTime());
                            bindBuyer.setClassesCode(StringUtil.toSafeString(param.getFilterMap().get("classesCode")));
                            bindBuyer.setMachiningCodeU(StringUtil.toSafeString(param.getFilterMap().get("machiningCodeU")));
                            bindBuyer.setLgcsAreaCode(StringUtil.toSafeString(param.getFilterMap().get("lgcsAreaCode")));
                            bindBuyer.setCityCode(StringUtil.toSafeString(param.getFilterMap().get("cityCode")));
                            bindBuyer.setBuyerType(StringUtil.toSafeString(param.getFilterMap().get("buyerType")));
                            bindBuyer.setBuyerPoolType("1");
                            bindBuyer.setRelationType("1");
                            bindBuyer.setJoinTime(DateTimeUtil.getCustomerDate());
                            bindBuyer.setCrtId(param.getCrtId());
                            bindBuyer.setCrtTime(param.getCrtTime());
                            bindBuyer.setUpdId(param.getUpdId());
                            bindBuyer.setUpdTime(param.getUpdTime());
                            bindBuyer.setActId(param.getActId());
                            bindBuyer.setActTime(param.getActTime());
                            //更新买家池分池
                            this.modify(SqlId.MODIFY_BUYER_POOL_RS_BY_BUYER_ID,bindBuyer);
                            //更新买家和管家关系
                            this.save(SqlId.ADD_HK_BUYER_RELATION_SHIP,bindBuyer);

                            synHkBindBuyer.add(bindBuyer);
                            publicBuyerPoolList.remove(publicBuyerPool);
                            publicBuyer = publicBuyerPoolList.size();
                            unDistributeHkCount = unDistributeHkCount - 1;
                            break;
                        }
                    }
                }
            }
        }
        //TODO 调用接口更新买手表中管家和买家关系
    }

    /**
     * 获取管家当前绑定专属会员数或锁定期买家数
     * @param param
     * @return
     */
    public int countCurrentBuyers(BaseParam param){
        return this.getCount(SqlId.COUNT_CURRENT_BUYER,param);
    }

    /**
     * 获取管家组下的所有管家以及每个管家的配置信息
     * @param param
     * @return
     */
    public List<BBR121407Bean> findHkSettingInfoByGroupId(BaseParam param){
        return this.findList(SqlId.FIND_HK_SETTING_BY_GROUP_ID,param);
    }
    /**
     * 获取单个管家配置信息
     * @param param
     * @return
     */
    public BBR121407Bean findHkSettingInfoByHk(BaseParam param){
        return this.findOne(SqlId.FIND_HK_SETTING_BY_HK,param);
    }

    /**
     * 获取管家绑定的所有买家以及绑定的开始时间和结束时间
     * @param param
     * @return
     */
    public List<BBR121407Bean> findBindBuyerByHk(BaseParam param){
        return this.findList(SqlId.FIND_BIND_BUYER_BY_HK,param);
    }
}
