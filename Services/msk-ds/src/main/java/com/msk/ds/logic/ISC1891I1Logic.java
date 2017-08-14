package com.msk.ds.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.ds.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * ISC1891I1Logic.
 *
 * @author yuan_chen
 */
@Service
public class ISC1891I1Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISC1891I1Logic.class);

    /**
     *
     * SqlId.
     *
     * @author pxg
     */
    interface SqlId {
        /** 订单主 */
        static final String SQL_ID_GET_UN_STOCK_NUM = "getUnStockNum";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 查询供应计划尚未入库的供应量
     *
     * @param param param
     * @return 查询供应计划尚未入库的供应量
     */
    @Transactional(readOnly = true)
    public ISC1891I1RsResult findAllList(RsRequest<ISC1891I1RsParam> param) {
        logger.info("查询供应计划尚未入库的供应量");
        ISC1891I1RsResult returnRst = new ISC1891I1RsResult();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("distMonth",param.getParam().getDistMonth());
        paramMap.put("lgcsCode",param.getParam().getLgcsCode());
        paramMap.put("suppCode",param.getParam().getSuppCode());
        paramMap.put("pdCode",param.getParam().getPdCode());
        List<ISC1891I1RsResultInfo> isc1891I1RsResults = super.findList(paramMap, SqlId.SQL_ID_GET_UN_STOCK_NUM);
        returnRst.setReturnInfos(isc1891I1RsResults);
        return returnRst;
    }
}
