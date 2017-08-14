package com.msk.bs.bean;

import com.msk.core.entity.BsBasicInfo;

/**
 * Created by gao_min on 2016/9/14.
 */
public class BS2102125Bean extends BsBasicInfo {

    /**
     * 联系人姓名
     */
    private String slContact;

    /**
     * FLAG1
     */
    private String flag1;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 开户行
     */
    private String bankName;

    /**
     * 账号
     */
    private String bankNo;

    /**
     * 买手三级分类
     */
    private String threeLever;

    /**
     * 买手二级分类
     */
    private String twoLever;

    /**
     * 买手一级分类
     */
    private String oneLever;

    public String getSlContact() {
        return slContact;
    }

    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getThreeLever() {
        return threeLever;
    }

    public void setThreeLever(String threeLever) {
        this.threeLever = threeLever;
    }

    public String getTwoLever() {
        return twoLever;
    }

    public void setTwoLever(String twoLever) {
        this.twoLever = twoLever;
    }

    public String getOneLever() {
        return oneLever;
    }

    public void setOneLever(String oneLever) {
        this.oneLever = oneLever;
    }
}