package com.msk.buyers.bean;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;
import java.util.Map;

public class BY121413Param extends BaseParam {

    private Long storeId;
    private  Long id;
    private String marketId;
    private Map<String,String> salePdMap;
    private Map<String,String> salePdCodeMap;
    private String remark;
    private String buyerStoreNo;
    private String[] salePds;
    private String[] salePdCodes;
    private String salePdCode;
    private String salePd;
    private String isChecked;
    private String flg;
    private String addFlg;
    private String isTargetMerchant;
    /** 买家类型 */
    private String merchantType;
    private String returnFlg;
    //
    private String storeNumber;
    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Map<String, String> getSalePdMap() {
        return salePdMap;
    }

    public void setSalePdMap(Map<String, String> salePdMap) {
        this.salePdMap = salePdMap;
    }

    public Map<String, String> getSalePdCodeMap() {
        return salePdCodeMap;
    }

    public void setSalePdCodeMap(Map<String, String> salePdCodeMap) {
        this.salePdCodeMap = salePdCodeMap;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBuyerStoreNo() {
        return buyerStoreNo;
    }

    public void setBuyerStoreNo(String buyerStoreNo) {
        this.buyerStoreNo = buyerStoreNo;
    }

    public String[] getSalePds() {
        return salePds;
    }

    public void setSalePds(String[] salePds) {
        this.salePds = salePds;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getSalePdCode() {
        return salePdCode;
    }

    public void setSalePdCode(String salePdCode) {
        this.salePdCode = salePdCode;
    }

    public String[] getSalePdCodes() {
        return salePdCodes;
    }

    public void setSalePdCodes(String[] salePdCodes) {
        this.salePdCodes = salePdCodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getSalePd() {
        return salePd;
    }

    public void setSalePd(String salePd) {
        this.salePd = salePd;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getIsTargetMerchant() {
        return isTargetMerchant;
    }

    public void setIsTargetMerchant(String isTargetMerchant) {
        this.isTargetMerchant = isTargetMerchant;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getReturnFlg() {
        return returnFlg;
    }

    public void setReturnFlg(String returnFlg) {
        this.returnFlg = returnFlg;
    }

    public String getAddFlg() {
        return addFlg;
    }

    public void setAddFlg(String addFlg) {
        this.addFlg = addFlg;
    }
}
