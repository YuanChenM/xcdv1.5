package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * Created by wang_jianzhou on 2016/8/10.
 */
public class LgcsAreaBean extends BaseResult {

    private String lgcsAreaCode;

    private String lgcsAreaName;

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }
}
