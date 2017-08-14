package com.msk.seller.bean;

import com.msk.core.entity.SlDistReguSug;

import java.util.List;

/**
 * Created by gyh on 2016/2/24.
 */
public class ISL231202RsParam {
    //分销章程卖家意见
    private List<SlDistReguSug> slSugs ;

    /**
     * 获得slSugs
     */
    public List<SlDistReguSug> getSlSugs() {
        return slSugs;
    }

    /**
     * 设置slSugs
     */
    public void setSlSugs(List<SlDistReguSug> slSugs) {
        this.slSugs = slSugs;
    }
}
