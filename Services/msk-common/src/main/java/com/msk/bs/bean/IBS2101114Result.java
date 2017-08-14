package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/13.
 */
@ApiModel(value = "IBS2101114Result",description = "result")
public class IBS2101114Result implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "买手店管家专属会员信息")
    List<IBS2101114Bean>  slBsBuyerList;

    public List<IBS2101114Bean> getSlBsBuyerList() {
        return slBsBuyerList;
    }

    public void setSlBsBuyerList(List<IBS2101114Bean> slBsBuyerList) {
        this.slBsBuyerList = slBsBuyerList;
    }
}
