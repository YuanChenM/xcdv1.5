package com.msk.seller.bean;


import com.msk.common.bean.RsPageResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang_chi on 2016/9/12.
 */
public class ISL231207PageResult extends RsPageResult implements Serializable {

    /**
     * 企业信息列表
     */
    private List<ISL231207Result> epList;

    public List<ISL231207Result> getEpList() {
        return epList;
    }

    public void setEpList(List<ISL231207Result> epList) {
        this.epList = epList;
    }
}
