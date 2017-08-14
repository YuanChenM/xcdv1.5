package com.msk.bs.bean;

import com.msk.core.entity.SlBsBuyer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "IBS2101106Bean",description = "bean")
public class IBS2101106Bean extends SlBsBuyer {

    @ApiModelProperty(value = "买手店编码")
    private Long hisId;
    @ApiModelProperty(value = "管家账号")
    private String houseAccount;
    @ApiModelProperty(value = "1:专属买家、2：抢单买家")
    private String buyerFlag;
    @ApiModelProperty(value = "创建者ID/更新者ID")
    private String loginId;
    @ApiModelProperty(value = "买家解除关系原因")
    private String buyerReason;
    @ApiModelProperty(value = "买手店解除关系原因")
    private String buyershopReason;

    /**
     * Getter method for property <tt>hisId</tt>.
     *
     * @return property value of hisId
     */
    public Long getHisId() {
        return hisId;
    }

    /**
     * Setter method for property <tt>hisId</tt>.
     *
     * @param hisId value to be assigned to property hisId
     */
    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }

    /**
     * Getter method for property <tt>buyerFlag</tt>.
     *
     * @return property value of buyerFlag
     */
    public String getBuyerFlag() {
        return buyerFlag;
    }

    /**
     * Setter method for property <tt>buyerFlag</tt>.
     *
     * @param buyerFlag value to be assigned to property buyerFlag
     */
    public void setBuyerFlag(String buyerFlag) {
        this.buyerFlag = buyerFlag;
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
     * Getter method for property <tt>buyerReason</tt>.
     *
     * @return property value of buyerReason
     */
    public String getBuyerReason() {
        return buyerReason;
    }

    /**
     * Setter method for property <tt>buyerReason</tt>.
     *
     * @param buyerReason value to be assigned to property buyerReason
     */
    public void setBuyerReason(String buyerReason) {
        this.buyerReason = buyerReason;
    }

    /**
     * Getter method for property <tt>buyershopReason</tt>.
     *
     * @return property value of buyershopReason
     */
    public String getBuyershopReason() {
        return buyershopReason;
    }

    /**
     * Setter method for property <tt>buyershopReason</tt>.
     *
     * @param buyershopReason value to be assigned to property buyershopReason
     */
    public void setBuyershopReason(String buyershopReason) {
        this.buyershopReason = buyershopReason;
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
}


















