package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.PdTcProviderPackage;

import java.util.List;


public class ISL231173RsParam extends BaseParam{
    /**
     * 卖家申请新产品包装List
     */
   private List<SL241130Param> newPdPkgList;

    /**
     * Getter method for property <tt>newPdPkgList</tt>.
     *
     * @return property value of newPdPkgList
     */
    public List<SL241130Param> getNewPdPkgList() {
        return newPdPkgList;
    }

    /**
     * Setter method for property <tt>newPdPkgList</tt>.
     *
     * @param newPdPkgList value to be assigned to property newPdPkgList
     */
    public void setNewPdPkgList(List<SL241130Param> newPdPkgList) {
        this.newPdPkgList = newPdPkgList;
    }
}
