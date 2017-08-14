package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by zhang_chi on 2016/10/19.
 */
public class ISLEnterpriseRsPageResult extends BaseEntity {


    /* oem  agent 类型  结果 */
    private ISLEnterpriseRsPage oemAgentResult;

    /* self 类型  结果 */
    private ISLEnterpriseRsPage selfResult;


    public ISLEnterpriseRsPage getOemAgentResult() {
        return oemAgentResult;
    }

    public void setOemAgentResult(ISLEnterpriseRsPage oemAgentResult) {
        this.oemAgentResult = oemAgentResult;
    }

    public ISLEnterpriseRsPage getSelfResult() {
        return selfResult;
    }

    public void setSelfResult(ISLEnterpriseRsPage selfResult) {
        this.selfResult = selfResult;
    }
}
