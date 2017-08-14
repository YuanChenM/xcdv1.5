package com.msk.price.bean;


import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ni_shaotang on 2016/8/26.
 */
@ApiModel(value = "ISP171185Param", description = "价盘通道和价格req")
public class ISP171185Param extends BaseParam {

    @ApiModelProperty(value = "价盘周期条件")
    private String pricePeriod;

    @ApiModelProperty(value = "产品信息列表")
    private List<ISP171185Bean> productList;

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public List<ISP171185Bean> getProductList() {
        return productList;
    }

    public void setProductList(List<ISP171185Bean> productList) {
        this.productList = productList;
    }
}
