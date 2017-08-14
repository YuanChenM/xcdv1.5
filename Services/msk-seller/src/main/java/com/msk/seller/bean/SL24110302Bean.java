package com.msk.seller.bean;

import java.util.List;

import com.msk.common.base.BaseBean;

public class SL24110302Bean extends BaseBean{
/**
 * 
 * pxg
 * 
 * 企业专业资质
 */
    //企业ID
    private int epId;
    //证照ID
    private int certId;
    //证照名称
    private String certName;
    //顺序号
    private Long certSeq;
    //企业专业资质项目
    private List<SL24110302_1Bean> beanList;
    //路径
    private String imgUrl;
    public String getCertName() {
        return this.certName;
    }
    public void setCertName(String certName) {
        this.certName = certName;
    }
    public List<SL24110302_1Bean> getBeanList() {
        return this.beanList;
    }
    public void setBeanList(List<SL24110302_1Bean> beanList) {
        this.beanList = beanList;
    }
    public int getEpId() {
        return this.epId;
    }
    public void setEpId(int epId) {
        this.epId = epId;
    }
    public int getCertId() {
        return this.certId;
    }
    public void setCertId(int certId) {
        this.certId = certId;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

}
