package com.msk.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Resource
    private Environment environment;
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        String url = environment.getProperty("spring.datasource.url");
        dataSource.setUrl(url);
        String userName = environment.getProperty("spring.datasource.username");
        dataSource.setUsername(userName);//用户名
        String password = environment.getProperty("spring.datasource.password");
        dataSource.setPassword(password);//密码
        String initialSize = environment.getProperty("spring.datasource.initial-size");
        dataSource.setInitialSize(Integer.parseInt(initialSize));
        String maxActive = environment.getProperty("spring.datasource.max-active");
        dataSource.setMaxActive(Integer.parseInt(maxActive));
        String minIdle = environment.getProperty("spring.datasource.max-idle");
        dataSource.setMinIdle(Integer.parseInt(minIdle));
        String maxWait = environment.getProperty("spring.datasource.max-wait");
        dataSource.setMaxWait(Integer.parseInt(maxWait));
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }
}
