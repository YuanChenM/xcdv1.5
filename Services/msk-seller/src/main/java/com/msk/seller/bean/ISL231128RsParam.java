package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 *增加企业专业资质传入参数
 */

public class ISL231128RsParam extends BaseParam{
    private static final long serialVersionUID = 1L;
   /**企业证照信息list*/
    private List<ISL231128CertInfoList> certInfoList;

    /**
     * 获得certInfoList
     */
    public List<ISL231128CertInfoList> getCertInfoList() {
        return certInfoList;
    }

    /**
     * 设置certInfoList
     */
    public void setCertInfoList(List<ISL231128CertInfoList> certInfoList) {
        this.certInfoList = certInfoList;
    }
}
