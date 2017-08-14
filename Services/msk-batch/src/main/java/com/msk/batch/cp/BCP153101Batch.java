package com.msk.batch.cp;

import com.hoperun.core.bean.BaseParam;
import com.msk.batch.BaseBatch;
import com.msk.batch.cp.logic.BCP153101Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BCP153101Batch.
 * 
 * @author Qiu_wenting
 */
@Component("BCP153101")
public class BCP153101Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BCP153101Batch.class);

    /** 注入BCP153101Logic */
    @Autowired
    private BCP153101Logic bcp153101Logic;

    /**
     * 创建Param
     * 
     * @param args String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] args) {

        return null;
    }

    /**
     * 业务处理
     * 
     * @param baseParam baseParam
     */
    @Override
    public void doOperate(BaseParam baseParam) {
        logger.debug("BCP153101Batch的doOperate");
        bcp153101Logic.dataResolve(baseParam);
    }

}
