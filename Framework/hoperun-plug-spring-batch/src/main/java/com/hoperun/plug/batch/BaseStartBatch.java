package com.hoperun.plug.batch;

import com.hoperun.batch.BaseBatch;
import com.hoperun.core.exception.SystemException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * BaseStartBatch
 * @author jiang_nan
 * @version 1.0
 **/
public class BaseStartBatch extends com.hoperun.batch.BaseStartBatch{
    /**
     * 获得ApplicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        ApplicationContext context = new FileSystemXmlApplicationContext(
                new String[] { "classpath*:/spring/application-context.xml", "classpath*:/spring/mybatis-context.xml" });
        return context;
    }
    /**
     * 获得Batch.
     *
     * @param context
     *        context
     * @param batchId
     *        batchId
     * @return Batch
     * @throws SystemException
     *         如果BatchId不存在的,启动失败
     */
    public static BaseBatch getBaseBatch(ApplicationContext context, String batchId) {
        BaseBatch batch = context.getBean(batchId, BaseBatch.class);
        if (batch == null) {
            throw new SystemException("启动的Batch ID[" + batchId + "]不正确。");
        }
        return batch;
    }
}
