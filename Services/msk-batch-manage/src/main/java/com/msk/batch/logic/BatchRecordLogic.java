package com.msk.batch.logic;

import com.msk.batch.bean.BasePageParam;
import com.msk.batch.bean.PageResult;
import com.msk.batch.consts.BusinessConstant;
import com.msk.batch.consts.NumberConst;
import com.msk.batch.consts.StatusConst;
import com.msk.batch.dao.BatchRecordDao;
import com.msk.batch.utils.DateTimeUtil;
import com.msk.core.entity.BatchRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 *Batch Record Logic
 * @author jiang_nan
 */
@Service
public class BatchRecordLogic {
    private static Logger logger = LoggerFactory.getLogger(BatchRecordLogic.class);
    @Autowired
    private BatchRecordDao batchRecordDao;
    @Transactional(readOnly = true)
    public PageResult<BatchRecord> findExecutingRecord(BasePageParam param){
        Map<String,Object> filterMap = param.getFilterMap();
        filterMap.put("status",String.valueOf(StatusConst.BatchStatusDef.RUN));
        return this.batchRecordDao.selectPageResult(param);
    }


    /**
     * 插入Batch 记录
     * @param batchRecord Batch记录
     */
    @Transactional
    public void save(BatchRecord batchRecord){
        logger.debug("Batch记录插入");
        Date customerDate = DateTimeUtil.getCustomerDate();

        batchRecord.setCrtId("Batch");
        batchRecord.setCrtTime(customerDate);

        batchRecord.setUpdId("Batch");
        batchRecord.setUpdTime(customerDate);

        batchRecord.setActId("Batch");
        batchRecord.setActTime(customerDate);
        //Online Batch
        batchRecord.setExecuteModel(BusinessConstant.ExecuteModel.ON_LINE);

        batchRecord.setVer(Integer.valueOf(NumberConst.IntDef.INT_ONE));

        batchRecord.setStatus(StatusConst.BatchStatusDef.NEW);

        this.batchRecordDao.insert(batchRecord);
    }


    /**
     * 更新Batch记录信息
     * @param batchRecord Batch 记录
     */
    @Transactional
    public void modify(BatchRecord batchRecord){
        logger.debug("Batch 记录更新");
        //更新影响行数
        int number = this.batchRecordDao.update(batchRecord);
        if(number== NumberConst.IntDef.INT_ZERO){
            throw new RuntimeException("更新失败");
        }

    }

}
