package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;


/**
 * 查询买家对应的买手信息
 * Created by ni_shaotang on 2016/8/1.
 */
@ApiModel(value = "IBS2101116Result",description = "result")
public class IBS2101116Result implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "买手店管家专属会员信息")
    List<IBS2101116Bean>  resultList;

    public List<IBS2101116Bean> getResultList() {
        return resultList;
    }

    public void setResultList(List<IBS2101116Bean> resultList) {
        this.resultList = resultList;
    }
}
