package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 封装参数
 * PD141143RsParam 神农客价盘通道同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141144RsParam", description = "param")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId" })
public class IPD141144RsParam extends BaseParam {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "平台Code或者卖家供应商ID")
    private String sellerCode;
    @ApiModelProperty(value = "对应电商平台类型")
    private String platformType;
    @ApiModelProperty(value = "物流区域编码")
    private Integer districtCode;
    @ApiModelProperty(value = "卖家类型")
    private Integer sellerType;
    @ApiModelProperty(value = "产品编码")
    private String pdCode;


    /**
     * Getter method for property <tt>sellerCode</tt>.
     *
     * @return property value of sellerCode
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * Setter method for property <tt>sellerCode</tt>.
     *
     * @param sellerCode value to be assigned to property sellerCode
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * Getter method for property <tt>platformType</tt>.
     *
     * @return property value of platformType
     */
    public String getPlatformType() {
        return platformType;
    }

    /**
     * Setter method for property <tt>platformType</tt>.
     *
     * @param platformType value to be assigned to property platformType
     */
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    /**
     * Getter method for property <tt>districtCode</tt>.
     *
     * @return property value of districtCode
     */
    public Integer getDistrictCode() {
        return districtCode;
    }

    /**
     * Setter method for property <tt>districtCode</tt>.
     *
     * @param districtCode value to be assigned to property districtCode
     */
    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * Getter method for property <tt>sellerType</tt>.
     *
     * @return property value of sellerType
     */
    public Integer getSellerType() {
        return sellerType;
    }

    /**
     * Setter method for property <tt>sellerType</tt>.
     *
     * @param sellerType value to be assigned to property sellerType
     */
    public void setSellerType(Integer sellerType) {
        this.sellerType = sellerType;
    }

    /**
     * Getter method for property <tt>pdCode</tt>.
     *
     * @return property value of pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * Setter method for property <tt>pdCode</tt>.
     *
     * @param pdCode value to be assigned to property pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }
}