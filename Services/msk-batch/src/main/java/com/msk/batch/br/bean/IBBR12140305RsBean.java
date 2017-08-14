package com.msk.batch.br.bean;

import com.msk.core.entity.SlHouseAccount;
import com.msk.core.entity.SlHouseProduct;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/9/18.
 */
public class IBBR12140305RsBean extends SlHouseAccount {
    //行政区划
    private String cityName;
    //物流区划
    private String lgcsAreaName;
    //买手名称
    private String slContact;
    //当前专属买家数
    private String buyerNum;
    private Integer greade;//买手店管家等级
    private String houseCategoryName;//买手店管家分类名称
    //private List<SlHouseArea> slAreaList;//经营区域List
    private List<SlHouseProduct> housePdList;//管家管理产品list
    private List<IBBR12140305RsParam> houseTYPEList;//管家分类

    private String rAreaName;
    private String rLgcsAreaName;
    private String rProvinceName;
    private String rCityName;
    private String rDistrictName;

    private String areaName;
    private String provinceName;
    private String districtName;

    private String vareaName;
    private String vlgcsAreaName;
    private String vprovinceName;
    private String vcityName;
    private String vdistrictName;
    private String publicBuyers;
    private String marketingDays;
    private String vipBuyers;
    private String isChangeBuyers;


    /**
     * Getter method for property <tt>cityName</tt>.
     *
     * @return property value of cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter method for property <tt>cityName</tt>.
     *
     * @param cityName value to be assigned to property cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Getter method for property <tt>lgcsAreaName</tt>.
     *
     * @return property value of lgcsAreaName
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * Setter method for property <tt>lgcsAreaName</tt>.
     *
     * @param lgcsAreaName value to be assigned to property lgcsAreaName
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * Getter method for property <tt>slContact</tt>.
     *
     * @return property value of slContact
     */
    public String getSlContact() {
        return slContact;
    }

    /**
     * Setter method for property <tt>slContact</tt>.
     *
     * @param slContact value to be assigned to property slContact
     */
    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    /**
     * Getter method for property <tt>buyerNum</tt>.
     *
     * @return property value of buyerNum
     */
    public String getBuyerNum() {
        return buyerNum;
    }

    /**
     * Setter method for property <tt>buyerNum</tt>.
     *
     * @param buyerNum value to be assigned to property buyerNum
     */
    public void setBuyerNum(String buyerNum) {
        this.buyerNum = buyerNum;
    }

    /**
     * Getter method for property <tt>greade</tt>.
     *
     * @return property value of greade
     */
    public Integer getGreade() {
        return greade;
    }

    /**
     * Setter method for property <tt>greade</tt>.
     *
     * @param greade value to be assigned to property greade
     */
    public void setGreade(Integer greade) {
        this.greade = greade;
    }

    /**
     * Getter method for property <tt>housePdList</tt>.
     *
     * @return property value of housePdList
     */
    public List<SlHouseProduct> getHousePdList() {
        return housePdList;
    }

    /**
     * Setter method for property <tt>housePdList</tt>.
     *
     * @param housePdList value to be assigned to property housePdList
     */
    public void setHousePdList(List<SlHouseProduct> housePdList) {
        this.housePdList = housePdList;
    }

    public String getHouseCategoryName() {
        return houseCategoryName;
    }

    public void setHouseCategoryName(String houseCategoryName) {
        this.houseCategoryName = houseCategoryName;
    }

    public List<IBBR12140305RsParam> getHouseTYPEList() {
        return houseTYPEList;
    }

    public void setHouseTYPEList(List<IBBR12140305RsParam> houseTYPEList) {
        this.houseTYPEList = houseTYPEList;
    }

    public String getrAreaName() {
        return rAreaName;
    }

    public void setrAreaName(String rAreaName) {
        this.rAreaName = rAreaName;
    }

    public String getrLgcsAreaName() {
        return rLgcsAreaName;
    }

    public void setrLgcsAreaName(String rLgcsAreaName) {
        this.rLgcsAreaName = rLgcsAreaName;
    }

    public String getrProvinceName() {
        return rProvinceName;
    }

    public void setrProvinceName(String rProvinceName) {
        this.rProvinceName = rProvinceName;
    }

    public String getrCityName() {
        return rCityName;
    }

    public void setrCityName(String rCityName) {
        this.rCityName = rCityName;
    }

    public String getrDistrictName() {
        return rDistrictName;
    }

    public void setrDistrictName(String rDistrictName) {
        this.rDistrictName = rDistrictName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getVareaName() {
        return vareaName;
    }

    public void setVareaName(String vareaName) {
        this.vareaName = vareaName;
    }

    public String getVlgcsAreaName() {
        return vlgcsAreaName;
    }

    public void setVlgcsAreaName(String vlgcsAreaName) {
        this.vlgcsAreaName = vlgcsAreaName;
    }

    public String getVprovinceName() {
        return vprovinceName;
    }

    public void setVprovinceName(String vprovinceName) {
        this.vprovinceName = vprovinceName;
    }

    public String getVcityName() {
        return vcityName;
    }

    public void setVcityName(String vcityName) {
        this.vcityName = vcityName;
    }

    public String getVdistrictName() {
        return vdistrictName;
    }

    public void setVdistrictName(String vdistrictName) {
        this.vdistrictName = vdistrictName;
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
}
