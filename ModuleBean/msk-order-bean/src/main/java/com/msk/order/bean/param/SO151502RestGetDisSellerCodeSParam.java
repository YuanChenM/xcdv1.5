package com.msk.order.bean.param;

import java.io.Serializable;
import java.util.List;

/**
 * 获取卖家显示编码  通过sellerCode
 */
public class SO151502RestGetDisSellerCodeSParam implements Serializable {

    private  List<String> slCodeList;//  卖家 ，供货商 编码


    public List<String> getSlCodeList() {
        return slCodeList;
    }

    public void setSlCodeList(List<String> slCodeList) {
        this.slCodeList = slCodeList;
    }
}
