/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_setting对应的BrSetting</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrSetting extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** 1表示按排行,0表示按百分比 */
    private String settingType;
    /** SETTING_NAME */
    private String settingName;
    /** 按排行排时录入 */
    private String settingStartValue;
    /** 按排行排时录入 */
    private String settingEndValue;
    /** 按百分比排时录入 */
    private String settingValue;
    /**
     * <p>默认构造函数</p>
     */
    public BrSetting() {

    }

    /**
     * <p>ID</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>1表示按排行,0表示按百分比</p>
     *
     * @return the 1表示按排行,0表示按百分比
     */
    public String getSettingType() {
        return settingType;
    }

    /**
     * <p>1表示按排行,0表示按百分比</p>
     *
     * @param settingType 1表示按排行,0表示按百分比
     */
    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    /**
     * <p>SETTING_NAME</p>
     *
     * @return the SETTING_NAME
     */
    public String getSettingName() {
        return settingName;
    }

    /**
     * <p>SETTING_NAME</p>
     *
     * @param settingName SETTING_NAME
     */
    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    /**
     * <p>按排行排时录入</p>
     *
     * @return the 按排行排时录入
     */
    public String getSettingStartValue() {
        return settingStartValue;
    }

    /**
     * <p>按排行排时录入</p>
     *
     * @param settingStartValue 按排行排时录入
     */
    public void setSettingStartValue(String settingStartValue) {
        this.settingStartValue = settingStartValue;
    }

    /**
     * <p>按排行排时录入</p>
     *
     * @return the 按排行排时录入
     */
    public String getSettingEndValue() {
        return settingEndValue;
    }

    /**
     * <p>按排行排时录入</p>
     *
     * @param settingEndValue 按排行排时录入
     */
    public void setSettingEndValue(String settingEndValue) {
        this.settingEndValue = settingEndValue;
    }

    /**
     * <p>按百分比排时录入</p>
     *
     * @return the 按百分比排时录入
     */
    public String getSettingValue() {
        return settingValue;
    }

    /**
     * <p>按百分比排时录入</p>
     *
     * @param settingValue 按百分比排时录入
     */
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

}
