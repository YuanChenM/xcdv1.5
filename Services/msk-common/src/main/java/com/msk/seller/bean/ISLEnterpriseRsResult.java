package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by zhang_chi on 2016/7/5.
 */
public class ISLEnterpriseRsResult extends BaseEntity {

    /** 卖家编码 */
    private  String  slCode;

    /** 卖家编码显示 */
    private  String  slCodeDis;

    /** 卖家账号*/
    private  String slAccount;

    /** 企業ID **/
    private String epId;

    /** 企业名称 */
    private  String  epName;

    /** 生产商编码 */
    private  String  slCodeManufacture;

    /** 授权期限开始 */
    private  Date  authTermBegin;

    /** 授权期限结束 */
    private  Date  authTermEnd;

    /** 生产商地址 */
    private  String  licAddr;

    /** 授权期限 */
    private  String  onTime;

    /** 行政区划 */
    private  String  slAreaCode;

    /** 卖家类型 */
    private  String  slMainClass;

    /** 企业List */
    private List<ISLEnterpriseRsResult> slEnterpriseList;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public List<ISLEnterpriseRsResult> getSlEnterpriseList() {
        return slEnterpriseList;
    }

    public void setSlEnterpriseList(List<ISLEnterpriseRsResult> slEnterpriseList) {
        this.slEnterpriseList = slEnterpriseList;
    }

    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    public Date getAuthTermBegin() {
        return authTermBegin;
    }

    public void setAuthTermBegin(Date authTermBegin) {
        this.authTermBegin = authTermBegin;
    }

    public Date getAuthTermEnd() {
        return authTermEnd;
    }

    public void setAuthTermEnd(Date authTermEnd) {
        this.authTermEnd = authTermEnd;
    }

    public String getLicAddr() {
        return licAddr;
    }

    public void setLicAddr(String licAddr) {
        this.licAddr = licAddr;
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    public String getSlAreaCode() {
        return slAreaCode;
    }

    public void setSlAreaCode(String slAreaCode) {
        this.slAreaCode = slAreaCode;
    }

    public String getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }
}
