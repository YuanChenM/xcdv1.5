package com.msk.springboot;

import com.alibaba.druid.pool.DruidDataSource;
import com.msk.comm.aspect.ReturnExceptionAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

/**
 * Created by jackjiang on 16/6/20.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.msk.springboot.config","com.msk.config"})
public class Application {
    @Resource
    private Environment environment;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("1024MB");
        factory.setMaxRequestSize("1024KB");
        return factory.createMultipartConfig();
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        String url = environment.getProperty("spring.datasource.url");
        dataSource.setUrl(url);
        String userName = environment.getProperty("spring.datasource.username");
        dataSource.setUsername(userName);//用户名
        String password = environment.getProperty("spring.datasource.password");
        dataSource.setPassword(password);//密码
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
    @Bean
    public ReturnExceptionAspect getReturnExceptionAspect(){
        return new ReturnExceptionAspect();
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
