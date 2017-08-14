package com.msk.batch;

import com.msk.batch.bean.BasePageParam;
import com.msk.batch.logic.BatchRecordLogic;
import com.msk.core.entity.BatchRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackjiang on 16/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
public class BatchRecordLogicTest {
    @Autowired
    private BatchRecordLogic batchRecordLogic;
    @Test
    public void testSave(){
        BatchRecord batchRecord = new BatchRecord();
        batchRecord.setRunId(99999L);
        batchRecord.setBatchCode("CCCC01");
        batchRecord.setStatus(1);
        this.batchRecordLogic.save(batchRecord);

    }
    @Test
    public void testModify(){
        BatchRecord batchRecord = new BatchRecord();
        batchRecord.setRunId(99999L);
        batchRecord.setStatus(2);
        this.batchRecordLogic.modify(batchRecord);
    }
    @Test
    public void testFindExecutingRecord(){
        BasePageParam param = new BasePageParam();
        this.batchRecordLogic.findExecutingRecord(param);
    }
}
