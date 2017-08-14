package com.msk.mq.subject;

import com.msk.mq.bean.ObserverBean;

/**
 * Created by mao_yejun on 2016/6/30.
 */
public interface Subject {

    public void addObserver(ObserverBean observerBean);

    public void removeObserver(ObserverBean observerBean);

    public void getContent(Object content,String queueName);

    public void updateObserver(ObserverBean observerBean);

}
