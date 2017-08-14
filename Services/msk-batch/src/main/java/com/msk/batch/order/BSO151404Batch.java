package com.msk.batch.order;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.msk.batch.BaseBatch;
import com.msk.batch.order.bean.BSO151404Bean;
import com.msk.batch.order.logic.BSO151404Logic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wang_shuai on 2016/8/30.
 */
@Component("BSO151404")
public class BSO151404Batch extends BaseBatch {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151404Batch.class);

    /**
     * 注入BSO151404Logic
     */
    @Autowired
    private BSO151404Logic bso151404Logic;


    /**
     * 创建BaseParam
     *
     * @param strings
     * @return
     */
    @Override
    public BaseParam createParam(String[] strings) {
        return new BaseParam();
    }

    @Override
    public void doOperate(BaseParam baseParam) {
        logger.debug("BSO152404Batch的doOperate");
        List<BSO151404Bean> stockOrderList = bso151404Logic.getStockOrderList();
        if (!CollectionUtils.isEmpty(stockOrderList)) {
            for (BSO151404Bean bso151404Bean : stockOrderList) {
                try {
                    bso151404Logic.stockTransfer(bso151404Bean);
                } catch (BusinessException e) {
                    logger.error(e.getMessage());
                    continue;
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    continue;
                }
            }
        }
    }
}
