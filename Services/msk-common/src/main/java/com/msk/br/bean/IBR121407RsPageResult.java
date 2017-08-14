package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrFileBuyerPool;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/18.
 */
public class IBR121407RsPageResult extends RsPageResult {

    private List<BrFileBuyerPool> brFileBuyerPoolList;

    public List<BrFileBuyerPool> getBrFileBuyerPoolList() {
        return brFileBuyerPoolList;
    }

    public void setBrFileBuyerPoolList(List<BrFileBuyerPool> brFileBuyerPoolList) {
        this.brFileBuyerPoolList = brFileBuyerPoolList;
    }
}
