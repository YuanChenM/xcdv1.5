package com.msk.mq.validator;

import com.msk.mq.bean.ObserverBean;
import com.msk.mq.dao.BaseRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by mao_yejun on 2016/7/6.
 */
@Component
public class Validator {
    @Autowired
    private  BaseRedisDao baseRedisDao;

    public boolean registerValidator(ObserverBean observerBean) {

        Map<String, String> map = baseRedisDao.getRedisMapValue(observerBean.getQueueName());
        if (map.containsKey(observerBean.getObserverName())) {
            return false;
        }

        return true;
    }
}
