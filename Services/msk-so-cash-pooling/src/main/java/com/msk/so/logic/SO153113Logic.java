package com.msk.so.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SoCpSelCharging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wu_honglei on 2016/5/5.
 */

@Service
public class SO153113Logic extends BaseLogic{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO153113Logic.class);

    /**
     * SqlId. sql定义
     *
     */
    interface SqlId {
        String SQL_ID_FIND_ALL = "findAll";
        String SQL_ID_FIND_ALL_COUNT = "findAllCount";
        String SQL_ID_FIND_BY_ID = "findById";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    @Transactional(readOnly = true)
    public PageResult<SoCpSelCharging> findSelChargingPage(BasePageParam param){
        logger.info("卖家资金池卖家交易计费详细列表");
        PageResult<SoCpSelCharging> result = this.findPage(param, SoCpSelCharging.class);
        return result;
    }


    @Transactional(readOnly = true)
    public SoCpSelCharging findSelChargingById(String selChargingId){
        logger.info("卖家资金池卖家交易计费详细详情");
        BaseParam param = new BaseParam();
        param.getFilterMap().put("selChargingId",selChargingId);
        return super.findOne(SqlId.SQL_ID_FIND_BY_ID,param);
    }


}
