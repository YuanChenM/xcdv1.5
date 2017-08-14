package com.msk.product.bean;


import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gyh on 2016/4/20.
 */
@ApiModel(value = "IPD141145RsParam", description = "param")
public class IPD141145RsParam extends RsPageParam {
    @ApiModelProperty(value = "卖家id")
    private String sellerCode;

    /**
     * Getter method for property <tt>sellerCode</tt>.
     *
     * @return property value of sellerCode
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * Setter method for property <tt>sellerCode</tt>.
     *
     * @param sellerCode value to be assigned to property sellerCode
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }
}
