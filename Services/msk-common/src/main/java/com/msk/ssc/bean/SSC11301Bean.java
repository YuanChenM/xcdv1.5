package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SscBidBasicInfo;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/6/28.
 */
public class SSC11301Bean extends SscBidBasicInfo{

    List<SscBidBasicInfo> sscBidBasicInfos;

    public List<SscBidBasicInfo> getSscBidBasicInfos() {
        return sscBidBasicInfos;
    }

    public void setSscBidBasicInfos(List<SscBidBasicInfo> sscBidBasicInfos) {
        this.sscBidBasicInfos = sscBidBasicInfos;
    }
}
