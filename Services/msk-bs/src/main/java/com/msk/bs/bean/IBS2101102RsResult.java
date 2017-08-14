package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gyh on 2016/1/14.
 */
@ApiModel(value = "IBS2101102RsResult", description = "result")
public class IBS2101102RsResult extends IBS2101102RsParam {
    @ApiModelProperty(value = "管家账号")
    private String houseAccount;
    @ApiModelProperty(value = "管家ID")
    private String houseCode;

    /**
     * Getter method for property <tt>houseAccount</tt>.
     *
     * @return property value of houseAccount
     */
    public String getHouseAccount() {
        return houseAccount;
    }

    /**
     * Setter method for property <tt>houseAccount</tt>.
     *
     * @param houseAccount value to be assigned to property houseAccount
     */
    public void setHouseAccount(String houseAccount) {
        this.houseAccount = houseAccount;
    }

    /**
     * Getter method for property <tt>houseCode</tt>.
     *
     * @return property value of houseCode
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * Setter method for property <tt>houseCode</tt>.
     *
     * @param houseCode value to be assigned to property houseCode
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
}
