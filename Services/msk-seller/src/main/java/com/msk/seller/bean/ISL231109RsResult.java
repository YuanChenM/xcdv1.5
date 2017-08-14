package com.msk.seller.bean;

import com.msk.core.entity.SlProduct;

import java.util.List;

/**
 * Created by gyh on 2016/2/29.
 */
public class ISL231109RsResult extends SlProduct {
    private List<ISL231109RsProduct> slPdList;//卖家能销售的产品信息/产品加工质量标准指标/产品加工技术标准指标

    /**
     * Getter method for property <tt>slPdList</tt>.
     *
     * @return property value of slPdList
     */
    public List<ISL231109RsProduct> getSlPdList() {
        return slPdList;
    }

    /**
     * Setter method for property <tt>slPdList</tt>.
     *
     * @param slPdList value to be assigned to property slPdList
     */
    public void setSlPdList(List<ISL231109RsProduct> slPdList) {
        this.slPdList = slPdList;
    }
}
