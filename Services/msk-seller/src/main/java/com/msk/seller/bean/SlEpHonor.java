package com.msk.seller.bean;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msk.common.base.BaseBean;

public class SlEpHonor extends BaseBean {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SlEpHonor.class);
    //企业ID
    private  Long epId;
    //荣誉ID
    private Integer honorId;
    //荣誉描述
    private String honorNo;
    //发证日期 
    private Date certDate;
    //发证单位
    private String certIssuer;
    //删除标志
    private String delFlg;
    //创建者ID
    private String crtId;
    //创建日时
    private Date crtTime;
    //更新者ID
    private String updId;
    //更新日时 
    private Date updTime;
    //生效者ID
    private String actId;
    //生效日时
    private Date actTime;
    //版本号
    private Integer ver;
    //资质描述
    private String desc;
    
    public Long getEpId() {
        return this.epId;
    }
    public void setEpId(Long epId) {
        this.epId = epId;
    }
    public Integer getHonorId() {
        return this.honorId;
    }
    public void setHonorId(Integer honorId) {
        this.honorId = honorId;
    }
    public Date getCertDate() {
        return this.certDate;
    }
    public void setCertDate(Date certDate) {
        this.certDate = certDate;
    }
    public String getCertIssuer() {
        return this.certIssuer;
    }
    public void setCertIssuer(String certIssuer) {
        this.certIssuer = certIssuer;
    }

    public String getCrtId() {
        return this.crtId;
    }
    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }
    public Date getCrtTime() {
        return this.crtTime;
    }
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
    public String getUpdId() {
        return this.updId;
    }
    public void setUpdId(String updId) {
        this.updId = updId;
    }
    public Date getUpdTime() {
        return this.updTime;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public String getActId() {
        return this.actId;
    }
    public void setActId(String actId) {
        this.actId = actId;
    }
    public Date getActTime() {
        return this.actTime;
    }
    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }
    public Integer getVer() {
        return this.ver;
    }
    public void setVer(Integer ver) {
        this.ver = ver;
    }
    public String getDelFlg() {
        return this.delFlg;
    }
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getHonorNo() {
        return honorNo;
    }

    public void setHonorNo(String honorNo) {
        this.honorNo = honorNo;
    }
}
