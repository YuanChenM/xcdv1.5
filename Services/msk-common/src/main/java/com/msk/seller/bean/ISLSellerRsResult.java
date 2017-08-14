package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by zhang_chi on 2016/8/23.
 */
public class ISLSellerRsResult extends BaseEntity {

    /** 卖家ID */
    private  String  slCode;

    /** 卖家显示编码 */
    private  String  slCodeDis;

    /** 卖家名称 */
    private  String  slShowName;

    /** sl_account 表影响行数 */
    private  Integer slAccountCount;

    /** sl_seller 表影响行数 */
    private  Integer slSellerCount;

    public Integer getSlSellerCount() {
        return slSellerCount;
    }

    public void setSlSellerCount(Integer slSellerCount) {
        this.slSellerCount = slSellerCount;
    }

    public Integer getSlAccountCount() {
        return slAccountCount;
    }

    public void setSlAccountCount(Integer slAccountCount) {
        this.slAccountCount = slAccountCount;
    }

    List<ISLSellerRsResult>  islSellerRsResultList;

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

    public List<ISLSellerRsResult> getIslSellerRsResultList() {
        return islSellerRsResultList;
    }

    public void setIslSellerRsResultList(List<ISLSellerRsResult> islSellerRsResultList) {
        this.islSellerRsResultList = islSellerRsResultList;
    }
}
