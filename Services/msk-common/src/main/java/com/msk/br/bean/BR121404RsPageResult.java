package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrSingleByFileInfo;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/7/12.
 */
public class BR121404RsPageResult extends RsPageResult {

    private List<BrSingleByFileInfo> brSingleByFileInfoList;

    public List<BrSingleByFileInfo> getBrSingleByFileInfoList() {
        return brSingleByFileInfoList;
    }

    public void setBrSingleByFileInfoList(List<BrSingleByFileInfo> brSingleByFileInfoList) {
        this.brSingleByFileInfoList = brSingleByFileInfoList;
    }
}
