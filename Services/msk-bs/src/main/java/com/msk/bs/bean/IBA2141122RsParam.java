package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 买手
 * Created by ni_shaotang on 2016/10/11.
 */
@ApiModel(value = "IBA2141122RsParam", description = "param")
public class IBA2141122RsParam extends BaseParam {

    @ApiModelProperty(value = "卖家编码")
    private String sellerCode;
    @ApiModelProperty(value = "订单来源,系统编码，参见系统列表")
    private String orderSource;
    @ApiModelProperty(value = "每页数量")
    private Integer pageCount;
    @ApiModelProperty(value = "查询页数")
    private Integer pageNo;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
