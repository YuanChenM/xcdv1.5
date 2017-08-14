package com.msk.seller.bean;


import com.msk.common.bean.RsPageResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang_chi on 2016/9/18.
 */
public class ISLEnterpriseRsPage extends RsPageResult implements Serializable {

    /**
     * 企业产品信息列表
     */
    private List<ISLEnterpriseRsResult> islEnterpriseList;

    public List<ISLEnterpriseRsResult> getIslEnterpriseList() {
        return islEnterpriseList;
    }

    public void setIslEnterpriseList(List<ISLEnterpriseRsResult> islEnterpriseList) {
        this.islEnterpriseList = islEnterpriseList;
    }
}
