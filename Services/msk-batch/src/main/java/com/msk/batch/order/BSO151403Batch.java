package com.msk.batch.order;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.exception.SystemException;
import com.msk.batch.BaseBatch;
import com.msk.batch.order.bean.BSO151403Param;
import com.msk.batch.order.logic.BSO151403Logic;
import com.msk.common.consts.StatusConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * *BSO151403Batch
 * *@author wang_jianzhou
 * *@version 1.0
 **/
@Component("BSO151403")
public class BSO151403Batch extends BaseBatch {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151403Batch.class);

    /** 注入Order共通类 */
    /*@Autowired
    private Order orderComm;*/
    /** 注入BSO152403Logic */
    @Autowired
    private BSO151403Logic bso151403Logic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return BSO151403Param
     */
    @Override
    public BaseParam createParam(String[] args) {
        logger.debug("BSO152403Batch创建param");

        BSO151403Param param = new BSO151403Param();
        return param;
    }

    @Override
    public void doOperate(BaseParam param) {
        logger.debug("BSO152403Batch的doOperate");

        BSO151403Param bso151403Param = (BSO151403Param) param;
        bso151403Param.setOrderStatus(StatusConst.OrderStatusDef.CONFIRM);
        try {
            bso151403Logic.findOrderList(bso151403Param);
        } catch (BusinessException be) {
            // throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
