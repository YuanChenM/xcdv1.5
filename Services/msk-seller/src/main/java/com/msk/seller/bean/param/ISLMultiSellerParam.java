package com.msk.seller.bean.param;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlSeller;

import java.util.List;

/**
 * Created by zhang_chi on 2016/9/13.
 */
public class ISLMultiSellerParam extends BaseParam {

    /**
     * 卖家信息List
     */
    private List<SlSeller> sellers;

    /**
     * 本期价盘周期开始日期（格式：yyyyMMdd）
     */
    private String startPriceCycle;

    /**
     * 本期价盘周期结束日期（格式：yyyyMMdd）
     */
    private String endPriceCycle;

    public List<SlSeller> getSellers() {
        return sellers;
    }

    public void setSellers(List<SlSeller> sellers) {
        this.sellers = sellers;
    }

    public String getStartPriceCycle() {
        return startPriceCycle;
    }

    public void setStartPriceCycle(String startPriceCycle) {
        this.startPriceCycle = startPriceCycle;
    }

    public String getEndPriceCycle() {
        return endPriceCycle;
    }

    public void setEndPriceCycle(String endPriceCycle) {
        this.endPriceCycle = endPriceCycle;
    }
}
