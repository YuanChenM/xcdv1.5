package com.msk.mq.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.msk.mq.bean.ConfigParam;
import com.msk.mq.bean.RsRequest;
import com.msk.mq.bean.RsResponse;
import com.msk.mq.util.RestClientUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/**
 * Created by mao_yejun on 2016/7/4.
 */
@Configuration
public class ProducerConfig {
    private static String AMQP_ADDRESS = "amqp.address";
    private static String AMQP_USER = "amqp.user";
    private static String AMQP_PWD = "amqp.pwd";
    private static String AMQP_EXCHANGE = "amqp.exchange";
    @Resource
    private Environment environment;
    @Autowired
    private AmqpConfiguration amqpConfiguration;

    @Bean
    RabbitAdmin rabbitAdmin(AmqpConfiguration amqpConfiguration) {
        return new RabbitAdmin(connectionFactory(amqpConfiguration));
    }


    /**
     * 创建连接
     *
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory(AmqpConfiguration amqpConfiguration) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        //地址
        cachingConnectionFactory.setAddresses(amqpConfiguration.getAddress());
        //用户名
        cachingConnectionFactory.setUsername(amqpConfiguration.getUser());
        //密码
        cachingConnectionFactory.setPassword(amqpConfiguration.getPwd());

        //必须设置
        cachingConnectionFactory.setPublisherConfirms(true);

        return cachingConnectionFactory;
    }


    /**
     * 定义交换机
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean
    public DirectExchange exchange(RabbitAdmin rabbitAdmin, AmqpConfiguration amqpConfiguration) {
        DirectExchange directExchange = new DirectExchange(amqpConfiguration.getExchange());
        rabbitAdmin.declareExchange(directExchange);
        return directExchange;
    }


    @Bean
    public Binding bindingExchangeToQueues(DirectExchange exchange, RabbitAdmin rabbitAdmin, AmqpConfiguration amqpConfiguration) {
        for (String queueName : amqpConfiguration.getQueues()) {
            Queue queue = new Queue(queueName, true);
            Binding binding = BindingBuilder.bind(queue).to(exchange).with(queue.getName());
            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareBinding(binding);

        }
        return null;
    }

    /**
     * 生产者用
     *
     * @return
     */
    @Bean
    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(jackson2Converter());
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    @Bean
    public EnvironmentConfig environmentConfig() {
        EnvironmentConfig environmentConfig = new EnvironmentConfig();
        String modelName = environment.getProperty("spring.configServer.modelName");
        String configServerUrl = environment.getProperty("spring.configServer.configServerUrl");
        String configLoadProperties = environment.getProperty("spring.configServer.configLoadProperties");
        String en = environment.getProperty("spring.configServer.environment");
        environmentConfig.setEnvironment(en);
        environmentConfig.setConfigLoadProperties(configLoadProperties);
        environmentConfig.setModelName(modelName);
        environmentConfig.setConfigServerUrl(configServerUrl);
        return environmentConfig;
    }

    @Bean
    public AmqpConfiguration amqpConfiguration(EnvironmentConfig environmentConfig) {
        Properties properties = new Properties();
        String url = environmentConfig.getConfigServerUrl();
        RsRequest<ConfigParam> requestParam = new RsRequest<>();
        requestParam.setAuth(environmentConfig.getModelName());
        requestParam.setLoginId(environmentConfig.getModelName());
        requestParam.setSiteCode("1");
        ConfigParam param = new ConfigParam();
        param.setModelName(environmentConfig.getModelName());
        param.setEnvironment(environmentConfig.getEnvironment());
        param.setType("Properties");
        requestParam.setParam(param);
        RsResponse<String> response = RestClientUtil.post(url, requestParam,
                new TypeReference<RsResponse<String>>() {
                });
        String result = response.getResult();
        Map<String, String> resultMap = JSONArray.parseObject(result, Map.class);
        if (resultMap != null) {
            for (Map.Entry<String, String> m : resultMap.entrySet()) {

                if (properties.getProperty(m.getKey()) == null) {
                    properties.setProperty(m.getKey(), m.getValue());
                }
            }
        }
        amqpConfiguration.setAddress(properties.getProperty(AMQP_ADDRESS));
        amqpConfiguration.setPwd(properties.getProperty(AMQP_PWD));
        amqpConfiguration.setUser(properties.getProperty(AMQP_USER));
        amqpConfiguration.setExchange(properties.getProperty(AMQP_EXCHANGE));

        return amqpConfiguration;
    }
}

