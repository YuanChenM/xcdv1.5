package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gyh on 2016/3/17.
 */
@ApiModel(value = "IBS210110501RsResult",description = "result")
public class IBS210110501RsResult extends RsPageResult {

    @ApiModelProperty(value = "管家列表")
    private List<IBS2101105RsResult> houseList;

    /**
     * Getter method for property <tt>houseList</tt>.
     *
     * @return property value of houseList
     */
    public List<IBS2101105RsResult> getHouseList() {
        return houseList;
    }

    /**
     * Setter method for property <tt>houseList</tt>.
     *
     * @param houseList value to be assigned to property houseList
     */
    public void setHouseList(List<IBS2101105RsResult> houseList) {
        this.houseList = houseList;
    }
}
