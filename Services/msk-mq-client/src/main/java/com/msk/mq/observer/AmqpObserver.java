package com.msk.mq.observer;

import com.msk.mq.bean.ObserverBean;
import com.msk.mq.subject.Subject;

/**
 * Created by mao_yejun on 2016/6/30.
 */
public class AmqpObserver {



    public AmqpObserver(Subject subject, ObserverBean observerBean) {
        subject.addObserver(observerBean);

    }


}
