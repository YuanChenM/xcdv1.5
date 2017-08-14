package com.msk.price.bean;


import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;

/**
 * Created by xu_wei
 */
public class SP171107Param extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    //产品编码
    private String pdCode;
    //物流区编码
    private String lgcsCode;
    //卖家编码
    private String slCode;
    //申报周期
    private String demandYearmonth;
    //登录Id
    private String loginId;
    //申报id
    private String demandId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getDemandYearmonth() {
        return demandYearmonth;
    }

    public void setDemandYearmonth(String demandYearmonth) {
        this.demandYearmonth = demandYearmonth;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }
}
