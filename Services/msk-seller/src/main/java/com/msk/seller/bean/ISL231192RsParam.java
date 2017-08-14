package com.msk.seller.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * Created by Administrator on 2016/4/26.
 */
public class ISL231192RsParam extends BaseParam {

    /**卖家ID*/
    private String slCode;
    /**卖家产品货号*/
    private String slPdArtNo;

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>slPdArtNo</tt>.
     *
     * @return property value of slPdArtNo
     */
    public String getSlPdArtNo() {
        return slPdArtNo;
    }

    /**
     * Setter method for property <tt>slPdArtNo</tt>.
     *
     * @param slPdArtNo value to be assigned to property slPdArtNo
     */
    public void setSlPdArtNo(String slPdArtNo) {
        this.slPdArtNo = slPdArtNo;
    }
}
