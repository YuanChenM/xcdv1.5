package com.msk.mq.subject;



import com.alibaba.fastjson.JSON;
import com.msk.mq.bean.ObserverBean;

import com.msk.mq.dao.BaseRedisDao;
import com.msk.mq.logic.BaseLogic;
import com.msk.mq.until.RestClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by mao_yejun on 2016/6/30.
 */
@Configuration
public class AmqpSubject implements Subject {
    private static Logger logger = LoggerFactory.getLogger(AmqpSubject.class);
    @Autowired
     private BaseRedisDao baseRedisDao;
    @Autowired
    private BaseLogic baseLogic;

    @Override
    public void addObserver( ObserverBean observerBean) {
        baseLogic.addObserver(observerBean);
    }

    @Override
    public void removeObserver(ObserverBean observerBean) {
        String redisKey = observerBean.getQueueName();
        String field = observerBean.getObserverName();
         baseRedisDao.delRedisMapOne(redisKey, field);

    }

    @Override
    public void getContent(Object content, String queueName) {
        Map<String, String> observerMap = null;
        //获得监听该队列的观察者map
        try {
            observerMap = baseRedisDao.getRedisMapValue(queueName);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if (CollectionUtils.isEmpty(observerMap)) {
            logger.error("当前没有消费者，队列名：" + queueName + " 消息：" + content);
            return;
        }
        for (String value : observerMap.values()) {
            ObserverBean observerBean = JSON.parseObject(value, ObserverBean.class);
            RestClientUtil.asyncPost(observerBean.getNotifyUrl(), content);
        }

    }

    @Override
    public void updateObserver(ObserverBean observerBean) {
        baseLogic.updateObserver(observerBean);
    }


}
