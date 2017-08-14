package com.msk.config.bean;

import java.io.Serializable;

/**
 * Config Param
 */
public class ConfigParam implements Serializable {
    /**
     * 模块名称
     */
    private String modelName;
    /**
     * 环境定义
     */
    private String environment;
    /**
     * 数据类型
     */
    private String type;
    /**
     * key
     */
    private String key;

    /**
     * system menu 参数
     */
    private String all;

    /**
     * 系统代码跟节点
     */

    private String systemRootCode;

    /**
     * 系统代码
     */
    private String systemCode;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getSystemRootCode() {
        return systemRootCode;
    }

    public void setSystemRootCode(String systemRootCode) {
        this.systemRootCode = systemRootCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获得modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * 设置modelName
     *
     * @param modelName modelName
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * 获得environment
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * 设置environment
     *
     * @param environment environment
     */
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
