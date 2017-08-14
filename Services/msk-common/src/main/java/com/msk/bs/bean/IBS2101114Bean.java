package com.msk.bs.bean;

import com.msk.core.entity.SlBsBuyer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhu_kai1 on 2016/7/13.
 */
@ApiModel(value = "IBS2101114Bean",description = "买手店管家专属会员信息")
public class IBS2101114Bean  extends SlBsBuyer {

    @ApiModelProperty(value = "管家显示名称")
    private  String houseShowName;
    @ApiModelProperty(value = "1-代表买手店管家买家关系履历表信息， 0-代表买手店管家专属会员表信息")
    private  String flag;
    public String getHouseShowName() {
        return houseShowName;
    }

    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
