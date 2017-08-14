package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.PdTcProvider;

import java.util.List;


public class ISL231172RsParam extends BaseParam{

    /**
     *卖家申请新产品品种 特征 净重List
     */
    private List<SL241130Param> newPdBFWList;

    /**
     * Getter method for property <tt>newPdBFWList</tt>.
     *
     * @return property value of newPdBFWList
     */
    public List<SL241130Param> getNewPdBFWList() {
        return newPdBFWList;
    }

    /**
     * Setter method for property <tt>newPdBFWList</tt>.
     *
     * @param newPdBFWList value to be assigned to property newPdBFWList
     */
    public void setNewPdBFWList(List<SL241130Param> newPdBFWList) {
        this.newPdBFWList = newPdBFWList;
    }
}
