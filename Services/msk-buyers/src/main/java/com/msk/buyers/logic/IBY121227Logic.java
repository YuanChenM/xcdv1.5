package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tao_zhifa on 2016/8/4.
 */
@Service
public class IBY121227Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121226Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        String FIND_TOOL_TO_BUYERIDS = "findToolToBuyerIds";
        String FIND_RECTIME_BY_BUYERIDS = "findRecTimeByBuyerIds";
    }

    public ByBuyerBasicInfo findToolToBuyerIds(BaseParam param){
        return super.findOne(SqlId.FIND_TOOL_TO_BUYERIDS,param);
    }

    public ByBuyerBasicInfo  findRecTimeByBuyerIds(BaseParam param){
        return super.findOne(SqlId.FIND_RECTIME_BY_BUYERIDS,param);
    }


}
