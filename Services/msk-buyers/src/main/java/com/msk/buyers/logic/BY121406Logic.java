package com.msk.buyers.logic;


import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121406Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByMarketTerminalBasic;
import com.msk.core.entity.ByMarketTerminalByInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 批发市场定性定级各阶段一览表 logic
 * Created by yuan_zhifei on 2016/7/13.
 */
@Service
public class BY121406Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121406Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private BY121403Logic by121403Logic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    @Transactional
    public void netMarketSave(List<BY121406Bean> paramList ,BaseEntity entity){
        List<ByMarketTerminalBasic> MarkBaseList = new ArrayList<ByMarketTerminalBasic>();
        for(BY121406Bean param:paramList){
            Long maxId = commonLogic.maxId("by_market_terminal_basic","ID");
            ByMarketTerminalBasic market = new ByMarketTerminalBasic();
            market.setId(maxId);
            market.setMarketId(param.getMarketId());
            market.setMarketNature(param.getNetMarketNature());
            market.setMarketLevelName(param.getNetMarketLevelName());
            market.setRadiationRangeTypeName(param.getNetRadiationRangeTypeName());
            market.setMarketName(param.getMarketName());
            market.setLgcsAreaName(param.getLgcsAreaName());
            market.setMarketAddr(param.getMarketAddr());
            market.setResearchPhase("3");
            market.setResearchPhaseName("网搜阶段");
            market.setIsMarketNew("0");
            market.setMarketStatus("1");
            MarkBaseList.add(market);
        }
        // 批量更新数据(1:通过批更新 创建新BEAN 以LIST对象做元素 SQL方可执行   2: 单纯循环处理)
        for(ByMarketTerminalBasic markets:MarkBaseList){
            // 先修改原有数据
            markets.setActId(entity.getActId());
            markets.setActTime(entity.getActTime());
            markets.setUpdId(entity.getUpdId());
            markets.setUpdTime(entity.getUpdTime());
            markets.setActId(entity.getActId());
            markets.setActTime(entity.getActTime());

            this.modify(markets);
            // 新增现有数据
            this.save(markets);
        }
    }
}
