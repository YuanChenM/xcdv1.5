/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表md_area对应的MdArea。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class MdArea extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 大区ID */
    private Long areaId;
    /** 大区编码 */
    private String areaCode;
    /** 大区名称 */
    private String areaName;
    /** 划分级别（大区1，省和物流区2，地区和城市3，区4） */
    private Integer divisionLevel;
    /** 拼音 */
    private String spell;
    /** 拼音首字母缩写 */
    private String shortSpell;
    /**
     * <p>默认构造函数。</p>
     */
    public MdArea() {

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
     * <p>大区编码。</p>
     *
     * @return the 大区编码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * <p>大区编码。</p>
     *
     * @param areaCode 大区编码。
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * <p>大区名称。</p>
     *
     * @return the 大区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * <p>大区名称。</p>
     *
     * @param areaName 大区名称。
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * <p>划分级别（大区1，省和物流区2，地区和城市3，区4）。</p>
     *
     * @return the 划分级别（大区1，省和物流区2，地区和城市3，区4）
     */
    public Integer getDivisionLevel() {
        return divisionLevel;
    }

    /**
     * <p>划分级别（大区1，省和物流区2，地区和城市3，区4）。</p>
     *
     * @param divisionLevel 划分级别（大区1，省和物流区2，地区和城市3，区4）。
     */
    public void setDivisionLevel(Integer divisionLevel) {
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

}
