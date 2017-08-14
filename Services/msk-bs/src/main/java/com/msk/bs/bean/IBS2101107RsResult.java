package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
@ApiModel(value = "IBS2101107RsResult",description = "result")
public class IBS2101107RsResult extends RsPageResult {
    @ApiModelProperty(value = "买手买家列表")
    private List<IBS2101107Bean> slBuyerList;

    /**
     * Getter method for property <tt>slBuyerList</tt>.
     *
     * @return property value of slBuyerList
     */
    public List<IBS2101107Bean> getSlBuyerList() {
        return slBuyerList;
    }

    /**
     * Setter method for property <tt>slBuyerList</tt>.
     *
     * @param slBuyerList value to be assigned to property slBuyerList
     */
    public void setSlBuyerList(List<IBS2101107Bean> slBuyerList) {
        this.slBuyerList = slBuyerList;
    }
}
