package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by whc on 2016/10/13.
 */
@ApiModel(value = "IBS2101139RsBean", description = "冻品管家信息与买家信息")
public class IBS2101139RsBean extends BaseParam{

    @ApiModelProperty(value = "买手ID")
    private String slCode;
    @ApiModelProperty(value = "管家ID")
    private String houseCode;
    @ApiModelProperty(value = "买家信息")
    private List<IBS2101138RsBean> buyerIds;

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

    public List<IBS2101138RsBean> getBuyerIds() {
        return buyerIds;
    }

    public void setBuyerIds(List<IBS2101138RsBean> buyerIds) {
        this.buyerIds = buyerIds;
    }
}
