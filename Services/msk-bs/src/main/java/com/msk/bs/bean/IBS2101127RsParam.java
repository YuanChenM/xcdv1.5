/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "IBS2101127RsParam",description = "用户帐号")
public class IBS2101127RsParam extends BaseParam {
    //买手账号
    @ApiModelProperty(value = "买手账号")
    private  String slAcount;
    @ApiModelProperty(value = "买手号码")
    private  String slTel;

    public String getSlAcount() {
        return slAcount;
    }

    public void setSlAcount(String slAcount) {
        this.slAcount = slAcount;
    }

    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }
}
