package com.msk.common.properties;

import java.util.Map;

import com.msk.common.utils.RestClientUtils;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.bean.param.ConfigParam;

/**
 * @author jiang_nan
 */
public class ConfigServerPropertyLoad {
    private static Environment environment;
    private static DataSourceProperties masterDataSourceProperties;
    private static DataSourceProperties slaveDataSourceProperties;
    public static DataSourceProperties getMasterDataSourceProperties() {
        return masterDataSourceProperties;
    }
    public static void setMasterDataSourceProperties(DataSourceProperties masterDataSourceProperties) {
        ConfigServerPropertyLoad.masterDataSourceProperties = masterDataSourceProperties;
    }
    public static DataSourceProperties getSlaveDataSourceProperties() {
        return slaveDataSourceProperties;
    }
    public static void setSlaveDataSourceProperties(DataSourceProperties slaveDataSourceProperties) {
        ConfigServerPropertyLoad.slaveDataSourceProperties = slaveDataSourceProperties;
    }
    public static void setEnvironment(Environment environment){
        ConfigServerPropertyLoad.environment = environment;
    }
    public static String getConfigServerEnvironment(){
        return environment.getProperty("config.server.environment");
    }
    public static String getConfigServerUrl(){
        return environment.getProperty("config.server.url");
    }
    public static String getConfigConstantUrl(){
        return getConfigServerUrl() + environment.getProperty("config.server.configConstantUrl");
    }
    public static String getBaseConfigConstantUrl(){
        return  environment.getProperty("config.server.configConstantUrl");
    }
    public static String getPropertiesUrl(){
        return getConfigServerUrl() + environment.getProperty("config.server.propertiesUrl");
    }
    public static String getModuleName(){
        return environment.getProperty("config.server.moduleName");
    }
    public static Map<String,String> getProperty(){
        String configUrl = getPropertiesUrl();
        RestRequest<ConfigParam> requestBody = new RestRequest<>();
        requestBody.setAuth(getModuleName());
        requestBody.setLoginId(getModuleName());
        requestBody.setParam(createPropertyParam());
        requestBody.setSiteCode("405");
        RestResponse<String> response = RestClientUtils.post(configUrl, requestBody, new TypeReference<RestResponse<String>>() {});
        String result = response.getResult();
        Map<String, String> resultMap = JSONArray.parseObject(result, Map.class);
        return resultMap;
    }
    public static ConfigParam createPropertyParam(){
        ConfigParam param = new ConfigParam();
        param.setModelName(getModuleName());
        param.setEnvironment(getConfigServerEnvironment());
        param.setType("Properties");
        return param;
    }









}
