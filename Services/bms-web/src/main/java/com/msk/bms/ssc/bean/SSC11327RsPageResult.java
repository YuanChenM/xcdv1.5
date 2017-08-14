package com.msk.bms.ssc.bean;


import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BaseEntity;

import java.util.List;


/**
 * SSC11327RsResult
 *
 * @author ph
 * @version 1.0
 **/
public class SSC11327RsPageResult extends RsPageResult {

    /**
     * 企业信息列表
     */

    private List<SSC11327RsBean> epList;

    public List<SSC11327RsBean> getEpList() {
        return epList;
    }

    public void setEpList(List<SSC11327RsBean> epList) {
        this.epList = epList;
    }
}
