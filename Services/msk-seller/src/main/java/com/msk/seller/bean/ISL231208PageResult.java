package com.msk.seller.bean;


import com.msk.common.bean.RsPageResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang_chi on 2016/9/12.
 */
public class ISL231208PageResult  extends RsPageResult implements Serializable {

    /**
     * 企业产品信息列表
     */
    private List<SL241116Bean> epPdList;

    public List<SL241116Bean> getEpPdList() {
        return epPdList;
    }

    public void setEpPdList(List<SL241116Bean> epPdList) {
        this.epPdList = epPdList;
    }
}
