package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

public class SlInfo extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String slAccount;
    private String slAreaCode;
    private String slCode;
    private Integer slEpId;
    private String slSnkFlg;
    private String slSelfFlg;
    private String slProxyFlg;
    private String slOemFlg;
    private Integer sqaStaturs;
    private String distQua;
    private String shopQua;

    public String getSlAccount() {
        return this.slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlAreaCode() {
        return this.slAreaCode;
    }

    public void setSlAreaCode(String slAreaCode) {
        this.slAreaCode = slAreaCode;
    }

    public String getSlCode() {
        return this.slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public Integer getSlEpId() {
        return this.slEpId;
    }

    public void setSlEpId(Integer slEpId) {
        this.slEpId = slEpId;
    }

    public String getSlSnkFlg() {
        return this.slSnkFlg;
    }

    public void setSlSnkFlg(String slSnkFlg) {
        this.slSnkFlg = slSnkFlg;
    }

    public String getSlSelfFlg() {
        return this.slSelfFlg;
    }

    public void setSlSelfFlg(String slSelfFlg) {
        this.slSelfFlg = slSelfFlg;
    }

    public String getSlProxyFlg() {
        return this.slProxyFlg;
    }

    public void setSlProxyFlg(String slProxyFlg) {
        this.slProxyFlg = slProxyFlg;
    }

    public String getSlOemFlg() {
        return this.slOemFlg;
    }

    public void setSlOemFlg(String slOemFlg) {
        this.slOemFlg = slOemFlg;
    }

    public Integer getSqaStaturs() {
        return this.sqaStaturs;
    }

    public void setSqaStaturs(Integer sqaStaturs) {
        this.sqaStaturs = sqaStaturs;
    }

    public String getDistQua() {
        return this.distQua;
    }

    public void setDistQua(String distQua) {
        this.distQua = distQua;
    }

    public String getShopQua() {
        return this.shopQua;
    }

    public void setShopQua(String shopQua) {
        this.shopQua = shopQua;
    }

}
