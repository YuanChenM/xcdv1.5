package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121302Bean;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByMarketFood;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BY121302Logic.
 *
 * @author yuan_chen
 */
@Service
public class BY121302Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121302Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     */
    interface SqlId {
        static String SQL_DELETE_MARKET_FOOD = "deleteMarkerFood";
    }

    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public ByMarketFood findMarketFoodById(String marketId) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("marketId", marketId);
        return super.findOne(param);
    }

    @Transactional
    public int deleteMarketFoodById(BY121302Bean param) {
        /*BaseParam param = new BaseParam();
        param.getFilterMap().put("marketId", marketId);*/
        return super.modify(SqlId.SQL_DELETE_MARKET_FOOD, param);
    }
}
