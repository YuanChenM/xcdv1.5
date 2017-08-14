package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/13.
 */
@ApiModel(value = "IBS2101138RsParam", description = "param")
public class IBS2101138RsParam extends BaseParam {

    @ApiModelProperty(value = "冻品管家信息与买家信息")
    private IBS2101139RsBean hkBuyers;

    public IBS2101139RsBean getHkBuyers() {
        return hkBuyers;
    }

    public void setHkBuyers(IBS2101139RsBean hkBuyers) {
        this.hkBuyers = hkBuyers;
    }
}
