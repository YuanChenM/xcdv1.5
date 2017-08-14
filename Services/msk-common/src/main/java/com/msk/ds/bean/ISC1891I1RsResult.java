package com.msk.ds.bean;

import com.msk.common.bean.RsPageResult;
import java.util.List;


/**
 * ISC1891I1RsResult.
 *
 * @author yuan_chen
 */
public class ISC1891I1RsResult extends RsPageResult {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public List<ISC1891I1RsResultInfo> getReturnInfos() {
        return returnInfos;
    }

    public void setReturnInfos(List<ISC1891I1RsResultInfo> returnInfos) {
        this.returnInfos = returnInfos;
    }

    /** 产品相关的信息 */
    private List<ISC1891I1RsResultInfo> returnInfos;

}
