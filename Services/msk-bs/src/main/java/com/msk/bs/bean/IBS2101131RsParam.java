package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/12.
 */
@ApiModel(value = "IBS2101131RsParam", description = "param")
public class IBS2101131RsParam extends BaseParam {

    @ApiModelProperty(value = "管家ID")
    private String houseCode;

    @ApiModelProperty(value = "管家名称")
    private String houseKeeperName;

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseKeeperName() {
        return houseKeeperName;
    }

    public void setHouseKeeperName(String houseKeeperName) {
        this.houseKeeperName = houseKeeperName;
    }
}
