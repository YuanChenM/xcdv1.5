package com.msk.buyers.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/4/27.
 */
public class BuyerRelationParam extends BaseParam{
    private static final long serialVersionUID = 1L;
    /**
     * 买家ID
     */
    private  List<String> buyerIdList;
    /**
     * 0加载买家基本信息，1-不加载。默认不加载
     */
    private int isLoadBuyerBasic=1;

    /**
     * 0加载买家产品销售对象信息，1-不加载。默认不加载
     */
    private int isLoadSalesTarget=1;
    /**
     * 0加载买家经营产品类别信息，1-不加载。默认不加载
     */
    private int isLoadPdClass=1;

    /**
     * 0加载买家收货地址信息，1-不加载。默认不加载
     */
    private int isLoadRecAddr=1;

    /**
     * 0加载买家收货时间信息，1-不加载。默认不加载
     */
    private int isLoadRecTime=1;
    /**
     * 0加载买家雇员信息，1-不加载。默认不加载
     */
    private int isLoadEmployee=1;

    /**
     * 0加载买家证照信息，1-不加载。默认不加载
     */
    private int isLoadLicence=1;

    /**
     * 0加载买家图片信息，1-不加载。默认不加载
     */
    private int isLoadPicture=1;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 物流区编码
     */
    private String lgcsAreaCode;
    /**
     * 地区（城市）编码
     */
    private String cityCode;
    /**
     * 区（县）编码
     */
    private String districtCode;

    private List<String> buyerCodeList;
    /**买家名称**/
    private String buyerName;
    /**买家地址**/
    private String buyerAddr;
    /**买家联系方式**/
    private  String busiTel;
    /**买家编码**/
    private  String buyerCode;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getIsLoadBuyerBasic() {
        return isLoadBuyerBasic;
    }

    public void setIsLoadBuyerBasic(int isLoadBuyerBasic) {
        this.isLoadBuyerBasic = isLoadBuyerBasic;
    }

    public int getIsLoadSalesTarget() {
        return isLoadSalesTarget;
    }

    public void setIsLoadSalesTarget(int isLoadSalesTarget) {
        this.isLoadSalesTarget = isLoadSalesTarget;
    }

    public int getIsLoadPdClass() {
        return isLoadPdClass;
    }

    public void setIsLoadPdClass(int isLoadPdClass) {
        this.isLoadPdClass = isLoadPdClass;
    }

    public int getIsLoadRecAddr() {
        return isLoadRecAddr;
    }

    public void setIsLoadRecAddr(int isLoadRecAddr) {
        this.isLoadRecAddr = isLoadRecAddr;
    }

    public int getIsLoadRecTime() {
        return isLoadRecTime;
    }

    public void setIsLoadRecTime(int isLoadRecTime) {
        this.isLoadRecTime = isLoadRecTime;
    }

    public int getIsLoadEmployee() {
        return isLoadEmployee;
    }

    public void setIsLoadEmployee(int isLoadEmployee) {
        this.isLoadEmployee = isLoadEmployee;
    }

    public int getIsLoadLicence() {
        return isLoadLicence;
    }

    public void setIsLoadLicence(int isLoadLicence) {
        this.isLoadLicence = isLoadLicence;
    }

    public int getIsLoadPicture() {
        return isLoadPicture;
    }

    public void setIsLoadPicture(int isLoadPicture) {
        this.isLoadPicture = isLoadPicture;
    }

    public List<String> getBuyerCodeList() {
        return buyerCodeList;
    }

    public void setBuyerCodeList(List<String> buyerCodeList) {
        this.buyerCodeList = buyerCodeList;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public List<String> getBuyerIdList() {
        return buyerIdList;
    }

    public void setBuyerIdList(List<String> buyerIdList) {
        this.buyerIdList = buyerIdList;
    }

    public String getBuyerAddr() {
        return buyerAddr;
    }

    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    public String getBusiTel() {
        return busiTel;
    }

    public void setBusiTel(String busiTel) {
        this.busiTel = busiTel;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }
}
