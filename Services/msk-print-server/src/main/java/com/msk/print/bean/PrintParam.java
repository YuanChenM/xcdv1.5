package com.msk.print.bean;

import java.io.Serializable;

/**
 * Created by jackjiang on 16/7/8.
 */
public class PrintParam implements Serializable{
    /**打印回调业务Url*/
    private String printCallBackUrl;
    /**业务服务参数*/
    private String callBackParam;

    /**
     * 获得printCallBackUrl
     **/
    public String getPrintCallBackUrl() {
        return printCallBackUrl;
    }

    /**
     * 设置printCallBackUrl
     *
     * @param printCallBackUrl printCallBackUrl
     **/
    public void setPrintCallBackUrl(String printCallBackUrl) {
        this.printCallBackUrl = printCallBackUrl;
    }

    /**
     * 获得callBackParam
     **/
    public String getCallBackParam() {
        return callBackParam;
    }

    /**
     * 设置callBackParam
     *
     * @param callBackParam callBackParam
     **/
    public void setCallBackParam(String callBackParam) {
        this.callBackParam = callBackParam;
    }
}
