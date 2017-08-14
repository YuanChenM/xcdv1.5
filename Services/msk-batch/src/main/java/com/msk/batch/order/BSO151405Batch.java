package com.msk.batch.order;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.SystemException;
import com.msk.batch.BaseBatch;
import com.msk.batch.order.bean.BSO151405Param;
import com.msk.batch.order.logic.BSO151405Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生成退货单batch
 *
 * @author sunjiaju on 2016/8/18.
 */
@Component("BSO151405")
public class BSO151405Batch extends BaseBatch {
    /**
     * logger
     */
    @Autowired
    private static Logger logger = LoggerFactory.getLogger(BSO151405Batch.class);
    /**
     * 注入BSO152405Logic
     */
    @Autowired
    private BSO151405Logic bso151405Logic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return BSO152405Param
     */
    @Override
    public BaseParam createParam(String[] args) {
        logger.debug("BSO152405Batch创建param");
        BSO151405Param param = new BSO151405Param();
        return param;
    }

    @Override
    public void doOperate(BaseParam param) {
        logger.debug("BSO152405Batch的doOperate");
        BSO151405Param bso151405Param = (BSO151405Param) param;
        try {
            bso151405Logic.createReturnXml(bso151405Param);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
