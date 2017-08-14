package com.msk.product.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/17.
 */
@ApiModel(value = "IPD1411213BsParam",description = "result")
public class IPD1411213BsParam extends RsPageResult {

    @ApiModelProperty(value = "买手列表")
    private List<IPD1411213BsBean> buyershopList;

    public List<IPD1411213BsBean> getBuyershopList() {
        return buyershopList;
    }

    public void setBuyershopList(List<IPD1411213BsBean> buyershopList) {
        this.buyershopList = buyershopList;
    }
}
