package com.msk.batch.br.bean;
import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/9/18
 */
public class IBBR12140306RsResult extends RsPageResult {
    /** Modif for Bug #3555 at 2016/11/03 by yuan_zhifei Start*/
    //买手买家列表
    private List<IBBR12140306RsBean> slBsBuyerList;

    public List<IBBR12140306RsBean> getSlBsBuyerList() {
        return slBsBuyerList;
    }

    public void setSlBsBuyerList(List<IBBR12140306RsBean> slBsBuyerList) {
        this.slBsBuyerList = slBsBuyerList;
    }
    /** Modif for Bug #3555 at 2016/11/03 by yuan_zhifei End*/
}
