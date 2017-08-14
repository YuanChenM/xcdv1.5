package com.msk.seller.bean;


import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by zhangchi on 2016/5/9.
 */
public class ISL231193RsBean extends BaseParam {

    /**卖家ID*/
    private String slCode;

    /**卖家Account*/
    private String slAccount;

    /**卖家显示ID*/
    private String slCodeDis;

    /** 物流区编码 */
    private String lgcsAreaCode;

    /** 企业id */
    private String epId;

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

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }
}
