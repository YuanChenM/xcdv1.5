package com.msk.seller.bean;

import com.msk.common.bean.RsPageResult;

import java.io.Serializable;
import java.util.List;

/**
 * 物流区供应商分页返回结果
 *
 * Created by yang_chunyan on 2016/6/8.
 */
public class ISL231198RsPageResult extends RsPageResult  implements Serializable {

    private List<ISL231198RsResult> pageResult;

    public List<ISL231198RsResult> getPageResult() {
        return pageResult;
    }

    public void setPageResult(List<ISL231198RsResult> pageResult) {
        this.pageResult = pageResult;
    }
}
