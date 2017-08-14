package com.msk.inventory.service;

import com.msk.inventory.bean.IvmTransactionLogBean;

/**
 * Created by zheng_xu on 2016/8/15.
 */
public interface ITransactionLogService {

    void insertOneTransactionLog(IvmTransactionLogBean sqlBean);
}
