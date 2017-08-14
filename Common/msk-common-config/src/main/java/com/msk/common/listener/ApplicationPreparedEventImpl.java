package com.msk.common.listener;

import com.msk.common.config.server.SystemServerManager;
import com.msk.common.properties.ConfigServerPropertyLoad;
import com.msk.common.properties.DataSourceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * Created by jackjiang on 16/8/13.
 */
public class ApplicationPreparedEventImpl implements IApplicationEvent<ApplicationPreparedEvent> {
    private static Logger logger = LoggerFactory.getLogger(ApplicationPreparedEventImpl.class);

    public void init(ApplicationPreparedEvent applicationEvent) {
        ConfigurableEnvironment environment = applicationEvent.getApplicationContext().getEnvironment();
        ConfigServerPropertyLoad.setEnvironment(environment);
        Map<String, String> configServerProperty = ConfigServerPropertyLoad.getProperty();
        ConfigServerPropertyLoad.setMasterDataSourceProperties(this.createMasterDataSourceProperties(configServerProperty));
        ConfigServerPropertyLoad.setSlaveDataSourceProperties(this.createSlaveDataSourceProperties(configServerProperty));
        logger.info("初始化DataSource Property完了");
        this.setSystemServerManagerProperties();
        logger.info("初始化SystemServerManager完了");
    }

    private DataSourceProperties createMasterDataSourceProperties(Map<String, String> configServerProperty) {
        String url = configServerProperty.get("jdbc.url");
        String userName = configServerProperty.get("jdbc.username");
        String password = configServerProperty.get("jdbc.userpwd");
        DataSourceProperties masterDataSourceProperties = new DataSourceProperties(url, userName, password);
        return masterDataSourceProperties;
    }

    private DataSourceProperties createSlaveDataSourceProperties(Map<String, String> configServerProperty) {
        String url = configServerProperty.get("jdbcReadOnly.url");
        String userName = configServerProperty.get("jdbc.username");
        String password = configServerProperty.get("jdbc.userpwd");
        DataSourceProperties slaveDataSourceProperties = new DataSourceProperties();
        slaveDataSourceProperties.setUrl(url);
        slaveDataSourceProperties.setUserName(userName);
        slaveDataSourceProperties.setPassword(password);
        return slaveDataSourceProperties;
    }

    private void setSystemServerManagerProperties() {
        SystemServerManager.setConfigServer(ConfigServerPropertyLoad.getConfigServerUrl());
        SystemServerManager.setEnvironment(ConfigServerPropertyLoad.getConfigServerEnvironment());
        SystemServerManager.setConfigLoadConfigConstant(ConfigServerPropertyLoad.getBaseConfigConstantUrl());
    }

}
