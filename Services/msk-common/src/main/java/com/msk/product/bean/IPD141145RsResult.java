package com.msk.product.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.PdTcProviderPackage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gyh on 2016/4/20.
 */
@ApiModel(value = "IPD141145RsResult", description = "result")
public class IPD141145RsResult extends RsPageResult {
    @ApiModelProperty(value = "卖家id")
    private String sellerCode;
    @ApiModelProperty(value = "卖家申请新品")
    private List<PdTcProviderPackage> pdList;

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
     * Getter method for property <tt>pdList</tt>.
     *
     * @return property value of pdList
     */
    public List<PdTcProviderPackage> getPdList() {
        return pdList;
    }

    /**
     * Setter method for property <tt>pdList</tt>.
     *
     * @param pdList value to be assigned to property pdList
     */
    public void setPdList(List<PdTcProviderPackage> pdList) {
        this.pdList = pdList;
    }
}
