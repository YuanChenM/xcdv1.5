package com.msk.batch.order;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.SystemException;
import com.msk.batch.BaseBatch;
import com.msk.batch.order.bean.BSO151406Param;
import com.msk.batch.order.logic.BSO151406Logic;
import com.msk.common.consts.StatusConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生成发货取消单batch
 *
 * @author sunjiaju on 2016/8/18.
 */
@Component("BSO151406")
public class BSO151406Batch extends BaseBatch {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151406Batch.class);

    /**
     * 注入BSO152406Logic
     */
    @Autowired
    private BSO151406Logic bso151406Logic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return BSO152406Param
     */
    @Override
    public BaseParam createParam(String[] args) {
        logger.debug("BSO152406Batch创建param");
        BSO151406Param param = new BSO151406Param();
        return param;
    }

    @Override
    public void doOperate(BaseParam param) {
        logger.debug("BSO152406Batch的doOperate");
        BSO151406Param bso151406Param = (BSO151406Param) param;
        bso151406Param.setShipStatus(StatusConst.OrderShipStatusDef.WAIT_CANCEL);
        try {
            bso151406Logic.createCancelXml(bso151406Param);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
