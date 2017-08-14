package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ren_qiang on 2016/8/19.
 */
@ApiModel(value = "IBS2101120Param",description = "冻品管家列表")
public class IBS2101120Param extends BaseParam {

    @ApiModelProperty(value = "买手店ID(冻品管家主键)")
    private  String slCode;

    @ApiModelProperty(value = "冻品管家编码(冻品管家主键)")
    private String houseCode;

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

}
