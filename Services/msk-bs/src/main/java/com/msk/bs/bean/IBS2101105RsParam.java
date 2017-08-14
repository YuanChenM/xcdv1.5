package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gyh on 2016/3/17.
 */
@ApiModel(value = "IBS2101105RsParam",description = "param")
public class IBS2101105RsParam extends RsPageParam {
    @ApiModelProperty(value = "买手店编码")
    private String slCode;
    @ApiModelProperty(value = "管家账号")
    private String houseAccount;
    @ApiModelProperty(value = "登录密码")
    private String accountPsd;
    @ApiModelProperty(value = "管家ID")
    private String houseCode;
    @ApiModelProperty(value = "虚拟省编码")
    private String vprovinceCode;
    @ApiModelProperty(value = "虚拟地区编码")
    private String vcityCode;
    @ApiModelProperty(value = "虚拟区编码")
    private String vdistrictCode;
    @ApiModelProperty(value = "虚拟管家地址")
    private String vhouseAddress;
    @ApiModelProperty(value = "管家分类")
    private String houseCategory;
    @ApiModelProperty(value = "等级")
    private String greade;
    @ApiModelProperty(value = "登录手机号码")
    private String houseTel;
    @ApiModelProperty(value = "1：神农客，2：美侍客")
    private String fromFlg;

    /**
     * Getter method for property <tt>fromFlg</tt>.
     *
     * @return property value of fromFlg
     */
    public String getFromFlg() {
        return fromFlg;
    }

    /**
     * Setter method for property <tt>fromFlg</tt>.
     *
     * @param fromFlg value to be assigned to property fromFlg
     */
    public void setFromFlg(String fromFlg) {
        this.fromFlg = fromFlg;
    }

    /**
     * Getter method for property <tt>houseTel</tt>.
     *
     * @return property value of houseTel
     */
    public String getHouseTel() {
        return houseTel;
    }

    /**
     * Setter method for property <tt>houseTel</tt>.
     *
     * @param houseTel value to be assigned to property houseTel
     */
    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    /**
     * Getter method for property <tt>vprovinceCode</tt>.
     *
     * @return property value of vprovinceCode
     */
    public String getVprovinceCode() {
        return vprovinceCode;
    }

    /**
     * Setter method for property <tt>vprovinceCode</tt>.
     *
     * @param vprovinceCode value to be assigned to property vprovinceCode
     */
    public void setVprovinceCode(String vprovinceCode) {
        this.vprovinceCode = vprovinceCode;
    }

    /**
     * Getter method for property <tt>vcityCode</tt>.
     *
     * @return property value of vcityCode
     */
    public String getVcityCode() {
        return vcityCode;
    }

    /**
     * Setter method for property <tt>vcityCode</tt>.
     *
     * @param vcityCode value to be assigned to property vcityCode
     */
    public void setVcityCode(String vcityCode) {
        this.vcityCode = vcityCode;
    }

    /**
     * Getter method for property <tt>vdistrictCode</tt>.
     *
     * @return property value of vdistrictCode
     */
    public String getVdistrictCode() {
        return vdistrictCode;
    }

    /**
     * Setter method for property <tt>vdistrictCode</tt>.
     *
     * @param vdistrictCode value to be assigned to property vdistrictCode
     */
    public void setVdistrictCode(String vdistrictCode) {
        this.vdistrictCode = vdistrictCode;
    }

    /**
     * Getter method for property <tt>vhouseAddress</tt>.
     *
     * @return property value of vhouseAddress
     */
    public String getVhouseAddress() {
        return vhouseAddress;
    }

    /**
     * Setter method for property <tt>vhouseAddress</tt>.
     *
     * @param vhouseAddress value to be assigned to property vhouseAddress
     */
    public void setVhouseAddress(String vhouseAddress) {
        this.vhouseAddress = vhouseAddress;
    }

    /**
     * Getter method for property <tt>houseCategory</tt>.
     *
     * @return property value of houseCategory
     */
    public String getHouseCategory() {
        return houseCategory;
    }

    /**
     * Setter method for property <tt>houseCategory</tt>.
     *
     * @param houseCategory value to be assigned to property houseCategory
     */
    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory;
    }

    /**
     * Getter method for property <tt>greade</tt>.
     *
     * @return property value of greade
     */
    public String getGreade() {
        return greade;
    }

    /**
     * Setter method for property <tt>greade</tt>.
     *
     * @param greade value to be assigned to property greade
     */
    public void setGreade(String greade) {
        this.greade = greade;
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
     * Getter method for property <tt>accountPsd</tt>.
     *
     * @return property value of accountPsd
     */
    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * Setter method for property <tt>accountPsd</tt>.
     *
     * @param accountPsd value to be assigned to property accountPsd
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }
}
