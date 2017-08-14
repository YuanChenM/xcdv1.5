package com.msk.comm.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.StatusConst;
import com.msk.core.entity.BatchRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jackjiang on 16/7/5.
 */
@Service
public class BatchRecordLogic extends BaseLogic{
    interface SqlId{
        String GET_BATCH_BY_BATCH_CODE_AND_STATUS = "getBatchByBatchCodeAndStatus";


    }

    @Transactional
    public BatchRecord getRunningBatch(String batchCode){
        BaseParam param = new BaseParam();
        param.setFilterObject("batchCode",batchCode);
        param.setFilterObject("status", StatusConst.BatchStatusDef.RUN);
        return super.findOne(SqlId.GET_BATCH_BY_BATCH_CODE_AND_STATUS,param);
    }
    @Transactional
    public BatchRecord getWaitBatch(String batchCode){
        BaseParam param = new BaseParam();
        param.setFilterObject("batchCode",batchCode);
        param.setFilterObject("status", StatusConst.BatchStatusDef.NEW);
        return super.findOne(SqlId.GET_BATCH_BY_BATCH_CODE_AND_STATUS,param);
    }




    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
