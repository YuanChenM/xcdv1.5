package com.msk.br.bean;

import com.msk.core.entity.BrFileBuyerPool;
import com.msk.core.entity.BrOClaMachiningInfo;

import java.util.List;

/**
 *
 * @author tao_zhifa
 */
public class IBR121409RsBean extends BrFileBuyerPool {

    //查询条件组合
    private String fileNameGroup;
    //买家类型
    private String buyersType;
    //物流区
    private String lgcsAreaName;

    //地区（城市）名称
    private String cityName;

    //一级分类名称
    private String classesName;

    //二级分类名称
    private String machiningName;

    //批发市场
    private String marketName;
    //买家名及主码
    private String buyerNameCode;
    //买家营业执照地址
    private String manageAddr;
    //微信号
    private String bossWechat;
    //QQ
    private String bossQq;
    //上线状态
    private String marketingsStatusName;
    //Boos 名称及联系方式
    private String bossNameTel;
    //买手店ID(冻品管家主键)
    private String slCode;
    //冻品管家编码(冻品管家主键)
    private String houseCode;
    //冻品管家名称
    private String houseName;
    private String cityCode;
    //虚拟物流区编码
    private String vLgcsAreaCode;
    //虚拟物流区
    private String vLgcsArea;
    // 虚拟大区编码
    private String vAreaCode;
    //虚拟大区
    private String vArea;
    //虚拟省编码
    private String vProvinceCode;
    //虚拟省
    private String vProvince;
    //虚拟地区编码
    private String vCityCode;
    // 虚拟地区
    private String vCity;
    //虚拟区编码
    private String vDistrictCode;
    //虚拟区
    private String vDistrict;
    //虚拟管家地址
    private String vAddress;
    //管理专属买家数（家）
    private String vipBuyers;
    //管理公众买家数(家)
    private String publicBuyers;
    //营销所属期时长(天)
    private String marketingDays;
    //可否串换公众买家 1:是 ； 0：否
    private String isChangeBuyers;
    /*待补充*/
    private List<BrOClaMachiningInfo> brOClaMachiningInfoList;
    //判断成功条件
    private String dataCount;
    //判断文件状态
    private String fileStatusFlag;

    public String getFileStatusFlag() {
        return fileStatusFlag;
    }

    public void setFileStatusFlag(String fileStatusFlag) {
        this.fileStatusFlag = fileStatusFlag;
    }

    public String getMarketingsStatusName() {
        return marketingsStatusName;
    }

    public void setMarketingsStatusName(String marketingsStatusName) {
        this.marketingsStatusName = marketingsStatusName;
    }

    public String getDataCount() {
        return dataCount;
    }

    public void setDataCount(String dataCount) {
        this.dataCount = dataCount;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    @Override
    public String getCityCode() {
        return cityCode;
    }

    @Override
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    public String getFileNameGroup() {
        return fileNameGroup;
    }

    public void setFileNameGroup(String fileNameGroup) {
        this.fileNameGroup = fileNameGroup;
    }

    public String getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(String buyersType) {
        this.buyersType = buyersType;
    }

    @Override
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    @Override
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    @Override
    public String getMarketName() {
        return marketName;
    }

    @Override
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getBuyerNameCode() {
        return buyerNameCode;
    }

    public void setBuyerNameCode(String buyerNameCode) {
        this.buyerNameCode = buyerNameCode;
    }

    public String getManageAddr() {
        return manageAddr;
    }

    public void setManageAddr(String manageAddr) {
        this.manageAddr = manageAddr;
    }

    public String getBossWechat() {
        return bossWechat;
    }

    public void setBossWechat(String bossWechat) {
        this.bossWechat = bossWechat;
    }

    public String getBossQq() {
        return bossQq;
    }

    public void setBossQq(String bossQq) {
        this.bossQq = bossQq;
    }

    public String getBossNameTel() {
        return bossNameTel;
    }

    public void setBossNameTel(String bossNameTel) {
        this.bossNameTel = bossNameTel;
    }

    public List<BrOClaMachiningInfo> getBrOClaMachiningInfoList() {
        return brOClaMachiningInfoList;
    }

    public void setBrOClaMachiningInfoList(List<BrOClaMachiningInfo> brOClaMachiningInfoList) {
        this.brOClaMachiningInfoList = brOClaMachiningInfoList;
    }
}
