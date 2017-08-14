package com.msk.ds.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * ISC1891I1RsResultInfo.
 *
 * @author yuan_chen
 */
public class ISC1891I1RsResultInfo extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 分销月度 */
    private String distMonth;
    /** 物流区编号 */
    private String lgcsCode;
    /** 供应商编号 */
    private String suppCode;
    /** 产品编码 */
    private String pdCode;
    /** 尚未入库的供应量 */
    private BigDecimal unStockNum;

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getDistMonth() {
        return distMonth;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }
    public BigDecimal getUnStockNum() {
        return unStockNum;
    }

    public void setUnStockNum(BigDecimal unStockNum) {
        this.unStockNum = unStockNum;
    }
}
