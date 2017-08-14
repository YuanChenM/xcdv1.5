package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.common.properties.ConfigServerProperties;
import com.msk.inventory.bean.IvmTransactionLogBean;
import com.msk.inventory.entity.IvmTransactionLog;
import com.msk.inventory.service.ITransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zheng_xu on 2016/8/15.
 */
@RestController
@RequestMapping("api")
public class TransactionLogRestController {
    @Autowired
    private ConfigServerProperties configServerProperties;
    @Autowired
    private ITransactionLogService transactionLogService;

    /**
     * 插入一条库存业务日志
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/transactionLog/insert",method = RequestMethod.POST)
    public RestResponse<IvmTransactionLog> saveTransactionLog(
            @RequestBody RestRequest<IvmTransactionLogBean> requestBody){
        transactionLogService.insertOneTransactionLog(requestBody.getParam());
        return null;
    }

}
