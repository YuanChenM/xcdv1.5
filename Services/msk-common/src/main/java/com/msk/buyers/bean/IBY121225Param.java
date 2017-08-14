package com.msk.buyers.bean;

import com.msk.bs.bean.IBS2101107Bean;
import com.msk.common.bean.RsPageParam;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/6/20.
 */
public class IBY121225Param extends RsPageParam {

  private List<IBS2101107Bean> buyerList;
    private String salesTargetType;//销售对象类型
    private String classCode;//产品类别编码
    private String  buyerCode;//买家编码
    private String buyerName; // 买家名称
    private String lgcsAreaCode;// 物流区编码
    private String buyerAddr; // 买家地址
    private String busiTel;// 买家联系电话;
    private String provinceCode;// 省编码
    private String cityCode;// 地区编码
    private String districtCode;//区编码
    private String employeeName;// 老板姓名
    private String buyerShop;// 店铺号
    private String buyerType;//买家类型
    private String marketId;// 菜场ID/批发市场ID
    private String buyerTypeName;//买家类型中文名
    private List<String> buyerIdList;// 防止buyerIdList过长，故新增该字段
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getSalesTargetType() {
        return salesTargetType;
    }

    public void setSalesTargetType(String salesTargetType) {
        this.salesTargetType = salesTargetType;
    }

  public List<IBS2101107Bean> getBuyerList() {
    return buyerList;
  }

  public String getBuyerTypeName() {
    return buyerTypeName;
  }

  public void setBuyerTypeName(String buyerTypeName) {
    this.buyerTypeName = buyerTypeName;
  }

  public void setBuyerList(List<IBS2101107Bean> buyerList) {
    this.buyerList = buyerList;
  }

  public String getBuyerCode() {
    return buyerCode;
  }

  public void setBuyerCode(String buyerCode) {
    this.buyerCode = buyerCode;
  }

  public String getBuyerName() {
    return buyerName;
  }

  public void setBuyerName(String buyerName) {
    this.buyerName = buyerName;
  }

  public String getLgcsAreaCode() {
    return lgcsAreaCode;
  }

  public void setLgcsAreaCode(String lgcsAreaCode) {
    this.lgcsAreaCode = lgcsAreaCode;
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

  public String getProvinceCode() {
    return provinceCode;
  }

  public void setProvinceCode(String provinceCode) {
    this.provinceCode = provinceCode;
  }

  public String getCityCode() {
    return cityCode;
  }

  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  public String getDistrictCode() {
    return districtCode;
  }

  public void setDistrictCode(String districtCode) {
    this.districtCode = districtCode;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getBuyerShop() {
    return buyerShop;
  }

  public void setBuyerShop(String buyerShop) {
    this.buyerShop = buyerShop;
  }

  public String getBuyerType() {
    return buyerType;
  }

  public void setBuyerType(String buyerType) {
    this.buyerType = buyerType;
  }

  public String getMarketId() {
    return marketId;
  }

  public void setMarketId(String marketId) {
    this.marketId = marketId;
  }

  public List<String> getBuyerIdList() {
    return buyerIdList;
  }

  public void setBuyerIdList(List<String> buyerIdList) {
    this.buyerIdList = buyerIdList;
  }
}
