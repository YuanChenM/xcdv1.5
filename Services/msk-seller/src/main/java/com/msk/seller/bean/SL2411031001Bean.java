package com.msk.seller.bean;

import java.util.Date;

import com.msk.common.base.BaseBean;

public class SL2411031001Bean extends BaseBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
  
    
    private String slCode;//卖家编码                                

    private Integer producerEpId;    //生产商_企业ID                             

    private Date authTermBegin; //授权期限开始
    
    private Date authTermEnd; //授权期限结束
    
    private String authTermUnliimited;//授权期限长期标志
    
    private String crtId;//建者ID

    public String getSlCode() {
        return this.slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public Integer getProducerEpId() {
        return this.producerEpId;
    }

    public void setProducerEpId(Integer producerEpId) {
        this.producerEpId = producerEpId;
    }

    public Date getAuthTermBegin() {
        return this.authTermBegin;
    }

    public void setAuthTermBegin(Date authTermBegin) {
        this.authTermBegin = authTermBegin;
    }

    public Date getAuthTermEnd() {
        return this.authTermEnd;
    }

    public void setAuthTermEnd(Date authTermEnd) {
        this.authTermEnd = authTermEnd;
    }

    public String getAuthTermUnliimited() {
        return this.authTermUnliimited;
    }

    public void setAuthTermUnliimited(String authTermUnliimited) {
        this.authTermUnliimited = authTermUnliimited;
    }

    public String getCrtId() {
        return this.crtId;
    }

    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

   
}
