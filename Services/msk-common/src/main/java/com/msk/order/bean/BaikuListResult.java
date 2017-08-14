package com.msk.order.bean;

import com.msk.common.bean.RsPageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/6/8.
 */
public class BaikuListResult extends RsPageResult {
    private List<BaikuResult> BaikuResultList=new ArrayList<>();

    public List<BaikuResult> getBaikuResultList() {
        return BaikuResultList;
    }

    public void setBaikuResultList(List<BaikuResult> baikuResultList) {
        BaikuResultList = baikuResultList;
    }
}
