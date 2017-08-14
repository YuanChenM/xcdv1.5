package com.msk.batch.mail.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 文本类型邮件内容
 */
public class MailTextContent implements Serializable{

    // 标题
    private String subject;
    // 内容
    private String text;

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
