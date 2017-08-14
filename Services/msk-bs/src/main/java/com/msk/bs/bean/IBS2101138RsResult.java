package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/11/4.
 */
@ApiModel(value = "IBS2101138RsResult",description = "result")
public class IBS2101138RsResult extends RsPageResult {
    @ApiModelProperty(value = "买家列表")
    private List<IBS2101107Bean> buyerList;

    public List<IBS2101107Bean> getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List<IBS2101107Bean> buyerList) {
        this.buyerList = buyerList;
    }
}
