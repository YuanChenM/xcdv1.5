package com.msk.batch.cp;

import com.hoperun.core.bean.BaseParam;
import com.msk.batch.BaseBatch;
import com.msk.batch.cp.logic.BCP153102Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 账套设置
 * BCP153102Batch
 * 
 * @author zhang_chi
 */
@Component("BCP153102")
public class BCP153102Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BCP153102Batch.class);

    /** 注入BCP153102Logic */
    @Autowired
    private BCP153102Logic bcp153102Logic;

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
     */
    @Override
    public void doOperate(BaseParam param) {
        logger.debug("BCP153102Batch的doOperate");
        bcp153102Logic.dataResolve();
    }

}
