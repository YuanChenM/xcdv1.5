/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表md_province对应的MdProvince。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class MdProvince extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 省ID */
    private Long provinceId;
    /** 大区ID */
    private Long areaId;
    /** 省编码 */
    private String provinceCode;
    /** 省名称 */
    private String provinceName;
    /** 划分级别（大区1，省和物流区2，地区和城市3，区4） */
    private java.math.BigDecimal divisionLevel;
    /** 拼音 */
    private String spell;
    /** 拼音首字母缩写 */
    private String shortSpell;
    /** 大区+省的编码（4位） */
    private String fullCode;
    /** 大区+省的名称 */
    private String fullName;
    /**
     * <p>默认构造函数。</p>
     */
    public MdProvince() {

    }

    /**
     * <p>省ID。</p>
     *
     * @return the 省ID
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * <p>省ID。</p>
     *
     * @param provinceId 省ID。
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * <p>大区ID。</p>
     *
     * @return the 大区ID
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * <p>大区ID。</p>
     *
     * @param areaId 大区ID。
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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
     * <p>省名称。</p>
     *
     * @return the 省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * <p>省名称。</p>
     *
     * @param provinceName 省名称。
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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
     * <p>拼音。</p>
     *
     * @return the 拼音
     */
    public String getSpell() {
        return spell;
    }

    /**
     * <p>拼音。</p>
     *
     * @param spell 拼音。
     */
    public void setSpell(String spell) {
        this.spell = spell;
    }

    /**
     * <p>拼音首字母缩写。</p>
     *
     * @return the 拼音首字母缩写
     */
    public String getShortSpell() {
        return shortSpell;
    }

    /**
     * <p>拼音首字母缩写。</p>
     *
     * @param shortSpell 拼音首字母缩写。
     */
    public void setShortSpell(String shortSpell) {
        this.shortSpell = shortSpell;
    }

    /**
     * <p>大区+省的编码（4位）。</p>
     *
     * @return the 大区+省的编码（4位）
     */
    public String getFullCode() {
        return fullCode;
    }

    /**
     * <p>大区+省的编码（4位）。</p>
     *
     * @param fullCode 大区+省的编码（4位）。
     */
    public void setFullCode(String fullCode) {
        this.fullCode = fullCode;
    }

    /**
     * <p>大区+省的名称。</p>
     *
     * @return the 大区+省的名称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * <p>大区+省的名称。</p>
     *
     * @param fullName 大区+省的名称。
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
