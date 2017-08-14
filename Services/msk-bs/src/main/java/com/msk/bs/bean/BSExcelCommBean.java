package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by zhu_kai1 on 2016/8/3.
 */
public class BSExcelCommBean extends BaseEntity{
    /** 地区名称 */
    private java.lang.String cityName;
    /** 管家名称 */
    private java.lang.String houseShowName;
    /** HOUSE_GREADE */
    private java.lang.String houseGreade;
    /** HOUSE_STAR */
    private java.lang.String houseStar;
    /** V_HOUSE_ADDRESS */
    private java.lang.String vhouseAddress;
    /**管家一级分类**/
    private  String categoryName;
    /**管家二级分类**/
    private String reclassifyName;
    /**公众买家数**/
    private int publicBuyers;
    /**会员买家数**/
    private int vipBuyers;
    /**营销所属时长**/
    private int marketingDays;
    /**可否自由串换买家**/
    private String isChangeBuyers;

    public String getHouseShowName() {
        return houseShowName;
    }

    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    public String getHouseGreade() {
        return houseGreade;
    }

    public void setHouseGreade(String houseGreade) {
        this.houseGreade = houseGreade;
    }

    public String getHouseStar() {
        return houseStar;
    }

    public void setHouseStar(String houseStar) {
        this.houseStar = houseStar;
    }

    public String getVhouseAddress() {
        return vhouseAddress;
    }

    public void setVhouseAddress(String vhouseAddress) {
        this.vhouseAddress = vhouseAddress;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getReclassifyName() {
        return reclassifyName;
    }

    public void setReclassifyName(String reclassifyName) {
        this.reclassifyName = reclassifyName;
    }

    public int getPublicBuyers() {
        return publicBuyers;
    }

    public void setPublicBuyers(int publicBuyers) {
        this.publicBuyers = publicBuyers;
    }

    public int getVipBuyers() {
        return vipBuyers;
    }

    public void setVipBuyers(int vipBuyers) {
        this.vipBuyers = vipBuyers;
    }

    public int getMarketingDays() {
        return marketingDays;
    }

    public void setMarketingDays(int marketingDays) {
        this.marketingDays = marketingDays;
    }

    public String getIsChangeBuyers() {
        return isChangeBuyers;
    }

    public void setIsChangeBuyers(String isChangeBuyers) {
        this.isChangeBuyers = isChangeBuyers;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
