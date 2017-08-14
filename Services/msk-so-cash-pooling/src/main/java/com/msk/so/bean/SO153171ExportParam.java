package com.msk.so.bean;

import com.hoperun.core.bean.BasePageParam;

/**账套设置
 * add for Bug #2425 at 2016/09/06 by ren_yi
 */
public class SO153171ExportParam extends BasePageParam {


    private String  slCode;
    private String slAccount;
    private String  slCodeDis;
    private String slMainClass;
    private String slName;
    private String accountName;
    private String bankName;
    private String cardNo;
    private String commDateStart;
    private String commDateEnd;
    private String lastPeriodEndStart;
    private String lastPeriodEndEnd;
    private String periodStart;
    private String periodEnd;

    public String getCommDateStart() {
        return commDateStart;
    }

    public void setCommDateStart(String commDateStart) {
        this.commDateStart = commDateStart;
    }

    public String getCommDateEnd() {
        return commDateEnd;
    }

    public void setCommDateEnd(String commDateEnd) {
        this.commDateEnd = commDateEnd;
    }

    public String getLastPeriodEndStart() {
        return lastPeriodEndStart;
    }

    public void setLastPeriodEndStart(String lastPeriodEndStart) {
        this.lastPeriodEndStart = lastPeriodEndStart;
    }

    public String getLastPeriodEndEnd() {
        return lastPeriodEndEnd;
    }

    public void setLastPeriodEndEnd(String lastPeriodEndEnd) {
        this.lastPeriodEndEnd = lastPeriodEndEnd;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
