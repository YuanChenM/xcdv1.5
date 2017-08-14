package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;
import com.msk.order.bean.result.ISO151415SlArtNoResult;

import java.util.List;

/**
 * Created by wang_shuai on 2016/8/25.
 */
public class ISO151415RestSkuParam extends BaseParam{
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
    private List<ISO151415SlArtNoResult> slList;

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

    public List<ISO151415SlArtNoResult> getSlList() {
        return slList;
    }

    public void setSlList(List<ISO151415SlArtNoResult> slList) {
        this.slList = slList;
    }
}
