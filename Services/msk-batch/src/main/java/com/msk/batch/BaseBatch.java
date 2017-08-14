package com.msk.batch;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.mail.bean.MailContext;
import com.msk.batch.mail.bean.MailTextContent;
import com.msk.comm.logic.BatchRecordLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.StatusConst;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BatchRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * *BaseBatch
 * *@author jiang_nan
 * *@version 1.0
 **/
public abstract class BaseBatch extends com.hoperun.batch.BaseBatch{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BaseBatch.class);
    @Autowired
    private BatchRecordLogic batchRecordLogic;

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
        BaseParam param = null;
        BaseParam modifyBatchRecordParam = new BaseParam();
        MailTextContent text = new MailTextContent();
        try {
            this.saveBatchRecord(batchId);
            param = this.createParam(data);
            this.before(param);
            this.doOperate(param);
            this.after(param);
            modifyBatchRecordParam.setFilterObject("status", StatusConst.BatchStatusDef.END);
            modifyBatchRecordParam.setFilterObject("result","正常结束");
            text.setSubject(batchId + "正常结束");
            text.setText(batchId + "正常结束");
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error("Batch异常结束："+ex.getMessage());
            modifyBatchRecordParam.setFilterObject("status", StatusConst.BatchStatusDef.END);
            modifyBatchRecordParam.setFilterObject("result","Batch异常结束");
            text.setSubject(batchId + "异常结束");
            text.setText(batchId + "异常结束");
        }finally {

            // TODO mail接口没发布，暂时不使用
            if(ConfigManager.isDebug()) {
                // 发送邮件
                RsRequest<MailContext<MailTextContent>> request = new RsRequest<MailContext<MailTextContent>>();
                MailContext<MailTextContent> content = new MailContext<MailTextContent>();
                content.setMailContent(text);
                String[] to = {"xcd.njleader.list@hoperun.com", "ma_b@hoperun.com"};
                content.setTo(to);
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setSiteCode("1");
                request.setParam(content);
                String url = SystemServerManager.mailServerManager.getSendTextMail();//"http://localhost:9490/msk-mail/api/text/mail/_send";
                RsResponse<String> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<String>>() {
                });
            }
            modifyBatchRecordParam.setFilterObject("updId","Batch");
            modifyBatchRecordParam.setFilterObject("updTime", DateTimeUtil.getCustomerDate());
            this.modifyBatchRecord(modifyBatchRecordParam,batchId);
            long endTime = System.currentTimeMillis();
            logger.info(batchId + "执行结束。花费时间:{} ms", endTime - startTime);
        }
    }

    public void modifyBatchRecord(BaseParam param,String batchId){
        BatchRecord runningBatch = this.batchRecordLogic.getRunningBatch(batchId);
        if(runningBatch == null){
            return;
        }
        param.setFilterObject("runId",runningBatch.getRunId());
        this.batchRecordLogic.modify(param);
    }

    @Override
    public void modifyBatchRecord(String batchId) {

    }
    @Override
    public void saveBatchRecord(String batchId){
        BatchRecord runningBatch = this.batchRecordLogic.getRunningBatch(batchId);
        if(runningBatch != null){
            throw new SystemException("当前Batch正在运行中,无法再次启用");
        }
        BatchRecord waitBatch = this.batchRecordLogic.getWaitBatch(batchId);
        if(waitBatch != null){
            BaseParam param = new BaseParam();
            param.setFilterObject("status", StatusConst.BatchStatusDef.RUN);
            param.setFilterObject("updId","Batch");
            param.setFilterObject("updTime", DateTimeUtil.getCustomerDate());
            param.setFilterObject("result","当前Batch执行中");
            param.setFilterObject("runId",waitBatch.getRunId());
            this.batchRecordLogic.modify(param);
            return;
        }
    }
}
