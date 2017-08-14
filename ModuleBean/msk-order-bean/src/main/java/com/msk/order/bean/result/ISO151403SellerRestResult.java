package com.msk.order.bean.result;


import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang_chi on 2016/8/23.
 */
public class ISO151403SellerRestResult implements Serializable {

    /**
     * 卖家ID
     */
    private String slCode;

    /**
     * 卖家显示编码
     */
    private String slCodeDis;

    /**
     * 卖家名称
     */
    private String slShowName;

    List<ISO151403SellerRestResult> islSellerRsResultList;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public List<ISO151403SellerRestResult> getIslSellerRsResultList() {
        return islSellerRsResultList;
    }

    public void setIslSellerRsResultList(List<ISO151403SellerRestResult> islSellerRsResultList) {
        this.islSellerRsResultList = islSellerRsResultList;
    }
}
