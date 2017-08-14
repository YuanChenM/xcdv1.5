package com.msk.mq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
    private int maxConcurrentConsumers;
    private int concurrentConsumers;

    public int getConcurrentConsumers() {
        return concurrentConsumers;
    }

    public void setConcurrentConsumers(int concurrentConsumers) {
        this.concurrentConsumers = concurrentConsumers;
    }

    public int getMaxConcurrentConsumers() {
        return maxConcurrentConsumers;
    }

    public void setMaxConcurrentConsumers(int maxConcurrentConsumers) {
        this.maxConcurrentConsumers = maxConcurrentConsumers;
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

    public ArrayList<String> getQueues() {
        return queues;
    }

    public void setQueues(ArrayList<String> queues) {
        this.queues = queues;
    }
}
