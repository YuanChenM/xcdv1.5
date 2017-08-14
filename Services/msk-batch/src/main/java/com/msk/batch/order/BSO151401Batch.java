package com.msk.batch.order;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.order.bean.BSO151401Param;
import com.msk.batch.order.logic.BSO151401Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liutao on 2016/9/7.
 */
@Component("BSO151401")
public class BSO151401Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151401Batch.class);

    @Autowired
    private BSO151401Logic bso151401Logic;

    @Override
    public BaseParam createParam(String[] strings) {
        logger.debug("BSO151401Batch创建param");

        BSO151401Param param = new BSO151401Param();
        param.setCurrentDate(DateTimeUtil.getCustomerDate());
        return param;
    }

    /**
     * 销售排行Batch
     * @param baseParam
     */
    @Override
    public void doOperate(BaseParam baseParam) {
        logger.debug("销售排行batch开始");
        bso151401Logic.saveSalesRanking(baseParam);
        logger.debug("销售排行batch结束");
    }
}
