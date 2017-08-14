package com.msk.seller.bean.result;

import com.msk.core.entity.SlSeller;

import java.util.List;

/**
 * Created by zhang_chi on 2016/9/13.
 */
public class ISLMultiSellerResult extends SlSeller{

    /** 产品编码 */
    private  String  pdCode;

    /** 卖家名称 */
    private  String  slShowName;


    private List<ISLMultiSellerResult> sellers;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public List<ISLMultiSellerResult> getSellers() {
        return sellers;
    }

    public void setSellers(List<ISLMultiSellerResult> sellers) {
        this.sellers = sellers;
    }
}
