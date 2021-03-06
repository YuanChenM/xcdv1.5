package com.msk.product.bean;

import com.msk.core.entity.PdTcForbid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/3/22.
 */
@ApiModel(value = "PdTcForbidParam",description = "禁止准入产品")
public class PdTcForbidParam extends PdTcForbid {

    @ApiModelProperty(value = "消息提示")
    private String message;

    @ApiModelProperty(value = "divID获取")
    private String getDivId;

    /**
     * Getter method for property <tt>getDivId</tt>.
     *
     * @return property value of getDivId
     */
    public String getGetDivId() {
        return getDivId;
    }

    /**
     * Setter method for property <tt>getDivId</tt>.
     *
     * @param getDivId value to be assigned to property getDivId
     */
    public void setGetDivId(String getDivId) {
        this.getDivId = getDivId;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
