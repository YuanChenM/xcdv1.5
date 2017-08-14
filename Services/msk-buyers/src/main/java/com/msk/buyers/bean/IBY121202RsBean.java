package com.msk.buyers.bean;

import com.msk.common.base.BaseBean;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrOBuyerPdCla;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/11/7.
 */
public class IBY121202RsBean extends BaseBean {

    private BrOBuyerInfo brOBuyerInfo;
    private List<BrOBuyerPdCla> brOBuyerPdClaList;
    private String isModify;

    public BrOBuyerInfo getBrOBuyerInfo() {
        return brOBuyerInfo;
    }

    public void setBrOBuyerInfo(BrOBuyerInfo brOBuyerInfo) {
        this.brOBuyerInfo = brOBuyerInfo;
    }

    public List<BrOBuyerPdCla> getBrOBuyerPdClaList() {
        return brOBuyerPdClaList;
    }

    public void setBrOBuyerPdClaList(List<BrOBuyerPdCla> brOBuyerPdClaList) {
        this.brOBuyerPdClaList = brOBuyerPdClaList;
    }

    public String getIsModify() {
        return isModify;
    }

    public void setIsModify(String isModify) {
        this.isModify = isModify;
    }
}
