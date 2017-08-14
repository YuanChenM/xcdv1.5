package com.hoperun.core.exception;


import com.hoperun.core.bean.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * The exception when business exception.
 * </p>
 * 
 * @author jiang_nan
 */
public class BusinessException extends SystemException {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERROR_CODE = "F000001";

    /** 错误编码 */
    private String errorCode = DEFAULT_ERROR_CODE;
    /** 消息列表 */
    private List<ExceptionMessage> exceptionMessages;

    /**
     * 构造方法
     * 
     * @param message 消息
     */
    public BusinessException(String message) {
        super(message);
        this.addExceptionMessage(new ExceptionMessage(message));
    }

    /**
     * 构造方法
     * 
     * @param errorCode 错误编码
     * @param message 消息
     */
    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.addExceptionMessage(new ExceptionMessage(message));
    }

    /**
     * 构造方法
     * 
     * @param message 消息
     * @param cause 原始异常
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        if (cause != null) {
            this.addExceptionMessage(new ExceptionMessage(message));
        }
    }

    /**
     * 构造方法
     * 
     * @param ExceptionMessages 消息列表
     */
    public BusinessException(List<ExceptionMessage> ExceptionMessages) {
        super(fetchMessage("system.error.business", ExceptionMessages));
        this.exceptionMessages = ExceptionMessages;
    }

    /**
     * 构造方法
     * 
     * @param ExceptionMessages 消息列表
     */
    public BusinessException(ExceptionMessage ExceptionMessages) {
        super("system.error.business");
        this.addExceptionMessage(ExceptionMessages);
    }

    /**
     * 获取错误编码
     * 
     * @return 异常消息列表
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * 获取异常消息列表
     * 
     * @return 异常消息列表
     */
    public List<ExceptionMessage> getExceptionMessages() {
        return this.exceptionMessages;
    }

    /**
     * 追加异常消息
     * 
     * @param message 异常消息
     */
    public void addExceptionMessage(String message) {
        this.addExceptionMessage(new ExceptionMessage(message));
    }

    /**
     * 追加异常消息
     * 
     * @param message 异常消息
     */
    public void addExceptionMessage(ExceptionMessage message) {
        if (this.exceptionMessages == null) {
            this.exceptionMessages = new ArrayList<ExceptionMessage>();
        }
        this.exceptionMessages.add(message);
    }

    /**
     * 追加多个异常消息
     * 
     * @param messages 多个异常消息
     */
    public void addValidatorMessageAll(List<ExceptionMessage> messages) {
        if (this.exceptionMessages == null) {
            this.exceptionMessages = new ArrayList<ExceptionMessage>();
        }
        this.exceptionMessages.addAll(messages);
    }

}
