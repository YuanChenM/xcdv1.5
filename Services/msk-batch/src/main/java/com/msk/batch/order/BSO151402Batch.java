package com.msk.batch.order;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.msk.batch.BaseBatch;
import com.msk.batch.order.bean.BSO151402OrderInfo;
import com.msk.batch.order.bean.BSO151402Param;
import com.msk.batch.order.logic.BSO151402Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/22.
 */
@Component("BSO151402")
public class BSO151402Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151402Batch.class);

    @Autowired
    private BSO151402Logic bso151402Logic;

    @Override
    public BaseParam createParam(String[] strings) {
        logger.debug("BSO151402Batch创建param");

        BSO151402Param param = new BSO151402Param();

        return param;
    }

    @Override
    public void doOperate(BaseParam param) {
        logger.debug("分单batch开始");

        BSO151402Param bso151402Param = (BSO151402Param) param;
        List<BSO151402OrderInfo> soSubOrders = bso151402Logic.getOrdersByStatus(bso151402Param);
        for (BSO151402OrderInfo soSubOrder : soSubOrders) {
            try {
                bso151402Logic.distributionOrder(soSubOrder);
            } catch (BusinessException e) {
                logger.error(e.getMessage());
                continue;
            } catch (Exception e) {
                logger.error(e.getMessage());
                continue;
            }
        }
        logger.debug("分单batch结束");
    }
}
