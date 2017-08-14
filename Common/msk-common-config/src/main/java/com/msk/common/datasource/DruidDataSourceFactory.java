package com.msk.common.datasource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class DruidDataSourceFactory extends UnpooledDataSourceFactory {

    private static DruidDataSource druidDataSource;

    public DruidDataSourceFactory() {
        this.dataSource = druidDataSource;
    }

    public static void setDruidDataSource(DruidDataSource druidDataSource) {
        DruidDataSourceFactory.druidDataSource = druidDataSource;
    }
}
