package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.BaseEntity;

import java.io.Serializable;

/**账套设置
 * wu_honglei
 */
public class SO153171Param extends BaseEntity{


    private String  slCode;
    private String slAccount;
    private String  slCodeDis;
    private String slMainClass;
    private String slName;
    private String accountName;
    private String bankName;
    private String cardNo;


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
