package com.msk.mq.logic;

import com.alibaba.fastjson.JSON;
import com.msk.mq.config.AmqpConfiguration;
import com.msk.mq.bean.BaseParam;
import com.msk.mq.bean.ObserverBean;
import com.msk.mq.bean.PageResult;
import com.msk.mq.dao.BaseRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mao_yejun on 2016/7/7.
 */
@Service
public class BaseLogic {
    @Autowired
    private AmqpConfiguration amqpConfiguration;
    @Autowired
    private BaseRedisDao baseRedisDao;

    /**
     * 获得队列列表
     *
     * @return
     */
    public List<BaseParam> getQueueList() {
        List<BaseParam> baseParamList = new ArrayList<BaseParam>();
        for (String queueName : amqpConfiguration.getQueues()) {
            BaseParam baseParam = new BaseParam();
            baseParam.setQueueName(queueName);
            baseParamList.add(baseParam);

        }
        return baseParamList;
    }

    /**
     * 根据队列名获得观察者详情
     *
     * @param queueName
     * @return
     */
    public PageResult<ObserverBean> getDetail(String queueName) {
        PageResult<ObserverBean> pageResult = new PageResult<ObserverBean>();
        Map<String, String> detail = baseRedisDao.getRedisMapValue(queueName);
        List<ObserverBean> baseParamList = new ArrayList<ObserverBean>();
        for (String observer : detail.values()) {
            baseParamList.add(JSON.parseObject(observer, ObserverBean.class));
        }
        pageResult.setData(baseParamList);
        return pageResult;
    }

    /**
     * 更新观察者
     *
     * @param observerBean
     * @return
     */
    public boolean updateObserver(ObserverBean observerBean) {
        String key = observerBean.getQueueName();
        String field = observerBean.getObserverName();

        String oldValue = baseRedisDao.getRedisMapFieldValue(key, field);
        ObserverBean changeObserverBean = JSON.parseObject(oldValue, ObserverBean.class);
        changeObserverBean.setUpdateTime(getNowTimeString());
        changeObserverBean.setNotifyUrl(observerBean.getNotifyUrl());
        String value = JSON.toJSONString(changeObserverBean);
        return baseRedisDao.saveRedisMapOne(key, field, value);

    }

    /**
     * 新增观察者
     *
     * @param observerBean
     */
    public void addObserver(ObserverBean observerBean) {
        observerBean.setCreatedTime(getNowTimeString());
        String observerDetail = JSON.toJSONString(observerBean);
        //保存到redis中的map
        Map<String, Object> saveRedisMap = new HashMap<String, Object>();
        saveRedisMap.put(observerBean.getObserverName(), observerDetail);
        baseRedisDao.saveRedisMap(observerBean.getQueueName(), saveRedisMap);
    }

    public static synchronized String getNowTimeString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
