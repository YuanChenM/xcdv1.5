package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/12.
 */
public class SSC11302RsPageResult extends RsPageResult {

    private Long  detailId;

    private List<SSC11302RsBeen> SSC11302RsBeen;

    public List<SSC11302RsBeen> getSSC11302RsBeen() {
        return SSC11302RsBeen;
    }

    public void setSSC11302RsBeen(List<SSC11302RsBeen> SSC11302RsBeen) {
        this.SSC11302RsBeen = SSC11302RsBeen;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
}
