package com.msk.buyers.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by guan_zhongheng on 2016/7/29.
 */
public class IBY121105RsResult extends RsPageResult {

    private List<IBY121105Bean> byAccountInfoList;

    /**
     * Getter method for property <tt>byAccountInfoList</tt>.
     *
     * @return property value of byAccountInfoList
     */
    public List<IBY121105Bean> getByAccountInfoList() {
        return byAccountInfoList;
    }

    /**
     * Setter method for property <tt>byAccountInfoList</tt>.
     *
     * @param byAccountInfoList value to be assigned to property byAccountInfoList
     */
    public void setByAccountInfoList(List<IBY121105Bean> byAccountInfoList) {
        this.byAccountInfoList = byAccountInfoList;
    }
}
