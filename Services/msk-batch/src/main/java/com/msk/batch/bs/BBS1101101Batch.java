package com.msk.batch.bs;

import com.hoperun.core.bean.BaseParam;
import com.msk.batch.BaseBatch;
import com.msk.batch.bs.logic.BBS1101101Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BBS1101101Batch.
 * 
 * @author gyh
 */
@Component("BBS1101101")
public class BBS1101101Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBS1101101Batch.class);

    /** 注入BSO152401Logic */
    @Autowired
    private BBS1101101Logic bbs1101101Logic;

    /**
     * 创建Param
     * 
     * @param args String[]
     * @return BSO152401Param
     */
    @Override
    public BaseParam createParam(String[] args) {
        logger.debug("BSO152401Batch创建param");

        BaseParam param = new BaseParam();

//        if (args.length > 0) {
//            if (StringUtil.equals(args[0], "test")) {
//                param.setTest(true);
//            }
//        }
        //Date currentDate = DateTimeUtil.getCustomerDate();
        //param.setCurrentDate(currentDate);
        return  param;
    }

    /**
     * 业务处理
     * 
     * @param param param
     */
    @Override
    public void doOperate(BaseParam param) {
        logger.debug("BBS1101101Batch的doOperate");
        bbs1101101Logic.removeSlBsBuyer();
    }

}
