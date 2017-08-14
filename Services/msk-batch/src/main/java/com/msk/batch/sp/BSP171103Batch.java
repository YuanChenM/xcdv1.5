package com.msk.batch.sp;

import com.hoperun.core.bean.BaseParam;
import com.msk.batch.BaseBatch;
import com.msk.batch.sp.bean.BSP171103Param;
import com.msk.batch.sp.logic.BSP171103Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 物流区产品同步
 * Created by ni_shaotang on 2016/5/18.
 */
@Component("BSP171103")
public class BSP171103Batch extends BaseBatch {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSP171103Batch.class);

    /** 注入BSP171103Logic */
    @Autowired
    private BSP171103Logic bsp171103Logic;
    /**
     * 创建Param
     *
     * @param strings String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] strings) {
        logger.debug("BSP171103Batch创建param");
        BSP171103Param param  = new BSP171103Param();
        return param;
    }
    /**
     * 业务处理
     *
     * @param baseParam baseParam
     */
    @Override
    public void doOperate(BaseParam baseParam) {
        logger.debug("BSP171103Batch的doOperate");
        try {
            bsp171103Logic.dataResolve(baseParam);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
