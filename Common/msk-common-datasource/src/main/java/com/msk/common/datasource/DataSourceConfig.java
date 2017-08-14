package com.msk.common.datasource;


import com.msk.common.datasource.properties.DataSourceProperties;
import com.msk.common.datasource.properties.DataSourcePropertiesLoad;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class DataSourceConfig {

    @Bean(name="slaveDataSource")
    public DataSource mybatisDataSource(){
        DataSourceProperties dataSourceProperties = DataSourcePropertiesLoad.getSlaveDataSourceProperties();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromDataSourceProperties(dataSourceProperties);
        return dataSource;
    }

    @Bean
    public DataSource dataSource(){
        DataSourceProperties dataSourceProperties = DataSourcePropertiesLoad.getMasterDataSourceProperties();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromDataSourceProperties(dataSourceProperties);
        return dataSource;
    }

}
