package com.msk.ssc.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC11307RsParam;
import com.msk.ssc.bean.SSC11320RsBean;
import com.msk.ssc.bean.SSC11320RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SSC11320Logic extends BaseLogic {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(SSC11320Logic.class);

    @Autowired
    public CommonLogic commonLogic;

    interface SqlId {
        String FIND_REQUSET= "findRequest";
        String FIND_INFO= "findInfo";
        String GET_ACCUMULATE_AMOUNT= "getAccumulateAmount";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询付款申请表Logic
     * @param ssc11320RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11320RsBean findRequestById(SSC11320RsParam ssc11320RsParam) {
        logger.info("查询付款申请表Logic");
        return super.findOne(SqlId.FIND_REQUSET, ssc11320RsParam);
    }

    /**
     * 查询付款记录Logic
     * @param ssc11320RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11320RsBean findInfoById(SSC11320RsParam ssc11320RsParam) {
        logger.info("查询付款记录表Logic");
        return super.findOne(SqlId.FIND_INFO, ssc11320RsParam);
    }

    /**
     * 查询累计金额Logic
     * @param ssc11320RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public BigDecimal getAccumulateAmount(SSC11320RsParam ssc11320RsParam) {
        logger.info("查询累计金额Logic");
        return (BigDecimal)super.findObject(SqlId.GET_ACCUMULATE_AMOUNT, ssc11320RsParam);
    }

}