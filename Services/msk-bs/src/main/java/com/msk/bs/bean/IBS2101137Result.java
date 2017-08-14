package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 买手账户和基本信息集合
 * Created by ni_shaotang on 2016/10/28.
 */
@ApiModel(value = "IBS2101137RsParam", description = "查询买手信息条件")
public class IBS2101137Result extends RsPageResult {

    @ApiModelProperty(value = "买手账户和基本信息集合")
    List<IBS2101137Bean> buyershopList;

    public List<IBS2101137Bean> getBuyershopList() {
        return buyershopList;
    }

    public void setBuyershopList(List<IBS2101137Bean> buyershopList) {
        this.buyershopList = buyershopList;
    }
}
