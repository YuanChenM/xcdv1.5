package com.msk.ds.bean;

import com.hoperun.core.bean.BaseParam;
import java.math.BigDecimal;

/**
 * zhou_yajun on 2016/1/28.
 */
public class SC183103ProductParam extends BaseParam{
      /**调整时间*/
    private String adjustDate;
    /**产品编码 */
    private String productCode;
    /**包装编码 */
    private String normsCode;
    /**调整前值*/
    private BigDecimal changeBeforeNum;
    /**调整后值 */
    private BigDecimal changeOverNum;
    //Add for 3487 at 2016/10/27 by likai End
    /**调整值 */
    private BigDecimal changeNum;
    /**产品净重 */
    private BigDecimal pdOutNw;
    //Add for 3487 at 2016/10/27 by likai Start

    public String getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(String adjustDate) {
        this.adjustDate = adjustDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public BigDecimal getChangeBeforeNum() {
        return changeBeforeNum;
    }

    public void setChangeBeforeNum(BigDecimal changeBeforeNum) {
        this.changeBeforeNum = changeBeforeNum;
    }

    public BigDecimal getChangeOverNum() {
        return changeOverNum;
    }

    public void setChangeOverNum(BigDecimal changeOverNum) {
        this.changeOverNum = changeOverNum;
    }

    public BigDecimal getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(BigDecimal changeNum) {
        this.changeNum = changeNum;
    }

    public BigDecimal getPdOutNw() {
        return pdOutNw;
    }

    public void setPdOutNw(BigDecimal pdOutNw) {
        this.pdOutNw = pdOutNw;
    }
}
