package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ni_shaotang on 2016/9/22.
 */
@ApiModel(value = "IBA2141107RsParam", description = "param")
public class IBA2141107RsParam {

    @ApiModelProperty(value = "买手code 平台的时候传\"0000000\"")
    private String slCode;

    @ApiModelProperty(value = "物流区编码")
    private Integer lgcsCode;

    @ApiModelProperty(value = "1：平台；2：买手")
    private Integer sellerType;

    @ApiModelProperty(value = "产品列表")
    private List<String> pdCodeList;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public Integer getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(Integer lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public Integer getSellerType() {
        return sellerType;
    }

    public void setSellerType(Integer sellerType) {
        this.sellerType = sellerType;
    }

    public List<String> getPdCodeList() {
        return pdCodeList;
    }

    public void setPdCodeList(List<String> pdCodeList) {
        this.pdCodeList = pdCodeList;
    }
}
