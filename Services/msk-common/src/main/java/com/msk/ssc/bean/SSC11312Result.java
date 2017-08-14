package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/7/5.
 */
public class SSC11312Result extends RsPageResult {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 入库差异单详情的集合 */
    private List<SSC11312Bean> sscDifferDetails;


    public List<SSC11312Bean> getSscDifferDetails() {
        return sscDifferDetails;
    }

    public void setSscDifferDetails(List<SSC11312Bean> sscDifferDetails) {
        this.sscDifferDetails = sscDifferDetails;
    }

}
