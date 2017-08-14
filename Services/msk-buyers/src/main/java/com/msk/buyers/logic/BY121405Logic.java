package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121001Bean;
import com.msk.buyers.bean.BY121403Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.BuyersConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByMarketTerminal;
import com.msk.core.entity.ByMarketTerminalBasic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 批发市场新增 logic
 * Created by zhou_yajun on 2016/7/8.
 */
@Service
public class BY121405Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121405Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BY121403Logic by121403Logic;
    @Autowired
    private BY121001Logic by121001Logic;

    interface SqlId{
        String MODIFY_TERMINAL = "modifyTerminal";
        String ADD_TERMINAL = "addTerminal";
        String FIND_BUYER_BY_MARKETID = "findBuyerByMarketId";
        String MODIFY_BUYER_BASIC = "modifyBuyerBasic";
        String SQL_GET_TERMARKET_ID = "getTerMarketId";

    }
    /**
     * 批发市场定性定级审批保存
     * @param by121403Bean
     */
    @Transactional
    public void marketApproveSave(BY121403Bean by121403Bean){
        //根据传参批发市场ID获取当前批发市场的基本信息
        BaseParam param = new BaseParam();
        param.setFilter("marketId",by121403Bean.getMarketId());
        ByMarketTerminalBasic marketTerminalBasic = by121403Logic.findOne(param);
        //先将本条数据先更新为历史数据,再插入一条新数据
        by121403Bean.setIsPhaseNew("0");
        int resultCount = by121403Logic.modify(by121403Bean);
        if(resultCount == NumberConst.IntDef.INT_ONE){
            Long maxId = commonLogic.maxId("by_market_terminal_basic","ID");
            marketTerminalBasic.setId(maxId);
            marketTerminalBasic.setMarketCode(by121403Bean.getMarketCode());
            marketTerminalBasic.setMarketNature(by121403Bean.getMarketNature());
            marketTerminalBasic.setMarketLevel(by121403Bean.getMarketLevel());
            marketTerminalBasic.setMarketLevelName(by121403Bean.getMarketLevelName());
            by121403Logic.save(marketTerminalBasic);
        }
    }

    /**
     * 当审批时该批发市场处于现场稽核阶段时,需要同步批发市场标准表数据
     * 更新买家基本信息中买家编码和批发市场编码
     * @param by121403Bean
     */
    @Transactional
    public void synchroBuyerCodeByMarketCode(BY121403Bean by121403Bean){

        //批发市场ID可确定唯一的批发市场
        String marketId = by121403Bean.getMarketId();

        BaseParam param = new BaseParam();
        param.setFilter("marketId",marketId);
        //获取批发市场基本信息
        ByMarketTerminalBasic marketTerminalBasic = by121403Logic.findOne(param);
        if(marketTerminalBasic != null){
            by121403Bean.setMarketName(marketTerminalBasic.getMarketName());
            by121403Bean.setMarketAddr(marketTerminalBasic.getMarketAddr());
            by121403Bean.setLgcsAreaCode(marketTerminalBasic.getLgcsAreaCode());
            by121403Bean.setLgcsAreaName(marketTerminalBasic.getLgcsAreaName());
            by121403Bean.setCityCode(marketTerminalBasic.getCityCode());
            by121403Bean.setCityName(marketTerminalBasic.getCityName());
        }
        //根据批发市场ID查询在标准表是否存在
        int count = this.getCount(param);
        //如果存在则更新,不存在则插入
        if(count == NumberConst.IntDef.INT_ONE){
            this.modify(SqlId.MODIFY_TERMINAL,by121403Bean);
        }else{

            /*this.save(SqlId.ADD_TERMINAL,by121403Bean);*/
        }
        this.modifyBuyerCode(by121403Bean);
    }

    /**
     * 同步买家基本信息表中该批发市场的编码,买家编码
     * @param by121403Bean
     */
    public void modifyBuyerCode(BY121403Bean by121403Bean){
        BaseParam param = new BaseParam();
        param.setFilter("marketId",by121403Bean.getMarketId());
        //同步买家基本信息表中该批发市场的编码,买家编码
        //根据批发市场ID获取使用该批发市场的买家信息
        List<ByBuyerBasicInfo> buyerByMarkerIdList = this.findList(SqlId.FIND_BUYER_BY_MARKETID,param);
        if(!CollectionUtils.isEmpty(buyerByMarkerIdList)){
            for (ByBuyerBasicInfo basicInfo : buyerByMarkerIdList){
                //替换买家编码前10位为最新的批发市场编码
                String buyerCode = basicInfo.getBuyerCode();
                //获取买家校验码
                BY121001Bean by121001Bean = new BY121001Bean();
                by121001Bean.setBuyerCode(buyerCode);
                String identifyCode = by121001Logic.getSecIdenCode(by121001Bean);
                buyerCode = by121403Bean.getMarketCode() + buyerCode.substring(10,buyerCode.length() - 2) + identifyCode;
                basicInfo.setBuyerCode(buyerCode);
                basicInfo.setSuperiorQua(by121403Bean.getMarketLevelName());
                //更新买家基本信息表
                this.modify(SqlId.MODIFY_BUYER_BASIC,basicInfo);
            }
        }
    }

    /**
     * 查询批发市场Id
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByMarketTerminal> getTerMarketId(BaseParam param){
        return super.findList(SqlId.SQL_GET_TERMARKET_ID,param);
    }


}
