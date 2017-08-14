package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "IBS2101103RsParam",description = "param")
public class IBS2101103RsParam extends RsPageParam {
    @ApiModelProperty(value = "买手账号")
    private String slAccount;
    @ApiModelProperty(value = "登录密码")
    private String accountPsd;
    //Add for 添加查询条件属性 at 2016/10/08 by ni_shaotang Start
    @ApiModelProperty(value = "买手编码")
    private String slCodeDis;
    //Add for 添加查询条件属性 at 2016/10/08 by ni_shaotang end
    @ApiModelProperty(value = "买手id")
    private String slCode;

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>accountPsd</tt>.
     *
     * @return property value of accountPsd
     */
    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * Setter method for property <tt>accountPsd</tt>.
     *
     * @param accountPsd value to be assigned to property accountPsd
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * Getter method for property <tt>slAccount</tt>.
     *
     * @return property value of slAccount
     */
    public String getSlAccount() {
        return slAccount;
    }

    /**
     * Setter method for property <tt>slAccount</tt>.
     *
     * @param slAccount value to be assigned to property slAccount
     */
    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }
}
