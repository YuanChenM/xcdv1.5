package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121103Bean;
import com.msk.buyers.bean.IBY121103RsParam;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * IBY121103Logic.
 *
 * @author yang_yang
 */
@Service
public class IBY121103Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121103Logic.class);

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        //获取批发市场列表
        static String SQL_FIND_MARKER_TERMINAL_LIST = "findMarkerTerminalList";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获取批发市场列表
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121103Bean> getMarkerList(IBY121103RsParam param) {

        logger.info("获取批发市场列表开始");

        List<IBY121103Bean> markerList = this.findList(SqlId.SQL_FIND_MARKER_TERMINAL_LIST, param);

        logger.info("获取批发市场列表结束");

        return markerList;
    }

}
