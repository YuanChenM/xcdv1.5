package com.msk.common.config;

import com.msk.common.datasource.DruidDataSource;
import com.msk.common.properties.ConfigServerPropertyLoad;
import com.msk.common.properties.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name="slaveDataSource")
    public DataSource mybatisDataSource(){
        DataSourceProperties dataSourceProperties = ConfigServerPropertyLoad.getSlaveDataSourceProperties();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromDataSourceProperties(dataSourceProperties);
        return dataSource;
    }

    @Bean
    public DataSource dataSource(){
        DataSourceProperties dataSourceProperties = ConfigServerPropertyLoad.getMasterDataSourceProperties();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromDataSourceProperties(dataSourceProperties);
        return dataSource;
    }
}
