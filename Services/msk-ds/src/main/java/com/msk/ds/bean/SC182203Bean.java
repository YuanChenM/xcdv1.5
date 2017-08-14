package com.msk.ds.bean;

import com.msk.core.entity.BaseEntity;

/**
 * yi_qixiang.
 */
public class SC182203Bean extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**半旬期 */
    private String halfPeriod;
    /**供应商区域 */
    private String lgcsName;
    /**销售区域编号 */
    private String lgcsCode;

    /**供应商编号 */
    private String suppCode;
    /**供应商名字 */
    private String suppName;


    /**主条形码 */
    private String proLotNum;


    public void setProLotNum(String proLotNum) {
        this.proLotNum = proLotNum;
    }
    public String getProLotNum() {
        return proLotNum;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public String getSuppName() {
        return suppName;
    }

    public String getHalfPeriod() {
        return halfPeriod;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setHalfPeriod(String halfPeriod) {
        this.halfPeriod = halfPeriod;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

}
