package com.msk.bs.bean;

import com.msk.core.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "IBS2101102RsParam", description = "param")
public class IBS2101102RsParam extends BaseEntity {
    @ApiModelProperty(value = "买手账号信息")
    private BsAccount slAccount;
    @ApiModelProperty(value = "买手基本信息")
    private BsBasicInfo slSeller;
    @ApiModelProperty(value = "买手店铺信息")
    private SlShopInfo slShopInfo;
    @ApiModelProperty(value = "买手店资质信息")
    private SlBuyershop slBuyerShop;
    @ApiModelProperty(value = "买手账户信息")
    private SlBsBankaccount slBsBankaccount;
    @ApiModelProperty(value = "创建者ID/更新者ID")
    private String loginId;
    @ApiModelProperty(value = "删除标志0：有效，1：删除")
    private String delFlg;
    @ApiModelProperty(value = "版本号")
    private Integer ver;

    public BsAccount getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(BsAccount slAccount) {
        this.slAccount = slAccount;
    }

    public BsBasicInfo getSlSeller() {
        return slSeller;
    }

    public void setSlSeller(BsBasicInfo slSeller) {
        this.slSeller = slSeller;
    }

    /**
     * Getter method for property <tt>slShopInfo</tt>.
     *
     * @return property value of slShopInfo
     */
    public SlShopInfo getSlShopInfo() {
        return slShopInfo;
    }

    /**
     * Setter method for property <tt>slShopInfo</tt>.
     *
     * @param slShopInfo value to be assigned to property slShopInfo
     */
    public void setSlShopInfo(SlShopInfo slShopInfo) {
        this.slShopInfo = slShopInfo;
    }

    /**
     * Getter method for property <tt>slBuyerShop</tt>.
     *
     * @return property value of slBuyerShop
     */
    public SlBuyershop getSlBuyerShop() {
        return slBuyerShop;
    }

    /**
     * Setter method for property <tt>slBuyerShop</tt>.
     *
     * @param slBuyerShop value to be assigned to property slBuyerShop
     */
    public void setSlBuyerShop(SlBuyershop slBuyerShop) {
        this.slBuyerShop = slBuyerShop;
    }

    /**
     * Getter method for property <tt>loginId</tt>.
     *
     * @return property value of loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Setter method for property <tt>loginId</tt>.
     *
     * @param loginId value to be assigned to property loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * Getter method for property <tt>delFlg</tt>.
     *
     * @return property value of delFlg
     */
    @Override
    public String getDelFlg() {
        return delFlg;
    }

    /**
     * Setter method for property <tt>delFlg</tt>.
     *
     * @param delFlg value to be assigned to property delFlg
     */
    @Override
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * Getter method for property <tt>ver</tt>.
     *
     * @return property value of ver
     */
    @Override
    public Integer getVer() {
        return ver;
    }

    /**
     * Setter method for property <tt>ver</tt>.
     *
     * @param ver value to be assigned to property ver
     */
    @Override
    public void setVer(Integer ver) {
        this.ver = ver;
    }


    public SlBsBankaccount getSlBsBankaccount() {
        return slBsBankaccount;
    }

    public void setSlBsBankaccount(SlBsBankaccount slBsBankaccount) {
        this.slBsBankaccount = slBsBankaccount;
    }
}
