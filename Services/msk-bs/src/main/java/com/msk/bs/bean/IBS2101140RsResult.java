package com.msk.bs.bean;

import java.util.List;

import com.msk.core.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by whc on 2016/10/24.
 */
@ApiModel(value = "IBS2101140RsResult", description = "result")
public class IBS2101140RsResult extends BaseEntity {


    @ApiModelProperty(value = "冻品管家信息与买家绑定关系返回结果")
    private List<IBS2101140RsBean> buyerIdList;

    public List<IBS2101140RsBean> getBuyerIdList() {
        return buyerIdList;
    }

    public void setBuyerIdList(List<IBS2101140RsBean> buyerIdList) {
        this.buyerIdList = buyerIdList;
    }
}
