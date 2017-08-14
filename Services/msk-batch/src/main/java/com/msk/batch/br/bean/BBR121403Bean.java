package com.msk.batch.br.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/9/13.
 */
public class BBR121403Bean extends BaseEntity {
    //物流区名称
    private String vlgcsAreaCode;
    //地区名称
    private String vcityCode;
    private String publicBuyers;
    private String marketingDays;
    private String vipBuyers;
    private String isChangeBuyers;

    private String slCode;
    private String houseCode;
    List<BBR121403Bean> houseList;

    public String getVlgcsAreaCode() {
        return vlgcsAreaCode;
    }

    public void setVlgcsAreaCode(String vlgcsAreaCode) {
        this.vlgcsAreaCode = vlgcsAreaCode;
    }

    public String getVcityCode() {
        return vcityCode;
    }

    public void setVcityCode(String vcityCode) {
        this.vcityCode = vcityCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getPublicBuyers() {
        return publicBuyers;
    }

    public void setPublicBuyers(String publicBuyers) {
        this.publicBuyers = publicBuyers;
    }

    public String getMarketingDays() {
        return marketingDays;
    }

    public void setMarketingDays(String marketingDays) {
        this.marketingDays = marketingDays;
    }

    public String getVipBuyers() {
        return vipBuyers;
    }

    public void setVipBuyers(String vipBuyers) {
        this.vipBuyers = vipBuyers;
    }

    public String getIsChangeBuyers() {
        return isChangeBuyers;
    }

    public void setIsChangeBuyers(String isChangeBuyers) {
        this.isChangeBuyers = isChangeBuyers;
    }

    public List<BBR121403Bean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<BBR121403Bean> houseList) {
        this.houseList = houseList;
    }
}
