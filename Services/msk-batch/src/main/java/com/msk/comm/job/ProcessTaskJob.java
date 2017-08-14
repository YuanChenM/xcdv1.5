package com.msk.comm.job;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.utils.SpringContextUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.StartOnlineBatch;
import com.msk.comm.logic.BatchRecordLogic;
import com.msk.common.consts.StatusConst;
import com.msk.core.entity.BatchRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Created by jackjiang on 16/7/5.
 */
@Component("ProcessTaskJob")
public class ProcessTaskJob {
    private static Logger logger = LoggerFactory.getLogger(ProcessTaskJob.class);
    @Autowired
    private BatchRecordLogic batchRecordLogic;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Scheduled(cron = "*/5 * * * * ?")
    public void processJob(){
        if(!StartOnlineBatch.isLoadDataSourceEnd()){
            logger.info("动态数据源没有加载完了!");
            return;
        }
        logger.info("Online Batch 执行");
        BaseParam param = new BaseParam();
        BatchRecord batchRecord = this.batchRecordLogic.findOne(param);
        if(batchRecord == null){
            logger.info("没有找到相应的Online Batch需要执行的");
            return;
        }
        String batchCode = batchRecord.getBatchCode();
        if(StringUtil.isEmpty(batchCode)){
            logger.debug("Batch 数据库读入参数错误,改值为空");
            return;
        }
        String batchParameter = batchRecord.getParameter();
        if(StringUtil.isEmpty(batchParameter)){
            logger.debug("Batch 参数错误,不能为空");
            return;
        }
        BaseBatch batch = null;
        Long runId = batchRecord.getRunId();
        try{
            batch = SpringContextUtil.getBean(batchCode,BaseBatch.class);
        }catch (Exception ex){
            logger.debug("没有定义当前Batch");
            param.setFilterObject("status", StatusConst.BatchStatusDef.EXCETION);
            param.setFilterObject("updId","Batch");
            param.setFilterObject("updTime", DateTimeUtil.getCustomerDate());
            param.setFilterObject("result","没有定义当前Batch");
            param.setFilterObject("runId",runId);
            this.batchRecordLogic.modify(param);
            return;
        }
        String [] paramArray = batchParameter.split(" ");
        ProcessThread processThread = new ProcessThread(runId,batch,paramArray);
        this.taskExecutor.execute(processThread);

    }
}
