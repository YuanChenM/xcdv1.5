package com.msk;

import com.msk.common.data.jpa.JpaConfig;
import com.msk.common.data.redis.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.msk.common.config.DataSourceConfig;
import com.msk.common.config.ResultParamConfig;
import com.msk.common.config.SwaggerConfig;

@Configuration
@Import({
        DispatcherServletAutoConfiguration.class,
        EmbeddedServletContainerAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        JacksonAutoConfiguration.class,
        MultipartAutoConfiguration.class,
        PersistenceExceptionTranslationAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class,
        RedisAutoConfiguration.class,
        ServerPropertiesAutoConfiguration.class,
        WebMvcAutoConfiguration.class,
        WebSocketAutoConfiguration.class,
        //HibernateJpaAutoConfiguration.class,
        //AbstractTransactionManagementConfiguration.class,
        //ProxyTransactionManagementConfiguration.class,
        //TransactionManagementConfigurer.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        DataSourceConfig.class,
        JpaConfig.class,
        RedisConfig.class,
        SwaggerConfig.class,
        ResultParamConfig.class
})
@ComponentScan(basePackages={
        "com.msk.common.properties",
        "com.msk.common.aop",
        "com.msk.common.data.jpa.impl",
        "com.msk.common.data.redis",
        "com.msk.order"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    }


}
