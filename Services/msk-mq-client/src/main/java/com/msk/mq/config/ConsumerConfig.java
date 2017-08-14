package com.msk.mq.config;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.msk.mq.bean.ConfigParam;
import com.msk.mq.bean.RsRequest;
import com.msk.mq.bean.RsResponse;
import com.msk.mq.until.RestClientUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Properties;

/**
 * Created by mao_yejun on 2016/6/29.
 */
@Configuration
public class ConsumerConfig {
    private static String AMQP_ADDRESS = "amqp.address";
    private static String AMQP_USER = "amqp.user";
    private static String AMQP_PWD = "amqp.pwd";
    private static String AMQP_EXCHANGE = "amqp.exchange";
    private static String AMQP_CONSUMERS = "amqp.currentConsumers";
    private static String AMQP_MAX_CONSUMERS = "amqp.maxConsumers";

    @Autowired
    private AmqpConfiguration amqpConfiguration;
    @Autowired
    private com.msk.mq.listener.MessageListener messageListener;
    @Resource
    private Environment environment;
    @Bean
    public ConnectionFactory connectionFactory(AmqpConfiguration amqpConfiguration) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        //地址
        cachingConnectionFactory.setAddresses(amqpConfiguration.getAddress());
        //用户名
        cachingConnectionFactory.setUsername(amqpConfiguration.getUser());
        //密码
        cachingConnectionFactory.setPassword(amqpConfiguration.getPwd());
        //返回确认
        cachingConnectionFactory.setPublisherConfirms(true);

        return cachingConnectionFactory;
    }


    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(AmqpConfiguration amqpConfiguration) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory(amqpConfiguration));
        for (String queueName : amqpConfiguration.getQueues()) {
            Queue queue = new Queue(queueName, true);
            container.addQueues(queue);
        }
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(amqpConfiguration.getMaxConcurrentConsumers());
        container.setConcurrentConsumers(amqpConfiguration.getConcurrentConsumers());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(messageListener);
        return container;
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
        amqpConfiguration.setConcurrentConsumers(Integer.valueOf(properties.getProperty(AMQP_CONSUMERS)));
        amqpConfiguration.setMaxConcurrentConsumers(Integer.valueOf(properties.getProperty(AMQP_MAX_CONSUMERS)));
        return amqpConfiguration;
    }
}
