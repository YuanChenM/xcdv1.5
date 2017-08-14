package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.IvmTransactionLogBean;
import com.msk.inventory.service.ITransactionLogService;
import org.springframework.stereotype.Service;

/**
 * Created by zheng_xu on 2016/8/15.
 */
@Service
public class TransactionLogServiceImpl extends BaseService implements ITransactionLogService{
    @Override
    public void insertOneTransactionLog(IvmTransactionLogBean sqlBean) {
        this.save("insertTransactionLog",sqlBean);
    }
}
