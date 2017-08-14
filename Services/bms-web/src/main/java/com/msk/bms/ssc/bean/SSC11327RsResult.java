package com.msk.bms.ssc.bean;


import com.hoperun.jdbc.bean.PageResult;
import com.msk.core.entity.BaseEntity;


/**
 * SSC11327RsResult
 *
 * @author ph
 * @version 1.0
 **/
public class SSC11327RsResult extends BaseEntity {

    /**
     * 企业信息列表
     */
    PageResult<SSC11327RsBean> epInfoList = new PageResult<SSC11327RsBean>();

    public PageResult<SSC11327RsBean> getEpInfoList() {
        return epInfoList;
    }

    public void setEpInfoList(PageResult<SSC11327RsBean> epInfoList) {
        this.epInfoList = epInfoList;
    }
}
