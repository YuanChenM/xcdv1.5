package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrFileBuyerPool;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/7/28.
 */
public class BR121406RsPageResult extends RsPageResult{

    private List<BR121405Bean> brFileBuyerPoolList;

    public List<BR121405Bean> getBrFileBuyerPoolList() {
        return brFileBuyerPoolList;
    }

    public void setBrFileBuyerPoolList(List<BR121405Bean> brFileBuyerPoolList) {
        this.brFileBuyerPoolList = brFileBuyerPoolList;
    }
}
