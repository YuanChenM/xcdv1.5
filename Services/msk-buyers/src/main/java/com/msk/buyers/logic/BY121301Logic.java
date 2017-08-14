package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121301Bean;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByMarketTerminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BY121301Logic.
 *
 * @author yuan_chen
 */
@Service
public class BY121301Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121301Logic.class);

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        static String SQL_DELETE_MARKET_TERMINAL = "deleteMarkerTerminal";
    }

    /**
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
    public ByMarketTerminal findMarketTerminalById(String marketId) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("marketId", marketId);
        return super.findOne(param);
    }

    @Transactional
    public int deleteMarketTerminalById(BY121301Bean entity) {
        return super.modify(SqlId.SQL_DELETE_MARKET_TERMINAL, entity);
    }
}
