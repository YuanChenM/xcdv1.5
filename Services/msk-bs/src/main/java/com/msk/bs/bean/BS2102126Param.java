package com.msk.bs.bean;

import com.hoperun.core.bean.BasePageParam;

/**
 * Created by whc on 2016/10/21.
 */
public class BS2102126Param extends BasePageParam {
    // 每页显示的条数
    private Integer pageCount;
    // 页数Y
    private Integer pageNo;
    // 物流区编码
    private String lgcsAreaCode;
    // 地区编码
    private String cityCode;
    // 区编码
    private String districtCode;
    // 买家地址
    private String buyerAddr;
    // 老板姓名
    private String bossName;
    // 联系电话
    private String busiTel;
    // 买家名称
    private String buyerName;
    // 店铺号
    private String storeNo;
    // 买家主码
    private String buyerCode;
    // 买家账号
    private String accountName;
    // 注册手机
    private String telNo;
    // 买家类型名称
    private String superiorName;
    // 营销状态名称
    private String marketingsStatusName;
    /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc start */
    // 营销状态Code
    private String marketingsStatus;
    /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc end */
    // 物流区名称
    private String lgcsAreaName;
    // 菜场名称/批发市场名称
    private String marketName;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getBuyerAddr() {
        return buyerAddr;
    }

    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getBusiTel() {
        return busiTel;
    }

    public void setBusiTel(String busiTel) {
        this.busiTel = busiTel;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getSuperiorName() {
        return superiorName;
    }

    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
    }

    public String getMarketingsStatusName() {
        return marketingsStatusName;
    }

    public void setMarketingsStatusName(String marketingsStatusName) {
        this.marketingsStatusName = marketingsStatusName;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }
}
