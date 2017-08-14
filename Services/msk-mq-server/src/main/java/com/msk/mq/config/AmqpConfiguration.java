package com.msk.mq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.ArrayList;

/**
 * Created by mao_yejun on 2016/7/1.
 */
@ConfigurationProperties(prefix = "amqp")
public class AmqpConfiguration {
    private String address;
    private String user;
    private String pwd;
    private String exchange;
    private ArrayList<String> queues;

    public ArrayList<String> getQueues() {
        return queues;
    }

    public void setQueues(ArrayList<String> queues) {
        this.queues = queues;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

}
