package com.msk.mq.listener;

import com.alibaba.fastjson.JSON;
import com.msk.mq.subject.AmqpSubject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mao_yejun on 2016/6/29.
 */
@Configuration
public class MessageListener implements ChannelAwareMessageListener {
    private static Logger logger = LoggerFactory.getLogger(MessageListener.class);
    @Autowired
    private AmqpSubject amqpSubject;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //队列名
        String queueName = message.getMessageProperties().getConsumerQueue();
        String content = new String(message.getBody(),"UTF-8");
        logger.info("收到队列"+queueName+"消息："+content);
        amqpSubject.getContent(JSON.parse(content), queueName);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费

    }
}
