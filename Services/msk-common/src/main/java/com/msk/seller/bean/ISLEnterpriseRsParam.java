package com.msk.seller.bean;


import com.msk.common.bean.RsPageParam;

/**
 * Created by li_huiqian on 2016/8/17.
 */
public class ISLEnterpriseRsParam extends RsPageParam {

    /* 采购商卖家编码 */
    private String slCode;

    /* oem  agent 类型  */
    private ISLEnterpriseRsParam oemAgentParam;

    /* self 类型 */
    private ISLEnterpriseRsParam selfParam;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public ISLEnterpriseRsParam getSelfParam() {
        return selfParam;
    }

    public void setSelfParam(ISLEnterpriseRsParam selfParam) {
        this.selfParam = selfParam;
    }

    public ISLEnterpriseRsParam getOemAgentParam() {
        return oemAgentParam;
    }

    public void setOemAgentParam(ISLEnterpriseRsParam oemAgentParam) {
        this.oemAgentParam = oemAgentParam;
    }
}
