package com.msk.seller.bean;

import java.util.List;

import com.msk.common.bean.RsPageResult;

/**
 * ISL231119RsResult.
 *
 * @author yuan_chen
 */
public class ISL231119RsResult extends RsPageResult {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 物流区卖家产品信息列表 */
    List<SlLogiAreaPdBean> slLogiAreaPdBeanList;

    /**
     * Getter method for property <tt>slLogiAreaPdBeanList</tt>.
     *
     * @return property value of slLogiAreaPdBeanList
     */
    public List<SlLogiAreaPdBean> getSlLogiAreaPdBeanList() {
        return slLogiAreaPdBeanList;
    }

    /**
     * Setter method for property <tt>slLogiAreaPdBeanList</tt>.
     *
     * @param slLogiAreaPdBeanList value to be assigned to property slLogiAreaPdBeanList
     */
    public void setSlLogiAreaPdBeanList(List<SlLogiAreaPdBean> slLogiAreaPdBeanList) {
        this.slLogiAreaPdBeanList = slLogiAreaPdBeanList;
    }
}
