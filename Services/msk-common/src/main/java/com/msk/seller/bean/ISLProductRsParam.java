package com.msk.seller.bean;

import java.util.List;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.SlPdArtno;
import com.msk.core.entity.SlProduct;
import com.msk.core.entity.SlSeller;

/**
 * Created by zhang_chi on 2016/7/6.
 */
public class ISLProductRsParam extends BaseParam {

    /**
     * 顺番码
     */
    private List<Integer> slMainClassList;

    /**
     * 7位，分类码1+区划码3+顺番码3
     */
    private String slCode;

    /**
     * sl_account 表
     */
    private SlAccount slAccount;

    /**
     * sl_seller 表
     */
    private SlSeller slSeller;

    /**
     * 销售平台
     */
    private String salePlatform;

    /**
     * 销售区域编码
     */
    private String saleRegionCode;

    /**
     * 卖家货号列表
     */
    private List<SlPdArtno> slList;

    /**
     * 产品信息
     */
    private List<SlProduct> products;

    public SlSeller getSlSeller() {
        return slSeller;
    }

    public void setSlSeller(SlSeller slSeller) {
        this.slSeller = slSeller;
    }

    public SlAccount getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(SlAccount slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public List<Integer> getSlMainClassList() {
        return slMainClassList;
    }

    public void setSlMainClassList(List<Integer> slMainClassList) {
        this.slMainClassList = slMainClassList;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getSaleRegionCode() {
        return saleRegionCode;
    }

    public void setSaleRegionCode(String saleRegionCode) {
        this.saleRegionCode = saleRegionCode;
    }

    public List<SlPdArtno> getSlList() {
        return slList;
    }

    public void setSlList(List<SlPdArtno> slList) {
        this.slList = slList;
    }

    public List<SlProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SlProduct> products) {
        this.products = products;
    }
}