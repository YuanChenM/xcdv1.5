package com.msk.bs.bean;

import com.msk.core.entity.SlHouseIntroduce;
import com.msk.core.entity.SlHouseManage;
import com.msk.core.entity.SlHouseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
@ApiModel(value = "IBS2101104RsParam",description = "param")
public class IBS2101104RsParam {
    //接口需要
    @ApiModelProperty(value = "冻品管家账号信息")
    private IBS2101104SlHouseAccount slHouseAccount;
    @ApiModelProperty(value = "经营区域List")
    private List<IBS2101104SlHouseArea> slAreaList;
    @ApiModelProperty(value = "冻品管家产品分类list")
    private List<IBS2101104SlHouseProduct> housePdList;
    //页面需要
    //冻品管家产品分类list  2016/08改版
    @ApiModelProperty(value = "冻品管家产品分类list ")
    private ArrayList<SlHouseType> houseTypeList;
    @ApiModelProperty(value = "SlHouseManage")
    private List<SlHouseManage> slHouseManage;
    @ApiModelProperty(value = "地区名")
    private String vcityName;
    @ApiModelProperty(value = "区名")
    private String vdistrictName;
    @ApiModelProperty(value = "户籍地区名")
    private String rcityName;
    @ApiModelProperty(value = "户籍区名")
    private String rdistrictName;
    @ApiModelProperty(value = "地区名")
    private String cityName;
    @ApiModelProperty(value = "区名")
    private String districtName;
    @ApiModelProperty(value = "冻品管家图片")
    private SlHouseIntroduce slHouseIntroduce;

    /**
     * Getter method for property <tt>slHouseAccount</tt>.
     *
     * @return property value of slHouseAccount
     */
    public IBS2101104SlHouseAccount getSlHouseAccount() {
        return slHouseAccount;
    }

    /**
     * Setter method for property <tt>slHouseAccount</tt>.
     *
     * @param slHouseAccount value to be assigned to property slHouseAccount
     */
    public void setSlHouseAccount(IBS2101104SlHouseAccount slHouseAccount) {
        this.slHouseAccount = slHouseAccount;
    }

    /**
     * Getter method for property <tt>slAreaList</tt>.
     *
     * @return property value of slAreaList
     */
    public List<IBS2101104SlHouseArea> getSlAreaList() {
        return slAreaList;
    }

    /**
     * Setter method for property <tt>slAreaList</tt>.
     *
     * @param slAreaList value to be assigned to property slAreaList
     */
    public void setSlAreaList(List<IBS2101104SlHouseArea> slAreaList) {
        this.slAreaList = slAreaList;
    }

    /**
     * Getter method for property <tt>housePdList</tt>.
     *
     * @return property value of housePdList
     */
    public List<IBS2101104SlHouseProduct> getHousePdList() {
        return housePdList;
    }

    /**
     * Setter method for property <tt>housePdList</tt>.
     *
     * @param housePdList value to be assigned to property housePdList
     */
    public void setHousePdList(List<IBS2101104SlHouseProduct> housePdList) {
        this.housePdList = housePdList;
    }

    public ArrayList<SlHouseType> getHouseTypeList() {
        return houseTypeList;
    }

    public void setHouseTypeList(ArrayList<SlHouseType> houseTypeList) {
        this.houseTypeList = houseTypeList;
    }

    public List<SlHouseManage> getSlHouseManage() {
        return slHouseManage;
    }

    public void setSlHouseManage(List<SlHouseManage> slHouseManage) {
        this.slHouseManage = slHouseManage;
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

    public String getRcityName() {
        return rcityName;
    }

    public void setRcityName(String rcityName) {
        this.rcityName = rcityName;
    }

    public String getRdistrictName() {
        return rdistrictName;
    }

    public void setRdistrictName(String rdistrictName) {
        this.rdistrictName = rdistrictName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public SlHouseIntroduce getSlHouseIntroduce() {
        return slHouseIntroduce;
    }

    public void setSlHouseIntroduce(SlHouseIntroduce slHouseIntroduce) {
        this.slHouseIntroduce = slHouseIntroduce;
    }
}
