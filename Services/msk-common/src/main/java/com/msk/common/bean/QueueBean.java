package com.msk.common.bean;

import java.io.Serializable;

/**
 * Created by shi_yuxi on 2016/6/2.
 */
public class QueueBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //id
    private String id;

    //指定队列
    private String queueKey;
    //消息内容
    private Object object;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getQueueKey() {
        return queueKey;
    }

    public void setQueueKey(String queueKey) {
        this.queueKey = queueKey;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
