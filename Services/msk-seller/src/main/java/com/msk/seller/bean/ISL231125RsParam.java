package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by Administrator on 2016/2/16.
 */
public class ISL231125RsParam  extends BaseParam {
    /**
     * 企业ID
     */
    public String epId;
    /**
     * 企业名称
     */
    public String epName;

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }
}
