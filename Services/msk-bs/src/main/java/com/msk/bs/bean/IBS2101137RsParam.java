package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import com.msk.core.entity.BsBasicInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 查询买手账号和基本信息
 * Created by ni_shaotang on 2016/10/28.
 */
@ApiModel(value = "IBS2101137RsParam", description = "查询买手信息条件")
public class IBS2101137RsParam extends RsPageParam {

    @ApiModelProperty(value = "查询类型：0:注册了账号的所有买手 1：账号和基本信息都注册了的买手")
    private Integer slCodeFlag;

    @ApiModelProperty(value = "查询条件")
    private List<BsBasicInfo> condition;

    public Integer getSlCodeFlag() {
        return slCodeFlag;
    }

    public void setSlCodeFlag(Integer slCodeFlag) {
        this.slCodeFlag = slCodeFlag;
    }

    public List<BsBasicInfo> getCondition() {
        return condition;
    }

    public void setCondition(List<BsBasicInfo> condition) {
        this.condition = condition;
    }
}
