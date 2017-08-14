package com.msk.mq.rest;


import com.msk.mq.bean.ContentBean;
import com.msk.mq.bean.ResultMessage;
import com.msk.mq.bean.RsRequest;
import com.msk.mq.bean.RsResponse;
import com.msk.mq.config.AmqpConfiguration;
import com.msk.mq.producer.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


/**
 * Created by mao_yejun on 2016/6/29.
 */
@RestController
@RequestMapping("api")
@Api(value = "MQ-Api",description = "接收MQ请求服务接口")
public class ReceiverController {
    private static Logger logger = LoggerFactory.getLogger(ReceiverController.class);
    @Autowired
    private Producer producer;
    @Autowired
    AmqpConfiguration amqpConfiguration;

    @RequestMapping(value = "/content/_send", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "发送消息",httpMethod = "POST",notes = "提供给内部系统使用的消息队列发送服务")
    public RsResponse<Void> send(@RequestBody RsRequest<ContentBean> content) {
        ContentBean contentBean=content.getParam();
        RsResponse response= new RsResponse();
        logger.info("队列名：" + contentBean.getQueueName() + " 消息：" + contentBean.getJsonParam());
        if (StringUtils.isEmpty(contentBean.getQueueName()) ||
                !amqpConfiguration.getQueues().contains(contentBean.getQueueName())) {
            response.setStatus("F");
            response.setMessage("队列名不正确！");
        } else {
            producer.sendContent(contentBean.getJsonParam(), contentBean.getQueueName());
            response.setStatus("S");
        }
        return response;
    }
}
