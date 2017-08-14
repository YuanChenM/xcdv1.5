package com.msk.comm.interceptor;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.interceptor.IPropertiesLoad;
import com.msk.common.config.server.SystemServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;





/**
 * Created by jackjiang on 16/7/5.
 */
public class ConfigPropertiesLoad implements IPropertiesLoad{
    private static Logger logger = LoggerFactory.getLogger(ConfigPropertiesLoad.class);
    private static Map<String,String> DATA_SOURCE_NAME_MAP = new HashMap<>();
    private static String DATA_SOURCE_PACKAGE_KEY = "dataSourcePackage";
    private static String ENVIRONMENT_KEY = "ENVIRONMENT";
    private static String CONFIG_SERVER_URL_KEY = "CONFIG.SERVER.URL";
    private static String CONFIG_LOAD_PROPERTIES_KEY = "CONFIG.LOAD.PROPERTIES";
    private static String CONFIG_LOAD_PROPERTIES_URL = null;
    /**Config Load Config Constant*/
    private static String CONFIG_LOAD_CONFIG_CONSTANT_KEY = "CONFIG.LOAD.CONFIG.CONSTANT";
    private static String CONFIG_LOAD_CODE_MASTER_KEY = "CONFIG.LOAD.CODE.MASTER";
    private static String ENVIRONMENT = null;
    @Override
    public void loadProperties(Properties properties) {
        String dataSourcePackage = properties.getProperty(DATA_SOURCE_PACKAGE_KEY);
        this.initDataSourceMap(dataSourcePackage);
        ENVIRONMENT = properties.getProperty(ENVIRONMENT_KEY);
        String configServerUrl = properties.getProperty(CONFIG_SERVER_URL_KEY);
        String configLoadProperties = properties.getProperty(CONFIG_LOAD_PROPERTIES_KEY);
        CONFIG_LOAD_PROPERTIES_URL = configServerUrl + configLoadProperties;





        SystemServerManager.setEnvironment(ENVIRONMENT);
        SystemServerManager.setConfigServer(configServerUrl);
        String configLoadConfigConstant = properties.getProperty(CONFIG_LOAD_CONFIG_CONSTANT_KEY);
        SystemServerManager.setConfigLoadConfigConstant(configLoadConfigConstant);
        String configLoadCodeMaster = properties.getProperty(CONFIG_LOAD_CODE_MASTER_KEY);
        SystemServerManager.setConfigLoadCodeMaster(configLoadCodeMaster);

    }
    public static String getConfigLoadPropertiesUrl(){
        return CONFIG_LOAD_PROPERTIES_URL;
    }
    public static String getEnvironment(){
        return ENVIRONMENT;
    }
    public static Map<String,String> getDataSourceMap(){
        return DATA_SOURCE_NAME_MAP;
    }

    private void initDataSourceMap(String dataSourcePackage){
        logger.info("Data Source Package:"+dataSourcePackage);
        if(StringUtil.isEmpty(dataSourcePackage)){
            return;
        }
        String [] dataSourceNameArray = dataSourcePackage.split("\\|");
        for (String dataSourceName: dataSourceNameArray) {
            logger.info("Data Source Name:"+dataSourceName);
            String[] packageToDataSource = dataSourceName.replaceAll("\\[","").replaceAll("]","").split(":");
            if(packageToDataSource.length<2){
                continue;
            }
            String packageName = packageToDataSource [NumberConst.IntDef.INT_ZERO];
            String configServerProperties = packageToDataSource [NumberConst.IntDef.INT_ONE];
            DATA_SOURCE_NAME_MAP.put(packageName,configServerProperties);
        }
    }

}
