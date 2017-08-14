package com.msk.sms.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by jackjiang on 16/6/21.
 */
@ApiModel
public class MessageParam implements Serializable{
    @ApiModelProperty(value = "手机号码",required = true)
    private String mobile;

    /**
     * 获得mobile
     **/
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置mobile
     *
     * @param mobile mobile
     **/
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
