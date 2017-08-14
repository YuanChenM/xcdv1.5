package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.SlSeller;

import java.util.List;

/**
 * Created by zhang_chi on 2016/7/6.
 */
public class ISLSellerRsParam extends BaseParam {

    /**卖家类型*/
    private List<Integer> slMainClassList;

    /**卖家编码*/
    private String slCode;

    /**生产商企业ID*/
    private Long prodEpId;

    /** sl_account 表 */
    private SlAccount slAccount;

    /** sl_seller 表 */
    private SlSeller slSeller;

    /**卖家编码List*/
    private List<String> sellCodeList;

    public List<String> getSellCodeList() {
        return sellCodeList;
    }

    public void setSellCodeList(List<String> sellCodeList) {
        this.sellCodeList = sellCodeList;
    }

    public SlSeller getSlSeller() {
        return slSeller;
    }

    public void setSlSeller(SlSeller slSeller) {
        this.slSeller = slSeller;
    }

    public SlAccount getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(SlAccount slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public List<Integer> getSlMainClassList() {
        return slMainClassList;
    }

    public void setSlMainClassList(List<Integer> slMainClassList) {
        this.slMainClassList = slMainClassList;
    }

    public Long getProdEpId() {
        return prodEpId;
    }

    public void setProdEpId(Long prodEpId) {
        this.prodEpId = prodEpId;
    }
}
