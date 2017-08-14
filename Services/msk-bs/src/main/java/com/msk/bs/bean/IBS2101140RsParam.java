package com.msk.bs.bean;

import java.util.List;

import com.hoperun.core.bean.BaseParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/13.
 */
@ApiModel(value = "IBS2101140RsParam", description = "param")
public class IBS2101140RsParam extends BaseParam {

    @ApiModelProperty(value = "买手ID")
    private String slCode;
    @ApiModelProperty(value = "管家ID")
    private String houseCode;
    @ApiModelProperty(value = "买家信息")
    private List<IBS2101140Bean>  relationList;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public List<IBS2101140Bean> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<IBS2101140Bean> relationList) {
        this.relationList = relationList;
    }
}
