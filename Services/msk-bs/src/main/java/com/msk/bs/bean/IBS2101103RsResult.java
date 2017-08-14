package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel(value = "IBS2101103RsResult",description = "result")
public class IBS2101103RsResult extends RsPageResult {
    //public class IBS2101103RsResult extends RsPageResult {
    @ApiModelProperty(value = "买手列表")
    private List<IBS210110301Bean> buyershopList;

    /**
     * Getter method for property <tt>buyershopList</tt>.
     *
     * @return property value of buyershopList
     */
    public List<IBS210110301Bean> getBuyershopList() {
        return buyershopList;
    }

    /**
     * Setter method for property <tt>buyershopList</tt>.
     *
     * @param buyershopList value to be assigned to property buyershopList
     */
    public void setBuyershopList(List<IBS210110301Bean> buyershopList) {
        this.buyershopList = buyershopList;
    }
}
