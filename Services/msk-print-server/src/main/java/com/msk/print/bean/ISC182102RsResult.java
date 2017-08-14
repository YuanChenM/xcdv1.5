package com.msk.print.bean;

import com.msk.comm.bean.BaseBean;

import java.util.List;

/**
 * Created by li_kai1 on 2016/8/5.
 */
public class ISC182102RsResult extends BaseBean {
    private static final long serialVersionUID=1L;

    /** 发货入库页面信息 */
    private SC182101Bean sc182102Info;

    /** 发货入库单明细list */
    private List<SC182102Param> detailList;


    public SC182101Bean getSc182102Info() {
        return sc182102Info;
    }

    public void setSc182102Info(SC182101Bean sc182102Info) {
        this.sc182102Info = sc182102Info;
    }

    public List<SC182102Param> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<SC182102Param> detailList) {
        this.detailList = detailList;
    }
}
