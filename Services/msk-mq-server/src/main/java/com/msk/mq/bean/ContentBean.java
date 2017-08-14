package com.msk.mq.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;

/**
 * Created by mao_yejun on 2016/6/30.
 */
@ApiModel(value = "ContentBean",description = "Rest服务业务参数")
public class ContentBean {
    @ApiModelProperty(value = "消息队列参数,数据格式为JSON格式")
    private HashMap jsonParam;
    @ApiModelProperty(value = "消息队列名称")
    private String queueName;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public HashMap getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(HashMap jsonParam) {
        this.jsonParam = jsonParam;
    }
}
