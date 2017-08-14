package com.msk.common.datasource;

import com.msk.common.properties.DataSourceProperties;

public class DruidDataSource extends com.alibaba.druid.pool.DruidDataSource{

    public void configFromDataSourceProperties(DataSourceProperties dataSourceProperties) {
        this.setUrl(dataSourceProperties.getUrl());
        this.setUsername(dataSourceProperties.getUserName());//用户名
        this.setPassword(dataSourceProperties.getPassword());//密码
        this.setInitialSize(dataSourceProperties.getInitialSize());
        this.setMaxActive(dataSourceProperties.getMaxActive());
        this.setMinIdle(dataSourceProperties.getMinIdle());
        this.setMaxWait(dataSourceProperties.getMaxWait());
        this.setValidationQuery(dataSourceProperties.getValidationQuery());
        this.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
        this.setTestWhileIdle(dataSourceProperties.isTestWhileIdle());
        this.setPoolPreparedStatements(dataSourceProperties.isPoolPreparedStatements());
    }
}
