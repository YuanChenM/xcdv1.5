package com.msk.batch.br;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.br.logic.BBR121401Logic;
import com.msk.common.consts.StatusConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * BBR121401Batch.
 *
 * @author zhou_yajun
 */
@Component("BBR121401")
public class BBR121401Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121401Batch.class);

    /** 注入BBR121401Logic */
    @Autowired
    private BBR121401Logic bbr121401Logic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] args) {
        logger.debug("BBR121401Batch创建param");
        BaseParam param  = new BaseParam();
        //设置订单状态
        param.setFilter("orderStatus", String.valueOf(StatusConst.OrderStatusDef.ALL_RECEIPT));
        //设置订单明细状态
        param.setFilter("orderDetailStatus", String.valueOf(StatusConst.OrderDetailStatusDef.ALL_RECEIPT));
        //设置共通参数
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtId("Batch");
        param.setCrtTime(currentDate);
        param.setActId("Batch");
        param.setActTime(currentDate);
        param.setUpdId("Batch");
        param.setUpdTime(currentDate);
        return param;
    }

    /**
     * 业务处理
     *
     * @param baseParam baseParam
     */
    @Override
    public void doOperate(BaseParam baseParam) {
        logger.debug("BBR121401Batch的doOperate");

        bbr121401Logic.dataResolve(baseParam);
    }
}
