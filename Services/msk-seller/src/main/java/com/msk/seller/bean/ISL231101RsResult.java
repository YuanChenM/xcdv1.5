package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISL231101RsResult extends BaseEntity {
    /**
     * 卖家账号
     */
    private String slAccount;
    /**
     * 登录手机号码
     */
    private String slTel;
    /**
     * 卖家显示名称
     */
    private String slShowName;
    /**
     * 联系人姓名
     */
    private String slContact;
    /**
     * 登录密码
     */
    private String accountPsd;
    /**
     * 卖家所在区划
     */
    private String slAreaCode;
    /**
     * 卖家编码
     */
    private String slCode;
    /**
     * 企业ID
     */
    private Long epId;
    /**
     * 企业名
     */
    private String epName;

    /**
     * 生产商与供应商代码对应的Map
     */
    private Map<String,String> epNameMap = new HashMap<String,String>();

    /**
     * 获得slAccount
     */
    public String getSlAccount() {
        return slAccount;
    }

    /**
     * 设置slAccount
     */
    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * 获得slTel
     */
    public String getSlTel() {
        return slTel;
    }

    /**
     * 设置slTel
     */
    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    /**
     * 获得slShowName
     */
    public String getSlShowName() {
        return slShowName;
    }

    /**
     * 设置slShowName
     */
    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * 获得slContact
     */
    public String getSlContact() {
        return slContact;
    }

    /**
     * 设置slContact
     */
    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    /**
     * 获得accountPsd
     */
    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * 设置accountPsd
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * 获得slAreaCode
     */
    public String getSlAreaCode() {
        return slAreaCode;
    }

    /**
     * 设置slAreaCode
     */
    public void setSlAreaCode(String slAreaCode) {
        this.slAreaCode = slAreaCode;
    }

    /**
     * 获得slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * 设置slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * 获得epId
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * 设置epId
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * 获得epName
     */
    public String getEpName() {
        return epName;
    }

    /**
     * 设置epName
     */
    public void setEpName(String epName) {
        this.epName = epName;
    }

    public Map<String, String> getEpNameMap() { return epNameMap; } public void setEpNameMap(Map<String, String> epNameMap) { this.epNameMap = epNameMap;
    }

}
