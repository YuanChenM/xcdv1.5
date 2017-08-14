package com.msk.comm.utils;

import com.msk.comm.bean.ConfigParam;
import com.msk.comm.bean.RestRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jackjiang on 16/8/5.
 */
public class ConfigInfo {
    private static ConfigInfo configInfo;
    private static Properties properties;
    private ConfigInfo() {

    }
    public static ConfigInfo newConfigInfo() {
        if (configInfo == null) {
            configInfo = new ConfigInfo();
            properties = new Properties();
            InputStream in = ConfigInfo.class.getResourceAsStream("/configInfo.properties");
            try {
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return configInfo;
    }

    public String getEnvironment(){
        return properties.getProperty("ENVIRONMENT");
    }
    public String getConfigServerUrl(){
        return properties.getProperty("CONFIG.SERVER.URL");
    }
    public String getConfigLoadConfigConstant(){
        return properties.getProperty("CONFIG.LOAD.CONFIG.CONSTANT");
    }
    public String getLoginModuleName(){
        return properties.getProperty("LOGIN_MODULE_NAME");
    }
    public String getLoginServerName(){
        return properties.getProperty("LOGIN_SERVER_NAME");
    }
    public String getConfigLoadCodeMaster(){
        return properties.getProperty("CODE.LOAD.CODE.MASTER");
    }
    public Map<String,Object> getConfigConstantServerUrl(ConfigParam param){
        ConfigInfo configInfo = ConfigInfo.newConfigInfo();
        param.setEnvironment(configInfo.getEnvironment());
        param.setType("ConfigConstant");
        RestRequest<ConfigParam> requestBody = new RestRequest<>();
        String configConstantUrl = configInfo.getConfigServerUrl() + configInfo.getConfigLoadConfigConstant();


        return null;
    }

}
