package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/8/18.
 */
@ApiModel(value = "IBS2101118Result",description = "result")
public class IBS2101118Result  extends RsPageResult{

    @ApiModelProperty(value = "冻品管家营销列表")
    private List<IBS2101118Bean> slHouseSaleList;

    public List<IBS2101118Bean> getSlHouseSaleList() {
        return slHouseSaleList;
    }

    public void setSlHouseSaleList(List<IBS2101118Bean> slHouseSaleList) {
        this.slHouseSaleList = slHouseSaleList;
    }
}
