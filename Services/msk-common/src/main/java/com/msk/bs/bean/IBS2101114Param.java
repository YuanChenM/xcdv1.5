package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/13.
 */
@ApiModel(value = "IBS2101114Param",description = "param")
public class IBS2101114Param  extends BaseParam{
    @ApiModelProperty(value = "买家id")
    private List<String> buyerIdList;
    @ApiModelProperty(value = "查询归属关系，1-代表当前关系，若不传那么是当前与履历的都有")
    private String buyerFlag;
    public List<String> getBuyerIdList() {
        return buyerIdList;
    }

    public void setBuyerIdList(List<String> buyerIdList) {
        this.buyerIdList = buyerIdList;
    }

    public String getBuyerFlag() {
        return buyerFlag;
    }

    public void setBuyerFlag(String buyerFlag) {
        this.buyerFlag = buyerFlag;
    }
}
