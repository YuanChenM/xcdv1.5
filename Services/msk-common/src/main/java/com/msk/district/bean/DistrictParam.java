package com.msk.district.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by liu_yan2 on 2016/4/27.
 */
public class DistrictParam extends BaseParam {

    /** 一组大区编码 */
    private String[] areaCodes;

    /** 大区编码 */
    private String areaCode;

    /** 一组省编码 */
    private String[] provinceCodes;

    /** 省编码 */
    private String provinceCode;

    /** 一组物流区编码 */
    private String[] lgcsAreaCodes;

    /** 物流区编码 */
    private String lgcsAreaCode;

    /** 一组城市编码 */
    private String[] cityCodes;

    /** 城市编码 */
    private String cityCode;

    /**物流区名称*/
    private String lgcsAreaName;

    /** 0加载子省数据,1不加载。默认为1不加载 */
    private int isLoadProvince = 1;

    /** 0加载子物流区数据,1不加载。默认为1不加载 */
    private int isLoadLgcsArea = 1;

    /** 0加载子城市数据,1不加载。默认为1不加载 */
    private int isLoadCity = 1;

    /** 0加载子区县数据,1不加载。默认为1不加载 */
    private int isLoadDistrict = 1;

    /** 0加载物流区 1加载省。 默认为1 */
    private int flag = 1;

    /** 组合主键 如城市Code+区县Code 如:["00101","00102","00202"]*/
    private String[] composeCodes;

    /** 城市名称 */
    private String cityName;

    /** 区县名称 */
    private String districtName;

    /** 省名称 */
    private String provinceName;

    /** 区县编码*/
    private String districtCode;

    /** 开放服务城市  0未开通 1开通 */
    private String openServiceFlg;

    /** 1字母升序 2城市名称升序 3城市编码升序*/
    private int cityOrderBy;

    /** 1字母升序 2区县名称升序 3区县编码升序*/
    private int districtOrderBy;

    public int getCityOrderBy() {
        return cityOrderBy;
    }

    public void setCityOrderBy(int cityOrderBy) {
        this.cityOrderBy = cityOrderBy;
    }

    public int getDistrictOrderBy() {
        return districtOrderBy;
    }

    public void setDistrictOrderBy(int districtOrderBy) {
        this.districtOrderBy = districtOrderBy;
    }

    public String getOpenServiceFlg() {
        return openServiceFlg;
    }

    public void setOpenServiceFlg(String openServiceFlg) {
        this.openServiceFlg = openServiceFlg;
    }

    public String[] getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(String[] areaCodes) {
        this.areaCodes = areaCodes;
    }

    public String[] getProvinceCodes() {
        return provinceCodes;
    }

    public void setProvinceCodes(String[] provinceCodes) {
        this.provinceCodes = provinceCodes;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String[] getLgcsAreaCodes() {
        return lgcsAreaCodes;
    }

    public void setLgcsAreaCodes(String[] lgcsAreaCodes) {
        this.lgcsAreaCodes = lgcsAreaCodes;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String[] getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String[] cityCodes) {
        this.cityCodes = cityCodes;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getIsLoadCity() {
        return isLoadCity;
    }

    public void setIsLoadCity(int isLoadCity) {
        this.isLoadCity = isLoadCity;
    }

    public int getIsLoadDistrict() {
        return isLoadDistrict;
    }

    public void setIsLoadDistrict(int isLoadDistrict) {
        this.isLoadDistrict = isLoadDistrict;
    }

    public int getIsLoadProvince() {
        return isLoadProvince;
    }

    public void setIsLoadProvince(int isLoadProvince) {
        this.isLoadProvince = isLoadProvince;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getIsLoadLgcsArea() {
        return isLoadLgcsArea;
    }

    public void setIsLoadLgcsArea(int isLoadLgcsArea) {
        this.isLoadLgcsArea = isLoadLgcsArea;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String[] getComposeCodes() {
        return composeCodes;
    }

    public void setComposeCodes(String[] composeCodes) {
        this.composeCodes = composeCodes;
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

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
