package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ni_shaotang on 2016/7/14.
 */
@ApiModel(value = "IBA2141102Param", description = "param")
public class IBA2141102Param extends RsPageParam {

    @ApiModelProperty(value = "手机号码")
    private String slTel;

    @ApiModelProperty(value = "登录密码")
    private String accountPsd;

    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    public String getAccountPsd() {
        return accountPsd;
    }

    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }
}
