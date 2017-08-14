package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerBasicInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhou_yajun on 2016/7/8.
 */
public class BY121414Bean extends ByBuyerBasicInfo {

    //调取冻品管家
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

   //
   private String buyerPoolName;

    private List<BY121414Bean> brBuyerPoolList;

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

    public List<BY121414Bean> getBrBuyerPoolList() {
        return brBuyerPoolList;
    }

    public void setBrBuyerPoolList(List<BY121414Bean> brBuyerPoolList) {
        this.brBuyerPoolList = brBuyerPoolList;
    }

    public String getBuyerPoolName() {
        return buyerPoolName;
    }

    public void setBuyerPoolName(String buyerPoolName) {
        this.buyerPoolName = buyerPoolName;
    }
}
