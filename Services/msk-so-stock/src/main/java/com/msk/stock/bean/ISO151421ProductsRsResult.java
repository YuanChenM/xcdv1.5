package com.msk.stock.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;

/**
 * 
 * IISO151421RsResult接口返回结果信息.
 *
 * @author pxg
 */
@JsonIgnoreProperties(value={"crtId","crtTime","updId","updTime","ver","actId","actTime","supplierName"})
public class ISO151421ProductsRsResult extends BaseEntity {

    /** 产品Code*/
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 可用在库数量 */
    private Integer avaQty;
    /** 在途数量 */
    private Integer transQty;
    /** 生产中数量 */
    private Integer proQty;
    /** 供应商名称 */
    private String supplierName;
    /**
     * 获得pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * 设置pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * 获得pdName
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * 设置pdName
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * 获得avaQty
     */
    public Integer getAvaQty() {
        return avaQty;
    }

    /**
     * 设置avaQty
     */
    public void setAvaQty(Integer avaQty) {
        this.avaQty = avaQty;
    }

    /**
     * 获得transQty
     */
    public Integer getTransQty() {
        return transQty;
    }

    /**
     * 设置transQty
     */
    public void setTransQty(Integer transQty) {
        this.transQty = transQty;
    }

    /**
     * 获得proQty
     */
    public Integer getProQty() {
        return proQty;
    }

    /**
     * 设置proQty
     */
    public void setProQty(Integer proQty) {
        this.proQty = proQty;
    }


    /**
     * 获得supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 设置supplierName
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
