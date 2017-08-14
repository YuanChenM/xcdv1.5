package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 同步卖家模块买手数据
 * Created by ni_shaotang on 2016/7/29.
 */
@ApiModel(value = "IBS2101116Param",description = "param")
public class IBS2101116Param extends BaseParam {

    @ApiModelProperty(value = "买家id集合")
    private List<String> buyerIdList;

    public List<String> getBuyerIdList() {
        return buyerIdList;
    }

    public void setBuyerIdList(List<String> buyerIdList) {
        this.buyerIdList = buyerIdList;
    }
}
