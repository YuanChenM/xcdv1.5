package com.msk.product.bean;

import com.msk.core.entity.SlHouseAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gao_min on 2016/10/17.
 */
@ApiModel(value = "IPD1411213HouseBean",description = "管家列表")
public class IPD1411213HouseBean extends SlHouseAccount {
    @ApiModelProperty(value = "行政区划")
    private String cityName;
    @ApiModelProperty(value = "物流区划")
    private String lgcsAreaName;
    @ApiModelProperty(value = "买手名称")
    private String slContact;
    @ApiModelProperty(value = "当前专属买家数")
    private String buyerNum;
    @ApiModelProperty(value = "买手店管家等级")
    private Integer greade;
    @ApiModelProperty(value = "买手店管家分类名称")
    private String  houseCategoryName;
    //  private List<SlHouseArea> slAreaList;//经营区域List
    //    private List<SlHouseProduct> housePdList;//管家管理产品list
    @ApiModelProperty(value = "管家分类")
    private List<IPD1411214HouseType> houseTYPEList;
    @ApiModelProperty(value = "户籍大区名")
    private String rAreaName;
    @ApiModelProperty(value = "户籍物流区名")
    private String rLgcsAreaName;
    @ApiModelProperty(value = "户籍省名")
    private String rProvinceName;
    @ApiModelProperty(value = "户籍地区名")
    private String rCityName;
    @ApiModelProperty(value = "户籍区名")
    private String rDistrictName;
    @ApiModelProperty(value = "大区名")
    private String areaName;
    @ApiModelProperty(value = "省名")
    private String provinceName;
    @ApiModelProperty(value = "区名")
    private String districtName;
    @ApiModelProperty(value = "大区名")
    private String vareaName;
    @ApiModelProperty(value = "物流区名")
    private String vlgcsAreaName;
    @ApiModelProperty(value = "省名")
    private String vprovinceName;
    @ApiModelProperty(value = "地区名")
    private String vcityName;
    @ApiModelProperty(value = "区名")
    private String vdistrictName;

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

    public String getHouseCategoryName() {
        return houseCategoryName;
    }

    public void setHouseCategoryName(String houseCategoryName) {
        this.houseCategoryName = houseCategoryName;
    }

    public List<IPD1411214HouseType> getHouseTYPEList() {
        return houseTYPEList;
    }

    public void setHouseTYPEList(List<IPD1411214HouseType> houseTYPEList) {
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
}
