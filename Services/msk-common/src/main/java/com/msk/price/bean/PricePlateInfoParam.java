package com.msk.price.bean;

import com.msk.common.bean.RsPageParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng_hao on 2016/6/6.
 */
public class PricePlateInfoParam extends RsPageParam {

    private static final long serialVersionUID = 1L;

    private String siteCode;
    private String auth;
    private String loginId;
    /*产品编码*/
    private String pdCode;
    /*物流区编码*/
    private String lgcsCode;
    /*价盘周期*/
    private String pricePeriod;

    private List<String> pdCodes;

    private List<PricePlateInfoParam> params = new ArrayList<>();

    public List<PricePlateInfoParam> getParams() {
        return params;
    }

    public void setParams(List<PricePlateInfoParam> params) {
        this.params = params;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

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

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public List<String> getPdCodes() {
        return pdCodes;
    }

    public void setPdCodes(List<String> pdCodes) {
        this.pdCodes = pdCodes;
    }


}
