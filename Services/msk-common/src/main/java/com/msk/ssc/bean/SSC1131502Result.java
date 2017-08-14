package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/22.
 */
public class SSC1131502Result extends RsPageResult {

    private List<SSC1131502Bean> productHistories;


    public List<SSC1131502Bean> getProductHistories() {
        return productHistories;
    }

    public void setProductHistories(List<SSC1131502Bean> productHistories) {
        this.productHistories = productHistories;
    }

}
