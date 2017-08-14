package com.msk.mail.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel(value = "MailTextContent",description = "文本类型邮件内容")
public class MailTextContent implements Serializable{
    @ApiModelProperty(name = "subject",notes = "标题")
    @NotNull
    private String subject;
    @ApiModelProperty(name = "text",notes = "内容")
    @NotNull
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
