package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141144RsResult.卖家产品库存查询
 *
 * @author xhy 2016-4-8
 */
@ApiModel(value = "IPD141144RsResult", description = "result")
public class IPD141144RsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "平台CODE或者卖家供应商ID")
    private String sellerCode;
    @ApiModelProperty(value = "物流区域编码")
    private Integer districtCode;
    @ApiModelProperty(value = "产品列表")
    private List<IPD141144RsProductsResult>  products;

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

    /**
     * Getter method for property <tt>districtCode</tt>.
     *
     * @return property value of districtCode
     */
    public Integer getDistrictCode() {
        return districtCode;
    }

    /**
     * Setter method for property <tt>districtCode</tt>.
     *
     * @param districtCode value to be assigned to property districtCode
     */
    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * Getter method for property <tt>products</tt>.
     *
     * @return property value of products
     */
    public List<IPD141144RsProductsResult> getProducts() {
        return products;
    }

    /**
     * Setter method for property <tt>products</tt>.
     *
     * @param products value to be assigned to property products
     */
    public void setProducts(List<IPD141144RsProductsResult> products) {
        this.products = products;
    }
}