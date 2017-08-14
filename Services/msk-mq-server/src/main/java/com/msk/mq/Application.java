package com.msk.mq;

import com.msk.mq.config.AmqpConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by mao_yejun on 2016/6/29.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.msk.mq.config","com.msk.mq"})
@EnableConfigurationProperties(AmqpConfiguration.class)
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
