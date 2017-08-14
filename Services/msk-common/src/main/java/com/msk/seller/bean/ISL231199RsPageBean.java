package com.msk.seller.bean;

import com.msk.common.bean.RsPageResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang_chi on 2016/6/16.
 */
public class ISL231199RsPageBean  extends RsPageResult implements Serializable {


    private List<ISL231199RsBean> pageResult;

    public List<ISL231199RsBean> getPageResult() {
        return pageResult;
    }

    public void setPageResult(List<ISL231199RsBean> pageResult) {
        this.pageResult = pageResult;
    }
}
