package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

public class SL24110302_1Bean extends BaseBean{
/**
 * 
 * pxg
 * 
 * 企业专业资质
 */
    //企业ID
    private String epId;
    //证照ID
    private String certId;
    //证照顺序号
    private Long certSeq;
    //证照项目顺序号
    private String certItemSeq;
    //证照项目ID
    private String certItemId;
    //证照项目名称
    private String certItemName;
    //证照项目内容
    private String certItemValue;
    //删除标识
    private String delFlg;
    public String getEpId() {
        return this.epId;
    }
    public void setEpId(String epId) {
        this.epId = epId;
    }
    public String getCertId() {
        return this.certId;
    }
    public void setCertId(String certId) {
        this.certId = certId;
    }
    public String getCertItemId() {
        return this.certItemId;
    }
    public void setCertItemId(String certItemId) {
        this.certItemId = certItemId;
    }
    public String getCertItemName() {
        return this.certItemName;
    }
    public void setCertItemName(String certItemName) {
        this.certItemName = certItemName;
    }
    public String getCertItemValue() {
        return this.certItemValue;
    }
    public void setCertItemValue(String certItemValue) {
        this.certItemValue = certItemValue;
    }
    public String getDelFlg() {
        return this.delFlg;
    }
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }
    /**
     * 获得certSeq
     */
    public Long getCertSeq() {
        return certSeq;
    }

    /**
     * 设置certSeq
     */
    public void setCertSeq(Long certSeq) {
        this.certSeq = certSeq;
    }

    /**
     * 获得certItemSeq
     */
    public String getCertItemSeq() {
        return certItemSeq;
    }

    /**
     * 设置certItemSeq
     */
    public void setCertItemSeq(String certItemSeq) {
        this.certItemSeq = certItemSeq;
    }


}
