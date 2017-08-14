/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_receive_book对应的SlHouseReceiveBook。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseReceiveBook extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** SL_RECBOOK_ID */
    private Long slRecbookId;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 收货人 */
    private String buyerName;
    /** 联系电话 */
    private String telNum;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 省编码 */
    private String provinceCode;
    /** 地区编码 */
    private String cityCode;
    /** 区编码 */
    private String districtCode;
    /** 详细地址 */
    private String address;
    /**显示用收货地址**/
    private String fullAddress;
    /**
     * <p>默认构造函数。</p>
     */
    public SlHouseReceiveBook() {

    }

    /**
     * <p>SL_RECBOOK_ID。</p>
     *
     * @return the SL_RECBOOK_ID
     */
    public Long getSlRecbookId() {
        return slRecbookId;
    }

    /**
     * <p>SL_RECBOOK_ID。</p>
     *
     * @param slRecbookId SL_RECBOOK_ID。
     */
    public void setSlRecbookId(Long slRecbookId) {
        this.slRecbookId = slRecbookId;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCode 区划(6)+顺序码(4)。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>收货人。</p>
     *
     * @return the 收货人
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * <p>收货人。</p>
     *
     * @param buyerName 收货人。
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * <p>联系电话。</p>
     *
     * @return the 联系电话
     */
    public String getTelNum() {
        return telNum;
    }

    /**
     * <p>联系电话。</p>
     *
     * @param telNum 联系电话。
     */
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param lgcsAreaCode 物流区编码。
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @return the 省编码
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @param provinceCode 省编码。
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @return the 地区编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @param cityCode 地区编码。
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @return the 区编码
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @param districtCode 区编码。
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>详细地址。</p>
     *
     * @return the 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * <p>详细地址。</p>
     *
     * @param address 详细地址。
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
