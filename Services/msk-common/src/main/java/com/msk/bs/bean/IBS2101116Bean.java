package com.msk.bs.bean;

import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ni_shaotang on 2016/8/1.
 */
@ApiModel(value = "IBS2101116Bean",description = "买手店管家专属会员信息")
public class IBS2101116Bean extends BaseBean {

    @ApiModelProperty(value = "买家ID")
    private String buyerId;

    @ApiModelProperty(value = "买手编码")
    private String slCode;

    @ApiModelProperty(value = "买手ID")
    private String slId;

    @ApiModelProperty(value = "买手名称")
    private String slName;

    @ApiModelProperty(value = "管家id")
    private String houseId;

    @ApiModelProperty(value = "管家编号")
    private String houseCode;

    @ApiModelProperty(value = "管家名称")
    private String houseName;



    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
