package com.msk.product.bean;

import java.util.List;

/**
 * Created by FjM on 2016/3/10.
 */
public class IPD141124RsResult{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private List<PD141147Bean> Resultlist;

    public List<PD141147Bean> getResultlist() {
        return Resultlist;
    }

    public void setResultlist(List<PD141147Bean> resultlist) {
        Resultlist = resultlist;
    }
}
