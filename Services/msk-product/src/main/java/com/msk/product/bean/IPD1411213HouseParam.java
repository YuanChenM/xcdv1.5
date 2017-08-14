package com.msk.product.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/17.
 */
@ApiModel(value = "IPD1411213HouseParam",description = "result")
public class IPD1411213HouseParam extends RsPageResult {
    @ApiModelProperty(value = "管家列表")
    private List<IPD1411213HouseBean> houseList;

    public List<IPD1411213HouseBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IPD1411213HouseBean> houseList) {
        this.houseList = houseList;
    }
}
