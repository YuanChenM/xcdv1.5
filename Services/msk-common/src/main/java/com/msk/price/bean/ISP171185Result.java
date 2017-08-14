package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ni_shaotang on 2016/8/26.
 */
@ApiModel(value = "ISP171185Result", description = "result")
public class ISP171185Result extends BaseEntity{

    @ApiModelProperty(value = "产品价格信息列表")
    private List<ISP171185Bean> productList;

    public List<ISP171185Bean> getProductList() {
        return productList;
    }

    public void setProductList(List<ISP171185Bean> productList) {
        this.productList = productList;
    }
}
