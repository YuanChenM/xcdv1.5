package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/7/4.
 */
public class SSC11311Result extends RsPageResult {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 入库差异单的集合 */
    private List<SSC11311Bean> sscDifferBasics;


    public List<SSC11311Bean> getSscDifferBasics() {
        return sscDifferBasics;
    }

    public void setSscDifferBasics(List<SSC11311Bean> sscDifferBasics) {
        this.sscDifferBasics = sscDifferBasics;
    }

}
