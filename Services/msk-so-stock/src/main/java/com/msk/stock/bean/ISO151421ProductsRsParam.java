package com.msk.stock.bean;

/**
 * 
 * ISO151421Rs接口接收参数Param.
 *
 * @author pxg
 */
public class ISO151421ProductsRsParam {
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

    /** 产品编码 */

    private String pdCode;
    /** 产品名称 */
    private String pdName;
}
