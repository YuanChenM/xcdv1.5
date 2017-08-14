package com.msk.bms.ssc.bean;


import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.bean.seller.SL241116Bean;
import com.msk.common.base.BaseBean;
import com.msk.common.bean.RsPageResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISC182209RsResult extends RsPageResult implements Serializable {

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
