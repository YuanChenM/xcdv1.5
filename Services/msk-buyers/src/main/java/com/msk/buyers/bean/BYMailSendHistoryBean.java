package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;

/**
 *
 * @author zhang_jian4
 */
public class BYMailSendHistoryBean extends BaseEntity {

    private Long mailSendHistoryId;
    /** 买家ID */
    private String buyerId;
    /** 邮件主题 */
    private String emailTopic;
    /** 邮件内容 */
    private String emailContent;
    /** 组别 */
    private String sendGroup;
    /** 是否成功 */
    private String isSuccess;

    public Long getMailSendHistoryId() {
        return mailSendHistoryId;
    }

    public void setMailSendHistoryId(Long mailSendHistoryId) {
        this.mailSendHistoryId = mailSendHistoryId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getEmailTopic() {
        return emailTopic;
    }

    public void setEmailTopic(String emailTopic) {
        this.emailTopic = emailTopic;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getSendGroup() {
        return sendGroup;
    }

    public void setSendGroup(String sendGroup) {
        this.sendGroup = sendGroup;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }
}
