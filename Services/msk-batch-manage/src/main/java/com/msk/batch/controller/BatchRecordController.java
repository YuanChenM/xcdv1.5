package com.msk.batch.controller;

import com.msk.batch.logic.BatchRecordLogic;
import com.msk.core.entity.BatchRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jackjiang on 16/7/4.
 */
@Controller
public class BatchRecordController {
    @Autowired
    private BatchRecordLogic batchRecordLogic;

    @RequestMapping(value = "/batch/record/save",method = RequestMethod.POST)
    public @ResponseBody String saveBatchRecord(BatchRecord batchRecord){
        this.batchRecordLogic.save(batchRecord);
        return "{\"flag\":\"success\"}";
    }
}
