package com.msk.mail.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "MailContext",description = "发送邮件上下文")
public class MailContext<T> implements Serializable{
    @ApiModelProperty(name = "mailContent",notes = "邮件类型")
    private T mailContent;
    @ApiModelProperty(name = "to",notes = "发送给谁")
    @NotNull
    private String[] to;
    @ApiModelProperty(name = "cc",notes = "CC给谁")
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
