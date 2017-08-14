/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_reason对应的SlBsReason</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsReason extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 原因ID */
    private String msgId;
    /** 原因描述 */
    private String msqReason;
    /**
     * <p>默认构造函数</p>
     */
    public SlBsReason() {

    }

    /**
     * <p>原因ID</p>
     *
     * @return the 原因ID
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * <p>原因ID</p>
     *
     * @param msgId 原因ID
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * <p>原因描述</p>
     *
     * @return the 原因描述
     */
    public String getMsqReason() {
        return msqReason;
    }

    /**
     * <p>原因描述</p>
     *
     * @param msqReason 原因描述
     */
    public void setMsqReason(String msqReason) {
        this.msqReason = msqReason;
    }

}
