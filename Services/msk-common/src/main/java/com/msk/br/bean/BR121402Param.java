package com.msk.br.bean;

import com.hoperun.core.bean.BasePageParam;

/**
 * 配置管理画面param
 * <p/>
 * Created by zhao_chen on 2016/06/14.
 */
public class BR121402Param extends BasePageParam {
    /** ID */
    private Long id;
    //配置类型
    private String settingType;
    //配置名称
    private String settingName;
    //配置开始值
    private String settingStartValue;
    //配置结束值
    private String settingEndValue;
    //配置值
    private String settingValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingStartValue() {
        return settingStartValue;
    }

    public void setSettingStartValue(String settingStartValue) {
        this.settingStartValue = settingStartValue;
    }

    public String getSettingEndValue() {
        return settingEndValue;
    }

    public void setSettingEndValue(String settingEndValue) {
        this.settingEndValue = settingEndValue;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
}
