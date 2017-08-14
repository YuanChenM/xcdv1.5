package com.hoperun.batch;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * *BaseBatch
 * *@author jiang_nan
 * *@version 1.0
 **/
public abstract class BaseBatch {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BaseBatch.class);
    /**
     * Batch执行表
     * @param args 参数
     */
    public void execution(String[] args) {
        long startTime = System.currentTimeMillis();
        String batchId = args[0];
        logger.info(batchId + " Batch 开始执行");
        int length = args.length;
        String[] data = null;
        //设置Batch参数
        if (length > NumberConst.IntDef.INT_ONE) {
            data = new String[length - NumberConst.IntDef.INT_ONE];
            for (int i = 0; i < length - NumberConst.IntDef.INT_ONE; i++) {
                data[i] = args[i + 1];
            }
        }
        BaseParam param = this.createParam(data);
        this.saveBatchRecord(batchId);
        try {
            this.before(param);
            this.doOperate(param);
            this.after(param);
        }catch (Exception ex){
            logger.error("Batch异常结束："+ex.getMessage());
        }finally {
            this.modifyBatchRecord(batchId);
            long endTime = System.currentTimeMillis();
            logger.info(batchId + "执行结束。花费时间:{} ms", endTime - startTime);
        }
    }

    /**
     * 更新Batch状态
     * @param batchId Batch Id
     */
    public abstract void modifyBatchRecord(String batchId);

    /**
     * 插入Batch状态
     * @param batchId
     */
    public abstract void saveBatchRecord(String batchId);

    /**
     * 创建Param
     *
     * @param args
     *        参数
     * @return Param
     */
    public abstract BaseParam createParam(String[] args);
    /**
     * 业务处理
     *
     * @param param
     *        param
     */
    public abstract void doOperate(BaseParam param);
    /**
     * 业务处理之前的操作
     *
     * @param param
     *        param
     */
    public void before(BaseParam param) {
        // nothing to do in base batch
        logger.debug("Batch Before operate");
    }
    /**
     * 整体业务出来完了
     *
     * @param param
     *        param
     */
    public void after(BaseParam param) {
        // nothing to do in base batch
        logger.debug("After Before operate");
    }
}
