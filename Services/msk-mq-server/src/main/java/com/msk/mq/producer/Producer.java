package com.msk.mq.producer;

import com.msk.mq.config.AmqpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mao_yejun on 2016/6/28.
 */
@Component
public class Producer {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);
    @Autowired
    private AmqpConfiguration amqpConfiguration;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;


    /**
     * 向指定队列发送消息
     * @param content
     * @param queueName
     */
    public void sendContent(Object content, String queueName) {

        rabbitMessagingTemplate.convertAndSend(amqpConfiguration.getExchange(),queueName,content);

    }

}
