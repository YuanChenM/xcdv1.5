package com.msk.batch.mail.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 发送邮件上下文
 * @param <T>
 */
public class MailContext<T> implements Serializable{

    //邮件类型
    private T mailContent;
    // 发送给谁
    private String[] to;
    // CC给谁
    private String[] cc;

    public T getMailContent() {
        return mailContent;
    }

    public void setMailContent(T mailContent) {
        this.mailContent = mailContent;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }
}
