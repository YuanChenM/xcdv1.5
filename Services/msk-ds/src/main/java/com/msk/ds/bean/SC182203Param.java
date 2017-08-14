package com.msk.ds.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * yi_qixiang.
 */
public class SC182203Param extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**半旬期 */
    private String halfPeriod;
    /**销售区域编号 */
    private String lgcsCode;
    /**供应商编号 */
    private String suppCode;
    /**用户账户 */
    private String slAccount;
    /**供应商区域 */
    private String lgcsName;
    /**主条形码 */
    private String proLotNum;

    public String getProLotNum() {
        return proLotNum;
    }

    public void setProLotNum(String proLotNum) {
        this.proLotNum = proLotNum;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getHalfPeriod() {
        return halfPeriod;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setHalfPeriod(String halfPeriod) {
        this.halfPeriod = halfPeriod;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

}
