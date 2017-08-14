package com.msk.seller.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/2/22.
 */
public class ISL231129RsResult {
    private static final long serialVersionUID = 1L;
    private List<ISL231127CertInfoList>  certInfoList;

    /**
     * 获得certInfoList
     */
    public List<ISL231127CertInfoList> getCertInfoList() {
        return certInfoList;
    }

    /**
     * 设置certInfoList
     */
    public void setCertInfoList(List<ISL231127CertInfoList> certInfoList) {
        this.certInfoList = certInfoList;
    }
}
