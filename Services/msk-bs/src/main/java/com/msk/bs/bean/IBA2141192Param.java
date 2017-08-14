package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ni_shaotang on 2016/10/27.
 */
@ApiModel(value = "IBA2141192Param", description = "param")
public class IBA2141192Param extends BaseParam {
    @ApiModelProperty(value = "手机号码")
    private String slTel;
    @ApiModelProperty(value = "短信验证码")
    private String messCode;
    @ApiModelProperty(value = "图片验证码")
    private String imgCode;
    @ApiModelProperty(value = "新密码")
    private String password;
    @ApiModelProperty(value = "用户类型(2,冻品管家；3，买手)")
    private String accessType;

    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    public String getMessCode() {
        return messCode;
    }

    public void setMessCode(String messCode) {
        this.messCode = messCode;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
