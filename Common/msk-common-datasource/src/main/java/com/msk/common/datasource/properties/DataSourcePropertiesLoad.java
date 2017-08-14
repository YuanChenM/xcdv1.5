package com.msk.common.datasource.properties;

import java.util.Map;

/**
 * Created by jackjiang on 16/8/24.
 */
public final class DataSourcePropertiesLoad {
    private static DataSourceProperties slaveDataSourceProperties;
    private static DataSourceProperties masterDataSourceProperties;
    private static String SLAVE_DATA_SOURCE_URL = "jdbcReadOnly.url";
    private static String SLAVE_DATA_SOURCE_USER_NAME = "slaveDataSource.username";
    private static String SLAVE_DATA_SOURCE_USER_PASSWORD = "slaveDataSource.userpwd";


    public final static void setSlaveDataSourceProperties(Map<String,String> propertiesMap) {
        String url = propertiesMap.get(SLAVE_DATA_SOURCE_URL);
        String userName = propertiesMap.get(SLAVE_DATA_SOURCE_USER_NAME);
        String password = propertiesMap.get(SLAVE_DATA_SOURCE_USER_PASSWORD);
        DataSourceProperties slaveDataSourceProperties = new DataSourceProperties();
        slaveDataSourceProperties.setUrl(url);
        slaveDataSourceProperties.setUserName(userName);
        slaveDataSourceProperties.setPassword(password);
        DataSourcePropertiesLoad.slaveDataSourceProperties = slaveDataSourceProperties;
    }

    public final static void setMasterDataSourceProperties(Map<String,Object> propertiesMap) {
        DataSourceProperties masterDataSourceProperties = new DataSourceProperties();

        DataSourcePropertiesLoad.masterDataSourceProperties = masterDataSourceProperties;
    }

    public static DataSourceProperties getSlaveDataSourceProperties(){
        return slaveDataSourceProperties;
    }
    public static DataSourceProperties getMasterDataSourceProperties(){
        return masterDataSourceProperties;
    }
}
