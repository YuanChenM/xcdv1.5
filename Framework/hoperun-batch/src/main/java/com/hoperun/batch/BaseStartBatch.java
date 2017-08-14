package com.hoperun.batch;

import com.hoperun.core.exception.SystemException;

/**
 * *BaseStartBatch
 * *@author jiang_nan
 * *@version 1.0
 **/
public abstract class BaseStartBatch {
    /**
     * Check Param
     *
     * @param args param
     */
    public static void checkParam(String[] args) {
        if (args.length <= 0) {
            throw new SystemException("启动Batch的参数不正常");
        }
    }

}
