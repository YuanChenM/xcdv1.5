package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SoOrderDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 买手
 * Created by ni_shaotang on 2016/10/11.
 */
@ApiModel(value = "IBA2141123RsParam", description = "param")
public class IBA2141123RsParam extends BaseParam {

    @ApiModelProperty(value = "卖家编码")
    private String sellerCode;
    @ApiModelProperty(value = "详情编码")
    private List<SoOrderDetail> orders;


    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public List<SoOrderDetail> getOrders() {
        return orders;
    }

    public void setOrders(List<SoOrderDetail> orders) {
        this.orders = orders;
    }
}
