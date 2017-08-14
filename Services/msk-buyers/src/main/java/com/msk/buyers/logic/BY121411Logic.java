package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121001Bean;
import com.msk.buyers.bean.BY121409Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByMarketFood;
import com.msk.core.entity.ByMarketFoodBasic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 菜场新增 logic
 * Created by zhou_yajun on 2016/7/8.
 */
@Service
public class BY121411Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121411Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BY121409Logic by121409Logic;
    @Autowired
    private BY121001Logic by121001Logic;

    interface SqlId{
        String MODIFY_FOOD = "modifyFood";
        String ADD_FOOD = "addFood";
        String FIND_BUYER_BY_MARKETID = "findBuyerByMarketId";
        String MODIFY_BUYER_BASIC = "modifyBuyerBasic";
        String SQL_GET_FOD_MARKET_ID = "getFodMarketId";
    }
    /**
     * 菜场定性定级审批保存
     * @param by121409Bean
     */
    @Transactional
    public void marketApproveSave(BY121409Bean by121409Bean){
        //根据传参菜场ID获取当前菜场的基本信息
        BaseParam param = new BaseParam();
        param.setFilter("marketId",by121409Bean.getMarketId());
        ByMarketFoodBasic marketFoodBasic = by121409Logic.findOne(param);
        //先将本条数据先更新为历史数据,再插入一条新数据
        by121409Bean.setIsPhaseNew("0");
        int resultCount = by121409Logic.modify(by121409Bean);
        if(resultCount == NumberConst.IntDef.INT_ONE){
            Long maxId = commonLogic.maxId("by_market_terminal_basic","ID");
            marketFoodBasic.setId(maxId);
            marketFoodBasic.setMarketCode(by121409Bean.getMarketCode());
            marketFoodBasic.setMarketNature(by121409Bean.getMarketNature());
            marketFoodBasic.setMarketNatureName(by121409Bean.getMarketNatureName());
            this.save(marketFoodBasic);
        }
    }

    /**
     * 当审批时该菜场处于现场稽核阶段时,需要同步菜场标准表数据
     * 更新买家基本信息中买家编码和菜场编码
     * @param by121409Bean
     */
    @Transactional
    public void synchroBuyerCodeByMarketCode(BY121409Bean by121409Bean){

        //菜场ID可确定唯一的菜场
        String marketId = by121409Bean.getMarketId();

        BaseParam param = new BaseParam();
        param.setFilter("marketId",marketId);
        //获取菜场基本信息
        ByMarketFoodBasic marketFoodBasic = by121409Logic.findOne(param);
        if(marketFoodBasic != null){
            by121409Bean.setMarketName(marketFoodBasic.getMarketName());
            by121409Bean.setMarketAddr(marketFoodBasic.getMarketAddr());
            by121409Bean.setSectionType(marketFoodBasic.getSectionType());
            by121409Bean.setSectionTypeName(marketFoodBasic.getSectionTypeName());
            by121409Bean.setLgcsAreaCode(marketFoodBasic.getLgcsAreaCode());
            by121409Bean.setLgcsAreaName(marketFoodBasic.getLgcsAreaName());
            by121409Bean.setCityCode(marketFoodBasic.getCityCode());
            by121409Bean.setCityName(marketFoodBasic.getCityName());
            by121409Bean.setDistrictCode(marketFoodBasic.getDistrictCode());
            by121409Bean.setDistrictName(marketFoodBasic.getDistrictName());
        }
        //根据菜场ID查询在标准表是否存在
        int count = this.getCount(param);
        //如果存在则更新,不存在则插入
        if(count == NumberConst.IntDef.INT_ONE){
            this.modify(SqlId.MODIFY_FOOD,by121409Bean);
        }else{
            /*this.save(SqlId.ADD_FOOD,by121409Bean);*/
        }
        this.modifyBuyerCode(by121409Bean);
    }

    /**
     * 同步买家基本信息表中该菜场的编码,买家编码
     * @param by121409Bean
     */
    public void modifyBuyerCode(BY121409Bean by121409Bean){
        BaseParam param = new BaseParam();
        param.setFilter("marketId",by121409Bean.getMarketId());
        //同步买家基本信息表中该菜场的编码,买家编码
        //根据菜场ID获取使用该菜场的买家信息
        List<ByBuyerBasicInfo> buyerByMarkerIdList = this.findList(SqlId.FIND_BUYER_BY_MARKETID,param);
        if(!CollectionUtils.isEmpty(buyerByMarkerIdList)){
            for (ByBuyerBasicInfo basicInfo : buyerByMarkerIdList){
                //替换买家编码前10位为最新的菜场编码
                String buyerCode = basicInfo.getBuyerCode();
                String buyerType = basicInfo.getSuperiorType();
                String buyerSubType = basicInfo.getSuperiorSubType();
                //获取买家校验码
                BY121001Bean by121001Bean = new BY121001Bean();
                by121001Bean.setBuyerCode(buyerCode);
                String identifyCode = by121001Logic.getSecIdenCode(by121001Bean);
                //菜场买家时
                if("02".equals(buyerType)){
                    buyerCode = buyerType + by121409Bean.getMarketCode() + buyerCode.substring(12,buyerCode.length() - 2) + identifyCode;
                }else if("05".equals(buyerType)){
                    //中餐卤肉熟食买家类型时
                    if("01".equals(buyerSubType)){
                        buyerCode = buyerType + buyerSubType + by121409Bean.getMarketCode() + buyerCode.substring(14,buyerCode.length() - 2) + identifyCode;
                    }
                }
                basicInfo.setBuyerCode(buyerCode);
                basicInfo.setSuperiorQua(by121409Bean.getMarketNatureName());
                //更新买家基本信息表
                this.modify(SqlId.MODIFY_BUYER_BASIC,basicInfo);
            }
        }
    }

    /**
     * 查询菜场id
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByMarketFood> getFodMarketId(BaseParam param){
        return super.findList(SqlId.SQL_GET_FOD_MARKET_ID, param);
    }

}
