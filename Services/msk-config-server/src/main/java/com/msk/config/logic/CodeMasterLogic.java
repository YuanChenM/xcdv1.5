package com.msk.config.logic;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by shi_yuxi on 2016/7/15.
 */
@Service
public class CodeMasterLogic {
    public DataSource dataSource(String url, String userName, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        //String url = environment.getProperty("spring.datasource.url");
        dataSource.setUrl(url);
        //String userName = environment.getProperty("spring.datasource.username");
        dataSource.setUsername(userName);//用户名
        //String password = environment.getProperty("spring.datasource.password");
        dataSource.setPassword(password);//密码
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }
}
