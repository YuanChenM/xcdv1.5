package com.msk.batch.sp;


import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.exception.SystemException;
import com.msk.batch.BaseBatch;
import com.hoperun.core.bean.BaseParam;
import com.msk.batch.sp.bean.BSP171102Param;
import com.msk.batch.sp.logic.BSP171102Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BSP171102Batch.
 *
 * @author sun_jiaju
 */
@Component("BSP171102")
public class BSP171102Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSP171102Batch.class);

    /** 注入BSP171102Logic */
    @Autowired
    private BSP171102Logic bsp171102Logic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] args) {
        logger.debug("BSP171102Batch创建param");
        BSP171102Param param  = new BSP171102Param();
        return param;
    }

    /**
     * 业务处理
     *
     * @param baseParam baseParam
     */
    @Override
    public void doOperate(BaseParam baseParam) {
        logger.debug("BSP171102Batch的doOperate");
        try {
            bsp171102Logic.dataResolve(baseParam);
        } catch (BusinessException be) {
            logger.error(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException(e);
        }
    }
}
