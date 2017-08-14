/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表md_city对应的MdCity。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class MdCity extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** CITY_ID */
    private java.lang.Long cityId;
    /** PROVINCE_ID */
    private java.lang.Long provinceId;
    /** LGCS_AREA_ID */
    private java.lang.Long lgcsAreaId;
    /** CITY_CODE */
    private java.lang.String cityCode;
    /** CITY_NAME */
    private java.lang.String cityName;
    /** 划分级别（大区1，省和物流区2，地区和城市3，区4） */
    private java.math.BigDecimal divisionLevel;
    /** SPELL */
    private java.lang.String spell;
    /** 拼音首字母缩写 */
    private java.lang.String shortSpell;
    /** 省+地区的编码（5位） */
    private java.lang.String shortCodeP;
    /** 省+地区的名称 */
    private java.lang.String shortNameP;
    /** 物流区+地区的编码（5位） */
    private java.lang.String shortCodeL;
    /** 物流区+地区的名称 */
    private java.lang.String shortNameL;
    /** 大区+省+地区的编码（7位） */
    private java.lang.String fullCodeP;
    /** 大区+省+地区的名称 */
    private java.lang.String fullNameP;
    /** 大区+物流区+地区的编码（7位） */
    private java.lang.String fullCodeL;
    /** 大区+物流区+地区的名称 */
    private java.lang.String fullNameL;
    /** 开放服务城市  0未开通 1开通 */
    private java.lang.String openServiceFlg;
    /**
     * <p>默认构造函数。</p>
     */
    public MdCity() {

    }

    /**
     * <p>CITY_ID。</p>
     *
     * @return the CITY_ID
     */
    public java.lang.Long getCityId() {
        return cityId;
    }

    /**
     * <p>CITY_ID。</p>
     *
     * @param cityId CITY_ID。
     */
    public void setCityId(java.lang.Long cityId) {
        this.cityId = cityId;
    }

    /**
     * <p>PROVINCE_ID。</p>
     *
     * @return the PROVINCE_ID
     */
    public java.lang.Long getProvinceId() {
        return provinceId;
    }

    /**
     * <p>PROVINCE_ID。</p>
     *
     * @param provinceId PROVINCE_ID。
     */
    public void setProvinceId(java.lang.Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * <p>LGCS_AREA_ID。</p>
     *
     * @return the LGCS_AREA_ID
     */
    public java.lang.Long getLgcsAreaId() {
        return lgcsAreaId;
    }

    /**
     * <p>LGCS_AREA_ID。</p>
     *
     * @param lgcsAreaId LGCS_AREA_ID。
     */
    public void setLgcsAreaId(java.lang.Long lgcsAreaId) {
        this.lgcsAreaId = lgcsAreaId;
    }

    /**
     * <p>CITY_CODE。</p>
     *
     * @return the CITY_CODE
     */
    public java.lang.String getCityCode() {
        return cityCode;
    }

    /**
     * <p>CITY_CODE。</p>
     *
     * @param cityCode CITY_CODE。
     */
    public void setCityCode(java.lang.String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>CITY_NAME。</p>
     *
     * @return the CITY_NAME
     */
    public java.lang.String getCityName() {
        return cityName;
    }

    /**
     * <p>CITY_NAME。</p>
     *
     * @param cityName CITY_NAME。
     */
    public void setCityName(java.lang.String cityName) {
        this.cityName = cityName;
    }

    /**
     * <p>划分级别（大区1，省和物流区2，地区和城市3，区4）。</p>
     *
     * @return the 划分级别（大区1，省和物流区2，地区和城市3，区4）
     */
    public java.math.BigDecimal getDivisionLevel() {
        return divisionLevel;
    }

    /**
     * <p>划分级别（大区1，省和物流区2，地区和城市3，区4）。</p>
     *
     * @param divisionLevel 划分级别（大区1，省和物流区2，地区和城市3，区4）。
     */
    public void setDivisionLevel(java.math.BigDecimal divisionLevel) {
        this.divisionLevel = divisionLevel;
    }

    /**
     * <p>SPELL。</p>
     *
     * @return the SPELL
     */
    public java.lang.String getSpell() {
        return spell;
    }

    /**
     * <p>SPELL。</p>
     *
     * @param spell SPELL。
     */
    public void setSpell(java.lang.String spell) {
        this.spell = spell;
    }

    /**
     * <p>拼音首字母缩写。</p>
     *
     * @return the 拼音首字母缩写
     */
    public java.lang.String getShortSpell() {
        return shortSpell;
    }

    /**
     * <p>拼音首字母缩写。</p>
     *
     * @param shortSpell 拼音首字母缩写。
     */
    public void setShortSpell(java.lang.String shortSpell) {
        this.shortSpell = shortSpell;
    }

    /**
     * <p>省+地区的编码（5位）。</p>
     *
     * @return the 省+地区的编码（5位）
     */
    public java.lang.String getShortCodeP() {
        return shortCodeP;
    }

    /**
     * <p>省+地区的编码（5位）。</p>
     *
     * @param shortCodeP 省+地区的编码（5位）。
     */
    public void setShortCodeP(java.lang.String shortCodeP) {
        this.shortCodeP = shortCodeP;
    }

    /**
     * <p>省+地区的名称。</p>
     *
     * @return the 省+地区的名称
     */
    public java.lang.String getShortNameP() {
        return shortNameP;
    }

    /**
     * <p>省+地区的名称。</p>
     *
     * @param shortNameP 省+地区的名称。
     */
    public void setShortNameP(java.lang.String shortNameP) {
        this.shortNameP = shortNameP;
    }

    /**
     * <p>物流区+地区的编码（5位）。</p>
     *
     * @return the 物流区+地区的编码（5位）
     */
    public java.lang.String getShortCodeL() {
        return shortCodeL;
    }

    /**
     * <p>物流区+地区的编码（5位）。</p>
     *
     * @param shortCodeL 物流区+地区的编码（5位）。
     */
    public void setShortCodeL(java.lang.String shortCodeL) {
        this.shortCodeL = shortCodeL;
    }

    /**
     * <p>物流区+地区的名称。</p>
     *
     * @return the 物流区+地区的名称
     */
    public java.lang.String getShortNameL() {
        return shortNameL;
    }

    /**
     * <p>物流区+地区的名称。</p>
     *
     * @param shortNameL 物流区+地区的名称。
     */
    public void setShortNameL(java.lang.String shortNameL) {
        this.shortNameL = shortNameL;
    }

    /**
     * <p>大区+省+地区的编码（7位）。</p>
     *
     * @return the 大区+省+地区的编码（7位）
     */
    public java.lang.String getFullCodeP() {
        return fullCodeP;
    }

    /**
     * <p>大区+省+地区的编码（7位）。</p>
     *
     * @param fullCodeP 大区+省+地区的编码（7位）。
     */
    public void setFullCodeP(java.lang.String fullCodeP) {
        this.fullCodeP = fullCodeP;
    }

    /**
     * <p>大区+省+地区的名称。</p>
     *
     * @return the 大区+省+地区的名称
     */
    public java.lang.String getFullNameP() {
        return fullNameP;
    }

    /**
     * <p>大区+省+地区的名称。</p>
     *
     * @param fullNameP 大区+省+地区的名称。
     */
    public void setFullNameP(java.lang.String fullNameP) {
        this.fullNameP = fullNameP;
    }

    /**
     * <p>大区+物流区+地区的编码（7位）。</p>
     *
     * @return the 大区+物流区+地区的编码（7位）
     */
    public java.lang.String getFullCodeL() {
        return fullCodeL;
    }

    /**
     * <p>大区+物流区+地区的编码（7位）。</p>
     *
     * @param fullCodeL 大区+物流区+地区的编码（7位）。
     */
    public void setFullCodeL(java.lang.String fullCodeL) {
        this.fullCodeL = fullCodeL;
    }

    /**
     * <p>大区+物流区+地区的名称。</p>
     *
     * @return the 大区+物流区+地区的名称
     */
    public java.lang.String getFullNameL() {
        return fullNameL;
    }

    /**
     * <p>大区+物流区+地区的名称。</p>
     *
     * @param fullNameL 大区+物流区+地区的名称。
     */
    public void setFullNameL(java.lang.String fullNameL) {
        this.fullNameL = fullNameL;
    }

    /**
     * <p>开放服务城市  0未开通 1开通。</p>
     *
     * @return the 开放服务城市  0未开通 1开通
     */
    public java.lang.String getOpenServiceFlg() {
        return openServiceFlg;
    }

    /**
     * <p>开放服务城市  0未开通 1开通。</p>
     *
     * @param openServiceFlg 开放服务城市  0未开通 1开通。
     */
    public void setOpenServiceFlg(java.lang.String openServiceFlg) {
        this.openServiceFlg = openServiceFlg;
    }

}
