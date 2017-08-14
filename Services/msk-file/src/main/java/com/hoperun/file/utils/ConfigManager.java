package com.hoperun.file.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ConfigManager
 *
 * @author jiang_nan
 * @version 1.0
 **/
@Component("configManager")
public class ConfigManager {
    private static ConfigInfo config;
    @Autowired
    public void setConfigInfo(final ConfigInfo config) {
        ConfigManager.config = config;
    }
    /**
     * 获得系统版本
     *
     * @return 系统版本
     */
    public static String getTest() {
        return config.getTest();
    }

    public static String getFileServerPath(){ return config.getFileServerPath(); }
}
