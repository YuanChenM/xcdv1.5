package com.msk.seller.bean;

import com.msk.core.entity.SlEnterprise;

import java.util.List;

/**
 * Created by zhang_chi on 2016/4/29.
 */
public class ISL231125RsResult {
    /** 状态 */
    private String status;
    /** 返回代码 */
    private String returnCode;
    /** 结果消息 */
    private String message;
    /**企业基本资质*/
    private List<SlEnterprise> epInfoList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SlEnterprise> getEpInfoList() {
        return epInfoList;
    }

    public void setEpInfoList(List<SlEnterprise> epInfoList) {
        this.epInfoList = epInfoList;
    }
}
