package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrByPoolFileInfo;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/18.
 */
public class BR121401RsPageResult extends RsPageResult {

    private List<BrByPoolFileInfo> brByPoolFileInfos;

    public List<BrByPoolFileInfo> getBrByPoolFileInfos() {
        return brByPoolFileInfos;
    }

    public void setBrByPoolFileInfos(List<BrByPoolFileInfo> brByPoolFileInfos) {
        this.brByPoolFileInfos = brByPoolFileInfos;
    }
}
