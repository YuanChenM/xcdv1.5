package com.msk.order.bean.result;
import com.msk.common.bean.result.BaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;

/**
 * Created by liu_tao2 on 2016/8/5.
 */
@ApiModel(value = "ContentBean",description = "Rest服务业务参数")
public class ISO151414ContentBean extends BaseResult{
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
