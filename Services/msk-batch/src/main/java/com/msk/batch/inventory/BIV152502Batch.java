package com.msk.batch.inventory;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.inventory.bean.BIV152502Param;
import com.msk.batch.inventory.logic.BIV152502Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by duan_kai on 2016/8/17.
 */
@Component("BIV152502")
public class BIV152502Batch extends BaseBatch {
    /**
     * logger
     */
    @Autowired
    private static Logger logger = LoggerFactory.getLogger(BIV152502Batch.class);

    /**
     * 注入BIV152502Logic
     */
    @Autowired
    private BIV152502Logic biv152502Logic;

    /**
     * 创建param
     * @param args String[]
     *
     * @return
     */
    @Override
    public BaseParam createParam(String[] args){
        //logger.debug("BIV152501Batch创建param");
        BIV152502Param param = new BIV152502Param();
        param.setCrtId("1");
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtTime(currentDate);
        return param;
    }

    /**
     *
     * @param param
     */
    @Override
    public void doOperate(BaseParam param){
        logger.debug("BIV152501Batch的doOperate");
        try {
            biv152502Logic.touchProdIVFromIVDetail(param);
        } catch (BusinessException be) {
            // TODO 业务异常处理
            // throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
