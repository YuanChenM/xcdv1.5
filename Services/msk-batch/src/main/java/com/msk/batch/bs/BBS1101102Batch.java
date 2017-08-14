package com.msk.batch.bs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoperun.core.bean.BaseParam;
import com.msk.batch.BaseBatch;
import com.msk.batch.bs.bean.BBS1101102Param;
import com.msk.batch.bs.logic.BBS1101102Logic;

/**
 * BBS1101102Batch.
 * 
 * @author wang_haichun
 */
@Component("BBS1101102")
public class BBS1101102Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBS1101102Batch.class);

    /** 注入BSO152401Logic */
    @Autowired
    private BBS1101102Logic bbs1101102Logic;

    /**
     * 创建Param
     * 
     * @param args String[]
     * @return BSO152401Param
     */
    @Override
    public BaseParam createParam(String[] args) {
        logger.debug("BSO152402Batch创建param");
        BBS1101102Param param = new BBS1101102Param();
        return param;
    }

    /**
     * 业务处理
     * 
     * @param param param
     */
    @Override
    public void doOperate(BaseParam param) {
        logger.debug("BBS1101102Batch的doOperate");
        bbs1101102Logic.init();
    }

}
