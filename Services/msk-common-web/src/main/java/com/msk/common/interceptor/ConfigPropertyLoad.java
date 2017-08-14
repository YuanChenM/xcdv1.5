package com.msk.common.interceptor;

import java.util.Map;
import java.util.Properties;

import com.msk.sso.client.constant.SSORelevanceDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.plug.spring.interceptor.IPropertiesLoad;
import com.msk.common.bean.ConfigParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;

/**
 * *ConfigPropertyLoad
 * *@author jiang_nan
 * *@version 1.0
 */
public class ConfigPropertyLoad implements IPropertiesLoad {
    private static Logger logger = LoggerFactory.getLogger(ConfigPropertyLoad.class);
    /** Config Server Property Model Name */
    private static String MODEL_NAME_KEY = "MODEL_NAME";
    /** Config Server Property Environment */
    private static String ENVIRONMENT_KEY = "ENVIRONMENT";
    /** Config Server Url */
    private static String CONFIG_SERVER_URL_KEY = "CONFIG.SERVER.URL";
    /** Config Load Properties */
    private static String CONFIG_LOAD_PROPERTIES_KEY = "CONFIG.LOAD.PROPERTIES";
    /**Config Load Config Constant*/
    private static String CONFIG_LOAD_CONFIG_CONSTANT_KEY = "CONFIG.LOAD.CONFIG.CONSTANT";
    /**加载Code Master模块数据URL*/
    private static String CONFIG_LOAD_CODE_MASTER_KEY = "CONFIG.LOAD.CODE.MASTER";
    private static String SERVER_NAME= "ServerName";
    @Override
    public void loadProperties(Properties properties) {
        // 模块名称
        String modelName = properties.getProperty(MODEL_NAME_KEY);
        if (modelName == null) {
            return;
        }
        // 环境
        String environment = properties.getProperty(ENVIRONMENT_KEY);
        // 请求URL
        String configUrl = properties.getProperty(CONFIG_SERVER_URL_KEY) + properties.getProperty(CONFIG_LOAD_PROPERTIES_KEY);

        RsRequest<ConfigParam> requestParam = new RsRequest<>();
        requestParam.setAuth(modelName);
        requestParam.setLoginId(modelName);
        requestParam.setSiteCode("1");
        ConfigParam param = new ConfigParam();
        param.setModelName(modelName);
        param.setEnvironment(environment);
        param.setType("Properties");
        requestParam.setParam(param);
        RsResponse<String> response = RestClientUtil.post(configUrl, requestParam,
            new TypeReference<RsResponse<String>>() {});
        String result = response.getResult();
        Map<String, String> resultMap = JSONArray.parseObject(result, Map.class);
        if (resultMap != null) {
            for (Map.Entry<String, String> m : resultMap.entrySet()) {
                logger.info(m.getKey() + ":" + m.getValue());
                if (properties.getProperty(m.getKey()) == null) {
                    properties.setProperty(m.getKey(), m.getValue());
                }
            }
        }

        SystemServerManager.setEnvironment(environment);
        String configServerUrl = properties.getProperty(CONFIG_SERVER_URL_KEY);
        SystemServerManager.setConfigServer(configServerUrl);
        String configLoadConfigConstant = properties.getProperty(CONFIG_LOAD_CONFIG_CONSTANT_KEY);
        SystemServerManager.setConfigLoadConfigConstant(configLoadConfigConstant);
        String configLoadCodeMaster = properties.getProperty(CONFIG_LOAD_CODE_MASTER_KEY);
        SystemServerManager.setConfigLoadCodeMaster(configLoadCodeMaster);
        SystemServerManager.setModelName(modelName);

        String serverName = properties.getProperty(SERVER_NAME);

        SSORelevanceDefine.setServerName(serverName);


    }
}
