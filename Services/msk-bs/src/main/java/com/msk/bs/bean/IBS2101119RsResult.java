package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.SlHouseAccount;
import com.msk.core.entity.SlHouseProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gyh on 2016/3/17.
 */
@ApiModel(value = "IBS2101119RsResult",description = "result")
public class IBS2101119RsResult extends RsPageResult {

    @ApiModelProperty(value = "冻品管家列表")
    List<IBS2101119RsBean> houseList;

    @ApiModelProperty(value = "买手店ID(冻品管家主键)")
    private String  slCode;

    @ApiModelProperty(value = "冻品管家编码(冻品管家主键)")
    private String  houseCode;

    @ApiModelProperty(value = "冻品管家名称")
    private String houseName;

    @ApiModelProperty(value = "虚拟物流区编码")
    private String vLgcsAreaCode;

    @ApiModelProperty(value = "虚拟物流区")
    private String  vLgcsArea;

    @ApiModelProperty(value = "虚拟大区编码")
    private String    vAreaCode;

    @ApiModelProperty(value = "虚拟大区")
    private String  vArea;

    @ApiModelProperty(value = "虚拟省编码")
    private String vProvinceCode;

    @ApiModelProperty(value = "虚拟省")
    private String vProvince;

    @ApiModelProperty(value = "虚拟地区编码")
    private String  vCityCode;

    @ApiModelProperty(value = "虚拟地区")
    private String   vCity;

    @ApiModelProperty(value = "虚拟区编码")
    private String  vDistrictCode;

    @ApiModelProperty(value = "虚拟区")
    private String vDistrict;

    @ApiModelProperty(value = "虚拟管家地址")
    private String vAddress;

    @ApiModelProperty(value = "管理专属买家数（家）")
    private String  vipBuyers;

    @ApiModelProperty(value = "管理公众买家数(家)")
    private String publicBuyers;

    @ApiModelProperty(value = "营销所属期时长(天)")
    private String marketingDays;

    @ApiModelProperty(value = "可否串换公众买家 1:是 ； 0：否")
    private String  isChangeBuyers;

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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getvLgcsAreaCode() {
        return vLgcsAreaCode;
    }

    public void setvLgcsAreaCode(String vLgcsAreaCode) {
        this.vLgcsAreaCode = vLgcsAreaCode;
    }

    public String getvLgcsArea() {
        return vLgcsArea;
    }

    public void setvLgcsArea(String vLgcsArea) {
        this.vLgcsArea = vLgcsArea;
    }

    public String getvAreaCode() {
        return vAreaCode;
    }

    public void setvAreaCode(String vAreaCode) {
        this.vAreaCode = vAreaCode;
    }

    public String getvArea() {
        return vArea;
    }

    public void setvArea(String vArea) {
        this.vArea = vArea;
    }

    public String getvProvinceCode() {
        return vProvinceCode;
    }

    public void setvProvinceCode(String vProvinceCode) {
        this.vProvinceCode = vProvinceCode;
    }

    public String getvProvince() {
        return vProvince;
    }

    public void setvProvince(String vProvince) {
        this.vProvince = vProvince;
    }

    public String getvCityCode() {
        return vCityCode;
    }

    public void setvCityCode(String vCityCode) {
        this.vCityCode = vCityCode;
    }

    public String getvCity() {
        return vCity;
    }

    public void setvCity(String vCity) {
        this.vCity = vCity;
    }

    public String getvDistrictCode() {
        return vDistrictCode;
    }

    public void setvDistrictCode(String vDistrictCode) {
        this.vDistrictCode = vDistrictCode;
    }

    public String getvDistrict() {
        return vDistrict;
    }

    public void setvDistrict(String vDistrict) {
        this.vDistrict = vDistrict;
    }

    public String getvAddress() {
        return vAddress;
    }

    public void setvAddress(String vAddress) {
        this.vAddress = vAddress;
    }

    public String getVipBuyers() {
        return vipBuyers;
    }

    public void setVipBuyers(String vipBuyers) {
        this.vipBuyers = vipBuyers;
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

    public String getIsChangeBuyers() {
        return isChangeBuyers;
    }

    public void setIsChangeBuyers(String isChangeBuyers) {
        this.isChangeBuyers = isChangeBuyers;
    }

    public List<IBS2101119RsBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBS2101119RsBean> houseList) {
        this.houseList = houseList;
    }
}
